package truco;

public class Jogador {
    private String nome;
    private int tentos;
    private int quedas;
    private int maosVencidas;
    private Carta cartas[] = new Carta[3];
    private Jogador dupla;
    private InterfaceJogador interfaceJog;
    
    public Jogador (String nome){
        this.nome = nome;
        interfaceJog = new InterfaceJogador(nome);
    }
    
    public void atualizaDupla (Jogador j){
        this.dupla = j;
    }
    
    public void atualizarMao (Carta[] cartasRetiradas){
        this.cartas = cartasRetiradas;
    }
    
    public String getNome (){
        return this.nome;
    }
    
    public void setQuedas (){
        this.quedas++;
        dupla.quedas++;
    }
    
    public int getQuedas (){
        return this.quedas;
    }
    
    public void setTentos (){
        this.tentos++;
        dupla.tentos++;
    }
    
    public void setTentos (int i){
        this.tentos = i;
        dupla.tentos = i;
    }
    
    public int getTentos (){
        return this.tentos;
    }
    
    public void atualizaMaosVencidas (){
        this.maosVencidas++;
        dupla.maosVencidas++;
    }
    
    public void setMaosVencidas (int a){
        this.maosVencidas = a;
        dupla.maosVencidas = a;
    }
    
    public int getMaosVencidas (){
        return this.maosVencidas;
    }
    
    public Carta[] getCartas (){
        return this.cartas;
    }
    
    public Jogador getDupla (){
        return this.dupla;
    }
    
    public InterfaceJogador getInterfaceJog (){
        return this.interfaceJog;
    }
}
