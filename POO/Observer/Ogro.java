
package jogo_2;

public class Ogro extends MonstroAbstract {

    public Ogro(String nome, Personagem p){
        super(nome, p);
    }
    
    @Override
    public void atualiza(String coord) {
        System.out.println("Eu sou um Ogro, e vou te atacar em: " + coord);
    }
    
}
