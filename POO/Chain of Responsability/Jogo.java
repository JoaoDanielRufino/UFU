package Jogo;

/**
 *
 * @Joao Daniel A. Rufino - 11621BCC033
 * @Pedro Henrique F. Teixeira - 11621BCC025
 */
public class Jogo {

    public static void main(String[] args) {
        Escudo e = new EscudoChibakuTensei();
        e.setEscudo(new EscudoAmateratsu());
        
        Personagem1 p1 = new Personagem1();
        p1.setEscudo(e);
        
        Ataque a = new AtacarForte();
        a = new Rasengan(a);
        
        System.out.println("Vida: " + p1.getVida());
        p1.defenderAtaque(a);
        System.out.println("Vida: " + p1.getVida());
        
       
    }
    
}
