package calculadora;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Calculadora {
    
    public Calculadora (){
        JFrame janela = new JFrame("Calculadora");
        janela.setPreferredSize(new Dimension(300,300));
        
        JLabel visor = new JLabel("0");
        visor.setHorizontalAlignment(JLabel.RIGHT);
        visor.setPreferredSize(new Dimension(50,50));
        visor.setFont(new Font("Serif", Font.PLAIN, 30));
        
        JButton clear = new JButton("Clear");
        
        JPanel painelNumeros = new JPanel(new GridLayout(4, 3));
        painelNumeros.setPreferredSize(new Dimension(250,300));
        JPanel painelOperacoes = new JPanel(new GridLayout(5, 1));
        painelOperacoes.setPreferredSize(new Dimension(70,300));
        
        String num[] = {"7", "8", "9", "4", "5", "6", "1", "2", "3", "", "0", ""};
        String op[] = {"+", "-", "*", "/", "="};
        
        ActionListener trataNumero = new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JButton botaoClicado = (JButton) e.getSource();
                if(visor.getText().equals("0"))
                    visor.setText("");
                visor.setText(visor.getText() + botaoClicado.getText());
            }          
        };
        
        ActionListener trataOperacao = new ActionListener(){
          public void actionPerformed(ActionEvent e){
              JButton botaoClicado = (JButton) e.getSource();
              if(!"=".equals(botaoClicado.getText())){
                  visor.setText(visor.getText() + botaoClicado.getText());
              }
              else{
                  String conta = visor.getText();
                  String esq = "", dir = "", op = "";
                  
                  for(int i = 0; i < conta.length(); i++){
                      if(op == "" && conta.charAt(i) >= '0' && conta.charAt(i) <= '9')
                          esq += conta.charAt(i);                     
                      else if(op != "" && conta.charAt(i) >= '0' && conta.charAt(i) <= '9')
                          dir += conta.charAt(i);                     
                      else
                          op += conta.charAt(i);                   
                  }
                 
                  switch(op){
                      case "+": visor.setText(Integer.parseInt(esq) + Integer.parseInt(dir) + "");
                                break;
                      case "-": visor.setText(Integer.parseInt(esq) - Integer.parseInt(dir) + "");
                                break;
                      case "*": visor.setText(Integer.parseInt(esq) * Integer.parseInt(dir) + "");
                                break;
                      case "/": visor.setText(Double.parseDouble(esq) / Double.parseDouble(dir) + "");
                                break;
                  }
              }
          }  
        };
        
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               visor.setText("0");
            }
        });
        
        for(int i = 0; i < num.length; i++){
            ((JButton) painelNumeros.add(new JButton(num[i]))).addActionListener(trataNumero);
        }
       
        for(int i = 0; i < op.length; i++){
            ((JButton) painelOperacoes.add(new JButton(op[i]))).addActionListener(trataOperacao);
        }
        
        janela.add(visor, BorderLayout.NORTH);
        janela.add(painelNumeros, BorderLayout.CENTER);
        janela.add(painelOperacoes, BorderLayout.EAST);
        janela.add(clear, BorderLayout.SOUTH);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.pack();
        janela.setVisible(true);
    }
    
    public static void main(String[] args) {
        new Calculadora();
    }
    
}
