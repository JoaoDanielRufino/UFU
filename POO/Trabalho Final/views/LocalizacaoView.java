package views;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.*;

public class LocalizacaoView extends JFrame {
    private JButton zonaNorte;
    private JButton zonaSul;
    private JLabel label;
    
    public LocalizacaoView(){
        super("Restaurante");
        
        this.zonaNorte = new JButton("Zona Norte");
        this.zonaSul = new JButton("Zona Sul");
        this.label = new JLabel("Escolha sua localizacao:");
        
        JPanel labelPanel = new JPanel(new GridLayout(1, 1));
        labelPanel.add(this.label);
        
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        buttonPanel.add(this.zonaNorte);
        buttonPanel.add(this.zonaSul);
        
        JPanel mainPanel = new JPanel(new GridLayout(2, 1));
        mainPanel.add(labelPanel);
        mainPanel.add(buttonPanel);
        
        this.add(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 200);
    }
    
    public void addZonaNorteListener(ActionListener listener){
        this.zonaNorte.addActionListener(listener);
    }
    
    public void addZonaSulListener(ActionListener listener){
        this.zonaSul.addActionListener(listener);
    }
    
}
