
package Jogo;

public class EscudoChibakuTensei extends Escudo {
    
    public EscudoChibakuTensei(){
        super(Dano.CHIBAKUTENSEI);
    }

    @Override
    public void defenderAtaque() {
        System.out.println("Ataque defendido");
    }
    
}
