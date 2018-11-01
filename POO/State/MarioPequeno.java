package state;

public class MarioPequeno extends Mario {

    public MarioPequeno(Jogo jogo){
        super(jogo);
    }
    
    @Override
    public void pegarCogumelo() {
        System.out.println("Mario pequeno pegou cogumelo");
        this.jogo.setMario(new MarioGrande(this.jogo));
    }

    @Override
    public void pegarFlor() {
        System.out.println("Mario pequeno pegou flor");
        this.jogo.setMario(new MarioFogo(this.jogo));
    }

    @Override
    public void pegarPena() {
        System.out.println("Mario pequeno pegou pena");
        this.jogo.setMario(new MarioCapa(this.jogo));
    }

    @Override
    public void levarDano() {
        System.out.println("Mario pequeno esta morto");
        
    }
    
}
