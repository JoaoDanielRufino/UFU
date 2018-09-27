package Jogo;

public abstract class Personagem extends Ataque {
    private Pular p;
    private Correr c;
    private Ataque a;
    private Escudo e;
    private int vida;
    
    public void setPular(Pular p){
        this.p = p;
    }
    
    public void setCorrer(Correr c){
        this.c = c;
    }
    
    public void setAtacar(Ataque a){
        this.a = a;
    }
    
    public void setEscudo(Escudo e){
        this.e = e;
    }
    
    public void setVida(int i){
        this.vida = i;
    }
    
    public int getVida(){
        return this.vida;
    }
    
    public void pular(){
        this.p.pular();
    }
    
    public void correr(){
        this.c.correr();
    }
    
    public void sofreuDano(int i){
        this.vida -= i;
        if(this.vida <= 0)
            System.out.println("O personagem morreu!");
    }
    
    public void defenderAtaque(Ataque a){
        this.e.defender(a, this);
    }
    
    @Override
    public String getAtaque(){
        return this.a.getAtaque();
    }
}
