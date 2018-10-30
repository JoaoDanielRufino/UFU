package facade;

/**
 *
 * @Joao Daniel Rufino
 * @Pedro Henrique Teixeira
 */
public class Facade {

    public static void main(String[] args) {
        
        HomeTheaterFacade home = new HomeTheaterFacade(new Amplificador(),new Sincronizador(), new CDPlayer(),new DVDPlayer(), new Projetor());

        home.assistirFilme(new Filme("As aventuras de Samuel Rifts",15));
        home.pararFilme();
    }
    
}
