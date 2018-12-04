package composite;

public abstract class OperadorBinario implements ExpressaoBooleana {    
    private ExpressaoBooleana e1;
    private ExpressaoBooleana e2;
    
    public OperadorBinario(ExpressaoBooleana e1, ExpressaoBooleana e2){
        this.e1 = e1;
        this.e2 = e2;
    }
    
    public ExpressaoBooleana getE1(){
        return this.e1;
    }
    
    public ExpressaoBooleana getE2(){
        return this.e2;
    }
    
}
