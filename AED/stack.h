#ifndef STACK_H
#define STACK_H

typedef struct stacknode{
    char data;
    struct stacknode *prev;
}StackNode;

typedef struct stack {
    int size;
    StackNode *last;
}Stack;

Stack* make_stack();

int vazia(Stack* p);

void push(Stack* p, char n);

void pop(Stack* p);

int top(Stack* p);

void free_stack(Stack* p);

#endif
