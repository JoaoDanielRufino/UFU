#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

int main(){

  int pipe1[2], pipe2[2], i, tmp, z;
  pid_t pid1, pid2;

  if(pipe(pipe1) == -1 || pipe(pipe2) == -1){
    printf("Erro ao criar pipe!!\n");
    exit(-1);
  }

  pid1 = fork();
  if(!pid1){ // Filho 1
    for(i = 0; i <= 100; i++){
      if(!(i & 1))
        write(pipe1[1], &i, sizeof(int));
      else{
        read(pipe2[0], &tmp, sizeof(int));
        printf("%d - Filho 1\n", tmp);
      }
    }
  }

  else if(pid1 > 0){
    pid2 = fork();
    if(!pid2){ // Filho 2
      for(i = 0; i <= 100; i++){
        if((i & 1))
          write(pipe2[1], &i, sizeof(int));
        else{
          read(pipe1[0], &tmp, sizeof(int));
          printf("%d - Filho 2\n", tmp);
        }
      }
    }

    else if(pid2 > 0){ // Pai
      wait(NULL);
      wait(NULL);
      close(pipe1[0]);
      close(pipe1[1]);
      close(pipe2[0]);
      close(pipe2[1]);
    }
  }

  return 0;
}
