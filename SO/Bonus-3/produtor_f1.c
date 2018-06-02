#include <stdio.h>
#include <stdlib.h>
#include <sys/shm.h>
#include <sys/types.h>
#include <time.h>
#include <semaphore.h>
#include <unistd.h>
#include <signal.h>

// Ao alterar o MAX_SZ, deve-se tambem alterar o MEM_SZ
#define MEM_SZ 80
#define MAX_SZ 10
#define NOME_ARQ "pid_consumidor.txt"

typedef struct shared_area{
  sem_t mutex;
  int fila[MAX_SZ];
  int pos;
}Shared_area;

void inicializa_struct(Shared_area *sh){
  int i;
  for(i = 0; i < MAX_SZ; i++)
    sh->fila[i] = -1;
  sh->pos = 0;
}

int pid_consumidor(){
  FILE *f = fopen(NOME_ARQ, "r");
  if(f == NULL){
    printf("Falha ao abrir arquivo!!\n");
    exit(-1);
  }

  int pid;
  fscanf(f, "%d", &pid);
  fclose(f);
  return pid;
}

int insere_fila(Shared_area *sh, int id){
  sem_wait(&sh->mutex);

  if(sh->pos >= MAX_SZ){
    int pid = pid_consumidor();
    kill(pid, SIGUSR1);
    sem_post(&sh->mutex);
    return 0;
  }

  sh->fila[sh->pos++] = (rand() % 1000) + 1;
  printf("Pos: %d & Valor: %d & ID: %d\n", sh->pos-1, sh->fila[sh->pos-1], id);
  sem_post(&sh->mutex);
  return 1;
}

int main(){

  Shared_area *sh;
  pid_t pid1, pid2, pid3;
  key_t key = 5555;
  int shmid;

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

  inicializa_struct(sh);

  srand(time(NULL));

  pid1 = fork();

  // Filho 1
  if(!pid1){
    while(insere_fila(sh, 1));
    exit(0);
  }

  else if(pid1 > 0){
    pid2 = fork();
    // Filho 2
    if(!pid2){
      while(insere_fila(sh, 2));
      exit(0);
    }
    // Pai
    else if(pid2 > 0){
      while(insere_fila(sh, 0));
    }
  }

  // Removendo shared memory
  shmdt(sh);
  shmctl(shmid, IPC_RMID, NULL);

  return 0;
}
