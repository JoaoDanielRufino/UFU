
package jogo_2;

import java.util.ArrayList;

public class Personagem implements Subject{
    
    private String name;
    private String coord;  
    private ArrayList<Observer> monstros;
    
    public Personagem(String name) {
        this.name = name;
        this.monstros = new ArrayList<>();
    }
    
    
    public void atacar(){
        System.out.println("Estou atacando.");
    }
    
     public void correr(){
        System.out.println("Estou correndo.");
    }

      public void pulando(){
        System.out.println("Estou pulando.");
    }
     
    
      public void position(){
          System.out.println("Estou na posicao: " + this.coord);
      }

    @Override
    public void atualizapos(String coord) {
        this.coord = coord;
        atualizaMonstro();
    }

    @Override
    public void registraMonstro(Observer o) {
        this.monstros.add(o);
    }

    @Override
    public void atualizaMonstro() {
        for(Observer o: this.monstros){
            o.atualiza(this.coord);
        }
    }
}
