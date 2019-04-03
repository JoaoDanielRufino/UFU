#include <bits/stdc++.h>
#define SIZE 4

using namespace std;

int calculaColisoes(vector<int> mat) {
    int cont = 0;
    for(int i = 0; i < SIZE - 1; i++){
        int aux = 1;
        for(int j = i + 1; j < SIZE; j++){
            if(mat[i] == mat[j] || mat[j] == mat[i] + aux || mat[j] ==  mat[i] - aux)
                cont++;
            aux++;
        }
    }
    return cont;
}

bool solve(vector<int> &mat, int prev) {
    vector<int> aux(mat);
    int colisoes, posCol, posLinha, minColisoes = INT_MAX;

    for(int i = 0; i < SIZE; i++) {
        if(prev == i) // Avancando duas posicoes se a dama anterior foi utitlizada
            i += 2;
        if(i < SIZE) {
            for(int j = 0; j < SIZE; j++) {
                aux[i] = j;
                colisoes = calculaColisoes(aux);
                if(colisoes < minColisoes && mat[i] != j) {
                    minColisoes = colisoes;
                    posLinha = i;
                    posCol = j;
                }
            }
        }
    }

    mat[posLinha] = posCol;
    colisoes = calculaColisoes(mat);

    for(int i = 0; i < SIZE; i++)
        cout << mat[i] << " ";
    cout << endl;

    if(!colisoes)
        return true;
    if(solve(mat, posLinha)) // Salvando a dama utitlizada
        return true;
    return false;
}

int main() {
    vector<int> mat(SIZE, 0); // O indice identifica a coluna e o valor indica a linha

    cout << "Comecando" << endl;

    solve(mat, -1);

    return 0;
}
