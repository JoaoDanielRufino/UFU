#include <iostream>
#include <fstream>
#include "LexicalAnalyzer.h"
#include "Token.h"

using namespace std;

int main() {

  LexicalAnalyzer la("input.txt");
  Token *token;

  token = la.getNextToken();
  while(token) {
    cout << "Name: " << token->getName() << "\tValue: " << token->getValue() << endl;
    token = la.getNextToken();
  }

  return 0;
}