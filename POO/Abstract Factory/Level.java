/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo2;

public abstract class Level {
   protected Monstro monstro;
   protected Ataque ataque;
   protected Escudo escudo;
   
   public abstract void iniciaJogo();
}
