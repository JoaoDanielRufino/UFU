#include "Token.h"

Token::Token(string name, string value) {
  this->name = name;
  this->value = value;
}

string Token::getName() {
  return name;
}

string Token::getValue() {
  return value;
}