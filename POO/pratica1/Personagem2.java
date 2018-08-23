package jogo;

public class Personagem2 extends Personagem {
    
    public Personagem2(){
        super.setPular(new PularAlto());
        super.setCorrer(new CorrerRapido());
        super.setAtacar(new AtacarMedio());
    }   
    
}
