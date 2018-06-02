#include <stdio.h>
#include <stdlib.h>
#include <sys/shm.h>
#include <sys/types.h>
#include <time.h>
#include <semaphore.h>
#include <unistd.h>
#include <signal.h>
#include <pthread.h>

// Ao alterar o MAX_SZ, deve-se tambem alterar o MEM_SZ
#define MEM_SZ 80
#define MAX_SZ 10
#define NOME_ARQ "pid_consumidor.txt"

typedef struct shared_area{
  sem_t mutex;
  int fila[MAX_SZ];
  int pos;
}Shared_area;

typedef struct shared_area_2{
  int fila[MAX_SZ];
  int size;
  int acesso_proc;
  int acesso_thread;
}Shared_area_2;

// Variaveis globais
Shared_area *sh;
int canal[2];
int indice = 0;
int ready = 0;

void *consumidor_f1(){
  sem_wait(&sh->mutex);
  while(indice <= sh->pos){
    write(canal[1], &sh->fila[indice], sizeof(int));
    indice++;
  }
  sem_post(&sh->mutex);
  pthread_exit(0);
}

void tratamento(){
  printf("Sinal recebido!!\n");
  ready = 1;
}

int main(){

  key_t key = 5555;
  int shmid, tmp;
  pid_t pid1, pid2;
  sem_t mutex2;
  pthread_t tid[2];

  shmid = shmget(key, MEM_SZ, 0666 | IPC_CREAT);
  if(shmid == -1){
    printf("Shmid falhou!!\n");
    exit(-1);
  }

  sh = (Shared_area *) shmat(shmid, (void *)0, 0);
  if(sh == (void *) -1){
    printf("Shmat falhou!!\n");
    exit(-1);
  }

  if(sem_init(&sh->mutex, 1, 1) != 0){
    printf("Sem_init falhou!!!\n");
    exit(-1);
  }

  if(sem_init(&mutex2, 1, 1) != 0){
    printf("Sem_init falhou!!!\n");
    exit(-1);
  }

  FILE *f = fopen(NOME_ARQ, "w");
  if(f == NULL){
    printf("Falha ao abrir arquivo!!\n");
    exit(-1);
  }

  // Gravando pid do processo em arquivo para o produtor conseguir enviar o sinal
  fprintf(f, "%d", getpid());
  fclose(f);

  printf("Meu pid: %d\n", getpid());

  signal(SIGUSR1, tratamento);

  // Esperando pelo sinal
  while(!ready);

  // Nova shared memory
  Shared_area_2 *sh2;
  int shmid2;
  key_t key2 = 0405;

  shmid2 = shmget(key2, MEM_SZ, 0666 | IPC_CREAT);
  if(shmid2 == -1){
    printf("Shmid falhou!!\n");
    exit(-1);
  }

  sh2 = (Shared_area_2 *) shmat(shmid2, (void *)0, 0);
  if(sh2 == (void *) -1){
    printf("Shmat falhou!!\n");
    exit(-1);
  }
  // Indice da shared_area_2
  sh2->size = 0;
  // Variaveis para busy wait
  sh2->acesso_proc = 0;
  sh2->acesso_thread = 0;

  // Criando canal
  if(pipe(canal) == -1){
    printf("Erro ao criar canal!!\n");
    exit(-1);
  }

  // Threads para consumir f1
  pthread_create(&tid[0], NULL, consumidor_f1, NULL);
  pthread_create(&tid[1], NULL, consumidor_f1, NULL);

  pthread_join(tid[0], NULL);
  pthread_join(tid[1], NULL);

  // Zerando indice da shared_area
  sh->pos = 0;

  pid1 = fork();

  // Nesta parte, tais processos comecam a produzir para f2.

  // Filho 1 (P5)
  if(!pid1){
    while(sh2->size < MAX_SZ){
      if(sh2->acesso_proc == 0){
        read(canal[0], &tmp, sizeof(int));
        sh2->fila[sh2->size++] = tmp;
        sem_post(&mutex2);
        printf("Valor lido: %d\n", tmp);
        sh2->acesso_proc = 1;
      }
    }
    exit(0);
  }

  else if(pid1 > 0){
    pid2 = fork();
    // Filho 2 (P6)
    if(!pid2){
      while(sh2->size < MAX_SZ){
        if(sh2->acesso_proc == 1){
          read(canal[0], &tmp, sizeof(int));
          sh2->fila[sh2->size++] = tmp;
          sem_post(&mutex2);
          printf("Valor lido: %d\n", tmp);
          sh2->acesso_proc = 0;
        }
      }
      exit(0);
    }
  }

  while(1);

  close(canal[0]);
  close(canal[1]);

  shmdt(sh2);
  shmctl(shmid2, IPC_RMID, NULL);

  return 0;
}
