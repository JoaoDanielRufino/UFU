#include<stdio.h>
#include<stdlib.h>
#include "stack.h"

Stack* make_stack(){
    Stack *p;
    p = (Stack*) malloc(sizeof(Stack));
    p->size=0;
    p->last=NULL;
    return p;
}

int vazia(Stack* p){
    return p->size==0;
}

void push(Stack* p, char n){
    StackNode *novo;
    novo = (StackNode*) malloc(sizeof(StackNode));
    if(vazia(p)){
        novo->data = n;
        novo->prev = NULL;
        p->last = novo;
    }
    else{
        novo->data = n;
        novo->prev = p->last;
        p->last = novo;
    }
    p->size++;
}

void pop(Stack* p){
    int i;
    StackNode *aux;
    aux = p->last;
    p->last = p->last->prev;
    free(aux);
    p->size--;
}

int top(Stack* p){
    return p->last->data-65;
}

void free_stack(Stack* p){
    while(!vazia(p))
        pop(p);
    free(p);
}
