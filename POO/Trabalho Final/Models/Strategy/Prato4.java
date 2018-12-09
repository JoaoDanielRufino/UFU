package Models.Strategy;

public class Prato4 extends PratoZonaNorte {
 
    public Prato4(){
        super.setCarne(new Costela());
        super.setSobremesa(new Pudim());
        super.setDescricao("A melhor costela acompanhada de pudim da regiao!!");
        super.setPrecoTotal();
    }
    
}
