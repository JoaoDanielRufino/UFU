
		/*SELECTS*/
--1
select j.nome from jogador j, tecnico t, selecao s 
where t.nome = 'Tite' and s.idtecnico = t.idtecnico and s.idjogador = j.idjogador;


--2
select j.nome,j.idade,j.nacionalidade,j.numerocamisa,j.cartoesacumulados from copa.jogador j, copa.selecao s 
where  j.idjogador = s.idjogador and s.nomeselecao = 'Brasil';

--3
select * from partida;
/*Pessoa que comprou mais ingressos de um jogo*/
select nome from pessoa p, compra c, ingresso i  
where p.idpessoa = c.idpessoa and i.idingresso = c.idingresso and 
c.quantidade = (select max(quantidade) from compra);

/*Somatorio de Ingressos comprados por pessoa*/
--4
select p.nome,sum(c.quantidade) from pessoa p, compra c, ingresso i  
where p.idpessoa = c.idpessoa and i.idingresso = c.idingresso group by p.nome order by p.nome;

/*Media de ingressos vendido por partida*/
	--5								
select i.idpartida,avg(c.quantidade) from compra c , ingresso i, partida pa
where c.idingresso = i.idingresso and i.idpartida = pa.idpartida group by i.idpartida order by i.idpartida;
--6
/*Quem comprou menos ingresso*/
select nome from pessoa p, compra c, ingresso i  
where p.idpessoa = c.idpessoa and i.idingresso = c.idingresso and 
c.quantidade = (select min(quantidade) from compra);
--7
select j.nome,j.idade,j.nacionalidade,j.numerocamisa,j.cartoesacumulados from copa.jogador j, copa.selecao s 
		where j.idjogador = s.idjogador and upper(s.nomeselecao) = upper('Brasil');
--8
select s.nomeselecao from copa.selecao s, copa.jogador j where s.idjogador = j.idjogador and upper(j.nome) = upper('Neymar');
--9
select distinct e.nome from copa.estadio e, copa.selecao s,copa.partida p where upper(s.nomeselecao) = upper('Argentina') 
						and  (p.idselecao2 =s.idselecao or p.idselecao1 = s.idselecao)
						and p.idestadio = e.idestadio;
--10
select j.nome, j.nacionalidade from copa.juiz j, copa.partida p , copa.selecao s 
					where j.idpartida = p.idpartida and (s.idselecao = p.idselecao1 or s.idselecao = p.idselecao2)
					and upper(s.nomeselecao) = upper('alemanha');
--11
select p.nome , sum(c.quantidade) as sum from copa.pessoa p ,copa.compra c 
					where p.idpessoa = c.idpessoa group by p.nome having upper(p.nome) like upper('j%');
--12
select ci.nome as cinome,e.nome as enome,s.nomeselecao as snome,c.nomeselecao as cnome 
					from copa.selecao s, copa.selecao c,copa.partida p,copa.cidade ci, copa.estadio e 
					where s.idselecao = p.idselecao1
					and p.idselecao2 = c.idselecao and e.idestadio = p.idestadio and ci.idcidade = p.idcidade 
					and (upper(s.nomeselecao) = upper('Brasil')or upper(c.nomeselecao) = upper('Argentina'));
--13
select i.localvenda,i.preco,i.quantidade,s.nomeselecao as s1nome,s2.nomeselecao as s2nome,i.lote
					from copa.ingresso i, copa.partida p, copa.selecao s, copa.selecao s2
					where p.idpartida = i.idpartida and p.idselecao1 = s.idselecao 
					and p.idselecao2 = s2.idselecao and (upper(s.nomeselecao) = upper('Brasil') or upper(s2.nomeselecao) = upper('Alemanha'));
--14
select p.nome,s1.nomeselecao as nomes1,s2.nomeselecao as nomes2,c.quantidade from copa.compra c, copa.pessoa p, copa.ingresso i, copa.partida pa,
					copa.selecao s1, copa.selecao s2
					where p.idpessoa = c.idpessoa and c.idingresso = i.idingresso and pa.idpartida = i.idpartida and pa.idselecao1 = s1.idselecao 
					and s2.idselecao = pa.idselecao2 and upper(p.nome) like upper('%$input%') order by p.nome;
