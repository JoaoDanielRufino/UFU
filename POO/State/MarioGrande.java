package state;

public class MarioGrande extends Mario {

    public MarioGrande(Jogo jogo) {
        super(jogo);
    }

    @Override
    public void pegarCogumelo() {
        System.out.println("Mario grande pegou cogumelo");
        this.pontos += 1000;
    }

    @Override
    public void pegarFlor() {
        System.out.println("Mario grande pegou flor");
        this.jogo.setMario(new MarioFogo(this.jogo));
    }

    @Override
    public void pegarPena() {
        System.out.println("Mario grande pegou pena");
        this.jogo.setMario(new MarioCapa(this.jogo));
    }

    @Override
    public void levarDano() {
        System.out.println("Mario grande levou dano");
        this.jogo.setMario(new MarioPequeno(this.jogo));
    }
    
}
