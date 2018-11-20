package command;

public class ArCondicionado {
    private int temperatura;
    
    public void setTemperatura(int t){
        this.temperatura = t;
        System.out.println("Tempratura: " + t);
    }
    
    public void ligar(){
        System.out.println("Ligando ar condicionado");
    }
    
    public void desligar(){
        System.out.println("Desligando ar condicionado");
    }
    
}
