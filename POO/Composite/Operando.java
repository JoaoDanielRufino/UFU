package composite;

public class Operando implements ExpressaoBooleana {
    private boolean b;
    
    public Operando(boolean b){
        this.b = b;
    }
    
    @Override
    public boolean operacao() {
        return this.b;
    }
    
}
