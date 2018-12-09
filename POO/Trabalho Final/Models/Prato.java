package Models;

public abstract class Prato {
    protected String descricao;
    protected float preco;
    
    public String getDescricao(){
        return this.descricao;
    }
    
    public float getPreco(){
        return this.preco;
    }
}
