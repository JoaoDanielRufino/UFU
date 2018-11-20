package command;

public class ComandoArCondicionado implements Comando {
    private ArCondicionado ac;
    
    public ComandoArCondicionado(ArCondicionado ac){
        this.ac = ac;
    }
    
    @Override
    public void executar() {
        this.ac.ligar();
        this.ac.setTemperatura(22);
    }
    
}
