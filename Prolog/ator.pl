consult('C:/Prolog/atores.pl').

menu:-
    repeat,
    write('[1]-Listar atores.'),nl,
    write('[2]-Adicionar ator.'),nl,
    write('[3]-Remover ator.'),nl,
    write('[4]-Listar informacoes.'),nl,
    write('[5]-Numero de filmes de um ator.'),nl,
    write('[6]-Sair.'),nl,
    write('Digite uma opcao: '),
    read(X),
    (X=6,nl,write('Saindo...'),!; op(X),fail).

op(1):-!,
    findall(X,ator(X,_,_,_),L),
    lista(L),nl.

op(2):-!,
    write('Digite o nome do ator: '),
    read(Nome),
    write('Digite o filme: '),
    read(Filme),
    write('Digite o ano: '),
    read(Ano),
    write('Digite o rank: '),
    read(Rank),
    inserir_ator(Nome,Filme,Ano,Rank),nl.

op(3):-!,
    write('Digite o nome do ator para remove-lo: '),
    read(Nome),valida_nome(Nome),nl.
   % remove_ator(Nome),nl.

op(4):-!,
    listing(ator/4),nl.

op(5):-!,
    write('Digite o nome do ator: '),
    read(Nome),
    findall(X,ator(Nome,X,_,_),L),
    calcula_filmes(L,N),nl,
    write('O ator possui: '),write(N),write(' Filme(s).'),
    nl,nl.

inserir_ator(Nome,Filme,Ano,Rank):-
    assert(ator(Nome,Filme,Ano,Rank)),
    tell('C:/Prolog/atores.pl'),
    listing(ator/4),
    told,nl.

valida_nome(Nome):-
    ator(Nome,_,_,_),!,remove_ator(Nome).
valida_nome(_):-
    write('Digite um nome valido.'),nl.

remove_ator(Nome):-
    retractall(ator(Nome,_,_,_)),
    tell('C:/Prolog/atores.pl'),
    listing(ator/4),
    told,nl.

lista([X]):-
    write(X),nl.
lista([X|Y]):-
    write(X),nl,
    lista(Y).

calcula_filmes([],0):-!.
calcula_filmes([_|Y],N):-
    calcula_filmes(Y,N1),
    N is N1+1.
