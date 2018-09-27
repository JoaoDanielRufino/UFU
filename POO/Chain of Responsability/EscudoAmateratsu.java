
package Jogo;


public class EscudoAmateratsu extends Escudo {
    
    public EscudoAmateratsu(){
        super(Dano.AMATERATSU);
    }
    
    @Override
    public void defenderAtaque() {
        System.out.println("Ataque defendido");
    }
}
