package mainjogo;

public class Jogador {
    private int pontos;
    private String nome;
    
    public Jogador (String nome){
        this.pontos = 0;
        this.nome = nome;
    }
    
    public int jogaDado (){
        int dado = ((int)(Math.random()*6)+1);
        return this.pontos += dado;
    }
    
    public int getPontos (){
        return this.pontos;
    }
    
    public String getNome (){
        return this.nome;
    }
}
