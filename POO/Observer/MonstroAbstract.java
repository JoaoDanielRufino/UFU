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
abstract public class MonstroAbstract implements Observer {

    private String nome;
    private Personagem p;
    
    public MonstroAbstract(String nome, Personagem p){
        this.nome = nome;
        this.p = p;
        this.p.registraMonstro(this);
    }
    
}
