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
public class ChibakuTensei extends PoderDecorator{
    private Ataque p; 

    public ChibakuTensei(Ataque p) {
        this.p = p;
    }
    
    
    @Override
    public String getDescricao() {
        return p.getDescricao() + " Pain.\n";
    }

    @Override
    public String getAtaque() {
       return p.getAtaque() + " ChibakuTensei.\n";
    }
}
