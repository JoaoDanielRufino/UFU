package Models.Chain;

public abstract class TrocoChain {
    private TrocoChain antecessor;
    private String troco = "";
    
    public void setAntecessor(Nota nota){
        if(this.antecessor == null)
            this.antecessor = nota;
        else
            this.antecessor.setAntecessor(nota);
    }
    
    public String calculaTroco(TrocoChain nota, int total){
        if(total < nota.getValor()){
            calculaTroco(nota.antecessor, total);
            return this.troco;
        }
        
        int x = total / nota.getValor();
        int t = total % nota.getValor();
        this.troco += " " + x + " : " + nota.getValor() + "\n";
        //System.out.println(" " + x + " : " + nota.getValor());
        
        if(t > 0){
            calculaTroco(nota.antecessor, t);
        }
        return this.troco;
    }
    
    public abstract int getValor();
    
}
