#include <iostream>
#include "Syntatic Analyzer/SyntaticAnalyzer.h"
#include "Lexical Analyzer/LexicalAnalyzer.h"
#include "Lexical Analyzer/Token.h"

using namespace std;

int main() {

  LexicalAnalyzer la("input.txt");
  SyntaticAnalyzer sa;

  Token *token = la.getNextToken();
  while(token) {
    if(token->getValue() != "" && token->getName() != "id") {
      if(sa.verifyInput(token->getValue()) == -1) {
        cout << "Syntatic error" << endl;
        break;
      }
    }
    else
      if(sa.verifyInput(token->getName()) == -1) {
        cout << "Syntatic error" << endl;
        break;
      }
    token = la.getNextToken();
  }
  
  cout << "Accepted? " << sa.isInputAccepted() << endl;

  cout << "Derivation Tree: " << endl;

  sa.printDerivationTree();

  cout << endl;

  return 0;
}