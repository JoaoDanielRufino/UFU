package Models.Strategy;

public class Prato1 extends PratoZonaNorte {
    
    public Prato1(){
        super.setCarne(new Cupim());
        super.setSobremesa(new Pudim());
        super.setDescricao("O melhor cupim acompanhado de pudim da regiao!!");
        super.setPrecoTotal();
    }
    
}
