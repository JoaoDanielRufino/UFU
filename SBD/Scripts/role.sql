	/*ROLE*/

CREATE ROLE pedro WITH LOGIN PASSWORD 'pedrim';

grant select on jogador,selecao,juiz,tecnico to pedro;

grant all on schema copa to pedro;
create schema copa;

create role joao;
create role pedro;

grant all on schema copa to joao;
grant insert on jogador to pedro;

set role joao;

create table jogador(
	id int not null,
	nome varchar(30)
);

insert into jogador values(1, 'teste');

set role pedro;

grant all on schema copa to pedro;
set role joao;

grant joao to pedro;

set role to postgres;

insert into jogador values(2, 'pedro');

select * from jogador;