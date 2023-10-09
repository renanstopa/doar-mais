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
    arquivo text,
    caminho_arquivo text,
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

create table troca_endereco(
	id int not null auto_increment,
    id_usuario int,
    cep varchar(8),
    uf char(2),
    cidade varchar(150),
    bairro varchar(150),
    logradouro varchar(200),
    numero int,
    complemento varchar(100),
    comprovante_residencia blob,
    primary key (id, id_usuario)
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
	(u.id = e.id_usuario);

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
	a.id, a.id_usuario_criador, a.titulo,
    concat(substring(a.cep, 1, 5), '-', substring(a.cep, 6, 8)) as cep, a.uf, a.cidade, a.bairro, a.logradouro, a.numero, a.complemento,
    a.ponto_referencia, date_format(a.data_inicio_disponibilidade, '%d/%m/%Y %H:%i:%s') as data_inicio_disponibilidade,
    date_format(a.data_fim_disponibilidade, '%d/%m/%Y %H:%i:%s') as data_fim_disponibilidade, u.nome,
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

-- drop view if exists vw_busca_propostas_agendadas
create view
    vw_busca_propostas_agendadas
as select
   	p.id, p.id_usuario, u.id_tipo_usuario, p.id_anuncio, a.id_tipo_anuncio, u.nome,
   	case
        when length(u.telefone) = 11 then concat('(', substring(u.telefone, 1, 2), ') ', substring(u.telefone, 3, 5), '-', substring(u.telefone, 8, 4))
        when length(u.telefone) = 10 then concat('(', substring(u.telefone, 1, 2), ') ', substring(u.telefone, 3, 4), '-', substring(u.telefone, 7, 4))
        else u.telefone
    end as
   		telefone, a.titulo, a.cidade, date_format(p.data_agendada, '%d/%m/%Y %H:%i:%s') as data_agendada, p.data_agendada as data_filtro
from
    proposta p
join
    anuncio a
on
    (p.id_anuncio = a.id)
join
    usuario u
on
    (p.id_usuario = u.id)
where
     p.id_situacao = 32;

-- drop view if exists vw_busca_propostas_pendentes
create view
    vw_busca_propostas_pendentes
as select
   	p.id, p.id_usuario, u.id_tipo_usuario, p.id_anuncio, a.id_tipo_anuncio, u.nome,
   	case
        when length(u.telefone) = 11 then concat('(', substring(u.telefone, 1, 2), ') ', substring(u.telefone, 3, 5), '-', substring(u.telefone, 8, 4))
        when length(u.telefone) = 10 then concat('(', substring(u.telefone, 1, 2), ') ', substring(u.telefone, 3, 4), '-', substring(u.telefone, 7, 4))
        else u.telefone
    end as
   		telefone, a.titulo, a.cidade, date_format(p.data_agendada, '%d/%m/%Y %H:%i:%s') as data_agendada, p.data_agendada as data_filtro
from
    proposta p
join
    anuncio a
on
    (p.id_anuncio = a.id)
join
    usuario u
on
    (p.id_usuario = u.id)
where
     p.id_situacao = 31;

-- drop view if exists vw_busca_propostas_historico
create view
    vw_busca_propostas_historico
as select
    p.id, p.id_usuario, u.id_tipo_usuario, p.id_anuncio, a.id_tipo_anuncio, p.id_situacao, s.descricao, u.nome,
    case
       when length(u.telefone) = 11 then concat('(', substring(u.telefone, 1, 2), ') ', substring(u.telefone, 3, 5), '-', substring(u.telefone, 8, 4))
       when length(u.telefone) = 10 then concat('(', substring(u.telefone, 1, 2), ') ', substring(u.telefone, 3, 4), '-', substring(u.telefone, 7, 4))
       else u.telefone
    end as
        telefone, a.titulo, a.cidade, date_format(p.data_agendada, '%d/%m/%Y %H:%i:%s') as data_agendada, p.data_agendada as data_filtro
from
    proposta p
join
    anuncio a
on
    (p.id_anuncio = a.id)
join
    usuario u
on
    (p.id_usuario = u.id)
join
    situacao s
on
    (p.id_situacao = s.id)
where
    p.id_situacao in (33, 34, 36, 37);

-- drop view if exists vw_consulta_prosposta
create view
    vw_consulta_prosposta
as select
   	p.id, s.descricao, uc.id as id_usuario_proposta, uc.nome as nome_usuario_proposta,
   case
       when length(uc.telefone) = 11 then concat('(', substring(uc.telefone, 1, 2), ') ', substring(uc.telefone, 3, 5), '-', substring(uc.telefone, 8, 4))
       when length(uc.telefone) = 10 then concat('(', substring(uc.telefone, 1, 2), ') ', substring(uc.telefone, 3, 4), '-', substring(uc.telefone, 7, 4))
       else uc.telefone
   end as
        telefone_usuario_proposta, ua.id as id_usuario_anuncio, ua.nome as nome_usuario_anuncio,
   case
       when length(ua.telefone) = 11 then concat('(', substring(ua.telefone, 1, 2), ') ', substring(ua.telefone, 3, 5), '-', substring(ua.telefone, 8, 4))
       when length(ua.telefone) = 10 then concat('(', substring(ua.telefone, 1, 2), ') ', substring(ua.telefone, 3, 4), '-', substring(ua.telefone, 7, 4))
       else ua.telefone
   end as
        telefone_usuario_anuncio,
   a.titulo, concat(substring(a.cep, 1, 5), '-', substring(a.cep, 6, 8)) as cep, a.uf, a.cidade, a.bairro, a.logradouro, a.numero, a.complemento,
   a.ponto_referencia, date_format(p.data_agendada, '%d/%m/%Y %H:%i:%s') as data_agendada
from
    proposta p
join
    usuario uc
on
    (p.id_usuario = uc.id)
join
    usuario ua
on
    ((select ac.id_usuario_criador from anuncio ac where ac.id = p.id_anuncio) = ua.id)
join
    anuncio a
on
    (p.id_anuncio = a.id)
join
    situacao s
on
    (p.id_situacao = s.id);

-- FIM DAS VIEWS