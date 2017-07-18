#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "list.h"
#include "queue.h"
#include "stack.h"
#include "jogo.h"

Player* jogo(List **li, Queue* q, int comeco, int fim){
    int i,j,pos_atual=comeco,dado,r=1,numjog=q->size,back;
    Node *aux;
    srand(time(NULL));
    while(1){
        printf("\nRodada[%d]:\n", r++);
        for(i=0; i<numjog; i++){
            if(not_visited_path(li[pos_atual],q->at)){
                dado = random_number(li[pos_atual],q->at,aux);
                printf("Nome: %s, Pos atual: %c, Dado = %d, ", q->at->name,(char) pos_atual+65,dado);
                aux = li[pos_atual]->head;
                for(j=1; j<dado; j++)
                    aux = aux->next;
                printf("Pos futura: %c\n", aux->data);
                q->at->visited[aux->data-65] = 1;
                push(q->at->route,aux->data);
            }
            else
                backtrack(q->at);
            if((int) aux->data-65 == fim){
                Player *vencedor;
                vencedor = q->at;
                return vencedor;
            }
            else if((int) aux->data-65 != fim)
                prox(q);
            pos_atual = top(q->at->route);
        }
    }
}

int random_number(List* li, Player* pl, Node* aux){
    int dado,i,pos;
    while(1){
        dado = 1 + rand()%li->size;
        aux = li->head;
        for(i=1; i<dado; i++)
            aux = aux->next;
        pos = (int) aux->data-65;
        if(!pl->visited[pos])
            return dado;
    }
}

int not_visited_path(List* li, Player* pl){
    int i,pos;
    Node *aux;
    aux = li->head;
    for(i=0; i<li->size; i++){
        pos = (int) aux->data-65;
        if(!pl->visited[pos])
            return 1;
        aux = aux->next;
    }
    return 0;
}

void backtrack(Player* pl){
    char r;
    r = (char) top(pl->route)+65;
    printf("Nome: %s, volta de %c ", pl->name,r);
    pop(pl->route);
    r = (char) top(pl->route)+65;
    printf("para %c\n", r);
}
