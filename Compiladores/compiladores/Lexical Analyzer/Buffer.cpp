#include "Buffer.h"

Buffer::Buffer(string input) {
  prox = 0;
  isEndOfFile = false;
  fs.open(input);

  if(!fs.is_open()) {
    cout << "Error, input file can not be opened" << input << endl;
    exit(1);
  }
}

void Buffer::fillBuffer() {
  if(isEndOfFile) {
    cout << "The file is all read." << endl;
    return;
  }

  char c;
  int i = 0;
  buffer.clear();
  while(fs >> c && i < BUFFER_SIZE) { // This already reads without spaces and indentation
    buffer += c;
    i++;
  }

  prox = 0;

  if(i < BUFFER_SIZE)
    isEndOfFile = true;
}

char Buffer::getProxChar() {
  if(isEndOfFile && prox == buffer.size())
    return EOF;

  if(!isEndOfFile && (buffer.empty() || prox == buffer.size()))
    fillBuffer();

  return buffer[prox++];
}

Buffer::~Buffer() {
  fs.close();
}