package truco;

import javax.swing.*;
import java.awt.*;

public class InterfaceJogador extends JPanel{
    private JPanel painelJog;
    private JButton cartas[], truco, aceitar, correr;
    private JLabel quedas;
    private JLabel tentos;
    
    public InterfaceJogador (String nome){
        painelJog = new JPanel(new GridLayout(4, 1));
        cartas = new JButton[3];
        
        JPanel painelCarta = new JPanel(new GridLayout(1, 3));
        JPanel painelBotao = new JPanel(new GridLayout(1, 3));
        JPanel painelPontos = new JPanel(new GridLayout(1, 2, 5, 5));
        
        JLabel texto1 = new JLabel(nome);
        texto1.setHorizontalAlignment(JLabel.CENTER);
        
        quedas = new JLabel("Quedas: ");
        tentos = new JLabel("Tentos: ");
        
        cartas[0] = new JButton("");
        cartas[0].setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/back.gif")));
        cartas[1] = new JButton("");
        cartas[1].setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/back.gif")));
        cartas[2] = new JButton("");
        cartas[2].setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/back.gif")));
        
        truco = new JButton("Truco!!!");
        aceitar = new JButton("Aceitar");
        correr = new JButton("Correr");
        
        for(int i = 0; i < 3; i++)
            painelCarta.add(cartas[i]);
        painelBotao.add(truco);
        painelBotao.add(aceitar);
        painelBotao.add(correr);
                
        painelPontos.add(quedas);
        painelPontos.add(tentos);
        
        painelJog.add(texto1);
        painelJog.add(painelCarta);
        painelJog.add(painelBotao);
        painelJog.add(painelPontos);
        
    }

    public JPanel getJPanel () {
        return painelJog;
    }
    
    public JButton getCarta (int index){
        return this.cartas[index];
    }
    
    public JButton getTruco (){
        return this.truco;
    }
    
    public JButton getAceitar (){
        return this.aceitar;
    }
    
    public JButton getCorrer (){
        return this.correr;
    }
    
    public void setCarta (String carta, int index){
        cartas[index].setIcon(new javax.swing.ImageIcon(getClass().getResource(carta)));
    }
    
    public void desabilitaCarta (int index){
        cartas[index].setEnabled(false);
    }
    
    public void habilitaCarta (int index){
        cartas[index].setEnabled(true);
    }
    
    public void setQuedas (int qd){
        quedas.setText("Quedas: " + qd);
    }
    
    public void setTentos (int t){
        tentos.setText("Tentos: " + t);
    }
}
