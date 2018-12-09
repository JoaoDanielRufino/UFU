package views;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.*;

public class CadastroView extends JFrame {
    private JLabel nomeLabel;
    private JLabel emailLabel;
    private JTextField nomeText;
    private JTextField emailText;
    private JButton confirmar;
    
    public CadastroView(){
        super("Cadastro");
        
        this.nomeLabel = new JLabel("Digite o nome: ");
        this.emailLabel = new JLabel("Digite o email: ");
        this.nomeText = new JTextField();
        this.emailText = new JTextField();
        this.confirmar = new JButton("Confirmar cadastro");
        
        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(this.nomeLabel);
        panel.add(this.nomeText);
        panel.add(this.emailLabel);
        panel.add(this.emailText);
        panel.add(this.confirmar);
        
        this.add(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 250);
    }
    
    public void addCadastrarListener(ActionListener listener){
        this.confirmar.addActionListener(listener);
    }
    
    public String getNome(){
        return this.nomeText.getText();
    }
    
    public String getEmail(){
        return this.emailText.getText();
    }
    
}
