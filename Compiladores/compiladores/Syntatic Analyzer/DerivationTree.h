#ifndef DERIVATION_TREE_H_INCLUDED
#define DERIVATION_TREE_H_INCLUDED

#include <iostream>
#include <vector>

using namespace std;

class DerivationTree {
  public:
    string key;
    vector<DerivationTree*> child;

    DerivationTree(string key);
    void printPreorder(DerivationTree *tree);
};

#endif