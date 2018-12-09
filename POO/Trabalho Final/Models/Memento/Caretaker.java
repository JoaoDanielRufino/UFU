package Models.Memento;

public class Caretaker {
    private Memento memento;
    
    public void setMemento(Memento m){
        this.memento = m;
    }
    
    public Memento getMemento(){
        return this.memento;
    }
    
}
