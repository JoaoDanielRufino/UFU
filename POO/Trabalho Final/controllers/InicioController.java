package controllers;

import java.awt.event.ActionEvent;
import views.CadastroView;
import views.LoginView;
import views.ViewInicial;

public class InicioController {
    private ViewInicial view;
    
    public InicioController(ViewInicial view){
        this.view = view;
        
        this.view.addLoginListener((ActionEvent e) -> {
            this.view.setVisible(false);
            LoginView loginView = new LoginView();
            LoginController loginController = new LoginController(loginView);
            loginView.setVisible(true);
        });
        
        this.view.addCadrastroListener((ActionEvent e) -> {
            this.view.setVisible(false);
            CadastroView cadastroView = new CadastroView();
            CadastroController cadastroController = new CadastroController(cadastroView);
            cadastroView.setVisible(true);
        });
    }
    
}
