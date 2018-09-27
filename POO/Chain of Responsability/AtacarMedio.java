package Jogo;

public class AtacarMedio extends Ataque  implements Atacar {

    @Override
    public void atacar() {
        System.out.println("Estou atacando medio!");
    }

    @Override
    public String getAtaque() {
        return "Ataque medio com: ";
    } 
    
}
