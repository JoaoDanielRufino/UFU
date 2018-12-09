package Models.Strategy;

public class Costela implements Carne {

    @Override
    public void prepararPrato() {
        //System.out.println("Preparando a Costela.");
    }

    @Override
    public float getPrecoPrato() {
        return 60.0f;
    }

    @Override
    public String descricao() {
        return "A melhor Costela da cidade!!";
    }
    
}
