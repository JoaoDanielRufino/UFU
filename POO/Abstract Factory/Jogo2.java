package jogo2;

/**
 *
 * @Pedro Henrique Faria Teixeira 11621BCC025
 * @João Daniel Aquino Rufino 11621BCC033
 */
public class Jogo2 {
    private LevelFactory levelFactory;
    private Level level;
    
    public Jogo2(String s){
        if(s.equals("facil")){
            this.levelFactory = new Iniciante();
            this.level = new LevelFacil(this.levelFactory);
        }
        else if(s.equals("medio")){
            this.levelFactory = new Medio();
            this.level = new LevelMedio(this.levelFactory);
        }
        else if(s.equals("dificil")){
            this.levelFactory = new Dificil();
            this.level = new LevelDificil(this.levelFactory);
        }
    }
    
    public void inicia(){
        this.level.iniciaJogo();
    }
    
    public static void main(String[] args) {
        Jogo2 j = new Jogo2("dificil");
        j.inicia();
    }
    
}
