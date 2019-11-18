#ifndef LEXICAL_ANALYZER_H_INCLUDED
#define LEXICAL_ANALYZER_H_INCLUDED

#include <iostream>
#include <unordered_map>
#include "Buffer.h"
#include "TransitionTable.h"
#include "Token.h"

using namespace std;

class LexicalAnalyzer {
  private:
    Buffer *buffer;
    TransitionTable *transitionTable;
    unordered_map<string, Token*> symbleTable;

    Token* createToken(int state, string c);

  public:
    LexicalAnalyzer(string input);
    ~LexicalAnalyzer();
    Token* getNextToken();
};

#endif
