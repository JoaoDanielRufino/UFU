package teste;

public class Aluno {
    String nome;
    String matricula;
    Historico historico = new Historico();
    
   void nome (String nome){
       this.nome = nome;
   }
   
   void matricula (String mat){
       this.matricula = mat;
   }
  
   void atualizaHistorico (Disciplina d){
       this.historico.matriculaDisciplina(d);
   }
   
   void media (){
       double media;
       media = 0.0;
       for(Disciplina d: this.historico.listaDisciplinas)
           media += d.nota;
       media = media / this.historico.listaDisciplinas.size();
       System.out.println("Media: " + media);
   }
   
   void imprime (Curso c){
       System.out.println("Curso: " + c.nome);
       System.out.println("Aluno: " + nome);
       System.out.println("Matricula: " + matricula);
       this.historico.imprime(c);
       this.media();
       System.out.println("---------------------------------");
   }
}
