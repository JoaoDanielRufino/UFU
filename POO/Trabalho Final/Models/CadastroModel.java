package Models;

import Models.Singleton.DBConnection;

public class CadastroModel {
    
    public boolean cadastrarCliente(String nome, String email){
        DBConnection db = DBConnection.getInstance();
        return db.inserirCliente(nome, email);
    }
    
}
