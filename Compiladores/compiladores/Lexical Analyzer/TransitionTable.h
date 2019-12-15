#ifndef TRANSITION_TABLE_H_INCLUDED
#define TRANSITION_TABLE_H_INCLUDED

#include <iostream>
#include <fstream>
#include <string>

#define ROW 69
#define COL 51

using namespace std;

class TransitionTable {
  private:
    int transitionTable[ROW][COL];
    fstream fs;

  public:
    TransitionTable(string table);
    ~TransitionTable();
    int move(int state, int c);
    bool isFinalState(int state);
};

#endif