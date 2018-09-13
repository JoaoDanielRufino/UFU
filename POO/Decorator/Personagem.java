package Jogo;

public abstract class Personagem extends Ataque {
    private Pular p;
    private Correr c;
    private Ataque a;
    
    public void setPular(Pular p){
        this.p = p;
    }
    
    public void setCorrer(Correr c){
        this.c = c;
    }
    
    public void setAtacar(Ataque a){
        this.a = a;
    }
    
    public void pular(){
        this.p.pular();
    }
    
    public void correr(){
        this.c.correr();
    }
    
    
    
    @Override
    public String getAtaque(){
        return this.a.getAtaque();
    }
}
