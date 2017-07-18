#include <stdio.h>
#include <stdlib.h>
#include "list.h"

List* make_list(){
  List *li;
  li = (List*) malloc(sizeof(List));
  li->size = 0;
  li->head = NULL;
  li->tail = NULL;
  return li;
}

int emptyli(List* li){
  return (li->size==0);
}

void push_back(List* li, char c){
  Node *new;
  new = (Node*) malloc(sizeof(Node));
  if(emptyli(li)){
    new->data = c;
    new->next = NULL;
    new->prev = NULL;
    li->head = new;
    li->tail = new;
  }
  else{
    new->data = c;
    new->next = NULL;
    new->prev = li->tail;
    li->tail->next = new;
    li->tail = new;
  }
  li->size++;
}

void delete(List* li){
    Node *aux;
    aux = li->head;
    if(li->size==1){
        li->head = NULL;
        li->tail = NULL;
    }
    else
        li->head = li->head->next;
    free(aux);
    li->size--;
}

void print(List* li, char c){
  if(emptyli(li))
    return;
  printf("Vertice[%c]: ", c);
  int i;
  Node *aux;
  aux = li->head;
  for(i=0; i<li->size; i++){
    printf("%c ", aux->data);
    aux = aux->next;
  }
  printf("\n");
}

void free_list(List **li){
    int i;
    for(i=0; i<26; i++){
        while(!emptyli(li[i]))
            delete(li[i]);
        free(li[i]);
    }
    free(li);
}
