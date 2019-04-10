package algoritmogenetico;

public class Tabuleiro {
    private DNA dna;
    private int fitness;
    
    public Tabuleiro(DNA dna) {
        this.dna = dna;
        this.fitness = 0;
    }
    
    public void calculaFitness() {
        int size = AlgoritmoGenetico.DAMAS_QTD;
        for(int i = 0; i < size-1; i++) {    
            int aux = 1;
            for(int j = i+1; j < size; j++) {
                if(this.dna.geneAt(i) != this.dna.geneAt(j) && this.dna.geneAt(j) != this.dna.geneAt(i)+aux && this.dna.geneAt(j) != this.dna.geneAt(i)-aux) {
                    this.fitness++;
                }
                aux++;
            }         
        }
    }
    
    public DNA getDNA() {
        return this.dna;
    }
    
    public int getFitness() {
        return this.fitness;
    }
    
}
