:-op(600,yfx,e).
:-op(500,yfx,gera).
:-op(800,fy,concatena).

%Concatenate lists.
concatena [] e L gera L:-!.
concatena [X|Y] e L gera [X|L1]:-
	concatena Y e L gera L1.

:-op(500,yfx,existe_em).
:-op(600,fy,member).

%Check if an element belongs to a list.
member X existe_em [X]:-!.
member X existe_em [Y|Z]:-
    X=:=Y;
    member X existe_em Z.

:-op(500,yfx,em).
:-op(600,fy,delete).

%Remove the first occurrence of an element.
delete _ em [] gera [].
delete N em [N|Y] gera Y.
delete N em [X|Y] gera [X|L]:-
    N\=X,
    delete N em Y gera L.

:-op(600,fy,mdc).
:-op(500,fy,entre).

%Calculate the Maximum Common Divisor.
mdc entre X e X gera X.
mdc entre X e Y gera D:-
    X<Y,
    Y1 is Y-X,
    mdc entre X e Y1 gera D.
mdc entre X e Y gera D:-
    X>Y,
    mdc entre Y e X gera D.

:-op(600,fy,mdc2).

%Another way to calculate the Maximum Common Divisor.
mdc2 entre X e 0 gera X:-!.
mdc2 entre X e Y gera D:-
    X>=Y,
    Y1 is mod(X,Y),
    mdc2 entre Y e Y1 gera D.
mdc2 entre X e Y gera D:-
    X<Y,
    mdc2 entre Y e X gera D.

:-op(600,fy,mmc).

%Calculate the Minimum Common Divisor.
mmc entre X e Y gera D:-
    mdc2 entre X e Y gera D1,!,
    D is X*Y/D1.
