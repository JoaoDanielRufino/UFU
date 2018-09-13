package Jogo;

public class AtacarFraco extends Ataque implements Atacar {

    @Override
    public void atacar() {
        System.out.println("Estou atacando fraco!");
    }

    @Override
    public String getAtaque() {
        return "Estou atacando fraco: ";
    }
    
}
