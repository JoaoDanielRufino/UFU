package Models;

import Models.Singleton.DBConnection;

public class LoginModel {
    
    public boolean checarLogin(String nome, String email){
        DBConnection db = DBConnection.getInstance();
        return db.findCliente(nome, email);
    }
    
}
