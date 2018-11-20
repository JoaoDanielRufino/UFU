package command;

public class ComandoAparelhoSom implements Comando{
    private AparelhoSom a;
    private Memento memento;
    
    public ComandoAparelhoSom(AparelhoSom a){
        this.a = a;
    }
    
    @Override
    public void executar(){
        this.memento = this.a.criarMemento();
        this.a.ligar();
        this.a.setVolume(20);
    }
    
    public void undo(){
        this.a.recuperarMemento(this.memento);
    }
    
}
