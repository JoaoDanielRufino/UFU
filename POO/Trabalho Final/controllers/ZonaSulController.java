package controllers;

import Models.Decorator.*;
import Models.Memento.*;
import Models.RestauranteFactory;
import Models.RestauranteZonaSul;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import views.PagamentoView;
import views.ZonaSulView;

public class ZonaSulController {
    private ZonaSulView view;
    private RestauranteFactory restaurante;
    private PratoZonaSul prato;
    private Caretaker caretaker;
    
    public ZonaSulController(ZonaSulView view){
        this.view = view;
        this.restaurante = new RestauranteZonaSul();
        this.caretaker = new Caretaker();
        this.prato = null;
        
        this.view.addTradicionalListener((ActionEvent e) -> {
            this.view.desabilitarBotoesPrato();
            this.view.habilitarBotoesAdicional();
            addTradicional();
        });
        
        this.view.addVegetarianoListener((ActionEvent e) -> {
            this.view.desabilitarBotoesPrato();
            this.view.habilitarBotoesAdicional();
            addVegetariano();
        });
        
        this.view.addAdicionalSaladaListener((ActionEvent e) -> {
            addSalada();
        });
        
        this.view.addAdicionalPureListener((ActionEvent e) -> {
            addPure();
        });
        
        this.view.addAdicionalMacarraoListener((ActionEvent e) -> {
            addMacarrao();
        });
        
        this.view.addConfirmarPedidoListener((ActionEvent e) -> {
            if(this.prato != null)
                this.restaurante.adicionaPedido(this.prato);
            
            this.view.setVisible(false);
            PagamentoView pagamentoView = new PagamentoView();
            pagamentoView.setVisible(true);
            
            float p = this.restaurante.getPrecoTotal();
            PagamentoController pc = new PagamentoController(pagamentoView, p);
        });
        
        this.view.addContinuarPedidoListener((ActionEvent e) -> {      
            if(this.restaurante.getPrecoTotal() == 0){
                this.restaurante.adicionaPedido(this.prato);
                this.caretaker.setMemento(this.restaurante.criarMemento());
            }
            else{
                this.caretaker.setMemento(this.restaurante.criarMemento());
                this.restaurante.adicionaPedido(this.prato);
            }
            
            
            this.prato = null;
            this.view.addInfoPedido("");
            this.view.habilitarBotoesPrato();
            this.view.desabilitarBotoesAdicional();
        });
        
        this.view.addCancelarUltimoPedidoListener((ActionEvent e) -> {
            this.prato = null;
            this.restaurante.recuperarMemento(this.caretaker.getMemento());
            JOptionPane.showMessageDialog(null, "Pedido removido com sucesso");
        });
    }
    
    private void addTradicional(){
        this.prato = new Tradicional();
        this.view.addInfoPedido(this.prato.getDescricao());
        System.out.println(this.prato.getDescricao());
        System.out.println(this.prato.getPreco());
    }
    
    private void addVegetariano(){
        this.prato = new Vegetariano();
        this.view.addInfoPedido(this.prato.getDescricao());
        System.out.println(this.prato.getDescricao());
        System.out.println(this.prato.getPreco());
    }
    
    private void addSalada(){
        this.prato = new Salada(this.prato);
        this.view.addInfoPedido(this.prato.getDescricao());
        System.out.println(this.prato.getDescricao());
        System.out.println(this.prato.getPreco());
    }
    
    private void addPure(){
        this.prato = new Pure(this.prato);
        this.view.addInfoPedido(this.prato.getDescricao());
        System.out.println(this.prato.getDescricao());
        System.out.println(this.prato.getPreco());
    }
    
    private void addMacarrao(){
        this.prato = new Macarrao(this.prato);
        this.view.addInfoPedido(this.prato.getDescricao());
        System.out.println(this.prato.getDescricao());
        System.out.println(this.prato.getPreco());
    }
    
}
