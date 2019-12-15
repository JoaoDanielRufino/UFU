#ifndef TOKEN_H_INCLUDED
#define TOKEN_H_INCLUDED

#include <iostream>

using namespace std;

class Token {
  private:
    string name;
    string value;

  public:
    Token(string name, string value);
    string getName();
    string getValue();
};

#endif