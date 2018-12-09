package views;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ZonaSulView extends JFrame {
    private JButton vegetariano;
    private JButton tradicional;
    private JButton adicionalSalada;
    private JButton adicionalPure;
    private JButton adicionalMacarrao;
    private JButton confirmar;
    private JButton continuar;
    private JButton cancelar;
    private JLabel pedido;
    private JLabel info;
    private JLabel prato;
    private JLabel adicional;
    
    public ZonaSulView(){
        super("Restaurante Zona Sul");
        
        this.vegetariano = new JButton("Vegetariano");
        this.tradicional = new JButton("Tradicional");
        this.adicionalSalada = new JButton("Salada");
        this.adicionalPure = new JButton("Pure");
        this.adicionalMacarrao = new JButton("Macarrao");
        this.confirmar = new JButton("Confirmar pedido");
        this.continuar = new JButton("Continuar pedindo");
        this.cancelar = new JButton("Cancelar ultimo pedido");
        this.pedido = new JLabel("Pedido: ");
        this.info = new JLabel("");
        this.prato = new JLabel("Prato:");
        this.adicional = new JLabel("Adicional:");
        
        JPanel pedidoPanel = new JPanel(new GridLayout(1, 2));
        pedidoPanel.add(this.pedido);
        pedidoPanel.add(this.info);
        
        JPanel pratoPanel = new JPanel(new GridLayout(1, 3));
        pratoPanel.add(this.prato);
        pratoPanel.add(this.tradicional);
        pratoPanel.add(this.vegetariano);
        
        JPanel adicionalPanel = new JPanel(new GridLayout(1, 4));
        adicionalPanel.add(this.adicional);
        adicionalPanel.add(this.adicionalSalada);
        adicionalPanel.add(this.adicionalPure);
        adicionalPanel.add(this.adicionalMacarrao);
        
        desabilitarBotoesAdicional();
        
        JPanel finalPanel = new JPanel(new GridLayout(1, 3));
        finalPanel.add(this.confirmar);
        finalPanel.add(this.continuar);
        finalPanel.add(this.cancelar);
        
        JPanel mainPanel = new JPanel(new GridLayout(4, 1));
        mainPanel.add(pedidoPanel);
        mainPanel.add(pratoPanel);
        mainPanel.add(adicionalPanel);
        mainPanel.add(finalPanel);
        
        this.add(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 300);
    }
    
    public void addTradicionalListener(ActionListener listener){
        this.tradicional.addActionListener(listener);
    }
    
    public void addVegetarianoListener(ActionListener listener){
        this.vegetariano.addActionListener(listener);
    }
    
    public void addAdicionalSaladaListener(ActionListener listener){
        this.adicionalSalada.addActionListener(listener);
    }
    
    public void addAdicionalPureListener(ActionListener listener){
        this.adicionalPure.addActionListener(listener);
    }
    
    public void addAdicionalMacarraoListener(ActionListener listener){
        this.adicionalMacarrao.addActionListener(listener);
    }
    
    public void addConfirmarPedidoListener(ActionListener listener){
        this.confirmar.addActionListener(listener);
    }
    
    public void addContinuarPedidoListener(ActionListener listener){
        this.continuar.addActionListener(listener);
    }
    
    public void addCancelarUltimoPedidoListener(ActionListener listener){
        this.cancelar.addActionListener(listener);
    }
    
    public void desabilitarBotoesPrato(){
        this.tradicional.setEnabled(false);
        this.vegetariano.setEnabled(false);
    }
    
    public void habilitarBotoesPrato(){
        this.tradicional.setEnabled(true);
        this.vegetariano.setEnabled(true);
    }
    
    public void desabilitarBotoesAdicional(){
        this.adicionalSalada.setEnabled(false);
        this.adicionalPure.setEnabled(false);
        this.adicionalMacarrao.setEnabled(false);
    }
    
    public void habilitarBotoesAdicional(){
        this.adicionalSalada.setEnabled(true);
        this.adicionalPure.setEnabled(true);
        this.adicionalMacarrao.setEnabled(true);
    }
    
    public void addInfoPedido(String str){
        this.info.setText(str);
    }
    
}
