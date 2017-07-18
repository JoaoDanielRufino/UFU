#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include "queue.h"
#include "list.h"
#include "stack.h"

Queue* make_queue(){
    Queue *q;
    q = (Queue*) malloc(sizeof(Queue));
    q->size = 0;
    q->at = NULL;
    q->tail = NULL;
    return q;
}

int empty(Queue* q){
    return q->size==0;
}

void make_player(Queue* q, char *name, char pos){
    int i;
    Player *new;
    new = (Player*) malloc(sizeof(Player));
    new->route = make_stack();
    push(new->route,pos);
    strcpy(new->name, name);
    if(empty(q)){
        q->at = new;
        q->tail = new;
        new->next = q->at;
    }
    else{
        q->tail->next = new;
        q->tail = new;
        new->next = q->at;
    }
    q->size++;
    for(i=0; i<26; i++)
        new->visited[i] = 0;
}

void dequeue(Queue* q){
    Player *aux;
    aux = q->at;
    if(q->size==1)
        q->at = NULL;
    else{
        q->at = q->at->next;
        q->tail->next = q->at;
    }
    free_stack(aux->route);
    free(aux);
    q->size--;
}

void prox(Queue* q){
    q->tail = q->at;
    q->at = q->at->next;
}

void free_queue(Queue* q){
    while(!empty(q))
        dequeue(q);
    free(q);
}
