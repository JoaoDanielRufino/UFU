package views;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ViewInicial extends JFrame {
    private JButton login;
    private JButton cadastroCliente;
    private JLabel label;
    
    public ViewInicial(){
        super("Restaurante");
        
        this.login = new JButton("Login");
        this.cadastroCliente = new JButton("Cadastrar");
        this.label = new JLabel("Escolha uma opcao:");
        
        JPanel info = new JPanel(new GridLayout(1, 2));
        info.add(this.login);
        info.add(this.cadastroCliente);
        
        JPanel mainPanel = new JPanel();
        mainPanel.add(this.label);
        mainPanel.add(info);
        
        this.add(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 100);
    }
    
    public void addLoginListener(ActionListener listener){
        this.login.addActionListener(listener);
    }
    
    public void addCadrastroListener(ActionListener listener){
        this.cadastroCliente.addActionListener(listener);
    }
}
