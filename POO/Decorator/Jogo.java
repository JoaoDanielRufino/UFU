package Jogo;

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
        
        Ataque a = new AtacarForte();
        a = new Rasengan(a);
        a = new Amateratsu(a);
        p1.setAtacar(a);
        
        System.out.println("P1: ");
        System.out.println(p1.getAtaque());
        
        Ataque a2 = new AtacarFraco();
        a2 = new ChibakuTensei(a2);
        a2 = new Amateratsu(a2);
        p2.setAtacar(a2);
        
        System.out.println("P2: ");
        System.out.println(p2.getAtaque());
        
        Ataque a3 = new AtacarMedio();
        a3 = new ChibakuTensei(a3);
        a3 = new Rasengan(a3);
        p3.setAtacar(a3);
        
        System.out.println("P3: ");
        System.out.println(p3.getAtaque());
    }
    
}
