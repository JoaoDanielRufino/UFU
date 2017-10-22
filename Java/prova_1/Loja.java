package loja;

import java.util.Vector;

public class Loja {
    private Vector<Produto> listaProdutos;
    
    public Loja (){
        listaProdutos = new Vector<Produto> ();
    }
    
    public void insereItem (String nome, int qtd, float preco){
        Produto p = new Produto(nome, qtd, preco);
        listaProdutos.add(p);
    }
    
    public void solicitarVenda (String nome, int qtd){
        for(Produto p: listaProdutos){
            if(p.getNome() == nome && (p.getEstoque() - qtd) >= 0){
                p.vender(qtd);
                return;
            }
        }
        System.out.println("Item nao encontrado ou qtd invalida!");
    }
    
    public void solicitarReposicao (String nome, int qtd){
        for(Produto p: listaProdutos){
            if(p.getNome() == nome){
                p.repor(qtd);
                return;
            }
        }
        System.out.println("Item nao encontrado!");
    }
    
    public int qtdEstoque (String nome){
        for(Produto p: listaProdutos){
            if(p.getNome() == nome)
               return p.getEstoque();
        }
        System.out.println("Item nao encontrado!");
        return -1;
    }
    
    public void tabelaPrecos (){
        for(Produto p: listaProdutos)
            System.out.println("Produto " + p.getNome() + ": R$" + p.getPreco());
    }
    
    public void estoqueProdutos (){
        for(Produto p: listaProdutos)
            System.out.println("Produto " + p.getNome() + ": " + p.getEstoque() + " item(ns)");
    }
}
