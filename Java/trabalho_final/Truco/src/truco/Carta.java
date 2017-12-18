package truco;

public class Carta {
    private String valor;
    private String naipe;
    private int prioridade;
    private String imagem;
    
    public Carta (String valor, String naipe, int prioridade){
        this.valor = valor;
        this.naipe = naipe;
        this.prioridade = prioridade;
        this.imagem = "/Imagens/" + valor.charAt(0) + naipe.charAt(0) + ".gif";
    }
    
    public String getValor (){
        return this.valor;
    }
    
    public String getNaipe (){
        return this.naipe;
    }
    
    public int getPrioridade (){
        return this.prioridade;
    }
    
    public String getImagem (){
        return this.imagem;
    }
}
