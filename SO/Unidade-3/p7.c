#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <unistd.h>

int pipe1[2], pipe2[2];

void *par(void *p){
  int i, tmp;

  for(i = 0; i <= 100; i++){
    if(!(i & 1))
      write(pipe1[1], &i, sizeof(int));
    else{
      read(pipe2[0], &tmp, sizeof(int));
      printf("%d - Thread 1\n", tmp);
    }
  }
}

void *impar(void *p){
  int i, tmp;

  for(i = 0; i <= 100; i++){
    if(i & 1)
      write(pipe2[1], &i, sizeof(int));
    else{
      read(pipe1[0], &tmp, sizeof(int));
      printf("%d - Thread 2\n", tmp);
    }
  }
}

int main(){

  pthread_t tid[2];

  if(pipe(pipe1) == -1 || pipe(pipe2) == -1){
    printf("Erro ao criar pipe!!\n");
    exit(-1);
  }

  pthread_create(&tid[0], NULL, par, NULL);
  pthread_create(&tid[1], NULL, impar, NULL);

  pthread_join(tid[0], NULL);
  pthread_join(tid[1], NULL);

  close(pipe1[0]);
  close(pipe1[1]);
  close(pipe2[0]);
  close(pipe2[1]);

  return 0;
}
