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
public interface Subject {
    
      public void registraMonstro(Observer o);
      public void atualizapos(String coord);
      public void atualizaMonstro();
}
