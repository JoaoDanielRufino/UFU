package algoritmogenetico;

public class AlgoritmoGenetico {
    public static final int DAMAS_QTD = 8;
    public static final int POP_QTD = 40;
    
    public static void main(String[] args) {
        Populacao p = new Populacao();
        
        while(!p.getEnd()) {
            p.calculaFitness();
            p.printaMelhor();
            p.selecaoNatural();
            p.novaGeracao();
        }
        
    }   
    
}
