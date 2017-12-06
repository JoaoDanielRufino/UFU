package testefila;

public class TesteFila {

    public void filaNaoTratada (Fila f){
        for(int i = 0; i <= 10; i++){
            f.enfileirar(i);
            System.out.println(f.desenfileirar());
        }
    }
    
    public void trataFilaCheia (Fila f, int elem){
        try{
            f.enfileirar(elem);
        }
        catch(FilaCheiaException e){
            System.out.println("Fila cheia");
        }
    }
    
    public void trataFilaVazia (Fila f){
        try{
            System.out.println(f.desenfileirar());
        }
        catch(FilaVaziaException e){
            System.out.println("Fila vazia!!");
        }
    }
    
    public void trataFilaCheiaEVazia (Fila f){
        try{
            for(int i = 0; i <= 10; i++){
                f.enfileirar(i);
                System.out.println(f.desenfileirar());
            }
        }
        catch(FilaCheiaException e){
            System.out.println("Fila cheia");
        }
        catch(FilaVaziaException e){
            System.out.println("Fila vazia!!");
        }
    }
    
    public static void main(String[] args) {
        TesteFila teste = new TesteFila();
        Fila f = new Fila(10);
        
        //teste.filaNaoTratada(f);
        /*for(int i = 0; i <= 10; i++){
            teste.trataFilaCheia(f, i);
        }*/

        //teste.trataFilaVazia(f);
        teste.trataFilaCheiaEVazia(f);
        
    }
    
}
