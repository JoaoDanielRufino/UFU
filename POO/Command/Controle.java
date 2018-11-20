package command;

public class Controle {
    private Comando[] comando;
    
    public Controle(){
        this.comando = new Comando[4];
    }
    
    public void setComando(int slot, Comando c){
        this.comando[slot] = c;
    }
    
    public void executar(){
        for(Comando c: this.comando){
            c.executar();
        }
    }
    
}
