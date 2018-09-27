/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jogo;

import java.util.Random;

/**
 *
 * @author a11621BCC033
 */
public abstract class Escudo {
    Escudo escudo;
    Dano tipoDano;
    
    public Escudo(Dano d){
        this.escudo = null;
        this.tipoDano = d;
    }
    
    public void setEscudo(Escudo e){
        if(this.escudo == null)
            this.escudo = e;
        else
            this.escudo.setEscudo(e);
    }
    
    public void defender(Ataque a, Personagem p){
        if(this.tipoDano == a.getTipoDano()){
            defenderAtaque();
        }
        else{
            if(this.escudo == null){
                Random r = new Random();
                int m = r.nextInt(3) + 1;
                System.out.println("Impossivel defender este ataque! ");
                System.out.println("Multiplicador do dano base: " + m);
                p.sofreuDano(a.getDano() * m);
            }
            else
                escudo.defender(a, p);
        }
    }
    
    public abstract void defenderAtaque();
}
