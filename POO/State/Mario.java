package state;

public abstract class Mario {
    protected Jogo jogo; 
    protected int pontos;
    
    public Mario(Jogo jogo){
        this.jogo = jogo;
        this.pontos = 0;
    }
    
    public abstract void pegarCogumelo();
    
    public abstract void pegarFlor();
    
    public abstract void pegarPena();
    
    public abstract void levarDano();
}
