package mainjogo;

import java.util.Vector;

public class Jogo {
    private Vector<Jogador> listaJogadores;
    
    public Jogo (){
        listaJogadores = new Vector<Jogador> ();
    }
    
    public void insereJogador (String nome){
        Jogador j = new Jogador(nome);
        listaJogadores.add(j);
    }
    
    void rodada (){
        int pontos;
        while(true){
            for(Jogador j: listaJogadores){
                pontos = j.jogaDado();
                System.out.println("O jogador " + j.getNome() + " possui " + pontos + " ponto(s).");
            }
            if(verificaVencedores()){
                mostraVencedores();
                return;
            }
        }
    }
    
    boolean verificaVencedores (){
        for(Jogador j: listaJogadores){
            if(j.getPontos() >= 21)
                return true;
        }
        return false;
    }
    
    void mostraVencedores (){
        System.out.print("Vencedore(s): ");
        for(Jogador j: listaJogadores){
            if(j.getPontos() >= 21)
                System.out.println(j.getNome() + " ");
        }
    }
}
