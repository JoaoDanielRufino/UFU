#include "LexicalAnalyzer.h"

LexicalAnalyzer::LexicalAnalyzer(string input) {
  buffer = new Buffer(input);
  transitionTable = new TransitionTable("tabela_afd.txt");
}

Token* LexicalAnalyzer::createToken(int state, string c) {
  Token *token = nullptr;
  
  switch(state) {
    case 1:
      token = new Token("RELOP", ">");
      break;
    case 18:
      token = new Token("RELOP", "<");
      break;
    case 6:
      token = new Token("OPERAND", "=");
      break;
    case 8:
      token = new Token("OPERAND", "+");
      break;
    case 14:
      token = new Token("OPERAND", "-");
      break;
    case 16:
      token = new Token("OPERAND", "*");
      break;
    case 17:
      token = new Token("OPERAND", "\\");
      break;
    case 11:
      token = new Token("CONST", "");
      break;
    case 36:
      token = new Token("CONST", "");
      break;
    case 67:
      token = new Token("CONST", "");
      break;
    case 19:
      token = new Token("PONCT", ";");
      break;
    case 13:
      token = new Token("PONCT", "(");
      break;
    case 23:
      token = new Token("PONCT", ")");
      break;
    case 65:
      token = new Token("PROGRAMA", "");
      break;
    case 60:
      token = new Token("INICIO", "");
      break;
    case 41:
      token = new Token("INT", "");
      break;
    case 37:
      token = new Token("SE", "");
      break;
    case 59:
      token = new Token("ENTAO", "");
      break;
    case 52:
      token = new Token("PARA", "");
      break;
    case 43:
      token = new Token("FIM", "");
      break;
    case 66:
      token = new Token("ENQUANTO", "");
      break;
    case 4:
      if(simbleTable.count(c))
        token = symbleTable[c];
      else {
        token = new Token("ID", c);
        symbleTable[c] = token;
      }
      break;
  }

  return token;
}

Token* LexicalAnalyzer::getNextToken() {
  int state, finalState;
  char c;
  string tmp;

  state = 0;
  c = buffer->getProxChar();
  while(c != EOF && state != -1) {
    //cout << "Char: " << c << "  State: " << state << endl;
    tmp += c;
    state = transitionTable->move(state, (int) c);

    if(transitionTable->isFinalState(state))
      break;
    
    c = buffer->getProxChar();
  }

  if(transitionTable->isFinalState(state)) {
    //cout << "Aceito  State: " << state << endl << endl;
    return createToken(state, tmp);
  }
  else {
    //cout << "Nao aceito" << endl;
    return nullptr;
  }
}

LexicalAnalyzer::~LexicalAnalyzer() {
  delete buffer;
  delete transitionTable;
  symbleTable.clear();
}
