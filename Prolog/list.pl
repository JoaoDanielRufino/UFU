%Number of elements in a list.
noelement([],0).
noelement([_|Y],N):-
    noelement(Y,N1),
    N is N1+1.

%Nth element.
nesimo(1,X,[X|_]):-!.
nesimo(N,X,[_|Z]):-
    N1 is N-1,
    nesimo(N1,X,Z).

%Catch elements from a list given the list of their positions.
pegar([],_,[]).
pegar([X|Y],Z,[L1|L]):-
    nesimo(X,L1,Z),
    pegar(Y,Z,L).

%Remove the first occurrence of an element. 
retirar1(_,[],[]).
retirar1(X,[X|Y],Y):-!.
retirar1(X,[Y|Z],[Y|L]):-
    X\=Y,
    retirar1(X,Z,L).

%Insertion for the permutation rule.
insert(X,[],[X]):-!.
insert(X,[Y|Z],[X,Y|Z]).
insert(X,[Y|Z],[Y|L]):-
    insert(X,Z,L).
    
%Permutation.
permutar([],[]).
permutar([X|Y],P):-
    permutar(Y,L),
    insert(X,L,P).

%Concatenate lists.
concatena([],L,L).
concatena([X|Y],L,[X|R]):-
    concatena(Y,L,R).

%Duplicate every element.
duplicar([],[]).
duplicar([X|Y],[X,X|Z]):-
    duplicar(Y,Z).

%Duplicate a selected element.
duplielem(_,[],[]).
duplielem(N,[X|Y],[X|L]):-
    N\=X,
    duplielem(N,Y,L).
duplielem(N,[X|Y],[X,X|Z]):-
    N=:=X,
    duplielem(N,Y,Z).

%Insert in the head.
inserecabeca(N,[],[N]).
inserecabeca(N,[X|Y],[N,X|Y]).

%Remove all selected element.
removeall(_,[],[]).
removeall(X,[X|Y],L):-
    !,removeall(X,Y,L).
removeall(X,[Y|Z],[Y|L]):-
    removeall(X,Z,L).

%Remove all repetitions of an selected element.
removerep([],[]):-!.
removerep([X|Y],[X|L2]):-
    removeall(X,Y,L1),
    removerep(L1,L2).

%Remove an element given his position.
removepos(1,[_|Y],Y):-!.
removepos(P,[X|Y],[X|L]):-
    P1 is P-1,
    removepos(P1,Y,L).

%Insert an element given his position.
inserepos(X,_,[],[X]).
inserepos(E,1,[X|Y],[E,X|Y]):-!.
inserepos(E,N,[X|Y],[X|L]):-
    N>0,
    N1 is N-1,
    inserepos(E,N1,Y,L).

%Invert a list.
invert([],[]).
invert([X|Y],L):-
    invert(Y,L1),
    concatena(L1,[X],L).

%Replace each selected element.
substitui(_,_,[],[]).
substitui(X,Y,[X|Z],[Y|L]):-
    substitui(X,Y,Z,L).
substitui(X,Y,[N|Z],[N|L]):-
    X\=N,
    substitui(X,Y,Z,L).

%Check if two lists are identical.
iguais([X],[X]):-!.
iguais([X|Y],[X|M]):-
    iguais(Y,M).

%Find the greater element.
maior([X],X):-!.
maior([X|Y],N):-
    maior(Y,N),
    N>=X.
maior([X|Y],X):-
    maior(Y,N),
    X>N.

%Find the least element.
menor([X],X):-!.
menor([X|Y],N):-
    menor(Y,N),
    N=<X.
menor([X|Y],X):-
    menor(Y,N),
    X<N.

%Insert in the final.
inserefinal(N,[],[N]):-!.
inserefinal(N,[X|Y],[X|L]):-
    inserefinal(N,Y,L).

%Sort a list.
ordena([],[]).
ordena([X|Y],L):-
    ordena(Y,L1),
    insereordenado(X,L1,L).
insereordenado(X,[],[X]).
insereordenado(X,[Y|Z],[X,Y|Z]):-
    X=<Y.
insereordenado(X,[Y|Z],[Y|L]):-
    X>Y,
    insereordenado(X,Z,L).

%Check if an element belongs to a list.
pertence(X,[X|_]):-!.
pertence(X,[_|Z]):-
    pertence(X,Z).

%Intersection.
interseccao([],_,[]).
interseccao(_,[],[]).
interseccao([X|Y],[W|Z],[X|L]):-
    pertence(X,[W|Z]),!,
    interseccao(Y,[W|Z],L).
interseccao([_|Y],[W|Z],L):-
    interseccao(Y,[W|Z],L).

%Calculate the sum.
soma([],0).
soma([X|Y],N):-
    soma(Y,N1),
    N is N1+X.

%Calculate the average.
media([X|Y],N):-
    noelement([X|Y],E),
    soma([X|Y],S),
    N is S/E.

%Check if its Palindrome.
palindromo([X|Y]):-
    invert([X|Y],L),
    iguais([X|Y],L).

%Calculate the number of repetitions of an element.
numrep(_,[],0).
numrep(X,[X|Y],N):-
    numrep(X,Y,N1),
    N is N1+1.
numrep(N,[X|Y],R):-
    N\=X,
    numrep(N,Y,R).

%Calculate the mode.
moda([X],X).
moda([X|Y],N):-
    numrep(X,[X|Y],N1),
    moda(Y,N),
    numrep(N,[X|Y],N2),
    N2>=N1,!.
moda([X|_],X).

%Last element.
last1([X],X):-!.
last1([_|Y],N):-
    last1(Y,N).

%Find the position of an element.
findpos(X,[X|_],1):-!.
findpos(N,[_|Y],P):-
    findpos(N,Y,P1),
    P is P1+1.
