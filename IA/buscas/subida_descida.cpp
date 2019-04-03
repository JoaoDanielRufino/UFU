/*
 * Este problema entra em um loop infinito,
 * pois ele atinge um mminimo local e nao sai dele
 */

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

bool solve(vector<int> &mat) {
    vector<int> aux(mat);
    int colisoes, posCol, posLinha, minColisoes = INT_MAX;

    for(int i = 0; i < SIZE; i++) {
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

    mat[posLinha] = posCol;
    colisoes = calculaColisoes(mat);

    for(int i = 0; i < SIZE; i++) {
        cout << mat[i] << " ";
    }
    cout << endl;

    if(!colisoes)
        return true;
    if(solve(mat))
        return true;
    return false;
}

int main() {
    vector<int> mat(SIZE, 0); // O indice identifica a coluna e o valor indica a linha

    cout << "Comecando" << endl;

    solve(mat);

    for(int i = 0; i < SIZE; i++) {
        cout << mat[i] << " ";
    }

    cout << endl;

    return 0;
}
