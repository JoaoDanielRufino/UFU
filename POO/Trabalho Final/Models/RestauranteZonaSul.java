package Models;

public class RestauranteZonaSul extends RestauranteFactory {
   
    public RestauranteZonaSul(){
        super();
    }
    
    @Override
    public void getInformacaoDoPedido() {
        int i = 1;
        for(Prato p: this.pedido){
            System.out.print("Prato " + i + ": ");
            System.out.println(p.getDescricao() + p.getPreco());
            i++;
        }
    }
    
}
