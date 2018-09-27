/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo2;

public class Dificil implements LevelFactory {

    @Override
    public Monstro criaMonstro() {
        return new Orc();
    }

    @Override
    public Ataque criaAtaque() {
        return new EdoTensei();
    }

    @Override
    public Escudo criaEscudo() {
        return new EscudoDeRaio();
    }
    
}
