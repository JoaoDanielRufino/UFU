package jogo;

/**
 *
 * @Joao Daniel A. Rufino - 11621BCC033
 * @Pedro Henrique F. Teixeira - 11621BCC025
 */
public class Jogo {

    public static void main(String[] args) {
        Personagem1 p1 = new Personagem1();
        Personagem2 p2 = new Personagem2();
        Personagem3 p3 = new Personagem3();
        
        System.out.println("P1 - Antes:");
        p1.pular();
        p1.correr();
        p1.atacar();
        
        // Atualizando p1
        p1.setAtacar(new AtacarFraco());
        p1.setCorrer(new CorrerRapido());
        
        System.out.println("P1 - Depois:");
        p1.pular();
        p1.correr();
        p1.atacar();
        
        System.out.println("\nP2 - Antes:");
        p2.pular();
        p2.correr();
        p2.atacar();
        
        // Atualizando p2
        p2.setPular(new PularBaixo());
        p2.setCorrer(new CorrerMedio());
        
        System.out.println("P2 - Depois:");
        p2.pular();
        p2.correr();
        p2.atacar();
        
        System.out.println("\nP3 - Antes:");
        p3.pular();
        p3.correr();
        p3.atacar();
        
        // Atualizando p3
        p3.setAtacar(new AtacarMedio());
        p3.setPular(new PularAlto());
        
        System.out.println("P3 - Depois:");
        p3.pular();
        p3.correr();
        p3.atacar();
    }
    
}
