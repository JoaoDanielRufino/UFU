package jogo;

public class Personagem3 extends Personagem {
    
    public Personagem3(){
        super.setPular(new PularBaixo());
        super.setCorrer(new CorrerRapido());
        super.setAtacar(new AtacarForte());
    }   
    
}
