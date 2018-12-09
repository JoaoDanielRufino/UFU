package Models.Strategy;

public class Prato3 extends PratoZonaNorte {
    
    public Prato3(){
        super.setCarne(new Cupim());
        super.setSobremesa(new Brigadeiro());
        super.setDescricao("O melhor cupim acompanhado de pudim da regiao!!");
        super.setPrecoTotal();
    }
    
}
