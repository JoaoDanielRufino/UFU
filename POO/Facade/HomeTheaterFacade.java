/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

/**
 *
 * @author a11621BCC033
 */
public class HomeTheaterFacade {
    Amplificador amplificador;
    Sincronizador sintonizador;
    CDPlayer cdPlayer;
    DVDPlayer dvdPlayer;
    Projetor projetor;

    public HomeTheaterFacade(Amplificador amplificador, Sincronizador sintonizador, CDPlayer cdPlayer, DVDPlayer dvdPlayer, Projetor projetor) {
        this.amplificador = amplificador;
        this.sintonizador = sintonizador;
        this.cdPlayer = cdPlayer;
        this.dvdPlayer = dvdPlayer;
        this.projetor = projetor;
    }

    public void assistirFilme(Filme filme){
    System.out.println(
    "Tudo ok para assistir um filme!");

    projetor.ligar();
    amplificador.ligar();
    dvdPlayer.ligar();
   
    }
    
    public void pararFilme(){
    System.out.println("Desligando ohome...");

    projetor.desligar();
    amplificador.desligar();
    dvdPlayer.desligar();
}


}
