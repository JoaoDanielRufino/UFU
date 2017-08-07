menu:-
	repeat,
	write('[1]-Especifica evidencia'),nl,
	write('[2]-Visualiza evidencia'),nl,
	write('[3]-Adivinhe'),nl,
	write('[4]-Remove respostas'),nl,
	write('[5]-Sair'),nl,
	write('Digite uma opcao: '),
	get(R),get0(_),
	R1 is R-48,
	(R1=5,nl,write('Saindo...'),nl,!; op(R1),fail).

op(1):-!,
	nl,
	write('Evidencias possiveis:'),nl,
	listing(evidencia/1),nl,
	write('Digite uma evidencia: '),
	le_atomo(X),
	valida_evidencia(X),nl.

op(2):-!,
	nl,
	write('Evidencias inseridas:'),nl,
	findall(X,sim(X),L),
	lista(L),nl.

op(3):-!,
	nl,
	write('Adivinhando...'),nl,
	inicio.

op(4):-!,
	nl,
	write('Respostas removidas.'),nl,
	removeRespostas,nl.

op(_):-!,
	nl,
	write('Saindo...'),nl,fail.

lista([]):-!,
	write('Sem evidencias'),nl.
lista([X]):-!,
	write(X),nl.
lista([X|Y]):-
	write(X),nl,
	lista(Y).

inicio :-
	repeat,
	hipotetiza(Animal),
	write('O animal eh: '),
	write(Animal),
	nl,nl,
	removeRespostas,
	write('Deseja que o computador adivinhe outro animal?'),
	analisa(X),nl,
	(X=n,!; fail).

analisa(X):-
	get(R),
	get0(_),
	validar(R,X).

validar(115,s):-!.
validar(110,n):-!.
validar(_,X):-
	write('Digite apenas s ou n'),nl,
	analisa(X).

valida_evidencia(X):-
	call(evidencia(X)),!,assert(sim(X)).
valida_evidencia(_):-
	write('Digite uma evidencia valida.'),nl.

evidencia(tem_pelo).
evidencia(tem_manchas_escuras).
evidencia(tem_listras_pretas).
evidencia(tem_pescoco_grande).
evidencia(tem_penas).
evidencia(tem_pernas_grandes).
evidencia(nao_voa).
evidencia(voa).
evidencia(nada).
evidencia(branco_e_preto).
evidencia(aparece_em_estoria_de_marinheiro).
evidencia(poe_ovos).
evidencia(amamenta).
evidencia(come_carne).
evidencia(tem_dentes_pontiagudos).
evidencia(tem_garras).
evidencia(tem_cascos).
evidencia(rumina).

/* hipoteses a serem testadas*/
hipotetiza(leopardo) :- leopardo, !.
hipotetiza(tigre) :- tigre, !.
hipotetiza(girafa) :- girafa, !.
hipotetiza(zebra) :- zebra, !.
hipotetiza(avestruz) :- avestruz, !.
hipotetiza(pinguim) :- pinguim, !.
hipotetiza(albatroz) :- albatroz, !.
hipotetiza(desconhecido). /* nao diagnosticado */

/* regras de identificação do animal */
leopardo :-
	mamifero,
	carnivoro,
	verifica(tem_manchas_escuras).
tigre :-
	mamifero,
	carnivoro,
	verifica(tem_listras_pretas).
girafa :-
	ungulado,
	verifica(tem_pescoco_grande),
	verifica(tem_pernas_grandes).
zebra :-
	ungulado,
	verifica(tem_listras_pretas).
avestruz :-
	passaro,
	verifica(nao_voa),
	verifica(tem_pescoco_grande).
pinguim :-
	passaro,
	verifica(nao_voa),
	verifica(nada),
	verifica(branco_e_preto).
albatroz :-
	passaro,
	verifica(aparece_em_estoria_de_marinheiro),
	verifica(voa).

/* regras de classificação */

mamifero :-
	verifica(tem_pelo), !.
mamifero :-
	verifica(amamenta).
passaro :-
	verifica(tem_penas), !.
passaro :-
	verifica(voa),
	verifica(poe_ovos).
carnivoro :-
	verifica(come_carne), !.
carnivoro :-
	verifica(tem_dentes_pontiagudos),
	verifica(tem_garras).
ungulado :-
	mamifero,
	verifica(tem_cascos), !.
ungulado :-
	mamifero,
	verifica(rumina).

/* formulando perguntas */
pergunta(Pergunta) :-
	write('O animal tem a seguinte caracteristica: '),
	write(Pergunta),
	write('? '),
	analisa(Resposta),
	nl,
	trate(Pergunta,Resposta).

trate(Pergunta,Resposta) :-
	Resposta == s,
	assert(sim(Pergunta)),!.
trate(Pergunta,_) :-
	assert(nao(Pergunta)),fail.

:- dynamic sim/1,nao/1.

/* verificando */

verifica(S) :- sim(S),!.
verifica(S) :- nao(S),!, fail.
verifica(S) :- pergunta(S).

/* remove todas as assercoes de sim e nao */
/*
removeRespostas :- retract(sim(_)),fail.
removeRespostas :- retract(nao(_)),fail.
*/
% removeRespostas :- abolish(sim/1),
% abolish(nao/1),dynamic(sim/1),dynamic(nao/1).

removeRespostas :- retractall(sim(_)), retractall(nao(_)).

le_atomo(Atomo) :-
	le_str(String),
	name(Atomo, String).

le_str(String) :-
	get0(Char),
	le_str_aux(Char, String).

le_str_aux(-1, []) :- !.
le_str_aux(10, []) :- !.
le_str_aux(13, []) :- !.
le_str_aux(Char, [Char|Resto]) :-
	le_str(Resto).
