package Models.Decorator;

public class Vegetariano extends PratoZonaSul {
    
    public Vegetariano(){
        this.descricao = "Salada, kibe vegano e arroz integral";
    }
    
    @Override
    public float getPreco(){
        return 25.0f;
    }
    
}
