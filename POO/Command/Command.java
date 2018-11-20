package command;

/**
 *
 * @Joao Daniel A. Rufino 11621BCC033
 * @Pedro Henrique Teixeira 11621BCC025
 */
public class Command {

    public static void main(String[] args) {
        Comando ac = new ComandoArCondicionado(new ArCondicionado());
        Comando forno = new ComandoForno(new Forno());
        Comando panela = new ComandoPanelaEletrica(new PanelaEletrica());
        ComandoAparelhoSom som = new ComandoAparelhoSom(new AparelhoSom());
        
        Controle controle = new Controle();
        controle.setComando(0, ac);
        controle.setComando(1, forno);
        controle.setComando(2, panela);
        controle.setComando(3, som);
        
        controle.executar();
        som.undo();
    }
    
}
