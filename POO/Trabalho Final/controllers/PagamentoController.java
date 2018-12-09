package controllers;

import Models.Chain.ChainNotas;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import views.PagamentoView;

public class PagamentoController {
    private PagamentoView view;
    private float preco;
    
    public PagamentoController(PagamentoView view, float preco){
        this.view = view;
        this.preco = preco;
        
        this.view.setPreco(this.preco);
        
        this.view.addCartaoListener((ActionEvent e) ->{
            this.view.setVisible(false);
            JOptionPane.showMessageDialog(null, "Obrigado por comparecer ao restaurante!!");
            System.exit(0);
        });
        
        this.view.addDinheiroListener((ActionEvent e) -> {
            int valor = Integer.parseInt(JOptionPane.showInputDialog("Digite o valor em dinheiro:"));
            ChainNotas troco = new ChainNotas();
            
            int aux = (int) (valor - preco);
            if(aux > 0){
                String res = troco.calcularTroco(aux);
                this.view.setVisible(false);
                JOptionPane.showMessageDialog(null, "Troco:\n" + res);
                JOptionPane.showMessageDialog(null, "Obrigado por comparecer ao restaurante!!");
                System.exit(0);
            }
            else if(aux == 0){
                this.view.setVisible(false);
                JOptionPane.showMessageDialog(null, "Obrigado por comparecer ao restaurante!!");
                System.exit(0);
            }
            else
                JOptionPane.showMessageDialog(null, "O valor informado eh inferior a conta");
        });
    }
    
}
