package Models.Strategy;

public class Pudim implements Sobremesa {

    @Override
    public String descricao() {
        return "O melhor pudim da regiao!!";
    }

    @Override
    public void prepararPrato() {
        //System.out.println("Preparando o pudim");
    }

    @Override
    public float getPrecoPrato() {
        return 10.0f;
    }
    
}
