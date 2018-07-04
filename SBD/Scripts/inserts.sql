			/*TECNICOS*/
 --1
insert into tecnico values(default, 'Tite', 49, 'Brasil');
 --2
insert into tecnico values(default, 'Maradona', 69, 'Argentina');
--3
insert into tecnico values(default, 'Joachim Low', 58, 'Alemanha');
--4
insert into tecnico values(default, 'Jan Andersson', 56, 'Suecia');
--5
insert into tecnico values(default, 'Fernando Hierro', 46, 'Espanha');
--6
insert into tecnico values(default, 'Fernando Santos', 70, 'Portugal');
--7
insert into tecnico values(default, 'Hector Cuper', 64, 'Egito');
--8
insert into tecnico values(default, 'Jorge Sampaoli', 43, 'Chile');
--9
insert into tecnico values(default, 'Vladimir Petkovic', 43, 'Suica');
--10
insert into tecnico values(default, 'Stanislav', 68, 'Russia');

				/*SELECAO*/
--1
insert into selecao values(default, 'Brasil', 1,1);
insert into selecao values(default, 'Brasil', 1,11);
--2
insert into selecao values(default, 'Argentina', 2,2);

--3
insert into selecao values(default, 'Alemanha', 3,3);
--4
insert into selecao values(default, 'Suecia', 4,4);
--5
insert into selecao values(default, 'Espanha', 5,5);
--6
insert into selecao values(default, 'Portugal', 6,6);
--7
insert into selecao values(default, 'Egito', 7,7);
--8
insert into selecao values(default, 'Chile', 8,8);
--9
insert into selecao values(default, 'Suica', 9,9);
--10
insert into selecao values(default, 'Russia', 10,10);


			/*JOGADOR*/
--1
insert into jogador values(default, 'Neymar', 29, 'Brasileiro', 10, 0);
insert into jogador values(default, 'Neymar', 29, 'Brasileiro', 10, 0);
--2
insert into jogador values(default, 'Messi', 35, 'Argentino', 9, 1);
--3
insert into jogador values(default, 'Kwwase', 19, 'Alemao', 11, 2);
--4
insert into jogador values(default, 'Ragnar', 18, 'Sueco', 12, 0);
--5
insert into jogador values(default, 'Ruevo Morale', 21, 'Espanhol', 14, 1);
--6
insert into jogador values(default, 'Cristiano', 28, 'Portugues', 6, 1);
--7
insert into jogador values(default, 'Salen', 25, 'Egipcio', 7, 2);
--8
insert into jogador values(default, 'Juan', 19, 'Chileno', 3, 0);
--9
insert into jogador values(default, 'Ragnar', 20, 'Suico', 4, 0);
--10
insert into jogador values(default, 'Vladmir', 18, 'Russo', 5, 1);


				/*CIDADES*/

--1
insert into cidade values (default,'São Petersburgo');
--2
insert into cidade values (default,'Kazan');
--3
insert into cidade values (default,'Caliningrado');
--4
insert into cidade values (default,'Moscou');
--5
insert into cidade values (default,'Sochi');
--6
insert into cidade values (default,'Volgogrado');
--7
insert into cidade values (default,'Saransk');
--8
insert into cidade values (default,'Samara');
--9
insert into cidade values (default,'Iecaterimburgo');
--10
insert into cidade values (default,'Rostov-on-don');
--11
insert into cidade values (default,'Níjni Novgorod');

					/*ESTAGIOS*/

--1
insert into estadio values (default, 4,'Lujniki',80000);
--2
insert into estadio values(default, 4,'Spartak',45000);
--3

--4
insert into estadio values(default, 5,'E.O.de Sochi',48000);
--5
insert into estadio values(default, 2,'Arena Kazan',45000);
--6
insert into estadio values(default, 8,'Arena Samara',45000);
--7
insert into estadio values(default, 10,'Arena Rostov',45000);
--8
insert into estadio values(default, 9,'Arena Ecaterimburgo',35000);
--9
insert into estadio values(default, 3,'E.Kaliningrado',35000);
--10
insert into estadio values(default, 7,'E.Saransk',44000);
--11
insert into estadio values(default, 6,'Arena Volgogrado',45000);

							/*PARTIDAS*/

--1
insert into partida values (default,1,1,'2018-06-23','18:30',1,2);
--2
insert into partida values (default,4,2,'2018-06-25','19:30',1,3);
--3
insert into partida values (default,4,3,'2018-07-10','22:00',1,4);
--4
insert into partida values (default,5,4,'2018-07-01','17:30',1,5);
--5
insert into partida values (default,2,5,'2018-07-15','15:00',1,6);
--6
insert into partida values (default,8,6,'2018-07-12','09:30',1,7);
--7
insert into partida values (default,10,7,'2018-07-25','10:00',1,8);
--8
insert into partida values (default,9,8,'2018-06-29','12:00',1,9);
--9
insert into partida values (default,3,9,'2018-06-30','13:30',1,10);
--10
insert into partida values (default,7,10,'2018-06-28','14:00',2,6);

	/*NARRADORES*/
--1
insert into narradores values (default,1,'Galvão Bueno','Brasileiro','Globo');
--2
insert into narradores values (default,3,'Kyrostzi','Alemão','Deutsche Welle');
--3
insert into narradores values (default,2,'Rose','Argentino','America Tv');
--4
insert into narradores values (default,4,'Akbhar','Egpicio','Alahukbar');
--5
insert into narradores values (default,6,'Karin','Sueco','Sveriges');
--6
insert into narradores values (default,7,'Britta','Espanhol','Delicast');
--7
insert into narradores values (default,5,'Ronaldo','Portugues','TDT');
--8
insert into narradores values (default,9,'Ermano','Chileno','TVN TV');
--9
insert into narradores values (default,10,'Leon','Suico','GGN');
--10
insert into narradores values (default,8,'Ivan','Russo','RT');

								/*JUIZ*/
--1
insert into juiz values (default,'José','Brasileiro',1);
--2
insert into juiz values (default,'Covawski','Alemão',2);
--3
insert into juiz values(default,'Greta','Sueco',3);
--4
insert into juiz values(default,'Nico','Suico',4);
--5
insert into juiz values(default,'Moises','Egipcio',5);
--6
insert into juiz values(default,'Nikolai','Russo',6);
--7
insert into juiz values(default,'Pedro','Portugues',7);
--8
insert into juiz values(default,'Vicente','Chileno',8);
--9
insert into juiz values(default,'Pablo','Argentino',9);
--10
insert into juiz values(default,'Lorenzo','Espanhol',10);

							/*HISTORICO_PARTIDAS*/
--1
insert into historico_partidas values(default,1,'Brasil',5,'Argentina',1);
--2
insert into historico_partidas values(default,1,'Brasil',7,'Alemanha',1);
--3
insert into historico_partidas values(default,1,'Brasil',4,'Suecia',3);
--4
insert into historico_partidas values(default,1,'Brasil',1,'Espanha',0);
--5
insert into historico_partidas values(default,1,'Brasil',7,'Portugal',1);
--6
insert into historico_partidas values(default,1,'Brasil',3,'Egito',2);
--7
insert into historico_partidas values(default,1,'Brasil',2,'Chile',0);
--8
insert into historico_partidas values(default,1,'Brasil',2,'Suica',1);
--9
insert into historico_partidas values(default,1,'Brasil',5,'Russia',0);
--10
insert into historico_partidas values(default,1,'Portugal',1,'Argentina',0);


					/*HOSPEDAGENS*/

--1
insert into hospedagem values (default,'Von del Piza',1,'5 estrelas',1);
--2
insert into hospedagem values (default,'Capone',2,'1 estrela',2);
--3
insert into hospedagem values (default,'Monte Vulca',4,'5 estrelas',4);
--4
insert into hospedagem values (default,'Pan del Mare',3,'4 estrelas',3);
--5
insert into hospedagem values (default,'Hostamarica',6,'3 estrelas',10);
--6
insert into hospedagem values (default,'Mariegoes',5,'5 estrelas',6);
--7
insert into hospedagem values (default,'Zambrostiz',7,'2 estrelas',7);
--8
insert into hospedagem values (default,'UnderBreak',10,'5 estrelas',8);
--9
insert into hospedagem values (default,'Bones',8,'5 estrelas',5);
--10
insert into hospedagem values (default,'Don Pierre',9,'4.5 estrelas',9);

						/*PATROCINADORES*/

--1
insert into patrocinador values (default,'Coca-Cola');
--2
insert into patrocinador values (default,'Gatorade');
--3
insert into patrocinador values (default,'Volkswagen');
--4
insert into patrocinador values (default,'PoweRade');
--5
insert into patrocinador values (default,'Algar Telecom');
--6
insert into patrocinador values (default,'Ambev');
--7
insert into patrocinador values (default,'Ford');
--8
insert into patrocinador values (default,'MecDonalds');
--9
insert into patrocinador values (default,'Fiat');
--10
insert into patrocinador values (default,'Zup');

						/*PATROCINA*/


--1
insert into patrocina values (default,5,1);
--2
insert into patrocina values (default,1,4);
--3
insert into patrocina values (default,6,3);
--4
insert into patrocina values (default,7,5);
--5
insert into patrocina values (default,9,2);
--6
insert into patrocina values (default,10,9);
--7
insert into patrocina values (default,2,6);
--8
insert into patrocina values (default,3,8);
--9
insert into patrocina values (default,4,7);
--10
insert into patrocina values (default,8,10);

				/*INGRESSO*/


--1
insert into ingresso values (default,'GamersClub',100.75,20000,1,'Primeiro');
--2
insert into ingresso values (default,'Kabum',250.15,25500,2,'Segundo');
--3
insert into ingresso values (default,'Pichau',150.55,30000,3,'Primeiro');
--4
insert into ingresso values (default,'GranMaster',115.75,10500,4,'Terceiro');
--5
insert into ingresso values (default,'Mercado Livre',128.71,23200,5,'Segundo');
--6
insert into ingresso values (default,'Zoom',90.50,13000,6,'Quarto');
--7
insert into ingresso values (default,'Americanas',180.90,24000,7,'Terceiro');
--8
insert into ingresso values (default,'Rocketz',105.78,27400,8,'Primeiro');
--9
insert into ingresso values (default,'Kalunga',166.30,28000,9,'Quarto');
--10
insert into ingresso values (default,'Submarino',210.25,45000,10,'Segundo');

				/*PESSOAS*/ 

--1
insert into pessoa values (default,'Marcos Jackson');
--2
insert into pessoa values (default,'João das Couves');
--3
insert into pessoa values (default,'Zé do Caixão');
--4
insert into pessoa values (default,'Freddie Mercury');
--5
insert into pessoa values (default,'Francisco');
--6
insert into pessoa values (default,'Camila');
--7
insert into pessoa values (default,'Juan Jack Russo');
--8
insert into pessoa values (default,'Barba Burger');
--9
insert into pessoa values (default,'Edward Teach');
--10
insert into pessoa values (default,'Anny Bone');


				/*COMPRA*/

--1
insert into compra values (1,1,10);

--2
insert into compra values (4,1,14);
insert into compra values (2,1,3);
--3
insert into compra values (3,4,10);
--4
insert into compra values (4,3,9);
--5
insert into compra values (5,7,10);
--6
insert into compra values (6,2,6);
--7
insert into compra values (7,10,20);
--8
insert into compra values (8,6,5);
--9
insert into compra values (9,5,15);
--10
insert into compra values (10,8,7);
--11
insert into compra values (4,9,1);
