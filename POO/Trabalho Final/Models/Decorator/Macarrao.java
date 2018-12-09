package Models.Decorator;

public class Macarrao extends AdicionalDecorator {
    private PratoZonaSul prato;
    
    public Macarrao(PratoZonaSul prato){
        this.prato = prato;
    }
    
    @Override
    public String getDescricao() {
        return this.prato.getDescricao() + " + Macarrao";
    }
    
    @Override
    public float getPreco(){
        return this.prato.getPreco() + 6.0f;
    }
}
