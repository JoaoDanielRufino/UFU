#ifndef QUEUE_H
#define QUEUE_H
#include "list.h"
#include "stack.h"

typedef struct player{
    char name[30];
    int visited[26];
    char local;
    Stack *route;
    struct player *next;
}Player;

typedef struct queue{
    int size;
    Player *at;
    Player *tail;
}Queue;

Queue* make_queue();

int empty(Queue* q);

void make_player(Queue* q, char *name, char pos);

void dequeue(Queue* q);

void prox(Queue* q);

void free_queue(Queue* q);

#endif
