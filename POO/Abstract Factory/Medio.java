/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo2;

/**
 *
 * @author a11621BCC025
 */
public class Medio implements LevelFactory {

    @Override
    public Monstro criaMonstro() {
        return new Troll();
    }

    @Override
    public Ataque criaAtaque() {
        return new Amateratsu();
    }

    @Override
    public Escudo criaEscudo() {
        return new EscudoDeGelo();
    }
    
}
