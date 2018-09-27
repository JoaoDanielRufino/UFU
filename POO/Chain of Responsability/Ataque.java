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
public abstract class Ataque {
   private String descricao;
   protected Dano tipoDano;
   protected int dano;
   
   public String getDescricao() {
        return descricao;
    }
   
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
   
   public Dano getTipoDano(){
       return this.tipoDano;
   }
   
   public int getDano(){
       return this.dano;
   }
   
    public abstract String getAtaque();
}
