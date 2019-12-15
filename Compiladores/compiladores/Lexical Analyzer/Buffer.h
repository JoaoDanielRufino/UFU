#ifndef BUFFER_H_INCLUDED
#define BUFFER_H_INCLUDED

#define BUFFER_SIZE 1024

#include <iostream>
#include <fstream>

using namespace std;

class Buffer{
  private:
    string buffer;
    bool isEndOfFile;
    fstream fs;

    void fillBuffer();

  public:
    int prox;
    Buffer(string input);
    ~Buffer();
    char getProxChar();
};

#endif