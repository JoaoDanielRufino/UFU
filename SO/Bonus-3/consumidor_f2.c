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

typedef struct shared_area_2{
  int fila[MAX_SZ];
  int size;
  int acesso_proc;
  int acesso_thread;
}Shared_area_2;

Shared_area_2 *sh2;
int indice = 0;

void *consumidor_f2_1(){
  while(indice < sh2->size){
    if(sh2->acesso_thread == 0){
      printf("Valor consumido: %d\n", sh2->fila[indice]);
      indice++;
      sh2->acesso_thread = 1;
    }
  }
  pthread_exit(0);
}

void *consumidor_f2_2(){
  while(indice < sh2->size){
    if(sh2->acesso_thread == 1){
      printf("Valor consumido: %d\n", sh2->fila[indice]);
      indice++;
      sh2->acesso_thread = 2;
    }
  }
  pthread_exit(0);
}

void *consumidor_f2_3(){
  while(indice < sh2->size){
    if(sh2->acesso_thread == 2){
      printf("Valor consumido: %d\n", sh2->fila[indice]);
      indice++;
      sh2->acesso_thread = 0;
    }
  }
  pthread_exit(0);
}

int main(){

  int shmid2, i;
  key_t key2 = 0405;
  pthread_t tid[3];

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

  pthread_create(&tid[0], NULL, consumidor_f2_1, NULL);
  pthread_create(&tid[1], NULL, consumidor_f2_2, NULL);
  pthread_create(&tid[2], NULL, consumidor_f2_3, NULL);

  for(i = 0; i < 3; i++)
    pthread_join(tid[i], NULL);

  return 0;
}
