#include "grafo_estacoes.h"

int main(){

    Grafo *g = NULL;
    int opcao;

    while(1){
        opcao = menu();

        if(opcao == 1){
            limpa_tela();
            g = ler_arquivo();
            if(g != NULL){
                printf("Grafo cirado com sucesso!!\n");
                pausa_tela();
            }
            else{
                printf("Grafo nao criado!!\n");
                pausa_tela();
            }
        }

        else if(opcao == 2){
            limpa_tela();
            escrever_grafo_no_arquivo(g);
            printf("Arquivo cirado com sucesso!!\n");
            pausa_tela();
        }

        else if(opcao == 3){
            g = grafo_pre_definido();
            limpa_tela();
            if(g != NULL){
                printf("Grafo cirado com sucesso!!\n");
                pausa_tela();
            }
            else{
                printf("Grafo nao criado!!\n");
                pausa_tela();
            }
        }

        else if(opcao == 4){
            limpa_tela();
            if(!verifica_vazio(g))
                printf("Numero de vertices: %d\n", num_vertices(g));
            pausa_tela();
        }

        else if(opcao == 5){
            int v;
            limpa_tela();
            if(!verifica_vazio(g)){
                printf("Digite o vertice: ");
                scanf("%d", &v);
                printf("Grau do vetice: %d\n", grau_vertice(g, v));
            }
            pausa_tela();
        }

        else if(opcao == 6){
            int v1,v2,ok;
            limpa_tela();
            printf("Digite o numero do primeiro vertice: ");
            scanf("%d", &v1);
            printf("Digite o numero do segundo vertice: ");
            scanf("%d", &v2);

            ok = verificar_adj(g, v1, v2);
            if(ok == 1)
                printf("Eh adjacente!!\n");
            else if(!ok)
                printf("Nao eh adjacente!!\n");
            else
                printf("Vertices ou grafo inexistente!!\n");
            pausa_tela();
        }

        else if(opcao == 7){
            char nome1[30],nome2[30];
            limpa_tela();
            if(!verifica_vazio(g)){
                getchar();
                printf("Digite o nome da primeira estacao: ");
                scanf("%[^\n]", nome1);
                getchar();
                printf("Digite o nome da segunda estacao: ");
                scanf("%[^\n]", nome2);
                dijkstra(g, nome1, nome2);
            }
            pausa_tela();
        }

        else if(opcao == 8){
            int qtd;
            limpa_tela();
            if(!verifica_vazio(g)){
                printf("Digite a quantidade de rotas: ");
                scanf("%d", &qtd);
                getchar();
                checar_rota(g, qtd);
                getchar();
            }
            else{
                pausa_tela();
            }
        }

        else if(opcao == 9){
            char nome[30];
            int tempo_max;
            limpa_tela();
            if(!verifica_vazio(g)){
                getchar();
                printf("Digite o nome da estacao de origem: ");
                scanf("%[^\n]", nome);
                printf("Digite o tempo maximo de percurso: ");
                scanf("%d", &tempo_max);
                estacoes_possiveis(g, nome, tempo_max);
            }
            pausa_tela();
        }

        else if(opcao == 10){
            libera_grafo(&g);
            printf("\nSaindo...\n\n");
            break;
        }
    }

    return 0;
}
