package testefila;

public class Fila {
    private int fila[];
    private int comeco;
    private int fim;
    private int tamanho;
    
    Fila (int tamanho){
        this.fila = new int[tamanho];
        this.tamanho = tamanho;
    }
    
    public void enfileirar (int elem) throws FilaCheiaException{
        if(fim >= tamanho)
            throw new FilaCheiaException("Fila cheia!!");
        fila[fim++] = elem;
    }
    
    public int desenfileirar () throws FilaVaziaException{
        if(comeco >= fim)
            throw new FilaVaziaException("Fila vazia!!");
        return fila[comeco++];
    }
}
