 package teste;

public class Teste {

    public static void main(String[] args) {
        Disciplina d1 = new Disciplina("AED2");
        Disciplina d2 = new Disciplina("C3");
        Disciplina d3 = new Disciplina("POO");
        Disciplina d4 = new Disciplina("AOC1");
        Aluno a1 = new Aluno();
        Aluno a2 = new Aluno();
        Curso curso1 = new Curso();
        Professor p1 = new Professor("Marcelo", "POO");
        Professor p2 = new Professor("Luiz Gustavo", "AED2");
        Professor p3 = new Professor("Adilson", "C3");
        Professor p4 = new Professor("Frosi", "AOC1");
        
        curso1.atualizaCurso("Ciencia da Computacao");
        curso1.atualizaProf(p1);
        curso1.atualizaProf(p2);
        curso1.atualizaProf(p3);
        curso1.atualizaProf(p4);
        
        d1.atualizaResultado(75);
        d2.atualizaResultado(80);
        a1.atualizaHistorico(d1);
        a1.atualizaHistorico(d2);
        a1.nome("Joao Daniel");
        a1.matricula("11621BCC033");
        curso1.atualizaAluno(a1);
        
        d3.atualizaResultado(65);
        d4.atualizaResultado(88);
        a2.atualizaHistorico(d3);
        a2.atualizaHistorico(d4);
        a2.nome("Matheus Silveira");
        a2.matricula("11621BCC021");
        curso1.atualizaAluno(a2);
       
        curso1.imprime();
        
    }
    
}
