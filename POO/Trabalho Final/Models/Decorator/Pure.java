package Models.Decorator;

public class Pure extends AdicionalDecorator {
    private PratoZonaSul prato;
    
    public Pure(PratoZonaSul prato){
        this.prato = prato;
    }
    
    @Override
    public String getDescricao() {
        return this.prato.getDescricao() + " + Pure";
    }
    
    @Override
    public float getPreco(){
        return this.prato.getPreco() + 3.0f;
    }
    
}
