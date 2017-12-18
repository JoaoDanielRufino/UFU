package truco;

import java.util.ArrayList;
import java.util.Collections;

public class Baralho {
    private ArrayList<Carta> baralho;
    private ArrayList<Carta> cartasRetiradas;
    
    public Baralho (){
        baralho = new ArrayList<> ();
        cartasRetiradas = new ArrayList<> ();
        
        baralho.add(new Carta("4", "Paus", 14));
        baralho.add(new Carta("7", "Copas", 13));
        baralho.add(new Carta("As", "Espadas", 12));
        baralho.add(new Carta("7", "Ouro", 11));
        baralho.add(new Carta("3", "Paus", 10));
        baralho.add(new Carta("3", "Copas", 10));
        baralho.add(new Carta("3", "Espadas", 10));
        baralho.add(new Carta("3", "Ouro", 10));
        baralho.add(new Carta("2", "Paus", 9));
        baralho.add(new Carta("2", "Copas", 9));
        baralho.add(new Carta("2", "Espadas", 9));
        baralho.add(new Carta("2", "Ouro", 9));
        baralho.add(new Carta("As", "Paus", 8));
        baralho.add(new Carta("As", "Copas", 8));
        baralho.add(new Carta("As", "Ouro", 8));
        baralho.add(new Carta("Rei", "Paus", 7));
        baralho.add(new Carta("Rei", "Copas", 7));
        baralho.add(new Carta("Rei", "Espadas", 7));
        baralho.add(new Carta("Rei", "Ouro", 7));
        baralho.add(new Carta("Valete", "Paus", 6));
        baralho.add(new Carta("Valete", "Copas", 6));
        baralho.add(new Carta("Valete", "Espadas", 6));
        baralho.add(new Carta("Valete", "Ouro", 6));
        baralho.add(new Carta("Dama", "Paus", 5));
        baralho.add(new Carta("Dama", "Copas", 5));
        baralho.add(new Carta("Dama", "Espadas", 5));
        baralho.add(new Carta("Dama", "Ouro", 5));
       /* baralho.add(new Carta("7", "Paus", 4));
        baralho.add(new Carta("7", "Espadas", 4));
        baralho.add(new Carta("6", "Paus", 3));
        baralho.add(new Carta("6", "Copas", 3));
        baralho.add(new Carta("6", "Espadas", 3));
        baralho.add(new Carta("6", "Ouro", 3));
        baralho.add(new Carta("5", "Paus", 2));
        baralho.add(new Carta("5", "Copas", 2));
        baralho.add(new Carta("5", "Espadas", 2));
        baralho.add(new Carta("5", "Ouro", 2));
        baralho.add(new Carta("4", "Copas", 1));
        baralho.add(new Carta("4", "Espadas", 1));
        baralho.add(new Carta("4", "Ouro", 1));*/
    }
    
    public void embaralhar (){
        for(Carta c: cartasRetiradas){ //Retorna as cartas retiradas para o baralho.
            baralho.add(c);
        }      
        Collections.shuffle(baralho); //Funcao para ebaralhar o baralho.
        cartasRetiradas.clear();
    }
    
    public Carta[] retirarCartas (){
        Carta[] cartas = new Carta[3];
        
        for(Carta c: cartasRetiradas){ //Atualizando o baralho com as cartas retiradas.
            baralho.remove(c);
        }
        
        for(int i = 0; i < 3; i++){
            cartas[i] = baralho.get(i); //Retirando 3 cartas do topo do baralho.
            cartasRetiradas.add(cartas[i]);         
        }
        return cartas;
    }
}
