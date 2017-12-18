#ifndef GRAFO_ESTACOES_H_INCLUDED
#define GRAFO_ESTACOES_H_INCLUDED

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct grafo Grafo;

Grafo* cria_grafo(int qtd_estacoes);

int verifica_vazio(Grafo* g);

int num_vertices(Grafo* g);

int grau_vertice(Grafo* g, int v);

int verificar_adj(Grafo* g, int v1, int v2);

int insere_rota(Grafo* g, int v1, int v2, char *nome_linha, int tempo);

int dijkstra(Grafo* g, char *comeco, char *fim);

void checar_rota(Grafo* g, int qtd_rotas);

void estacoes_possiveis(Grafo* g, char *comeco, int tempo_maximo);

Grafo* ler_arquivo();

void escrever_grafo_no_arquivo(Grafo* g);

void libera_grafo(Grafo **g);

Grafo* grafo_pre_definido();

void limpa_tela();

void pausa_tela();

int menu();

#endif // GRAFO_ESTACOES_H_INCLUDED
