package mainjogo;

public class Main {
    
    public static void main(String[] args) {
        Jogo jogo = new Jogo();
        
        jogo.insereJogador("Joao");
        jogo.insereJogador("Pedro");
     
        jogo.rodada();
    }
}
