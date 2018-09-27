/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jogo;

/**
 *
 * @author a11621BCC025
 */
public class Rasengan extends PoderDecorator{

    private Ataque p; 

    public Rasengan(Ataque p) {
        this.p = p;
        this.tipoDano = Dano.RASENGAN;
        this.dano += 15;
    }
    
    @Override
    public String getDescricao() {
        return p.getDescricao() + " Naruto.\n";
    }

    @Override
    public String getAtaque() {
       return p.getAtaque() + " Rasengan.\n";
    }
    
}
