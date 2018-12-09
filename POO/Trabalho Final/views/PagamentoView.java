package views;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.*;

public class PagamentoView extends JFrame {
    private JLabel precoTotal;
    private JButton cartao;
    private JButton dinheiro;
    
    public PagamentoView(){
        super("Pagamento");
        
        this.precoTotal = new JLabel("");
        this.cartao = new JButton("Cartao");
        this.dinheiro = new JButton("Dinheiro");
        
        JPanel labelPanel = new JPanel(new GridLayout(1, 1));
        labelPanel.add(this.precoTotal);
        
        JPanel infoLabel = new JPanel(new GridLayout(1, 2));
        infoLabel.add(this.dinheiro);
        infoLabel.add(this.cartao);
        
        JPanel mainPanel = new JPanel(new GridLayout(2, 1));
        mainPanel.add(labelPanel);
        mainPanel.add(infoLabel);
        
        this.add(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 300);
    }
    
    public void setPreco(float p){
        this.precoTotal.setText("Preco total: " + Float.toString(p));
    }
    
    public void addCartaoListener(ActionListener listener){
        this.cartao.addActionListener(listener);
    }
    
    public void addDinheiroListener(ActionListener listener){
        this.dinheiro.addActionListener(listener);
    }
    
}
