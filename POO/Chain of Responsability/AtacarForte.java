package Jogo;

public class AtacarForte extends Ataque implements Atacar {
   
    @Override
    public void atacar() {
        System.out.println("Estou atacando forte!");
    }

    @Override
    public String getAtaque() {
        return "Estou atacando forte com: ";
    }
    
}
