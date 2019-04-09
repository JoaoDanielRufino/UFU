package algoritmogenetico;

import java.util.Random;

public class DNA {
    // A posicao do array sera a coluna da rainha e o valor sera a linha;
    
    private int genes[];
    
    public DNA() {
        this.genes = randomize();
    }
    
    private DNA(int genes[]) {
        this.genes = genes;
    }
    
    private int[] randomize() {
        int g[] = new int[AlgoritmoGenetico.DAMAS_QTD];
        
        Random rand = new Random();
        for(int i = 0; i < g.length; i++) {
            g[i] = rand.nextInt(g.length);
        }
        
        return g;
    }
    
    public DNA corssover(DNA paiB) {
        int mid = this.genes.length / 2;
        int geneFilho[] = new int[this.genes.length];
        
        for(int i = 0; i < this.genes.length; i++) {
            geneFilho[i] = mid < i ? this.genes[i] : paiB.geneAt(i);
        }
        
        return new DNA(geneFilho);
    }
    
    public void mutate() {
        float mutationRate = 0.01f;
        
        Random rand = new Random();
        for(int i = 0; i < this.genes.length; i++) {
            if(mutationRate > rand.nextFloat()) {
                this.genes[i] = rand.nextInt(this.genes.length);
            }
        }       
    }
    
    public void print() {
        for(int i = 0; i < this.genes.length; i++) {
            System.out.print(this.genes[i] + " ");
        }
    }
    
    // Retornado o valor da linha
    public int geneAt(int pos) {
        return this.genes[pos];
    }
}
