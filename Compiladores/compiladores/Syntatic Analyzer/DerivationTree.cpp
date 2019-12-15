#include "DerivationTree.h"

DerivationTree::DerivationTree(string key) {
  this->key = key;
}

void DerivationTree::printPreorder(DerivationTree *tree) {
  if(!tree)
    return;

  cout << tree->key << " ";
  for(int i = 0; i < tree->child.size(); i++)
    printPreorder(tree->child[i]);
}