package algoritmogenetico;

import java.util.ArrayList;
import java.util.Random;

public class Populacao {
    private Tabuleiro tabuleiros[];
    private ArrayList<Tabuleiro> matingPool;
    private int geracao;
    
    public Populacao() {
        int popSize = AlgoritmoGenetico.POP_QTD;
        this.tabuleiros = new Tabuleiro[popSize];
        for(int i = 0; i < popSize; i++) {
            this.tabuleiros[i] = new Tabuleiro(new DNA());
        }
        this.matingPool = new ArrayList<>();       
        this.geracao = 0;
    }
    
    public void calculaFitness() {
        for(Tabuleiro t : this.tabuleiros) {
            t.calculaFitness();
        }
    }
    
    public void selecaoNatural() {
        this.matingPool.clear();
        for(Tabuleiro t : this.tabuleiros) {
            for(int i = 0; i < t.getFitness(); i++) {
                this.matingPool.add(t);
            }
        }
    }
    
    public void novaGeracao() {
        Tabuleiro novaPopulacao[] = new Tabuleiro[AlgoritmoGenetico.POP_QTD];
        
        Random rand = new Random();
        for(int i = 0; i < this.tabuleiros.length; i++) {
            int a = rand.nextInt(this.matingPool.size());
            int b = rand.nextInt(this.matingPool.size());
            
            DNA paiA = this.matingPool.get(a).getDNA();
            DNA paiB = this.matingPool.get(b).getDNA();
            
            DNA filho = paiA.corssover(paiB);
            filho.mutate();
            
            novaPopulacao[i] = new Tabuleiro(filho);
        }
        
        this.tabuleiros = novaPopulacao;
        this.geracao++;
    }
    
    public void printaMelhor() {
        int maxFitness = 0;
        Tabuleiro best = null;
        
        for(Tabuleiro t : this.tabuleiros) {
            int fitness = t.getFitness();
            if(maxFitness < fitness) {
                maxFitness = fitness;
                best = t;
            }
        }
               
        if(best != null) {
            best.getDNA().print();
            System.out.println("\nFitness: " + best.getFitness());
            System.out.println("Geracao: " + this.geracao);
        }      
    }
}
