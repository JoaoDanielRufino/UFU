#include <stdio.h>
#include <stdlib.h>
#include <sys/wait.h>
#include <unistd.h>

#define LINES 2500
#define COLS 2500

int M1[LINES][COLS];
int M2[LINES][COLS];
int M3[LINES][COLS];

int main(){

  int i,j,k;
  pid_t pid1,pid2;

  for(i = 0; i < LINES; i++){
    for(j = 0; j < COLS; j++){
      M1[i][j] = i+1;
      M2[i][j] = j+1;
      M3[i][j] = 0;
    }
  }

  FILE *f_even = fopen("res_even_multmat_proc.txt", "w");
  FILE *f_odd = fopen("res_odd_multmat_proc.txt", "w");

  if(f_even == NULL || f_odd == NULL)
    printf("Falha ao abrir o arquivo!!\n");

  else{
    pid1 = fork();
    if(!pid1){
      int sum1 = 0;
      for(i = 0; i < LINES; i += 2){
        for(j = 0; j < COLS; j++){
          for(k = 0; k < COLS; k++){
            sum1 += M1[i][k] * M2[k][j];
          }
          fprintf(f_even, "%d ", sum1);
          sum1 = 0;
        }
      }
      fclose(f_even);
      exit(0);
    }

    else if(pid1 > 0){
      pid2 = fork();
      if(!pid2){
        int sum2 = 0;
        for(i = 1; i < LINES; i += 2){
          for(j = 0; j < COLS; j++){
            for(k = 0; k < COLS; k++){
              sum2 += M1[i][k] * M2[k][j];
            }
            fprintf(f_even, "%d ", sum2);
            sum2 = 0;
          }
        }
        fclose(f_odd);
        exit(0);
      }

      else if(pid2 > 0){
        wait(NULL);
        wait(NULL);

        FILE *f_even = fopen("res_even_multmat_proc.txt", "r");
        FILE *f_odd = fopen("res_odd_multmat_proc.txt", "r");

        if(f_even == NULL || f_odd == NULL)
          printf("Falha ao abrir o arquivo!!\n");

        int tmp;
        for(i = 0; i < LINES; i++){
          for(j = 0; j < COLS; j++){
            if(!(i & 1))
              fscanf(f_even, "%d", &tmp);
            else
              fscanf(f_odd, "%d", &tmp);

            M3[i][j] = tmp;
          }
        }
        fclose(f_even);
        fclose(f_odd);
      }
    }
  }

  return 0;
}
