package Models;

import Models.Memento.Memento;
import java.util.ArrayList;

public abstract class RestauranteFactory {
    protected ArrayList<Prato> pedido;
    protected float precoTotal;
    
    protected RestauranteFactory(){
        this.pedido = new ArrayList<>();
        this.precoTotal = 0;
    }
    
    public void adicionaPedido(Prato p){
        this.pedido.add(p);
        this.precoTotal += p.getPreco();
    }
    
    public float getPrecoTotal(){
        return this.precoTotal;
    }
    
    public Memento criarMemento(){
        return new Memento(this.pedido, this.precoTotal);
    }
    
    public void recuperarMemento(Memento memento){
        this.pedido = memento.getPedido();
        this.precoTotal = memento.getPreco();
        System.out.println("Memento precoTotal: " + this.precoTotal);
        System.out.println("Memento array: " + this.pedido.get(0).getPreco());
    }
    
    public abstract void getInformacaoDoPedido();
}
