package Models.Memento;

import Models.Prato;
import java.util.ArrayList;

public class Memento {
    private ArrayList<Prato> pedido;
    private float preco;
    
    public Memento(ArrayList<Prato> p, float d){
        this.pedido = p;
        this.preco = d;
    }
    
    public ArrayList<Prato> getPedido(){
        return this.pedido;
    }
    
    public float getPreco(){
        return this.preco;
    }
    
}
