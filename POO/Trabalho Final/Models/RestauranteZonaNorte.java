package Models;

public class RestauranteZonaNorte extends RestauranteFactory {

    public RestauranteZonaNorte(){
        super();
    }

    @Override
    public void getInformacaoDoPedido() {
        for(Prato p: this.pedido){
            System.out.println(p.getDescricao());
            System.out.println(p.getPreco());
        }
    }
    
}
