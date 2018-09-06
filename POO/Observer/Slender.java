/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo_2;

/**
 *
 * @author a11621BCC033
 */
public class Slender extends MonstroAbstract{

    public Slender(String nome, Personagem p){
        super(nome, p);
    }
    
    @Override
    public void atualiza(String coord) {
        System.out.println("Eu sou um Slender e vou te atacar em: " + coord);
    }
    
}
