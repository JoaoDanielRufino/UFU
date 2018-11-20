package command;

public class Forno {
    private int temp;
    private int timer;
    
    public void setTemp(int t){
        this.temp = t;
        System.out.println("Temperatura: " + t);
    }
    
    public void setTimer(int t){
        this.timer = t;
        System.out.println("Timer: " + t);
    }
    
    public void ligar(){
        System.out.println("Ligando forno");
    }
    
    public void desligar(){
        System.out.println("Desligando forno");
    }
    
}
