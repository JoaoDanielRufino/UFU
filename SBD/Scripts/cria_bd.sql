CREATE SCHEMA copa;

SET search_path to copa;

CREATE TABLE tecnico (
	idtecnico SERIAL,
    nome varchar(20),
    idade int,
    nacionalidade varchar(20),
    CONSTRAINT id_tecnico PRIMARY KEY (idtecnico)
);

CREATE TABLE jogador(
	idjogador SERIAL,
    nome varchar(20),
    idade int,
    nacionalidade varchar(20),
    numerocamisa int,
    cartoesacumulados int,
    CONSTRAINT id_jogador PRIMARY KEY (idjogador)
);

CREATE TABLE selecao(
	idselecao SERIAL,
    nomeselecao varchar(20),
    idtecnico int,
    idjogador int, // Atributo Multivalorado
    CONSTRAINT id_selecao PRIMARY KEY (idselecao),
    CONSTRAINT id_tecnico FOREIGN KEY (idtecnico)
    REFERENCES tecnico (idtecnico),
 CONSTRAINT id_jogador FOREIGN KEY (idjogador)
    REFERENCES jogador (idjogador)
);



CREATE TABLE cidade(
	idcidade SERIAL,
    nome varchar(20),
    CONSTRAINT id_cidade PRIMARY KEY (idcidade)
);

CREATE TABLE estadio(
	idestadio SERIAL,
    idcidade int,
    nome varchar(20),
    lotacaomaxima int,
    CONSTRAINT id_estadio PRIMARY KEY (idestadio),
    CONSTRAINT id_cidade FOREIGN KEY (idcidade) 
    REFERENCES cidade (idcidade)
);

CREATE TABLE hospedagem(
	idhospedagem SERIAL,
    nome varchar(20),
    idselecao int,
    descricao TEXT,
    idcidade int,
    CONSTRAINT id_hospedagem PRIMARY KEY (idhospedagem),
    CONSTRAINT id_selecao FOREIGN KEY (idselecao)
    REFERENCES selecao (idselecao),
    CONSTRAINT id_cidade FOREIGN KEY (idcidade)
    REFERENCES cidade (idcidade)
);

CREATE TABLE partida(
	idpartida SERIAL,
    idcidade int, //foreign key
    idestadio int, //foreign key
    data date,
    horainicio varchar(20),
    idselecao1 int,
    idselecao2 int,
    CONSTRAINT id_partida PRIMARY KEY (idpartida),
    CONSTRAINT id_selecao1 FOREIGN KEY (idselecao1)
    REFERENCES selecao (idselecao),
    CONSTRAINT id_selecao2 FOREIGN KEY (idselecao2)
    REFERENCES selecao (idselecao)
); 

CREATE TABLE programacao(
	idprogramacao SERIAL,
    idpartida int,
    CONSTRAINT id_programacao PRIMARY KEY (idprogramacao),
    CONSTRAINT id_partida FOREIGN KEY (idpartida)
    REFERENCES partida (idpartida)
);

CREATE TABLE ingresso(
	idingresso SERIAL,
    localvenda varchar(20),
    preco float,
    quantidade int,
    idpartida int,
    lote varchar(10),
    preferencia varchar(20), --vpp
    numberobanco int,
    numerofileira int,
    CONSTRAINT id_ingresso PRIMARY KEY (idingresso),
    CONSTRAINT id_partida FOREIGN KEY (idpartida)
    REFERENCES partida (idpartida)
);

CREATE TABLE historico_partidas(
	idhistorico SERIAL,
    idpartida int,
    vencedor varchar(20),
    placar_vencedor int,
    perdedor varchar(20),
    placar_perdedor int,
    CONSTRAINT id_historico PRIMARY KEY (idhistorico),
    CONSTRAINT id_partida FOREIGN KEY (idpartida)
    REFERENCES partida (idpartida)
);

CREATE TABLE narradores(
	idnarrador SERIAL,
    idpartida int,
    nome varchar(20),
    nacionalidade varchar(20),
    emissora_tv varchar(20),
    CONSTRAINT id_narrador PRIMARY KEY (idnarrador),
    CONSTRAINT id_partida FOREIGN KEY (idpartida)
    REFERENCES partida (idpartida)
);

CREATE TABLE juiz(
	idjuiz SERIAL,
    nome varchar(20),
    nacionalidade varchar(20),
    idpartida int,
    CONSTRAINT id_juiz PRIMARY KEY (idjuiz),
    CONSTRAINT id_partida FOREIGN KEY (idpartida)
    REFERENCES partida (idpartida)
);

CREATE TABLE patrocinador(
	idpatrocinador SERIAL,
    nomepatrocinador varchar(20),
    CONSTRAINT id_patrocinador PRIMARY KEY (idpatrocinador)
);

CREATE TABLE patrocina(
    idpatrocinado SERIAL,
    idpatrocinador int,
    idselecao int,
    CONSTRAINT id_patrocinado PRIMARY KEY (idpatrocinado),
    CONSTRAINT id_patrocinador FOREIGN KEY (idpatrocinador)
    REFERENCES patrocinador (idpatrocinador),
    CONSTRAINT id_selecao FOREIGN KEY (idselecao)
    REFERENCES selecao (idselecao)
);

create table pessoa(
	idpessoa serial,
	nome varchar(50),
	PRIMARY KEY (idpessoa)
);

create table compra(
	idpessoa int,
	idingresso int,
	FOREIGN KEY (idpessoa) REFERENCES pessoa (idpessoa),
	foreign key (idingresso) references ingresso(idingresso)	
);
