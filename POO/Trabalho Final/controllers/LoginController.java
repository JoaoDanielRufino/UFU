package controllers;

import Models.LoginModel;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import views.LocalizacaoView;
import views.LoginView;

public class LoginController {
    private LoginView view;
    
    public LoginController(LoginView view){
        this.view = view;
        
        this.view.addCadastrarListener((ActionEvent e) -> {
            String nome = this.view.getNome();
            String email = this.view.getEmail();
            LoginModel loginModel = new LoginModel();
            if(loginModel.checarLogin(nome, email)){
                this.view.setVisible(false);
                LocalizacaoView localizacaoView = new LocalizacaoView();
                LocalizacaoController localizacaoController = new LocalizacaoController(localizacaoView);
                localizacaoView.setVisible(true);
            }
            else{
                JOptionPane.showMessageDialog(null, "Erro ao informar dados de login");
            }
        });
    }
}
