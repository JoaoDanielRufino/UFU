#include "TransitionTable.h"
#include "Buffer.h"

TransitionTable::TransitionTable(string table) {
  fs.open(table);

  if(fs.is_open()) {
    string num;
    for(int i = 0; i < ROW; i++) {
      for(int j = 0; j < COL; j++) {
        fs >> num;
        if(isdigit(num[0]) || (num.size() == 2 && num[0] == '-'))
          transitionTable[i][j] = stoi(num);
        else
          transitionTable[i][j] = num[0];
      }
    }
  }
  else {
    cout << "Error, AFD file can not be opened" << endl;
    exit(1);
  }
}

int TransitionTable::move(int state, int c) {
  for(int j = 1; j < COL; j++) {
    if(transitionTable[ROW-1][j] == c)
      return transitionTable[state][j];
  }

  return -1;
}

bool TransitionTable::isFinalState(int state) {
  return transitionTable[state][0] == (int) 'S';
}

TransitionTable::~TransitionTable() {
  fs.close();
}