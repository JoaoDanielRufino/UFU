package composite;

public abstract class OperadorUnario implements ExpressaoBooleana {
    private ExpressaoBooleana e;
    
    public OperadorUnario(ExpressaoBooleana e){
        this.e = e;
    }
    
    public ExpressaoBooleana getE(){
        return this.e;
    }
}
