package command;

public class ComandoPanelaEletrica implements Comando {
    private PanelaEletrica panela;
    
    public ComandoPanelaEletrica(PanelaEletrica p){
        this.panela = p;
    }
    
    public void executar(){
        this.panela.ligar();
    }
    
}
