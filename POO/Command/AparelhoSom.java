package command;

public class AparelhoSom {
    private int volume;
    
    public AparelhoSom(){
        this.volume = 0;
    }
    
    public void setVolume(int v){
        this.volume = v;
        System.out.println("Volume: " + v);
    }
    
    public void ligar(){
        System.out.println("Ligando aparelho som");
    }
    
    public void desligar(){
        System.out.println("Desligando aparelho som");
    }
    
    public Memento criarMemento(){
        return new Memento(this.volume);
    }
    
    public void recuperarMemento(Memento m){
        this.volume = m.getVolume();
        System.out.println("(Undo)Volume: " + this.volume);
    }
    
}
