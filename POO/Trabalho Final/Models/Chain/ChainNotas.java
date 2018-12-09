package Models.Chain;

public class ChainNotas {
    private Nota[] nota;
    
    public ChainNotas(){
        this.nota = new Nota[7];
        
        this.nota[0] = new Nota(100);
        this.nota[1] = new Nota(50);
        this.nota[2] = new Nota(20);
        this.nota[3] = new Nota(10);
        this.nota[4] = new Nota(5);
        this.nota[5] = new Nota(2);
        this.nota[6] = new Nota(1);
        
        for(int i = 0; i < this.nota.length-1; i++){
            this.nota[i].setAntecessor(this.nota[i+1]);
        }
    }
    
    public String calcularTroco(int valor){
        return this.nota[0].calculaTroco(this.nota[0], valor);
    }
    
}
