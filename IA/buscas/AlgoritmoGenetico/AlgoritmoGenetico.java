package algoritmogenetico;

public class AlgoritmoGenetico {
    public static final int DAMAS_QTD = 8;
    public static final int POP_QTD = 5;
    
    public static void main(String[] args) {
        Populacao p = new Populacao();
        
        for(int i = 0; i < 30; i++) {
            p.calculaFitness();
            p.printaMelhor();
            p.selecaoNatural();
            p.novaGeracao();
        }
        
    }
    
}
