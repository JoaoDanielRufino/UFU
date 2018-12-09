package Models.Strategy;

public class Cupim implements Carne {

    @Override
    public void prepararPrato() {
        //ystem.out.println("Preparando o Cupim.");
    }

    @Override
    public float getPrecoPrato() {
        return 50.0f;
    }

    @Override
    public String descricao() {
        return "O melhor Cupim da regiao!!";
    }
    
}
