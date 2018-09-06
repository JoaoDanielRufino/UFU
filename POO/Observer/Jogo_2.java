
package jogo_2;

/**
 *
 * @Joao Daniel A. Rufino - 11621BCC033
 * @Pedro Henrique F. Teixeira - 11621BCC025
 */
public class Jogo_2 {

    public static void main(String[] args) {
        String coord = "(12, 20)";
        Personagem p = new Personagem("Joao");
        
        Ogro o = new Ogro("Pedro", p);
        Slender s = new Slender("Jão",p);
        Troll t = new Troll("Yan",p);
        
        p.atualizapos(coord);
    }
    
}
