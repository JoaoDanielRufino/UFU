package controllers;

import java.awt.event.ActionEvent;
import views.LocalizacaoView;
import views.ZonaNorteView;
import views.ZonaSulView;

public class LocalizacaoController {
    private LocalizacaoView view;
    
    public LocalizacaoController(LocalizacaoView view){
        this.view = view;
        
        this.view.addZonaNorteListener((ActionEvent e) -> {
            this.view.setVisible(false);
            ZonaNorteView zonaNorteView = new ZonaNorteView();
            ZonaNorteController zonaNorteController = new ZonaNorteController(zonaNorteView);
            zonaNorteView.setVisible(true);
        });
        
        this.view.addZonaSulListener((ActionEvent e) ->{
            this.view.setVisible(false);
            ZonaSulView zonaSulView = new ZonaSulView();
            ZonaSulController zonaSulController = new ZonaSulController(zonaSulView);
            zonaSulView.setVisible(true);
        });
    }
    
}
