package loja;

public class MainLoja {
  
    public static void main(String[] args) {
        Loja loja = new Loja();
        
        loja.insereItem("Bola", 20, 25.00f);
        loja.insereItem("Tenis", 10, 100.00f);
        
        loja.solicitarVenda("Bola", 10);
        loja.solicitarVenda("Tenis", 3);
        
        loja.solicitarReposicao("Bola", 5);
        
        loja.estoqueProdutos();
    }
    
}
