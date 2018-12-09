package Models.Chain;

public class Nota extends TrocoChain {
    private int valor;

    public Nota(int v) {
        this.valor = v;
    }
    
    @Override
    public int getValor(){
        return this.valor;
    }
    
}
