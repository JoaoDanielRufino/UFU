package state;

public class MarioFogo extends Mario {

    public MarioFogo(Jogo jogo) {
        super(jogo);
    }

    @Override
    public void pegarCogumelo() {
        System.out.println("Mario fogo pegou cogumelo");
        this.pontos += 1000;
    }

    @Override
    public void pegarFlor() {
        System.out.println("Mario fogo pegou flor");
        this.pontos += 1000;
    }

    @Override
    public void pegarPena() {
        System.out.println("Mario fogo pegou pena");
        this.jogo.setMario(new MarioCapa(this.jogo));
    }

    @Override
    public void levarDano() {
        System.out.println("Mario fogo levou dano");
        this.jogo.setMario(new MarioGrande(this.jogo));
    }
    
}
