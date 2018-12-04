package composite;

/**
 *
 * @Joao Daniel Rufino 11621BCC033
 * @Pedro Henrique Teixeira 11621BCC025
 */
public class Composite {

    public static void main(String[] args) {
        ExpressaoBooleana e = new AND(new Operando(true), new OR(new Operando(false), new Operando(false)));
        System.out.println(e.operacao());
        
        ExpressaoBooleana e2 = new XOR(new AND(new NOT(new Operando(false)), new Operando(true)), new OR(new Operando(false), new Operando(true)));
        System.out.println(e2.operacao());
    }
    
}
