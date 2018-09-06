
package jogo_2;

public class Troll extends MonstroAbstract {

    public Troll(String nome, Personagem p){
        super(nome, p);
    }
    
    @Override
    public void atualiza(String coord) {
        System.out.println("Eu sou um Troll e vou te atacar em: " + coord);
    }
    
}
