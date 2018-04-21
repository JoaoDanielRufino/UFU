#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <time.h>

#define LINES 2500
#define COLS 2500

int M1[LINES][COLS];
int M2[LINES][COLS];
int M3[LINES][COLS];

void *thread_par(void *p){
  int i,j,k;

  for(i = 0; i < LINES; i += 2){
    for(j = 0; j < COLS; j++){
      for(k = 0; k < COLS; k++){
        M3[i][j] += M1[i][k] * M2[k][j];
      }
    }
  }
}

void *thread_impar(void *p){
  int i,j,k;

  for(i = 1; i < LINES; i += 2){
    for(j = 0; j < COLS; j++){
      for(k = 0; k < COLS; k++){
        M3[i][j] += M1[i][k] * M2[k][j];
      }
    }
  }
}

int main(){

  int i,j,k;
  pthread_t tid[2];

  for(i = 0; i < LINES; i++){
    for(j = 0; j < COLS; j++){
      M1[i][j] = 1;
      M2[i][j] = 2;
      M3[i][j] = 0;
    }
  }

  pthread_create(&tid[0], NULL, thread_par, NULL);
  pthread_create(&tid[1], NULL, thread_impar, NULL);

  pthread_join(tid[0], NULL);
  pthread_join(tid[1], NULL);

  //Code for printing M3
  /*for(i = 0; i < LINES; i++){
    for(j = 0; j < COLS; j++){
      printf("%d ", M3[i][j]);
    }
    printf("\n");
  }*/

  return 0;
}
