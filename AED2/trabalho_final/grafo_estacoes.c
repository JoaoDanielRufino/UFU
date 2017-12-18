#include "grafo_estacoes.h"
#define MAX 10101010

typedef struct estacao{ //Vertice
    int numero_estacao;
    int tempo_espera;
    int grau;
    char nome_estacao[30];
}Estacao;

typedef struct rota{ //Aresta
    int vertice;
    char nome_linha[30];
    int tempo;
    struct rota* prox;
}Rota;

struct grafo{
    int qtd_estacoes, qtd_rotas;
    Estacao *estacao;
    Rota **rota;
};

//typedef struct grafo Grafo;

Grafo* cria_grafo(int qtd_estacoes){
    if(qtd_estacoes <= 0)
        return NULL;

    Grafo *g;
    g = (Grafo*) malloc(sizeof(Grafo));
    if(g == NULL)
        return NULL;

    g->qtd_estacoes = qtd_estacoes;
    g->qtd_rotas = 0;

    g->estacao = (Estacao*) malloc(qtd_estacoes*sizeof(Estacao));
    if(g->estacao == NULL){
        free(g);
        return NULL;
    }

    g->rota = (Rota**) malloc(qtd_estacoes*sizeof(Rota*));
    if(g->rota == NULL){
        free(g->rota);
        free(g);
        return NULL;
    }

    int i;
    for(i = 0; i < qtd_estacoes; i++){
        g->estacao[i].grau = 0;
        g->rota[i] = NULL;
    }

    return g;
}

int verifica_vazio(Grafo* g){
    if(g == NULL){
        printf("Grafo vazio ou inexistente!!\n");
        return 1;
    }
    else
        return 0;
}

int num_vertices(Grafo* g){
    return g->qtd_estacoes;
}

int grau_vertice(Grafo* g, int v){
    return g->estacao[v].grau;
}

int verificar_adj(Grafo* g, int v1, int v2){
    if(g == NULL || v1 < 0 || v1 >= g->qtd_estacoes || v2 < 0 || v2 >= g->qtd_estacoes)
       return -1;

    Rota *aux;
    aux = g->rota[v1];
    while(aux != NULL && aux->vertice != v2)
       aux = aux->prox;

    if(aux == NULL)
       return 0;

    return 1;
}

int cria_estacao(Grafo* g, int num_est, char *nome, int tempo_espe){
    if(g == NULL || num_est < 0 || num_est >= g->qtd_estacoes)
       return 0;

    g->estacao[num_est].numero_estacao = num_est;
    g->estacao[num_est].tempo_espera = tempo_espe;
    strcpy(g->estacao[num_est].nome_estacao, nome);

    return 1;
}

int insere_rota(Grafo* g, int v1, int v2, char *nome_linha, int tempo){
    int verifica = verificar_adj(g, v1, v2);

    if(verifica == -1)
       return -1;

    if(verifica == 1)
       return 0;

    Rota *nova_rota, *nova_rota2;
    nova_rota = (Rota*) malloc(sizeof(Rota));
    nova_rota2 = (Rota*) malloc(sizeof(Rota));

    if(nova_rota == NULL || nova_rota2 == NULL)
       return 0;

    nova_rota->vertice = v2;
    strcpy(nova_rota->nome_linha, nome_linha);
    nova_rota->tempo = tempo;
    nova_rota->prox = g->rota[v1];
    g->rota[v1] = nova_rota;

    nova_rota2->vertice = v1;
    strcpy(nova_rota2->nome_linha, nome_linha);
    nova_rota2->tempo = tempo;
    nova_rota2->prox = g->rota[v2];
    g->rota[v2] = nova_rota2;

    g->qtd_rotas++;
    g->estacao[v1].grau++;
    g->estacao[v2].grau++;

    return 1;
}

void mostra_caminho(Grafo* g, int origem, int destino, int *caminho){
   if(origem == destino)
      printf("%s |", g->estacao[origem].nome_estacao);
   else if(caminho[destino] == -1)
      printf("Nao existe caminho de %d para %d\n", origem,destino);
   else{
      mostra_caminho(g, origem, caminho[destino], caminho);
      printf("%s |", g->estacao[destino].nome_estacao);
   }
}

int menor(int *tempo, int *visitados, int n){
   int min = MAX, minpos,i;
   for(i = 0; i < n; i++){
      if(!visitados[i] && tempo[i] < min){
         min = tempo[i];
         minpos = i;
      }
   }
   return minpos;
}

int dijkstra(Grafo* g, char *comeco, char *fim){
    int *tempo,*visitados,*caminho,i,j,origem,destino;
    Estacao A, B;

    caminho = (int*) malloc(g->qtd_estacoes*sizeof(int));
    visitados = (int*) calloc(g->qtd_estacoes, sizeof(int));
    tempo = (int*) malloc(g->qtd_estacoes*sizeof(int));

    for(i = 0; i < g->qtd_estacoes; i++){
        tempo[i] = MAX;
        caminho[i] = -1;
        if(!strcmp(g->estacao[i].nome_estacao, comeco)){
            origem = i;
            A = g->estacao[i];
        }
        if(!strcmp(g->estacao[i].nome_estacao, fim)){
            destino = i;
            B = g->estacao[i];
        }
    }

    tempo[origem] = A.tempo_espera;
    Rota *aux;

    for(i = 0; i < g->qtd_estacoes-1; i++){
       int u = menor(tempo, visitados, g->qtd_estacoes);
       visitados[u] = 1;

       for(aux = g->rota[u]; aux != NULL; aux = aux->prox){
          int tmp;
          Estacao aux2 = g->estacao[aux->vertice];
          if(aux->vertice != destino)
             tmp = tempo[u] + aux2.tempo_espera + aux->tempo;

          else
             tmp = tempo[u] + aux->tempo;

          if(!visitados[aux->vertice] && tempo[u] != MAX && (tmp < tempo[aux->vertice])){
             tempo[aux->vertice] = tmp;
             caminho[aux->vertice] = u;
          }
       }
    }
    printf("Menor tempo entre %s e %s: %d\nMenor caminho: ", comeco,fim,tempo[destino]);
    mostra_caminho(g, origem, destino, caminho);
}

void checar_rota(Grafo* g, int qtd_rotas){
    int i,j;
    char nome_percurso[10][30];

    for(i = 0; i < qtd_rotas; i++){
        printf("Digite o nome da estacao[%d]: ", i+1);
        scanf("%[^\n]", nome_percurso[i]); getchar();
    }

    for(i = 0; i < qtd_rotas-1; i++){
        dijkstra(g, nome_percurso[i], nome_percurso[i+1]);
        printf("\n\n");
    }
}

void estacoes_possiveis(Grafo* g, char *comeco, int tempo_maximo){
    int *tempo,*visitados,i,j,origem;

    visitados = (int*) calloc(g->qtd_estacoes, sizeof(int));
    tempo = (int*) malloc(g->qtd_estacoes*sizeof(int));

    for(i = 0; i < g->qtd_estacoes; i++){
        tempo[i] = MAX;
        if(!strcmp(g->estacao[i].nome_estacao, comeco)){
            tempo[i] = g->estacao[i].tempo_espera;
        }
    }

    Rota *aux;
    for(i = 0; i < g->qtd_estacoes-1; i++){
       int u = menor(tempo, visitados, g->qtd_estacoes);
       visitados[u] = 1;

       for(aux = g->rota[u]; aux != NULL; aux = aux->prox){
          int tmp;
          Estacao aux2 = g->estacao[aux->vertice];

          tmp = tempo[u] + aux2.tempo_espera + aux->tempo;

          if(!visitados[aux->vertice] && tempo[u] != MAX && (tmp < tempo[aux->vertice])){
             tempo[aux->vertice] = tmp;
          }
       }
    }

    printf("Possiveis estacoes:");
    for(i = 0; i < g->qtd_estacoes; i++){
        if((tempo[i] - g->estacao[i].tempo_espera) <= tempo_maximo)
            printf(" %s |", g->estacao[i].nome_estacao);
    }
}

Grafo* ler_arquivo(){
    int tamanho_do_grafo,estacao,tempo_espera,origem,destino;
    char nome[30],nome_est[30],nome_rota[30];

    printf("Digite o nome do arquivo de estacoes: ");
    scanf("%s", nome_est);
    printf("Digite o nome do arquivo de rotas: ");
    scanf("%s", nome_rota);

    FILE *estacoes = fopen(nome_est, "r");
    FILE *rotas = fopen(nome_rota, "r");

    if(estacoes == NULL){
        printf("Erro ao abrir o arquivo!!\n");
        return;
    }

    if(rotas == NULL){
        printf("Erro ao abrir o arquivo!!\n");
        return;
    }

    fscanf(estacoes, "%d", &tamanho_do_grafo);
    Grafo *g = cria_grafo(tamanho_do_grafo);

    while(tamanho_do_grafo--){
        fscanf(estacoes, "%d; %s %d", &estacao, nome, &tempo_espera);
        cria_estacao(g, estacao, nome, tempo_espera);
    }

    fclose(estacoes);

    while(fscanf(rotas, "%d; %d; %s %d", &origem, &destino, nome, &tempo_espera) != EOF){
        insere_rota(g, origem, destino, nome, tempo_espera);
    }

    fclose(rotas);

    return g;
}

void escrever_grafo_no_arquivo(Grafo* g){
    int i;
    char nome_est[30],nome_rota[30];

    printf("Digite o nome do arquivo de estacoes: ");
    scanf("%s", nome_est);
    printf("Digite o nome do arquivo de rotas: ");
    scanf("%s", nome_rota);

    FILE *estacoes = fopen(nome_est, "w");
    FILE *rotas = fopen(nome_rota, "w");

    fprintf(estacoes, "%d\n",g->qtd_estacoes);
    for(i = 0; i < g->qtd_estacoes; i++){
        fprintf(estacoes, "%d; %s %d\n", g->estacao[i].numero_estacao, g->estacao[i].nome_estacao, g->estacao[i].tempo_espera);
    }
    fclose(estacoes);

    Rota *aux;
    for(i = 0; i < g->qtd_estacoes; i++){
        for(aux = g->rota[i]; aux != NULL; aux = aux->prox){
            if(aux->vertice >= i)
                fprintf(rotas, "%d; %d; %s %d\n", i, aux->vertice, aux->nome_linha, aux->tempo);
        }
    }
    fclose(rotas);
}

void libera_grafo(Grafo **g){
   if(*g == NULL)
      return;

   int i;
   Rota *aux,*aux2;

   for(i = 0; i < (*g)->qtd_estacoes; i++){
      aux = (*g)->rota[i];
      while(aux != NULL){
         aux2 = aux;
         aux = aux->prox;
         free(aux2);
      }
   }
   free((*g)->rota);
   free((*g)->estacao);
   free((*g));
   *g = NULL;
}

Grafo* grafo_pre_definido(){
    Grafo *g;

    g = cria_grafo(11);

    cria_estacao(g, 0, "Sucupira", 2);
    cria_estacao(g, 1, "Ipe_roxo", 5);
    cria_estacao(g, 2, "Ipe_amarelo", 8);
    cria_estacao(g, 3, "Porto_alegre", 3);
    cria_estacao(g, 4, "Jardim_miranda", 4);
    cria_estacao(g, 5, "Sao_Luiz", 6);
    cria_estacao(g, 6, "Karaiba", 5);
    cria_estacao(g, 7, "Serra_da_leoa", 4);
    cria_estacao(g, 8, "Altamira", 8);
    cria_estacao(g, 9, "Guara", 9);
    cria_estacao(g, 10, "Asa_norte", 8);

    insere_rota(g, 0, 2,"Amarela", 5);
    insere_rota(g, 0, 3, "Preta", 3);
    insere_rota(g, 1, 8, "Marrom", 10);
    insere_rota(g, 1, 2, "Azul", 6);
    insere_rota(g, 1, 4, "Verde", 2);
    insere_rota(g, 2, 3, "Preto", 5);
    insere_rota(g, 2, 8, "Cinza", 4);
    insere_rota(g, 3, 8, "Rosa", 11);
    insere_rota(g, 4, 10, "Roxo", 13);
    insere_rota(g, 4, 7, "Lilas", 5);
    insere_rota(g, 4, 9, "Vermelho", 7);
    insere_rota(g, 4, 5, "Ambar", 4);
    insere_rota(g, 5, 9, "Ameixa", 6);
    insere_rota(g, 5, 6, "Bege", 3);
    insere_rota(g, 6, 9, "Branco", 6);
    insere_rota(g, 7, 10, "Bordo", 5);
    insere_rota(g, 8, 10, "Bronze", 9);

    return g;
}

void limpa_tela(){
    #ifdef _WIN32
        system("cls");
    #else
        system("clear");
    #endif
}

void pausa_tela(){
    #ifdef _WIN32
        printf("\n");
        system("pause");
    #else
        printf("\nPressione qualquer tecla para continuar . . . ");
        getchar(); getchar();
    #endif
}

int menu(){
    int opcao;

    limpa_tela();
    printf("Menu:\n");
    printf("[1] - Baixar grafo por arquivo\n");
    printf("[2] - Carregar grafo para arquivo\n");
    printf("[3] - Utilizar grafo pre definido\n");
    printf("[4] - Numero de vetices\n");
    printf("[5] - Grau do vertice\n");
    printf("[6] - Verifica adjacente\n");
    printf("[7] - Calcular caminho mais rapido entre duas estacoes\n");
    printf("[8] - Possivel caminho de estacoes indicadas\n");
    printf("[9] - Estacoes acessadas em um determinado tempo\n");
    printf("[10] - Sair\n");
    printf("Escolha uma opcao: ");
    scanf("%d", &opcao);

    return opcao;
}
