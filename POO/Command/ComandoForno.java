package command;

public class ComandoForno implements Comando {
    private Forno forno;
    
    public ComandoForno(Forno f){
        this.forno = f;
    }
    
    public void executar(){
        this.forno.ligar();
        this.forno.setTemp(200);
        this.forno.setTimer(30);
    }
    
}
