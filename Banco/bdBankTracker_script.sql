create database bdBankTracker;
use bdBankTracker;

create table Banco(
	idBanco int primary key auto_increment,
    nome varchar(45),
    cep char(8),
    cnpj char(14),
    logradouro varchar(100),
    numeroLogradouro varchar(10),
    telefone char(9)
);

create table Agencia(
	numAgencia int primary key auto_increment,
    nome varchar(45),
	cep char(8),
    cnpj char(14),
    logradouro varchar(100),
    numeroLogradouro varchar(10),
	telefone char(9),
    senha varchar(45),
    fk_banco int,
    foreign key (fk_banco) references Banco (idBanco) 
);

create table CaixaEletronico(
	idCaixaEletronico int primary key auto_increment,
    sistemaOperacional varchar(45),
    cpuCaixa bigint,
    processador varchar(45),
    ram bigint,
    hd bigint,
    hostname varchar(45),
    rede bigint,
    dataFabricacao date,
    dataRevisao date,
    ativo char(1),
    fk_agencia char(4)
);

create table Suporte(
	idSuporte int primary key auto_increment,
    nomeCompleto varchar(60),
    cpf char(11),
    telefone char(11),
    email varchar(100),
    senha varchar(45),
    fk_numAgencia char(4)
);

create table Historico(
	idHistorico int primary key auto_increment,
	cpuCaixa bigint,
    processador varchar(45),
    ram bigint,
    hd bigint,
    rede bigint,
    dataHora datetime,
    fk_caixaEletronico int,
    foreign key (fk_caixaEletronico) references CaixaEletronico (idCaixaEletronico)
);

create table Incidente(
	idIncidente int primary key auto_increment,
    descricaoIncidente varchar(255),
    dataHoraInicio datetime,
    dataHoraFinal datetime,
	fk_suporte int,
    fk_caixaEletronico int,
    foreign key (fk_suporte) references Suporte (idSuporte),
    foreign key (fk_caixaEletronico) references CaixaEletronico (idCaixaEletronico)
);

