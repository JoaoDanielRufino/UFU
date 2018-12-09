package Models.Strategy;

import Models.Prato;

public abstract class PratoZonaNorte extends Prato {
    private Carne carne;
    private Sobremesa sobremesa;
    
    public void setCarne(Carne c){
        this.carne = c;
    }
    
    public String getDiscricaoCarne(){
        return this.carne.descricao();
    }
    
    public void prepararPratoDeCarne(){
        this.carne.prepararPrato();
    }
    
    public void setSobremesa(Sobremesa s){
        this.sobremesa = s;
    }
    
    public String getDiscricaoSobremesa(){
        return this.sobremesa.descricao();
    }

    public void setDescricao(String s){
        this.descricao = s;
    }
    
    public void setPrecoTotal(){
        this.preco = this.carne.getPrecoPrato() + this.sobremesa.getPrecoPrato();
    }
    
}
