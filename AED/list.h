#ifndef LIST_H
#define LIST_H

typedef struct node{
  char data;
  struct node *next;
  struct node *prev;
}Node;

typedef struct list{
  int size;
  Node *head;
  Node *tail;
}List;

List* make_list();

int emptyli(List* li);

void push_back(List* li, char c);

void delete(List* li);

void print(List* li, char c);

void free_list(List **li);

#endif
