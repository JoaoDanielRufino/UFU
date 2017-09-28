package teste;

public class Disciplina {
    String nome;
    SituacaoDisciplina situacao;
    double nota;

    public Disciplina (String nome) {
       this.nome = nome;
       situacao = SituacaoDisciplina.CURSANDO;
    }

    void atualizaResultado (double nota) {
       if (nota >= 60.0)
           situacao = SituacaoDisciplina.APROVADO;
       else
           situacao = SituacaoDisciplina.REPROVADO;
       this.nota = nota;
    }

    void imprime (Curso c) {
        String prof;
        prof = c.retornaProf(this);
        if(prof != null)
            System.out.print("Prof.: "+ prof + " - ");
        else
            System.out.print("Professor nao encontrado." + " - ");
        System.out.println(nome + " - " + nota + ": " + situacao);
    }
}
