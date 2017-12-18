package truco;

import java.util.ArrayList;

public class Jogo {
    private Baralho baralho;
    private ArrayList<Jogador> jogadores;
    private int jogadorDaVez;
    private int maosJogadas;
    private int cartasJogadas;
    private int posPrimeiraMaoVencida;
    private boolean cangado;
    private boolean pediuTruco;
    private boolean trucado;
    private int valorTrucado;
    
    public Jogo (){
        baralho = new Baralho();
        jogadores = new ArrayList<> ();
        posPrimeiraMaoVencida = -1;
    }
    
    public void insereJogador (String nome){
        jogadores.add(new Jogador(nome));
    }
    
    public void criarDupla (Jogador j1, Jogador j2){
        j1.atualizaDupla(j2);
        j2.atualizaDupla(j1);
    }
    
    public void distribuirCartas (){
        baralho.embaralhar();
        for(Jogador j: jogadores){
            j.atualizarMao(baralho.retirarCartas());
        }
    }
    
    public Jogador vencedorDaRodada (){
        for(Jogador j: jogadores){
            if(j.getMaosVencidas() == 2)
                return j;
        }
        return null;
    }
    
    public int cartaVencedora (Carta a, Carta b){
        if(a.getPrioridade() > b.getPrioridade())
            return 0;
        else if(a.getPrioridade() < b.getPrioridade())
            return 1;
        return -1;
    }
    
    public ArrayList getJogadores (){
        return this.jogadores;
    }
    
    public Jogador getJogador (int index){
        return this.jogadores.get(index);
    }
    
    public int numJogadores (){
        return this.jogadores.size();
    }
    
    public void atualizaJogadorDaVez (){
        if((this.jogadorDaVez + 1) >= numJogadores())
            this.jogadorDaVez = 0;
        else
            this.jogadorDaVez++;
    }
    
    public void setJogadorDaVez (int i){
        this.jogadorDaVez = i;
    }
    
    public int getJogadorDaVez (){
        return this.jogadorDaVez;
    }
    
    public void atualizaMaosJogadas (){
        if((this.maosJogadas + 1) > 3)
            this.maosJogadas = 0;
        else
            this.maosJogadas++;
    }
    
    public int getMaosJogadas (){
        return this.maosJogadas;
    }
    
    public void setCartasJogadas (int i){
        this.cartasJogadas = i;
    }
    
    public void atualizaCartasJogadas (){
        if((this.cartasJogadas + 1) > 4)
            this.cartasJogadas = 1;
        else
            this.cartasJogadas++;
    }
    
    public int getCartasJogadas (){
        return this.cartasJogadas;
    }
    
    public void setPosPrimeiraMaoVencida (int pos){
        this.posPrimeiraMaoVencida = pos;
    }
    
    public int getPosPrimeiraMaoVencida (){
        return this.posPrimeiraMaoVencida;
    }
    
    public void setCangado (boolean c){
        this.cangado = c;
    }
    
    public boolean getCangado (){
        return this.cangado;
    }
    
    public void setPediuTruco (boolean a){
        this.pediuTruco = a;
    }
    
    public boolean getPediuTruco (){
        return this.pediuTruco;
    }
    
    public void setTrucado (boolean a){
        this.trucado = a;
    }
    
    public boolean getTrucado (){
        return this.trucado;
    }
    
    public void setValorTrucado (int v){
        this.valorTrucado = v;
    }
    
    public int getValorTrucado (){
        return this.valorTrucado;
    }
}
