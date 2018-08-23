package jogo;

public abstract class Personagem {
    private Pular p;
    private Correr c;
    private Atacar a;
    
    public void setPular(Pular p){
        this.p = p;
    }
    
    public void setCorrer(Correr c){
        this.c = c;
    }
    
    public void setAtacar(Atacar a){
        this.a = a;
    }
    
    public void pular(){
        this.p.pular();
    }
    
    public void correr(){
        this.c.correr();
    }
    
    public void atacar(){
        this.a.atacar();
    }
}
