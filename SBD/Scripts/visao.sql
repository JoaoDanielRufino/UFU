create view info_part (cidade,estadio,selecao1,selecao2) as 
select ci.nome as cinome,e.nome as enome,s.nomeselecao as snome,c.nomeselecao as cnome from copa.selecao s, copa.selecao c,copa.partida p,copa.cidade ci, copa.estadio e 
where s.idselecao = p.idselecao1
and p.idselecao2 = c.idselecao and e.idestadio = p.idestadio and ci.idcidade = p.idcidade;



create or replace function trigger_vision ()
returns trigger as $$ 
begin
	insert into info_part (cidade,estadio,selecao1,selecao2)
	(select ci.nome ,e.nome ,s.nomeselecao ,c.nomeselecao 
	from copa.selecao s, copa.selecao c,copa.partida p,copa.cidade ci, copa.estadio e 
	where s.idselecao = p.idselecao1
	and p.idselecao2 = c.idselecao and e.idestadio = p.idestadio and ci.idcidade = p.idcidade ) ;
	return new;
end $$ language  'plpgsql';

create trigger att_vision after insert on partida
for each row execute procedure trigger_vision();

insert into partida values (default,6,7,'2018-07-29','22:30',3,9);