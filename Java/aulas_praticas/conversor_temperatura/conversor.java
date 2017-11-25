package aleatorio;

import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import javax.swing.*;

public class Conversor {

    public Conversor (){
        JFrame janela = new JFrame("Conversor de temperatura");
        
        JLabel celsius = new JLabel("Celsius");
        
        JTextField temp = new JTextField(15);
        
        JButton kelvin = new JButton("Kelvein");
        JButton fahrenheit = new JButton("Fahrenheit");
        
        kelvin.addActionListener((ActionEvent e) -> {
            int temperatura = Integer.parseInt(temp.getText());
            
            JLabel result = new JLabel("Resultado: ");
            
            JTextField result2 = new JTextField(10);
            result2.setText(temperatura + 273 + "K");
            
            JPanel p = new JPanel();
            p.add(result);
            p.add(result2);
            
            JFrame j = new JFrame("Conversor de temperatura");
            j.getContentPane().add(p);
            j.setSize(300,100);
            j.setVisible(true);
            
            temp.setText("");
        });
        
        fahrenheit.addActionListener((ActionEvent e) -> {
           int temperatura = Integer.parseInt(temp.getText());
           
           JLabel result = new JLabel("Resultado: ");
            
            JTextField result2 = new JTextField(10);
            DecimalFormat df = new DecimalFormat(".##");
            double result3 = 1.8*temperatura + 32;
            result2.setText(df.format(result3) + "");
            
            JPanel p = new JPanel();
            p.add(result);
            p.add(result2);
            
            JFrame j = new JFrame("Conversor de temperatura");
            j.getContentPane().add(p);
            j.setSize(300,100);
            j.setVisible(true);
            
            temp.setText("");
        });
        
        JPanel panel = new JPanel();
        panel.add(celsius);
        panel.add(temp);
        panel.add(kelvin);
        panel.add(fahrenheit);
        
        janela.getContentPane().add(panel);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setSize(300,100);
        janela.setVisible(true);
    }
    
    public static void main(String[] args) {
       new Conversor();
        
    }
    
}
