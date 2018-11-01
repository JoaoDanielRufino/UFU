package state;

public class Jogo {
    private Mario mario;
    
    public Jogo(){
        this.mario = new MarioPequeno(this);
    }
    
    public void pegarCogumelo(){
        this.mario.pegarCogumelo();
    }
    
    public void pegarFlor(){
        this.mario.pegarFlor();
    }
    
    public void pegarPena(){
        this.mario.pegarPena();
    }
    
    public void levarDano(){
        this.mario.levarDano();
    }
    
    public void setMario(Mario mario){
        this.mario = mario;
    }
    
}
