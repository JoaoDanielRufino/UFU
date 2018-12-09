package controllers;

import Models.CadastroModel;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import views.CadastroView;
import views.LocalizacaoView;

public class CadastroController {
    private CadastroView view;
    
    public CadastroController(CadastroView view){
        this.view = view;
        
        this.view.addCadastrarListener((ActionEvent e) -> {
            String nome = this.view.getNome();
            String email = this.view.getEmail();
            CadastroModel cadastroModel = new CadastroModel();
            if(cadastroModel.cadastrarCliente(nome, email)){
                this.view.setVisible(false);
                LocalizacaoView localizacaoView = new LocalizacaoView();
                LocalizacaoController localizacaoController = new LocalizacaoController(localizacaoView);
                localizacaoView.setVisible(true);
            }
            else{
                JOptionPane.showMessageDialog(null, "Erro ao tentar cadastrar usuario");
            }
        });
    }
    
}
