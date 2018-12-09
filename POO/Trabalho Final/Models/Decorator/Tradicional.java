package Models.Decorator;

public class Tradicional extends PratoZonaSul {
    
    public Tradicional(){
        this.descricao = "Arroz, feijao e bife";
    }
    
    @Override
    public float getPreco(){
        return 20.0f;
    }
    
}
