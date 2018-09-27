/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jogo;

/**
 *
 * @author a11621BCC033
 */
public class EscudoRasengan extends Escudo {

    public EscudoRasengan(){
        super(Dano.RASENGAN);
    }
    
    @Override
    public void defenderAtaque() {
        System.out.println("Ataque defendido");
    }
    
}
