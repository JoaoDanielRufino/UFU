package teste;

import java.util.Vector;

public class Curso {
    String nome;
    Vector<Professor> listaProfessor;
    Vector<Aluno> listaAlunos;
    
    public Curso (){
        listaProfessor = new Vector<Professor> ();
        listaAlunos = new Vector<Aluno> ();
    }
    
    void atualizaCurso (String nome){
        this.nome = nome;
    }
    
    void atualizaProf (Professor p){
        listaProfessor.add(p);
    }
    
    void atualizaAluno (Aluno a){
        listaAlunos.add(a);
    }
    
    String retornaProf (Disciplina d){
        for(Professor p: listaProfessor){
            if(p.materia == d.nome)
                return p.nome;
        }
        return null;
    }
    
    void imprime (){
        for(Aluno a: listaAlunos)
            a.imprime(this);
    }
}
