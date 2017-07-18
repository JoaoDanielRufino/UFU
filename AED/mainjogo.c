#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <conio.h>
#include "list.h"
#include "queue.h"
#include "jogo.h"

int main(){

    int i,j,n,vert,ares,x,numjog,comeco,fim;
    char v,adj,inicio,final,name[30];
    List **li;
    Queue *q;
    Player *vencedor;

    printf("\nDigite o numero de vertices e de arestas: ");
    scanf("%d %d", &vert,&ares);

    li = (List**) malloc(26*sizeof(List*));

    for(i=0; i<26; i++)
        li[i] = make_list();

    printf("Digite o vetice e seu adjacente(letras maiusculas):\n");
    for(i=0; i<ares; i++){
      getchar();
      scanf("%c %c", &v,&adj);
      x = (int) v-65;
      push_back(li[x],adj);
    }

    printf("Digite o vertice de inicio e final(letras maiusculas):\n");
    getchar();
    scanf("%c %c", &inicio,&final);
    comeco = (int) inicio-65;
    fim = (int) final-65;

    q = make_queue();

    printf("Digite o numero de jogadores: ");
    scanf("%d", &numjog);

    for(i=0; i<numjog; i++){
        printf("Digite o nome do jogador[%d]: ", i+1);
        getchar();
        scanf("%[^\n]", name);
        make_player(q,name,inicio);
    }

    system("cls");
    printf("MAPA:\n");
    for(i=0; i<26; i++){
        v = (char) i+65;
        print(li[i],v);
    }

    vencedor = jogo(li,q,comeco,fim);

    printf("\nTodos saudem o(a) %s, pois eh o(a) vencedor(a)!!!\n", vencedor->name);

    free_list(li);
    free_queue(q);

    return 0;
}
