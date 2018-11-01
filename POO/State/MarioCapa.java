package state;

public class MarioCapa extends Mario {

    public MarioCapa(Jogo jogo) {
        super(jogo);
    }

    @Override
    public void pegarCogumelo() {
        System.out.println("Mario capa pegou cogumelo");
        this.pontos += 1000;
    }

    @Override
    public void pegarFlor() {
        System.out.println("Mario capa pegou flor");
        this.jogo.setMario(new MarioFogo(this.jogo));
    }

    @Override
    public void pegarPena() {
        System.out.println("Mario capa pegou pena");
        this.pontos += 1000;
    }

    @Override
    public void levarDano() {
        System.out.println("Mario levou dano");
        this.jogo.setMario(new MarioGrande(this.jogo));
    }
    
}
