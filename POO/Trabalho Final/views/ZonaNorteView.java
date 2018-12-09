package views;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ZonaNorteView extends JFrame {  
    private JButton cupim;
    private JButton costela;
    private JButton cupim2;
    private JButton costela2;
    private JButton pagamento;
    private JLabel label;
    private JLabel pedidoLabel;
    
    public ZonaNorteView(){
        super("Restaurante Zona Norte");
        
        this.cupim = new JButton("Cupim e Pudim(#1)");
        this.costela = new JButton("Costela e Brigadeiro(#2)");
        this.cupim2 = new JButton("Cupim e Brigadeiro(#3)");
        this.costela2 = new JButton("Costela e Pudim(#4)");
        this.pagamento = new JButton("Realizar pagamento");
        this.label = new JLabel("Escolha os pratos que desejar: ");
        this.pedidoLabel = new JLabel("");
        
        JPanel labelPanel = new JPanel(new GridLayout(1, 2));
        labelPanel.add(this.label);
        labelPanel.add(this.pedidoLabel);
        
        JPanel pratosPanel = new JPanel(new GridLayout(1, 4));
        pratosPanel.add(this.cupim);
        pratosPanel.add(this.costela);
        pratosPanel.add(this.cupim2);
        pratosPanel.add(this.costela2);      
        
        JPanel pagamentoPanel = new JPanel(new GridLayout(1, 1));
        pagamentoPanel.add(this.pagamento);
        
        JPanel mainPanel = new JPanel(new GridLayout(3, 1));
        mainPanel.add(labelPanel);
        mainPanel.add(pratosPanel);
        mainPanel.add(pagamentoPanel);
        
        this.add(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 500);
    }
    
    public void addCupimListener(ActionListener listener){
        this.cupim.addActionListener(listener);
    }
    
    public void addCostelaListener(ActionListener listener){
        this.costela.addActionListener(listener);
    }
    
    public void addCupim2Listener(ActionListener listener){
        this.cupim2.addActionListener(listener);
    }
    
    public void addCostela2Listener(ActionListener listener){
        this.costela2.addActionListener(listener);
    }
    
    public void addPedidoLabel(String s){
        this.pedidoLabel.setText(this.pedidoLabel.getText() + s);
    }
    
    public void addPagamentoListener(ActionListener listener){
        this.pagamento.addActionListener(listener);
    }
    
}
