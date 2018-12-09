package Models.Strategy;

public class Brigadeiro implements Sobremesa {

    @Override
    public String descricao() {
        return "O melhor brigadeiro!!";
    }

    @Override
    public void prepararPrato() {
        
    }

    @Override
    public float getPrecoPrato() {
        return 5.0f;
    }
    
}
