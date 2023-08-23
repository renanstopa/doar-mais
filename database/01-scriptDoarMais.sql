drop database if exists doarmais;
create database doarmais;
use doarmais;

-- CRIAÇÃO DO BANCO

create table situacao(
	id int not null,
    descricao varchar(45),
    primary key (id)
);

create table tipo_usuario(
	id int not null,
    descricao varchar(45),
    primary key (id)
);

create table usuario(
	id int not null auto_increment,
    id_tipo_usuario int,
    id_situacao int,
    nome varchar(200),
    email varchar(200),
    senha varchar(200),
    telefone varchar(11),
    documento varchar(14),
    cargo varchar(45),
    comprovante_residencia blob,
    primary key (id),
    foreign key (id_tipo_usuario) references tipo_usuario (id),
    foreign key (id_situacao) references situacao (id)
);

create table endereco(
	id int not null auto_increment,
    id_usuario int,
    cep varchar(8),
    uf char(2),
    cidade varchar(150),
    bairro varchar(150),
    logradouro varchar(200),
    numero int,
    complemento varchar(100),
    ativo int,
    primary key (id, id_usuario),
    foreign key (id_usuario) references usuario (id)
);

create table tipo_denuncia(
	id int not null,
    descricao varchar(45),
    primary key (id)
);

create table denuncia(
	id int not null auto_increment,
    id_tipo_denuncia int,
    id_usuario int,
    id_usuario_denunciado int,
    id_situacao int,
    descricao text,
    data_criacao datetime,
    primary key (id),
    foreign key (id_tipo_denuncia) references tipo_denuncia (id),
    foreign key (id_usuario) references usuario (id),
    foreign key (id_usuario_denunciado) references usuario (id),
    foreign key (id_situacao) references situacao (id)
);

create table tipo_anuncio(
	id int not null,
    descricao varchar(45),
    primary key (id)
);

create table anuncio(
	id int not null auto_increment,
    id_tipo_anuncio int,
    id_usuario_criador int,
    id_situacao int,
    data_criacao datetime,
    titulo varchar(100),
    data_inicio_disponibilidade datetime,
    data_fim_disponibilidade datetime,
    cep varchar(8),
    uf char(2),
    cidade varchar(150),
    bairro varchar(150),
    logradouro varchar(200),
    numero int,
    complemento varchar(100),
    ponto_referencia varchar(100),
    quantidade_proposta int,
    primary key (id),
    foreign key (id_tipo_anuncio) references tipo_anuncio (id),
    foreign key (id_usuario_criador) references usuario (id),
    foreign key (id_situacao) references situacao (id)
);

create table proposta(
	id int not null auto_increment,
    id_usuario int,
    id_anuncio int,
    id_situacao int,
    data_agendada datetime,
    primary key (id),
    foreign key (id_usuario) references usuario (id),
    foreign key (id_anuncio) references anuncio (id),
    foreign key (id_situacao) references situacao (id)
);

create table categoria_item(
	id int not null,
    descricao varchar(45),
    primary key (id)
);

create table item_anuncio(
	id int not null auto_increment,
    id_anuncio int,
    id_categoria_item int,
    nome varchar(60),
    quantidade int,
    descricao text,
    primary key (id),
    foreign key (id_anuncio) references anuncio (id),
    foreign key (id_categoria_item) references categoria_item (id)
);

create table item_anuncio_proposta(
    id int not null auto_increment,
	id_proposta int,
    id_item int,
    quantidade_solicitada int,
    primary key (id),
    foreign key (id_proposta) references proposta (id),
    foreign key (id_item) references item_anuncio (id)
);

create table reset_senha(
	id int not null auto_increment,
    email_usuario varchar(200),
    id_situacao int,
    token varchar(200),
    data_validade datetime,
    primary key (id)
);

create table autenticacao_email(
	id int not null auto_increment,
    email_usuario varchar(200),
    id_situacao int,
    token varchar(200),
    primary key (id)
);

create table punicao(
    id int not null auto_increment,
    id_usuario int,
    data_agendada datetime,
    data_cancelamento datetime,
    motivo text,
    primary key (id)
);

-- FIM BANCO

-- CRIAÇÃO DAS VIEWS

-- drop view if exists vw_perfil_usuario;
create view
	vw_perfil_usuario
as select
	u.id, e.id as id_endereco, u.nome,
    case
        when length(u.telefone) = 11 then concat('(', substring(u.telefone, 1, 2), ') ', substring(u.telefone, 3, 5), '-', substring(u.telefone, 8, 4))
        when length(u.telefone) = 10 then concat('(', substring(u.telefone, 1, 2), ') ', substring(u.telefone, 3, 4), '-', substring(u.telefone, 7, 4))
        else u.telefone
    end as
		telefone,
    case
        when u.id = 1 then concat(substring(u.documento, 1, 3), '.', substring(u.documento, 4, 3), '.', substring(u.documento, 7, 3), '-', substring(u.documento, 10, 2))
        when u.id = 2 then concat(substring(u.documento, 1, 2), '.', substring(u.documento, 3, 3), '.', substring(u.documento, 6, 3), '/', substring(u.documento, 9, 4), '-', substring(u.documento, 13, 2))
        else u.documento
    end as
		documento, concat(substring(e.cep, 1, 5), '-', substring(e.cep, 6, 8)) as cep,
        e.uf, upper(e.cidade) as cidade, upper(e.bairro) as bairro, upper(e.logradouro) as logradouro,
        e.numero, upper(e.complemento) as complemento
from
	usuario u
join
	endereco e
on
	(u.id = e.id_usuario)
where
	e.ativo = 1;

-- drop view if exists vw_busca_anuncio
create view
	vw_busca_anuncio
as select
	a.id, a.id_tipo_anuncio, u.id_tipo_usuario, a.id_usuario_criador, u.nome, a.titulo,
    a.data_inicio_disponibilidade,  a.data_fim_disponibilidade, a.cidade
from
	anuncio a
join
	usuario u
on
	(a.id_usuario_criador = u.id)
where
	a.id_situacao = 21;

-- drop view if exists vw_consulta_anuncio
create view
	vw_consulta_anuncio
as select
	a.id, a.id_usuario_criador, a.titulo, a.data_inicio_disponibilidade, a.data_fim_disponibilidade,
    concat(concat_ws(', ', a.logradouro, a.numero, if(a.complemento is not null and a.complemento != '', a.complemento, null)),
    if(a.ponto_referencia is not null and a.ponto_referencia != '', concat(', ', a.ponto_referencia), ''), ' - ', a.cidade, ', ', a.bairro, ' - ', a.uf) as endereco_completo,
    u.nome,
    case
        when length(u.telefone) = 11 then concat('(', substring(u.telefone, 1, 2), ') ', substring(u.telefone, 3, 5), '-', substring(u.telefone, 8, 4))
        when length(u.telefone) = 10 then concat('(', substring(u.telefone, 1, 2), ') ', substring(u.telefone, 3, 4), '-', substring(u.telefone, 7, 4))
        else u.telefone
    end as
		telefone
from
	anuncio a
join
	usuario u
on
	(a.id_usuario_criador = u.id)
where
	a.id_situacao = 21;

-- FIM DAS VIEWS