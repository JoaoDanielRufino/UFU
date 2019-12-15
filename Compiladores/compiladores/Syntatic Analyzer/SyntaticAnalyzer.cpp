#include "SyntaticAnalyzer.h"

SyntaticAnalyzer::SyntaticAnalyzer() {
  st.push("S");
  dt = new DerivationTree("S");
  stTree.push(dt);

  vector<string> aux;

  aux.push_back("programa");
  aux.push_back("Bloco");
  table[make_pair("S", "programa")] = aux;

  aux.clear();
  aux.push_back("inicio");
  aux.push_back("Declaracoes");
  aux.push_back("Comandos");
  aux.push_back("fim");
  table[make_pair("Bloco", "inicio")] = aux;

  aux.clear();
  aux.push_back("Tipo");
  aux.push_back("id");
  aux.push_back(";");
  aux.push_back("Declaracoes");
  table[make_pair("Declaracoes", "int")] = aux;

  aux.clear();
  aux.push_back("");
  table[make_pair("Declaracoes", "fim")] = aux;
  table[make_pair("Declaracoes", "id")] = aux;
  table[make_pair("Declaracoes", "const")] = aux;
  table[make_pair("Declaracoes", "se")] = aux;
  table[make_pair("Declaracoes", "entao")] = aux;

  aux.clear();
  aux.push_back("");
  table[make_pair("Comandos", "fim")] = aux;
  
  aux.clear();
  aux.push_back("Equacao");
  aux.push_back(";");
  aux.push_back("Comandos");
  table[make_pair("Comandos", "id")] = aux;

  aux.clear();
  aux.push_back("const");
  table[make_pair("Comandos", "const")] = aux;

  aux.clear();
  aux.push_back("se");
  aux.push_back("Condicao");
  aux.push_back("entao");
  aux.push_back("Bloco");
  aux.push_back("Comandos");
  table[make_pair("Comandos", "se")] = aux;

  aux.clear();
  aux.push_back("enquanto");
  aux.push_back("Condicao");
  aux.push_back("Bloco");
  aux.push_back("Comandos");
  table[make_pair("Comandos", "enquanto")] = aux;

  aux.clear();
  aux.push_back("int");
  table[make_pair("Tipo", "int")] = aux;

  aux.clear();
  aux.push_back("Termo");
  aux.push_back("Relop");
  aux.push_back("Termo");
  table[make_pair("Condicao", "id")] = aux;

  aux.clear();
  aux.push_back("Termo");
  aux.push_back("Relop");
  aux.push_back("Termo");
  table[make_pair("Condicao", "const")] = aux;

  aux.clear();
  aux.push_back("id");
  aux.push_back("Equacao``");
  //aux.push_back(";");
  table[make_pair("Equacao", "id")] = aux;

  aux.clear();
  aux.push_back("const");
  table[make_pair("Equacao", "const")] = aux;
  
  aux.clear();
  aux.push_back("");
  table[make_pair("Equacao``", "fim")] = aux;
  table[make_pair("Equacao``", "id")] = aux;
  table[make_pair("Equacao``", "const")] = aux;

  aux.clear();
  aux.push_back("Operadores");
  aux.push_back("Operacao");
  table[make_pair("Equacao``", "+")] = aux;
  table[make_pair("Equacao``", "-")] = aux;
  table[make_pair("Equacao``", "/")] = aux;
  table[make_pair("Equacao``", "*")] = aux;

  aux.clear();
  aux.push_back("");
  table[make_pair("Equacao``", "se")] = aux;
  table[make_pair("Equacao``", "enquanto")] = aux;

  aux.clear();
  aux.push_back("=");
  aux.push_back("Termo");
  table[make_pair("Equacao``", "=")] = aux;

  aux.clear();
  aux.push_back("id");
  table[make_pair("Termo", "id")] = aux;

  aux.clear();
  aux.push_back("const");
  table[make_pair("Termo", "const")] = aux;

  aux.clear();
  aux.push_back("<");
  table[make_pair("Relop", "<")] = aux;

  aux.clear();
  aux.push_back(">");
  table[make_pair("Relop", ">")] = aux;
  
  aux.clear();
  aux.push_back("<=");
  table[make_pair("Relop", "<=")] = aux;

  aux.clear();
  aux.push_back(">=");
  table[make_pair("Relop", ">=")] = aux;

  aux.clear();
  aux.push_back("==");
  table[make_pair("Relop", "==")] = aux;

  aux.clear();
  aux.push_back("<>");
  table[make_pair("Relop", "<>")] = aux;

  aux.clear();
  aux.push_back("id");
  aux.push_back("Operacao``");
  //aux.push_back(";");
  table[make_pair("Operacao", "id")] = aux;
  
  aux.clear();
  aux.push_back("const");
  table[make_pair("Operacao", "const")] = aux;

  aux.clear();
  aux.push_back("");
  // table[make_pair("Operacao``", "fim")] = aux;
  // table[make_pair("Operacao``", "id")] = aux;
  // table[make_pair("Operacao``", "const")] = aux;
  // table[make_pair("Operacao``", "se")] = aux;
  // table[make_pair("Operacao``", "enquanto")] = aux;
  table[make_pair("Operacao``", ";")] = aux;

  aux.clear();
  aux.push_back("Operadores");
  aux.push_back("Operacao");
  table[make_pair("Operacao``", "+")] = aux;
  table[make_pair("Operacao``", "-")] = aux;
  table[make_pair("Operacao``", "*")] = aux;
  table[make_pair("Operacao``", "/")] = aux;
  table[make_pair("Operacao``", "=")] = aux;

  aux.clear();
  aux.push_back("+");
  table[make_pair("Operadores", "+")] = aux;

  aux.clear();
  aux.push_back("-");
  table[make_pair("Operadores", "-")] = aux;

  aux.clear();
  aux.push_back("*");
  table[make_pair("Operadores", "*")] = aux;
  
  aux.clear();
  aux.push_back("/");
  table[make_pair("Operadores", "/")] = aux;
}

bool SyntaticAnalyzer::isTerminal(string x) {
  return islower(x[0]) || x == ";" || x == "+" || x == "=" || x == "-" || x == "*" || x == "/" 
        || x == ">" || x == "<" || x == ">=" || x == "<=" || x == "<>" || x == "==";
}

int SyntaticAnalyzer::verifyInput(string input) {
  while(!st.empty()) {
    string top = st.top();

    cout << "Top: " << top << "  Input: " << input << endl;

    if(isTerminal(top)) { // Terminal
      if(top == input) {
        st.pop();
        return 1;
      }
      else {
        //cout << "AQUI" << endl;
        return -1; // Error
      }
    }
    else { // Non-Terminal
      if(table.count(make_pair(top, input))) {
        DerivationTree *curr = stTree.top();

        for(int i = 0; i < curr->child.size(); i++) {
          if(curr->child[i]->key == top) {
            curr = curr->child[i];
            stTree.push(curr);
            break;
          }
        }

        st.pop();
        vector<string> prod = table[make_pair(top, input)];
        int j = 0, index = 0;
        for(int i = prod.size()-1; i >= 0; i--) {
          if(prod[j] == "")
            stTree.pop();
          curr->child.push_back(new DerivationTree(prod[j++]));

          if(prod[i] != "")
            st.push(prod[i]);
        }
      }
      else {
        //cout << "ALOU" << endl;
        return -1;
      }
    }
  }

  return 0;
}

bool SyntaticAnalyzer::isInputAccepted() {
  return st.empty();
}

void SyntaticAnalyzer::printDerivationTree() {
  dt->printPreorder(dt);
}