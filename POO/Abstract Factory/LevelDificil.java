/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo2;

public class LevelDificil extends Level {
    private LevelFactory lf;
    
    public LevelDificil(LevelFactory l){
        this.lf = l;
    }
    
    @Override
    public void iniciaJogo() {
        System.out.println("Iniciando jogo Dificil");
        this.lf.criaMonstro();
        this.lf.criaAtaque();
        this.lf.criaEscudo();
    }
    
}
