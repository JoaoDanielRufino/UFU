package controllers;

import Models.Strategy.PratoZonaNorte;
import Models.RestauranteFactory;
import Models.RestauranteZonaNorte;
import Models.Strategy.*;
import java.awt.event.ActionEvent;
import views.PagamentoView;
import views.ZonaNorteView;

public class ZonaNorteController {
    private ZonaNorteView view;
    private RestauranteFactory restaurante;
    
    public ZonaNorteController(ZonaNorteView view){
        this.view = view;
        this.restaurante = new RestauranteZonaNorte();
        
        this.view.addCupimListener((ActionEvent e) ->{
            addPratoCupim();
            //informacaoPedido();
        });
        
        this.view.addCostelaListener((ActionEvent e) ->{
            addPratoCostela();
            //informacaoPedido();
        });
        
        this.view.addCupim2Listener((ActionEvent e) ->{
            addPratoCupim2();
            //informacaoPedido();
        });
        
        this.view.addCostela2Listener((ActionEvent e) ->{
            addPratoCostela2();
            //informacaoPedido();
        });
        
        this.view.addPagamentoListener((ActionEvent e) ->{
            this.view.setVisible(false);
            PagamentoView p = new PagamentoView();
            p.setVisible(true);
            
            float preco = this.restaurante.getPrecoTotal();
            PagamentoController pagamentoController = new PagamentoController(p, preco);
        });
    }
    
    private void informacaoPedido(){
        this.restaurante.getInformacaoDoPedido();
    }
    
    private void addPratoCupim(){
        PratoZonaNorte p = new Prato1();
        this.view.addPedidoLabel("#1, ");
        //p.prepararPratoDeCarne();
        //p.getDiscricaoSobremesa();
        this.restaurante.adicionaPedido(p);
    }
    
    private void addPratoCostela(){
        PratoZonaNorte p = new Prato2();
        this.view.addPedidoLabel("#2, ");
        //this.view.addPedidoLabel(p.getDiscricaoSobremesa());
        //p.prepararPratoDeCarne();
        this.restaurante.adicionaPedido(p);
    }
    
    private void addPratoCupim2(){
        PratoZonaNorte p = new Prato3();
        this.view.addPedidoLabel("#3, ");
        //p.prepararPratoDeCarne();
        //p.getDiscricaoSobremesa();
        this.restaurante.adicionaPedido(p);
    }
    
    private void addPratoCostela2(){
        PratoZonaNorte p = new Prato4();
        this.view.addPedidoLabel("#4, ");
        //this.view.addPedidoLabel(p.getDiscricaoSobremesa());
        //p.prepararPratoDeCarne();
        this.restaurante.adicionaPedido(p);
    }
    
}
