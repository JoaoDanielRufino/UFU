package testeinterface;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.*;


public class TesteInterface {
    ArrayList<Pessoa> pessoas;

    public TesteInterface() {
        pessoas = new ArrayList<> ();
        
        final JFrame janela = new JFrame("Agenda telefonica");
        janela.setSize(300,300);
        
        final JLabel text1 = new JLabel("Insira os dados");
        final JLabel nome = new JLabel("Nome:");
        final JLabel telefone = new JLabel("Telefone: ");
        final JLabel endereco = new JLabel("Endereco: ");
        
        final JTextField campoNome = new JTextField(15);
        final JTextField campoTelefone = new JTextField(15);
        final JTextField campoEndereco = new JTextField(15);
        
        final JButton apagar = new JButton("Apagar");
        final JButton procurar = new JButton("Procurar");
        final JButton cadastrar = new JButton("Cadastrar");
        final JButton listar = new JButton("Listar pessoas");
        
        final JPanel panel = new JPanel();
        
        panel.add(text1);
        panel.add(nome);
        panel.add(campoNome);
        panel.add(telefone);
        panel.add(campoTelefone);
        panel.add(endereco);
        panel.add(campoEndereco);
        panel.add(procurar);
        panel.add(apagar);
        panel.add(listar);
        panel.add(cadastrar);
        
        cadastrar.addActionListener((ActionEvent e) -> {
            String nome1 = campoNome.getText();
            String telefone1 = campoTelefone.getText();
            String endereco1 = campoEndereco.getText();
            
            pessoas.add(new Pessoa(nome1, telefone1, endereco1));
            campoNome.setText("");
            campoTelefone.setText("");
            campoEndereco.setText("");
        });
        
        procurar.addActionListener((ActionEvent e) -> {
            final JFrame janela2 = new JFrame("Agenda telefonica");
            janela2.setSize(300,300);
            
            final JPanel panel2 = new JPanel();
            
            String nome1 = campoNome.getText();
            campoNome.setText("");
            
            for(Pessoa p: pessoas){
                if(p.getNome().equals(nome1)){
                    final JLabel lNome = new JLabel("Nome: " + p.getNome());
                    final JLabel lTel = new JLabel("Telefone: " + p.getTelefone());
                    final JLabel lEnd = new JLabel("Endereco: " + p.getEndereco());
                    
                    panel2.add(lNome);
                    panel2.add(lTel);
                    panel2.add(lEnd);
                    
                    janela2.getContentPane().add(panel2);
                    janela2.setVisible(true);
                    return;
                }
            }
            
            final JLabel lError = new JLabel("Pessoa nao encontrada!");
            
            panel2.add(lError);
            
            janela2.getContentPane().add(panel2);
            janela2.setVisible(true);
            
        });
        
        apagar.addActionListener((ActionEvent e) -> {
            String nome1 = campoNome.getText();
            
            for(Pessoa p: pessoas){
                if(p.getNome().equals(nome1)){
                    pessoas.remove(p);
                    break;
                }
            }
            campoNome.setText("");
        });
        
        listar.addActionListener((ActionEvent e) -> {
            final JFrame janela2 = new JFrame("Agenda telefonica");
            janela2.setSize(300,300);
            
            final JPanel panel2 = new JPanel();
            
            String nomes = "<html>Lista de pessoas: <br>";
            for(Pessoa p: pessoas){
                nomes += "<center>" + p.getNome() + "<br></center> ";
            }
            nomes += "</html>";
            
            final JLabel lb2 = new JLabel(nomes); 
            
            panel2.add(lb2);
            
            janela2.getContentPane().add(panel2);
            janela2.setVisible(true);
        });
        
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.getContentPane().add(panel);
        janela.setVisible(true);
    }
    public static void main(String[] args) {
        new TesteInterface();
    }

}
