#ifndef SYNTATIC_ANALYZER_H_INCLUDED
#define SYNTATIC_ANALYZER_H_INCLUDED

#include <iostream>
#include <map>
#include <vector>
#include <stack>
#include <algorithm>
#include "DerivationTree.h"

using namespace std;

class SyntaticAnalyzer {
  private:
    map< pair<string, string>, vector<string> > table;
    stack<string> st;
    DerivationTree *dt;
    stack<DerivationTree*> stTree;

    bool isTerminal(string x);

  public:
    SyntaticAnalyzer();
    int verifyInput(string input);
    bool isInputAccepted();
    void printDerivationTree();
};

#endif