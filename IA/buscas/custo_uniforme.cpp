#include <bits/stdc++.h>

using namespace std;

vector< vector< pair<int,int> > > grafo;
vector<string> mymap;

void iniciaGrafo() {
  int numCidades,A,B,peso;
  FILE *rotas = fopen("grafo.txt", "r");

  if(rotas == NULL) {
    cout << "Erro ao abrir o arquivo" << endl; 
    return;
  }

  fscanf(rotas, "%d", &numCidades);
  grafo.assign(numCidades, vector< pair<int,int> > ());

  while(fscanf(rotas, "%d" "%d" "%d", &A, &B, &peso) != EOF) {
    grafo[A].push_back(make_pair(B, peso)); // Bidirecional
    grafo[B].push_back(make_pair(A, peso));
  }

}

void iniciaMapeamento() {
  char nome[20];
  FILE *mapeamentoRotas = fopen("mapeamento.txt", "r");

  if(mapeamentoRotas == NULL) {
    cout << "Erro ao abrir arquivo" << endl;
    return;
  }

  while(fscanf(mapeamentoRotas, "%s", nome) != EOF) {
    mymap.push_back(string(nome));
  }

}

void mostraCaminho(int origem, int destino, vector<int> caminho) {
  if(origem == destino) {
    cout << mymap[origem];    
  }
  else {
    mostraCaminho(origem, caminho[destino], caminho);
    cout << " -> " << mymap[destino];
  }
}

void dikstra(int origem, int destino) {
  priority_queue< pair<int,int>, vector<pair<int,int> >, greater<pair<int,int> > > pq;
  vector<int> caminho(grafo.size());
  vector<int> dist(grafo.size(), INT_MAX);

  dist[origem] = 0;
  pq.push(make_pair(0, origem));
  while(!pq.empty()) {
    int v = pq.top().second;
    pq.pop();

    if(v == destino)
      break;

    for(int i = 0; i < grafo[v].size(); i++) {
      int u = grafo[v][i].first;
      int peso = grafo[v][i].second;
      if(dist[u] > dist[v] + peso) {
        dist[u] = dist[v] + peso;
        caminho[u] = v;
        pq.push(make_pair(dist[u], u));
      }
    }
  }

  cout << "Distancia: " << dist[destino] << "km" << endl;
  mostraCaminho(origem, destino, caminho);
}

int main() {

  int inicio = -1, fim = -1;
  string origem, destino;

  iniciaGrafo();
  iniciaMapeamento();

  cout << "Digite a cidade de origem: ";
  getline(cin, origem);
  cout << "Digite a cidade de destino: ";
  getline(cin, destino);

  for(int i = 0; i < mymap.size(); i++) {
    if(mymap[i] == origem)
      inicio = i;
    if(mymap[i] == destino)
      fim = i;
  }

  if(inicio == -1) {
    cout << "Destino de origem nao encontrado" << endl;
    exit(0);
  }

  if(fim == -1) {
    cout << "Destino final nao encontrado" << endl;
    exit(0);
  }

  cout << "\nCaminho em profundidade:" << endl;
  dikstra(inicio, fim);

  return 0;
}
