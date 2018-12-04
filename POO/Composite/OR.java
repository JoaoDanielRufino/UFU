package composite;

public class OR extends OperadorBinario{

    public OR(ExpressaoBooleana e1, ExpressaoBooleana e2){
        super(e1, e2);
    }
    
    @Override
    public boolean operacao() {
        return getE1().operacao() || getE2().operacao();
    }
    
}
