package composite;

public class AND extends OperadorBinario {
    
    public AND(ExpressaoBooleana e1, ExpressaoBooleana e2){
        super(e1, e2);
    }
    
    @Override
    public boolean operacao() {
        return getE1().operacao() && getE2().operacao();
    }
    
}
