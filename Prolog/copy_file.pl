copia:-
    see('file_location'),
    tell('file_location'),
    copia_arq,
    told,seen.

copia_arq:-
    read(Termo),
    processa(Termo).

processa(end_of_file):-!.
processa(Termo):-
    copia_termo(Termo),
    copia_arq.

copia_termo(Termo):-
    write(Termo),
    write('.'),nl.
