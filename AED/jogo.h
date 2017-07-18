#ifndef JOGO_H
#define JOGO_H
#include "queue.h"
#include "list.h"

Player* jogo(List **li, Queue* q, int comeco, int fim);

int random_number(List* li, Player* pl, Node* aux);

int not_visited_path(List* li, Player* pl);

void backtrack(Player* pl);

#endif
