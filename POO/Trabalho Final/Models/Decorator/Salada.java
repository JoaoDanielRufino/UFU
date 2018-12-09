package Models.Decorator;

public class Salada extends AdicionalDecorator {
    private PratoZonaSul prato;
    
    public Salada(PratoZonaSul prato){
        this.prato = prato;
    }
    
    @Override
    public String getDescricao() {
        return this.prato.getDescricao() + " + Salada";
    }
    
    @Override
    public float getPreco(){
        return this.prato.getPreco() + 4.0f;
    }
    
}
