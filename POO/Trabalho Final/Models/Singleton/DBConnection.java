package Models.Singleton;

import java.sql.*;

public class DBConnection {
    private static DBConnection db;
    private static Connection con;
    
    private DBConnection(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/poo2", "root", "");
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    public synchronized static DBConnection getInstance(){
        if(db == null){    
            db = new DBConnection();
        }
        return db;
    }
    
    public boolean inserirCliente(String nome, String email){
        try{
            PreparedStatement pst = con.prepareStatement("INSERT INTO clientes(nome,email) VALUES(?,?)");
            pst.setString(1, nome);
            pst.setString(2, email);
            pst.executeUpdate();
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }
    
    public boolean findCliente(String nome, String email){
        try{
            PreparedStatement pst = con.prepareStatement("SELECT * FROM clientes WHERE nome = ? AND email = ?");
            pst.setString(1, nome);
            pst.setString(2, email);
            ResultSet rs = pst.executeQuery();
            return rs.next();
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }
    
}
