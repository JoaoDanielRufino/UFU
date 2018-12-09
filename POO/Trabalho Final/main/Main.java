package main;

import controllers.InicioController;
import views.ViewInicial;

/**
 * @João Daniel Rufino 11621BCC033
 * @Pedro Henrique Teixeira 11621BCC025
 */
public class Main {

    public static void main(String[] args) {
        ViewInicial view = new ViewInicial();
        
        InicioController inicioController = new InicioController(view);
        
        view.setVisible(true);
        
    }
    
}
