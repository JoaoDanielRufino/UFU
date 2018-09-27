package Jogo;

public class Personagem1 extends Personagem {
   
    public Personagem1(){
        super.setPular(new PularMedio());
        super.setCorrer(new CorrerMedio());
        super.setAtacar(new AtacarForte());
        super.setVida(100);
    } 
}
