package Models.Strategy;

public class Prato2 extends PratoZonaNorte {
    
    public Prato2(){
        super.setCarne(new Costela());
        super.setSobremesa(new Brigadeiro());
        super.setDescricao("A melhor costela acompanhada de brigadeiro da regiao");
        super.setPrecoTotal();
    }
    
}
