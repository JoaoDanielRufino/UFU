#include <stdio.h>
#include <time.h>

#define LINES 2500
#define COLS 2500

int M1[LINES][COLS];
int M2[LINES][COLS];
int M3[LINES][COLS];

int main(){

  int i,j,k;
  double t;

  clock_t begin = clock();

  for(i = 0; i < LINES; i++){
    for(j = 0; j < COLS; j++){
      M1[i][j] = 1;
      M2[i][j] = 2;
      M3[i][j] = 0;
    }
  }

  for(i = 0; i < LINES; i++){
    for(j = 0; j < COLS; j++){
      for(k = 0; k < COLS; k++){
        M3[i][j] += M1[i][k] * M2[k][j];
      }
    }
  }

  clock_t end = clock();

  t = (double)(end - begin)/CLOCKS_PER_SEC;
  printf("Time: %f\n", t);

  //Code for printing M3
  /*for(i = 0; i < LINES; i++){
    for(j = 0; j < COLS; j++){
      printf("%d ", M3[i][j]);
    }
    printf("\n");
  }*/

  return 0;
}
