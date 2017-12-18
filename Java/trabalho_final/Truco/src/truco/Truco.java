/*
Made by: Joao Daniel A. Rufino
         Pedro Henrique Faria Teixeira
*/

package truco;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Truco {

    private Jogo jogo;
    private boolean cartaNaMesa; //indica se a mesa possui uma carta
    private int posCartaVencedora[]; //Indica o jogador e a carta vencedora
    private JButton mesaCarta[];
    private JLabel infoJog1, infoJog2; //Mosra as maos vencidas pelos jogadores
    private InterfaceJogador interfaceJogador[];

    public Truco() { //Construtor do jogo Truco
        jogo = new Jogo();
        jogo.insereJogador("Joao");
        jogo.insereJogador("Ze");
        jogo.insereJogador("Pedro");
        jogo.insereJogador("Lucas");
        jogo.criarDupla(jogo.getJogador(0), jogo.getJogador(2));
        jogo.criarDupla(jogo.getJogador(1), jogo.getJogador(3));

        JFrame janela = new JFrame("Truco");
        janela.setPreferredSize(new Dimension(850, 700));

        infoJog1 = new JLabel(jogo.getJogador(0).getNome() + " e " + jogo.getJogador(0).getDupla().getNome() + ": ");
        infoJog2 = new JLabel(jogo.getJogador(1).getNome() + " e " + jogo.getJogador(1).getDupla().getNome() + ": ");
        JPanel painel = new JPanel(new GridLayout(3, 3));
        JPanel mesa = new JPanel(new GridLayout(3, 3, 0, 10));
        JPanel infoJogadores = new JPanel(new GridLayout(3, 0));

        infoJogadores.add(new JLabel("Maos vencidas: "));
        infoJogadores.add(infoJog1);
        infoJogadores.add(infoJog2);

        posCartaVencedora = new int[2]; //Posicao [0] indica o jogador vencedor,
        //Posicao [1] indica a carta do jogador vencedor

        mesaCarta = new JButton[4];
        for (int i = 0; i < 4; i++) {
            mesaCarta[i] = new JButton(""); //Cria os botoes na mesa
        }

        cartaNaMesa = false;
        JButton iniciaJogo = new JButton("Iniciar jogo");

        //Insercao das cartas na mesa
        mesa.add(new JLabel());
        mesa.add(mesaCarta[2]);
        mesa.add(new JLabel());
        mesa.add(mesaCarta[1]);
        mesa.add(new JLabel());
        mesa.add(mesaCarta[3]);
        mesa.add(new JLabel());
        mesa.add(mesaCarta[0]);
        mesa.add(new JLabel());

        interfaceJogador = new InterfaceJogador[jogo.numJogadores()];

        //Criando a interface de cada jogador no JFrame
        for (int i = 0; i < jogo.numJogadores(); i++) {
            interfaceJogador[i] = jogo.getJogador(i).getInterfaceJog();
            interfaceJogador[i].getJPanel().setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        }

        //Listener do botao inicia Jogo
        iniciaJogo.addActionListener((ActionEvent e) -> {
            interfaceJogador[0].getJPanel().setBorder(BorderFactory.createLineBorder(Color.RED, 5));

            jogo.distribuirCartas();

            for (int i = 0; i < 3; i++) {
                interfaceJogador[0].setCarta(jogo.getJogador(0).getCartas()[i].getImagem(), i);
            }

            for (int i = 0; i < jogo.numJogadores(); i++) {
                interfaceJogador[i].setQuedas(0);
                interfaceJogador[i].setTentos(0);
            }

            setInfoJog1();
            setInfoJog2();

        });

        verificaJogador1();
        verificaJogador2();
        verificaJogador3();
        verificaJogador4();

        painel.add(new JLabel().add(iniciaJogo));
        painel.add(interfaceJogador[2].getJPanel());
        painel.add(infoJogadores);
        painel.add(interfaceJogador[1].getJPanel());
        painel.add(mesa);
        painel.add(interfaceJogador[3].getJPanel());
        painel.add(new JLabel());
        painel.add(interfaceJogador[0].getJPanel());
        janela.add(painel);
        janela.pack();
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setVisible(true);

    }

    private void verificaJogador1() {

        interfaceJogador[0].getCarta(0).addActionListener((ActionEvent e) -> { //Jogador 0, carta 0
            mesaCarta[0].setIcon(new javax.swing.ImageIcon(getClass().getResource(jogo.getJogador(0).getCartas()[0].getImagem())));
            interfaceJogador[0].setCarta("/Imagens/back.gif", 0);
            interfaceJogador[0].desabilitaCarta(0);

            if (!cartaNaMesa) {
                posCartaVencedora[0] = 0; //Jogador vencedor
                posCartaVencedora[1] = 0; //Carta do jogador vencedor
                cartaNaMesa = true; //Agora sabemos que a mesa possui alguma carta
            } //Se estiver carta na mesa, entra no else
            else {
                int posVencedora = jogo.cartaVencedora(jogo.getJogador(0).getCartas()[0], jogo.getJogador(posCartaVencedora[0]).getCartas()[posCartaVencedora[1]]);
                if (posVencedora == 0) { //posVencedora retorna a carta vencedora da mesa
                    mesaCarta[posCartaVencedora[0]].setEnabled(false); //Nova ganha da antiga e desabilita
                    posCartaVencedora[0] = 0;
                    posCartaVencedora[1] = 0;
                } else if (posVencedora == 1) { //Nova carta perde para a antiga e desabilita
                    mesaCarta[0].setEnabled(false);
                } else {
                    cangou();
                }
            }

            jogo.atualizaJogadorDaVez();
            jogo.atualizaCartasJogadas();
            controle(); //Funcao que chega e atualiza o jogo
            //Tudo se repetira para os demais jogadores
        });

        interfaceJogador[0].getCarta(1).addActionListener((ActionEvent e) -> {
            mesaCarta[0].setIcon(new javax.swing.ImageIcon(getClass().getResource(jogo.getJogador(0).getCartas()[1].getImagem())));
            interfaceJogador[0].setCarta("/Imagens/back.gif", 1);
            interfaceJogador[0].desabilitaCarta(1);

            if (!cartaNaMesa) {
                posCartaVencedora[0] = 0;
                posCartaVencedora[1] = 1;
                cartaNaMesa = true;
            } else {
                int posVencedora = jogo.cartaVencedora(jogo.getJogador(0).getCartas()[1], jogo.getJogador(posCartaVencedora[0]).getCartas()[posCartaVencedora[1]]);
                if (posVencedora == 0) {
                    mesaCarta[posCartaVencedora[0]].setEnabled(false);
                    posCartaVencedora[0] = 0;
                    posCartaVencedora[1] = 1;
                } else if (posVencedora == 1) {
                    mesaCarta[0].setEnabled(false);
                } else {
                    cangou();
                }
            }

            jogo.atualizaJogadorDaVez();
            jogo.atualizaCartasJogadas();
            controle();

        });

        interfaceJogador[0].getCarta(2).addActionListener((ActionEvent e) -> {
            mesaCarta[0].setIcon(new javax.swing.ImageIcon(getClass().getResource(jogo.getJogador(0).getCartas()[2].getImagem())));
            interfaceJogador[0].setCarta("/Imagens/back.gif", 2);
            interfaceJogador[0].desabilitaCarta(2);

            if (!cartaNaMesa) {
                posCartaVencedora[0] = 0;
                posCartaVencedora[1] = 2;
                cartaNaMesa = true;
            } else {

                int posVencedora = jogo.cartaVencedora(jogo.getJogador(0).getCartas()[2], jogo.getJogador(posCartaVencedora[0]).getCartas()[posCartaVencedora[1]]);
                if (posVencedora == 0) {
                    mesaCarta[posCartaVencedora[0]].setEnabled(false);
                    posCartaVencedora[0] = 0;
                    posCartaVencedora[1] = 2;
                } else if (posVencedora == 1) {
                    mesaCarta[0].setEnabled(false);
                } else {
                    cangou();
                }
            }

            jogo.atualizaJogadorDaVez();
            jogo.atualizaCartasJogadas();
            controle();

        });

        interfaceJogador[0].getTruco().addActionListener((ActionEvent e) -> {
            if (jogo.getJogador(0).getTentos() == 11) {
                JOptionPane.showMessageDialog(null, "O jogador nao pode trucar com 11 pontos!!\nAcaba de perder o jogo!!");
                jogo.getJogador(1).setTentos(12);
                jogo.setJogadorDaVez(1);
                jogo.setCartasJogadas(0);
                posCartaVencedora[0] = 1;
                novaMao();
                novaRodada();
                atualizaJogadorAtual();
            } else {
                JOptionPane.showMessageDialog(null, "Jogador " + jogo.getJogador(0).getNome()
                        + " pediu truco contra " + jogo.getJogador(1).getNome() + "!!!!\n"
                        + "Clique em aceitar para entrar e em correr para sair.");
                jogo.setPediuTruco(true);
            }
        });

        interfaceJogador[0].getAceitar().addActionListener((ActionEvent e) -> {
            if (!jogo.getPediuTruco()) {
                JOptionPane.showMessageDialog(null, "O jogador nao pediu truco!!");
            } else {
                JOptionPane.showMessageDialog(null, jogo.getJogador(0).getNome() + " aceitou o truco de " + jogo.getJogador(3).getNome()
                        + "\nO jogo esta trucado!!");

                if (jogo.getTrucado() == true) {
                    jogo.setValorTrucado(jogo.getValorTrucado() + 3);
                } else {
                    jogo.setTrucado(true);
                    jogo.setValorTrucado(3);
                }
            }
        });

        interfaceJogador[0].getCorrer().addActionListener((ActionEvent e) -> {
            if (!jogo.getPediuTruco()) {
                JOptionPane.showMessageDialog(null, "O jogo nao esta trucado!!");
            } else {
                JOptionPane.showMessageDialog(null, "Jogador " + jogo.getJogador(0).getNome() + " correu do truco!");
                posCartaVencedora[0] = 3;
                jogo.setJogadorDaVez(3);
                jogo.setCartasJogadas(0);

                atualizaJogadorAtual();
                novaRodada();
                novaMao();

                jogo.getJogador(0).setMaosVencidas(0);
                jogo.getJogador(1).setMaosVencidas(0);
                jogo.setPediuTruco(false);
                setInfoJog1();
                setInfoJog2();
            }
        });

    }

    private void verificaJogador2() {

        interfaceJogador[1].getCarta(0).addActionListener((ActionEvent e) -> {
            mesaCarta[1].setIcon(new javax.swing.ImageIcon(getClass().getResource(jogo.getJogador(1).getCartas()[0].getImagem())));
            interfaceJogador[1].setCarta("/Imagens/back.gif", 0);
            interfaceJogador[1].desabilitaCarta(0);

            if (!cartaNaMesa) {
                posCartaVencedora[0] = 1;
                posCartaVencedora[1] = 0;
                cartaNaMesa = true;
            } else {

                int posVencedora = jogo.cartaVencedora(jogo.getJogador(1).getCartas()[0], jogo.getJogador(posCartaVencedora[0]).getCartas()[posCartaVencedora[1]]);
                if (posVencedora == 0) {
                    mesaCarta[posCartaVencedora[0]].setEnabled(false);
                    posCartaVencedora[0] = 1;
                    posCartaVencedora[1] = 0;
                } else if (posVencedora == 1) {
                    mesaCarta[1].setEnabled(false);
                } else {
                    cangou();
                }
            }

            jogo.atualizaJogadorDaVez();
            jogo.atualizaCartasJogadas();
            controle();

        });

        interfaceJogador[1].getCarta(1).addActionListener((ActionEvent e) -> {
            mesaCarta[1].setIcon(new javax.swing.ImageIcon(getClass().getResource(jogo.getJogador(1).getCartas()[1].getImagem())));
            interfaceJogador[1].setCarta("/Imagens/back.gif", 1);
            interfaceJogador[1].desabilitaCarta(1);

            if (!cartaNaMesa) {
                posCartaVencedora[0] = 1;
                posCartaVencedora[1] = 1;
                cartaNaMesa = true;
            } else {
                int posVencedora = jogo.cartaVencedora(jogo.getJogador(1).getCartas()[1], jogo.getJogador(posCartaVencedora[0]).getCartas()[posCartaVencedora[1]]);
                if (posVencedora == 0) {
                    mesaCarta[posCartaVencedora[0]].setEnabled(false);
                    posCartaVencedora[0] = 1;
                    posCartaVencedora[1] = 1;
                } else if (posVencedora == 1) {
                    mesaCarta[1].setEnabled(false);
                } else {
                    cangou();
                }
            }

            jogo.atualizaJogadorDaVez();
            jogo.atualizaCartasJogadas();
            controle();

        });

        interfaceJogador[1].getCarta(2).addActionListener((ActionEvent e) -> {
            mesaCarta[1].setIcon(new javax.swing.ImageIcon(getClass().getResource(jogo.getJogador(1).getCartas()[2].getImagem())));
            interfaceJogador[1].setCarta("/Imagens/back.gif", 2);
            interfaceJogador[1].desabilitaCarta(2);

            if (!cartaNaMesa) {
                posCartaVencedora[0] = 1;
                posCartaVencedora[1] = 2;
                cartaNaMesa = true;
            } else {

                int posVencedora = jogo.cartaVencedora(jogo.getJogador(1).getCartas()[2], jogo.getJogador(posCartaVencedora[0]).getCartas()[posCartaVencedora[1]]);
                if (posVencedora == 0) {
                    mesaCarta[posCartaVencedora[0]].setEnabled(false);
                    posCartaVencedora[0] = 1;
                    posCartaVencedora[1] = 2;
                } else if (posVencedora == 1) {
                    mesaCarta[1].setEnabled(false);
                } else {
                    cangou();
                }
            }

            jogo.atualizaJogadorDaVez();
            jogo.atualizaCartasJogadas();
            controle();

        });

        interfaceJogador[1].getTruco().addActionListener((ActionEvent e) -> {
            if (jogo.getJogador(1).getTentos() == 11) {
                JOptionPane.showMessageDialog(null, "O jogador nao pode trucar com 11 pontos!!\nAcaba de perder o jogo!!");
                jogo.getJogador(2).setTentos(12);
                jogo.setJogadorDaVez(2);
                jogo.setCartasJogadas(0);
                posCartaVencedora[0] = 2;
                novaMao();
                novaRodada();
                atualizaJogadorAtual();
            } else {
                JOptionPane.showMessageDialog(null, "Jogador " + jogo.getJogador(1).getNome()
                        + " pediu truco contra " + jogo.getJogador(2).getNome() + "!!!!\n"
                        + "Clique em aceitar para entrar e em correr para sair.");
                jogo.setPediuTruco(true);
            }
        });

        interfaceJogador[1].getAceitar().addActionListener((ActionEvent e) -> {
            if (!jogo.getPediuTruco()) {
                JOptionPane.showMessageDialog(null, "O jogador nao pediu truco!!");
            } else {
                JOptionPane.showMessageDialog(null, jogo.getJogador(1).getNome() + " aceitou o truco de " + jogo.getJogador(0).getNome()
                        + "\nO jogo esta trucado!!");

                if (jogo.getTrucado() == true) {
                    jogo.setValorTrucado(jogo.getValorTrucado() + 3);
                } else {
                    jogo.setTrucado(true);
                    jogo.setValorTrucado(3);
                }
            }
        });

        interfaceJogador[1].getCorrer().addActionListener((ActionEvent e) -> {
            if (!jogo.getPediuTruco()) {
                JOptionPane.showMessageDialog(null, "O jogo nao esta trucado!!");
            } else {
                JOptionPane.showMessageDialog(null, "Jogador " + jogo.getJogador(1).getNome() + " correu do truco!");
                posCartaVencedora[0] = 0;
                jogo.setJogadorDaVez(0);
                jogo.setCartasJogadas(0);

                atualizaJogadorAtual();
                novaRodada();
                novaMao();

                jogo.getJogador(0).setMaosVencidas(0);
                jogo.getJogador(1).setMaosVencidas(0);
                jogo.setPediuTruco(false);
                setInfoJog1();
                setInfoJog2();
            }
        });
    }

    private void verificaJogador3() {

        interfaceJogador[2].getCarta(0).addActionListener((ActionEvent e) -> {
            mesaCarta[2].setIcon(new javax.swing.ImageIcon(getClass().getResource(jogo.getJogador(2).getCartas()[0].getImagem())));
            interfaceJogador[2].setCarta("/Imagens/back.gif", 0);
            interfaceJogador[2].desabilitaCarta(0);

            if (!cartaNaMesa) {
                posCartaVencedora[0] = 2;
                posCartaVencedora[1] = 0;
                cartaNaMesa = true;
            } else {

                int posVencedora = jogo.cartaVencedora(jogo.getJogador(2).getCartas()[0], jogo.getJogador(posCartaVencedora[0]).getCartas()[posCartaVencedora[1]]);
                if (posVencedora == 0) {
                    mesaCarta[posCartaVencedora[0]].setEnabled(false);
                    posCartaVencedora[0] = 2;
                    posCartaVencedora[1] = 0;
                } else if (posVencedora == 1) {
                    mesaCarta[2].setEnabled(false);
                } else {
                    cangou();
                }
            }

            jogo.atualizaJogadorDaVez();
            jogo.atualizaCartasJogadas();
            controle();

        });

        interfaceJogador[2].getCarta(1).addActionListener((ActionEvent e) -> {
            mesaCarta[2].setIcon(new javax.swing.ImageIcon(getClass().getResource(jogo.getJogador(2).getCartas()[1].getImagem())));
            interfaceJogador[2].setCarta("/Imagens/back.gif", 1);
            interfaceJogador[2].desabilitaCarta(1);

            if (!cartaNaMesa) {
                posCartaVencedora[0] = 2;
                posCartaVencedora[1] = 1;
                cartaNaMesa = true;
            } else {

                int posVencedora = jogo.cartaVencedora(jogo.getJogador(2).getCartas()[1], jogo.getJogador(posCartaVencedora[0]).getCartas()[posCartaVencedora[1]]);
                if (posVencedora == 0) {
                    mesaCarta[posCartaVencedora[0]].setEnabled(false);
                    posCartaVencedora[0] = 2;
                    posCartaVencedora[1] = 1;
                } else if (posVencedora == 1) {
                    mesaCarta[2].setEnabled(false);
                } else {
                    cangou();
                }
            }

            jogo.atualizaJogadorDaVez();
            jogo.atualizaCartasJogadas();
            controle();

        });

        interfaceJogador[2].getCarta(2).addActionListener((ActionEvent e) -> {
            mesaCarta[2].setIcon(new javax.swing.ImageIcon(getClass().getResource(jogo.getJogador(2).getCartas()[2].getImagem())));
            interfaceJogador[2].setCarta("/Imagens/back.gif", 2);
            interfaceJogador[2].desabilitaCarta(2);

            if (!cartaNaMesa) {
                posCartaVencedora[0] = 2;
                posCartaVencedora[1] = 2;
                cartaNaMesa = true;
            } else {

                int posVencedora = jogo.cartaVencedora(jogo.getJogador(2).getCartas()[2], jogo.getJogador(posCartaVencedora[0]).getCartas()[posCartaVencedora[1]]);
                if (posVencedora == 0) {
                    mesaCarta[posCartaVencedora[0]].setEnabled(false);
                    posCartaVencedora[0] = 2;
                    posCartaVencedora[1] = 2;
                } else if (posVencedora == 1) {
                    mesaCarta[2].setEnabled(false);
                } else {
                    cangou();
                }
            }

            jogo.atualizaJogadorDaVez();
            jogo.atualizaCartasJogadas();
            controle();

        });

        interfaceJogador[2].getTruco().addActionListener((ActionEvent e) -> {
            if (jogo.getJogador(2).getTentos() == 11) {
                JOptionPane.showMessageDialog(null, "O jogador nao pode trucar com 11 pontos!!\nAcaba de perder o jogo!!");
                jogo.getJogador(3).setTentos(12);
                jogo.setJogadorDaVez(3);
                jogo.setCartasJogadas(0);
                posCartaVencedora[0] = 3;
                novaMao();
                novaRodada();
                atualizaJogadorAtual();
            } else {
                JOptionPane.showMessageDialog(null, "Jogador " + jogo.getJogador(2).getNome()
                        + " pediu truco contra " + jogo.getJogador(3).getNome() + "!!!!\n"
                        + "Clique em aceitar para entrar e em correr para sair.");
                jogo.setPediuTruco(true);
            }
        });

        interfaceJogador[2].getAceitar().addActionListener((ActionEvent e) -> {
            if (!jogo.getPediuTruco()) {
                JOptionPane.showMessageDialog(null, "O jogador nao pediu truco!!");
            } else {
                JOptionPane.showMessageDialog(null, jogo.getJogador(2).getNome() + " aceitou o truco de " + jogo.getJogador(1).getNome()
                        + "\nO jogo esta trucado!!");

                if (jogo.getTrucado() == true) {
                    jogo.setValorTrucado(jogo.getValorTrucado() + 3);
                } else {
                    jogo.setTrucado(true);
                    jogo.setValorTrucado(3);
                }
            }
        });

        interfaceJogador[2].getCorrer().addActionListener((ActionEvent e) -> {
            if (!jogo.getPediuTruco()) {
                JOptionPane.showMessageDialog(null, "O jogo nao esta trucado!!");
            } else {
                JOptionPane.showMessageDialog(null, "Jogador " + jogo.getJogador(2).getNome() + " correu do truco!");
                posCartaVencedora[0] = 1;
                jogo.setJogadorDaVez(1);
                jogo.setCartasJogadas(0);

                atualizaJogadorAtual();
                novaRodada();
                novaMao();

                jogo.getJogador(0).setMaosVencidas(0);
                jogo.getJogador(1).setMaosVencidas(0);
                jogo.setPediuTruco(false);
                setInfoJog1();
                setInfoJog2();
            }
        });
    }

    private void verificaJogador4() {

        interfaceJogador[3].getCarta(0).addActionListener((ActionEvent e) -> {
            mesaCarta[3].setIcon(new javax.swing.ImageIcon(getClass().getResource(jogo.getJogador(3).getCartas()[0].getImagem())));
            interfaceJogador[3].setCarta("/Imagens/back.gif", 0);
            interfaceJogador[3].desabilitaCarta(0);

            if (!cartaNaMesa) {
                posCartaVencedora[0] = 3;
                posCartaVencedora[1] = 0;
                cartaNaMesa = true;
            } else {

                int posVencedora = jogo.cartaVencedora(jogo.getJogador(3).getCartas()[0], jogo.getJogador(posCartaVencedora[0]).getCartas()[posCartaVencedora[1]]);
                if (posVencedora == 0) {
                    mesaCarta[posCartaVencedora[0]].setEnabled(false);
                    posCartaVencedora[0] = 3;
                    posCartaVencedora[1] = 0;
                } else if (posVencedora == 1) {
                    mesaCarta[3].setEnabled(false);
                } else {
                    cangou();
                }
            }

            jogo.atualizaJogadorDaVez();
            jogo.atualizaCartasJogadas();
            controle();

        });

        interfaceJogador[3].getCarta(1).addActionListener((ActionEvent e) -> {
            mesaCarta[3].setIcon(new javax.swing.ImageIcon(getClass().getResource(jogo.getJogador(3).getCartas()[1].getImagem())));
            interfaceJogador[3].setCarta("/Imagens/back.gif", 1);
            interfaceJogador[3].desabilitaCarta(1);

            if (!cartaNaMesa) {
                posCartaVencedora[0] = 3;
                posCartaVencedora[1] = 1;
                cartaNaMesa = true;
            } else {

                int posVencedora = jogo.cartaVencedora(jogo.getJogador(3).getCartas()[1], jogo.getJogador(posCartaVencedora[0]).getCartas()[posCartaVencedora[1]]);
                if (posVencedora == 0) {
                    mesaCarta[posCartaVencedora[0]].setEnabled(false);
                    posCartaVencedora[0] = 3;
                    posCartaVencedora[1] = 1;
                } else if (posVencedora == 1) {
                    mesaCarta[3].setEnabled(false);
                } else {
                    cangou();
                }
            }

            jogo.atualizaJogadorDaVez();
            jogo.atualizaCartasJogadas();
            controle();

        });

        interfaceJogador[3].getCarta(2).addActionListener((ActionEvent e) -> {
            mesaCarta[3].setIcon(new javax.swing.ImageIcon(getClass().getResource(jogo.getJogador(3).getCartas()[2].getImagem())));
            interfaceJogador[3].setCarta("/Imagens/back.gif", 2);
            interfaceJogador[3].desabilitaCarta(2);

            if (!cartaNaMesa) {
                posCartaVencedora[0] = 3;
                posCartaVencedora[1] = 2;
                cartaNaMesa = true;
            } else {

                int posVencedora = jogo.cartaVencedora(jogo.getJogador(3).getCartas()[2], jogo.getJogador(posCartaVencedora[0]).getCartas()[posCartaVencedora[1]]);
                if (posVencedora == 0) {
                    mesaCarta[posCartaVencedora[0]].setEnabled(false);
                    posCartaVencedora[0] = 3;
                    posCartaVencedora[1] = 2;
                } else if (posVencedora == 1) {
                    mesaCarta[3].setEnabled(false);
                } else {
                    cangou();
                }
            }

            jogo.atualizaJogadorDaVez();
            jogo.atualizaCartasJogadas();
            controle();

        });

        interfaceJogador[3].getTruco().addActionListener((ActionEvent e) -> {
            if (jogo.getJogador(3).getTentos() == 11) {
                JOptionPane.showMessageDialog(null, "O jogador nao pode trucar com 11 pontos!!\nAcaba de perder o jogo!!");
                jogo.getJogador(0).setTentos(12);
                jogo.setJogadorDaVez(0);
                jogo.setCartasJogadas(0);
                posCartaVencedora[0] = 0;
                novaMao();
                novaRodada();
                atualizaJogadorAtual();
            } else {
                JOptionPane.showMessageDialog(null, "Jogador " + jogo.getJogador(3).getNome()
                        + " pediu truco contra " + jogo.getJogador(0).getNome() + "!!!!\n"
                        + "Clique em aceitar para entrar e em correr para sair.");
                jogo.setPediuTruco(true);
            }
        });

        interfaceJogador[3].getAceitar().addActionListener((ActionEvent e) -> {
            if (!jogo.getPediuTruco()) {
                JOptionPane.showMessageDialog(null, "O jogador nao pediu truco!!");
            } else {
                JOptionPane.showMessageDialog(null, jogo.getJogador(3).getNome() + " aceitou o truco de " + jogo.getJogador(2).getNome()
                        + "\nO jogo esta trucado!!");

                if (jogo.getTrucado() == true) {
                    jogo.setValorTrucado(jogo.getValorTrucado() + 3);
                } else {
                    jogo.setTrucado(true);
                    jogo.setValorTrucado(3);
                }
            }
        });

        interfaceJogador[3].getCorrer().addActionListener((ActionEvent e) -> {
            if (!jogo.getPediuTruco()) {
                JOptionPane.showMessageDialog(null, "O jogo nao esta trucado!!");
            } else {
                JOptionPane.showMessageDialog(null, "Jogador " + jogo.getJogador(3).getNome() + " correu do truco!");
                posCartaVencedora[0] = 2;
                jogo.setJogadorDaVez(2);
                jogo.setCartasJogadas(0);

                atualizaJogadorAtual();
                novaRodada();
                novaMao();

                jogo.getJogador(0).setMaosVencidas(0);
                jogo.getJogador(1).setMaosVencidas(0);
                jogo.setPediuTruco(false);
                setInfoJog1();
                setInfoJog2();
            }
        });
    }

    private void controle() {
        atualizaJogadorAtual();

        if (jogo.vencedorDaRodada() != null) {
            novaRodada();
        }

        if (jogo.getCartasJogadas() == 4) {
            novaMao();
        }
    }

    private void atualizaJogadorAtual() {

        for (int i = 0; i < jogo.numJogadores(); i++) {
            if (i == jogo.getJogadorDaVez()) {
                interfaceJogador[i].getJPanel().setBorder(BorderFactory.createLineBorder(Color.RED, 5));
                for (int j = 0; j < 3; j++) {
                    interfaceJogador[i].setCarta(jogo.getJogador(i).getCartas()[j].getImagem(), j);
                }
            } else {
                interfaceJogador[i].getJPanel().setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                for (int j = 0; j < 3; j++) {
                    interfaceJogador[i].setCarta("/Imagens/back.gif", j);
                }
            }
        }
    }

    private void novaRodada() {
        jogo.distribuirCartas();
        jogo.setCangado(false);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                if (i != posCartaVencedora[0]) {
                    interfaceJogador[i].setCarta("/Imagens/back.gif", j); //Virando todas cartas dos jogadores
                } else {
                    interfaceJogador[i].setCarta(jogo.getJogador(i).getCartas()[j].getImagem(), j);
                }
                interfaceJogador[i].habilitaCarta(j);
            }
            jogo.getJogador(i).setMaosVencidas(0); //Atualizando as maos vencidas de cada jogador para 0
        }

        setInfoJog1();
        setInfoJog2();

        if (jogo.getTrucado() == false) {
            jogo.getJogador(posCartaVencedora[0]).setTentos(); //Incrementando os tentos da dupla vencedora
        } else {
            jogo.getJogador(posCartaVencedora[0]).setTentos(jogo.getJogador(posCartaVencedora[0]).getTentos() + jogo.getValorTrucado());
        }

        jogo.setTrucado(false);
        jogo.setPediuTruco(false);
        jogo.setPosPrimeiraMaoVencida(-1);

        if (jogo.getJogador(posCartaVencedora[0]).getTentos() >= 12) { //Checando para ver se o jogo acabou
            verificaFimDeJogo(jogo.getJogador(posCartaVencedora[0]));
        }

        interfaceJogador[posCartaVencedora[0]].setTentos(jogo.getJogador(posCartaVencedora[0]).getTentos()); //Atualizando os tentos do jogador vencedor
        interfaceJogador[(posCartaVencedora[0] + 2) % 4].setTentos(jogo.getJogador((posCartaVencedora[0] + 2) % 4).getTentos()); //Atualizando os tentos da dupla do jogador vencedor
    }

    private void novaMao() {

        for (int i = 0; i < 4; i++) {
            mesaCarta[i].setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/back.gif"))); //VIrando todas cartas da mesa
            mesaCarta[i].setEnabled(true);
            for (int j = 0; j < 3; j++) {
                interfaceJogador[i].setCarta("/Imagens/back.gif", j); //Virando todas cartas dos jogadores
            }
            interfaceJogador[i].getJPanel().setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); //Colocando todas boradas pretas
        }

        for (int i = 0; i < 3; i++) {
            interfaceJogador[posCartaVencedora[0]].setCarta(jogo.getJogador(posCartaVencedora[0]).getCartas()[i].getImagem(), i); //Deixando visivel apenas a carta do jogador da vez
        }

        interfaceJogador[posCartaVencedora[0]].getJPanel().setBorder(BorderFactory.createLineBorder(Color.RED, 5)); //Deixando apenas a borda do jogador da vez vermelha
        cartaNaMesa = false;
        jogo.setJogadorDaVez(posCartaVencedora[0]); //Atualizando o jogador da vez

        if (jogo.getPosPrimeiraMaoVencida() == -1) {
            jogo.setPosPrimeiraMaoVencida(posCartaVencedora[0]); //Guardando a primeira mao vencida
        }

        jogo.getJogador(posCartaVencedora[0]).atualizaMaosVencidas(); //Atualizando as maos vencidas da dupla vencedora
        setInfoJog1();
        setInfoJog2();
        jogo.atualizaMaosJogadas();

        if (jogo.vencedorDaRodada() != null) {
            novaRodada();
        }

    }

    private void cangou() {
        if (!jogo.getCangado() && 2 > (jogo.getJogador(0).getMaosVencidas() + jogo.getJogador(1).getMaosVencidas())) {
            JOptionPane.showMessageDialog(null, "O jogo cangou!!\nTodos jogadores devem mostrar as maiores cartas!");
            for (int i = 0; i < 4; i++) {
                mesaCarta[i].setEnabled(true);
                mesaCarta[i].setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/back.gif")));
            }
            jogo.setCartasJogadas(-1);
            jogo.getJogador(0).setMaosVencidas(1);
            jogo.getJogador(1).setMaosVencidas(1);
            cartaNaMesa = false;
            jogo.setCangado(true);
        } else if (!jogo.getCangado() && 2 <= (jogo.getJogador(0).getMaosVencidas() + jogo.getJogador(1).getMaosVencidas())) {
            posCartaVencedora[0] = jogo.getPosPrimeiraMaoVencida();
            jogo.setCartasJogadas(3);
            jogo.setJogadorDaVez(jogo.getPosPrimeiraMaoVencida() - 1);
        }
    }

    private void setInfoJog1() {
        infoJog1.setText(jogo.getJogador(0).getNome() + " e " + jogo.getJogador(0).getDupla().getNome() + ": " + jogo.getJogador(0).getMaosVencidas());
    }

    private void setInfoJog2() {
        infoJog2.setText(jogo.getJogador(1).getNome() + " e " + jogo.getJogador(1).getDupla().getNome() + ": " + jogo.getJogador(1).getMaosVencidas());
    }

    private void verificaFimDeJogo(Jogador vencedor) {
        for (int i = 0; i < 2; i++) {
            jogo.getJogador(i).setTentos(0);
        }

        for (int i = 0; i < 4; i++) {
            interfaceJogador[i].setTentos(0);
        }
        vencedor.setQuedas();
        interfaceJogador[posCartaVencedora[0]].setQuedas(jogo.getJogador(posCartaVencedora[0]).getQuedas()); //Atualizando os tentos do jogador vencedor
        interfaceJogador[(posCartaVencedora[0] + 2) % 4].setQuedas(jogo.getJogador((posCartaVencedora[0] + 2) % 4).getQuedas()); //Atualizando os tentos da dupla do jogador vencedor

        if (vencedor.getQuedas() == 2) {
            JOptionPane.showMessageDialog(null, vencedor.getNome() + " e sua dupla venceram!!!");
            //System.exit(0);
        }
    }

    public static void main(String[] args) {
        new Truco();
    }

}
