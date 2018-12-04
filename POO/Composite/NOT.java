package composite;

public class NOT extends OperadorUnario {

    public NOT(ExpressaoBooleana e){
        super(e);
    }
    
    @Override
    public boolean operacao() {
        return !getE().operacao();
    }
    
}
