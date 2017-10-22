package loja;

public class Produto {
    private String nome;
    private int estoque;
    private float preco;
    
    public Produto (String nome, int estoque, float preco){
        this.nome = nome;
        this.estoque = estoque;
        this.preco = preco;
    }
    
    public void vender (int qtd){
        this.estoque -= qtd;
    }
    
    public void repor (int qtd){
        this.estoque += qtd;
    }
    
    public String getNome (){
        return this.nome;
    }
    
    public int getEstoque (){
        return this.estoque;
    }
    
    public float getPreco (){
        return this.preco;
    }
}
