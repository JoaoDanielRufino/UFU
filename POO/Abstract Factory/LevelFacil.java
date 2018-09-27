/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo2;

public class LevelFacil extends Level {
    private LevelFactory lf;
    
    public LevelFacil(LevelFactory l){
        this.lf = l;
    }
    
    @Override
    public void iniciaJogo() {
        System.out.println("Iniciando jogo Facil");
        this.lf.criaMonstro();
        this.lf.criaAtaque();
        this.lf.criaEscudo();
    }
    
}
