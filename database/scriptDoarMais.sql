drop database if exists doarmais;
create database doarmais;
use doarmais;

create table tab_situacao(
	cd_situacao int not null,
    tx_situacao varchar(45),
    primary key (cd_situacao)
);

create table tab_tipo_usuario(
	cd_tipo_usuario int not null,
    tx_tipo_usuario varchar(45),
    primary key (cd_tipo_usuario)
);

create table tab_usuario(
	cd_usuario int not null auto_increment,
    cd_tipo_usuario int,
    cd_situacao int,
    tx_usuario varchar(200),
    tx_email varchar(200),
    tx_senha varchar(200),
    tx_telefone varchar(11),
    tx_documento varchar(14),
    tx_role varchar(45),
    img_comprovante_residencia blob,
    primary key (cd_usuario),
    foreign key (cd_tipo_usuario) references tab_tipo_usuario (cd_tipo_usuario),
    foreign key (cd_situacao) references tab_situacao (cd_situacao)
);

create table tab_endereco(
	cd_endereco int not null auto_increment,
    cd_usuario int,
    tx_cep varchar(8),
    tx_uf char(2),
    tx_cidade varchar(150),
    tx_bairro varchar(150),
    tx_logradouro varchar(200),
    cd_numero int,
    tx_complemento varchar(100),
    primary key (cd_endereco, cd_usuario),
    foreign key (cd_usuario) references tab_usuario (cd_usuario)
);

create table tab_tipo_denuncia(
	cd_tipo_denuncia int not null,
    tx_tipo_denuncia varchar(45),
    primary key (cd_tipo_denuncia)
);

create table tab_denuncia(
	cd_denuncia int not null auto_increment,
    cd_tipo_denuncia int,
    cd_usuario int,
    cd_situacao int,
    desc_denuncia text,
    dt_denuncia datetime,
    primary key (cd_denuncia),
    foreign key (cd_tipo_denuncia) references tab_tipo_denuncia (cd_tipo_denuncia),
    foreign key (cd_usuario) references tab_usuario (cd_usuario),
    foreign key (cd_situacao) references tab_situacao (cd_situacao)
);

create table tab_tipo_anuncio(
	cd_tipo_anuncio int not null,
    tx_tipo_anuncio varchar(45),
    primary key (cd_tipo_anuncio)
);

create table tab_anuncio(
	cd_anuncio int not null auto_increment,
    cd_tipo_anuncio int,
    dt_criacao_anuncio datetime,
    tx_titulo_anuncio varchar(100),
    tx_cep varchar(8),
    tx_cidade varchar(150),
    tx_uf char(2),
    primary key (cd_anuncio),
    foreign key (cd_tipo_anuncio) references tab_tipo_anuncio (cd_tipo_anuncio)
);

create table tab_proposta(
	cd_proposta int not null auto_increment,
	cd_usuario int,
    cd_usuario_aceito int,
    cd_situacao int,
    dt_agendada datetime,
    primary key (cd_proposta, cd_usuario, cd_usuario_aceito, cd_situacao),
    foreign key (cd_usuario) references tab_usuario (cd_usuario),
    foreign key (cd_usuario_aceito) references tab_usuario (cd_usuario),
    foreign key (cd_situacao) references tab_situacao (cd_situacao)
);

create table tab_categoria_item(
	cd_categoria_item int not null,
    tx_categoria_item varchar(45),
    primary key (cd_categoria_item)
);

create table tab_item_anuncio(
	cd_item int not null auto_increment,
    cd_anuncio int,
    cd_categoria_item int,
    qtd_item int,
    desc_item_anuncio text,
    primary key (cd_item),
    foreign key (cd_anuncio) references tab_anuncio (cd_anuncio),
    foreign key (cd_categoria_item) references tab_categoria_item (cd_categoria_item)
);

create table tab_item_anuncio_proposta(
	cd_proposta int,
    cd_item int,
    qtd_solicitada_item int,
    primary key (cd_proposta, cd_item),
    foreign key (cd_proposta) references tab_proposta (cd_proposta),
    foreign key (cd_item) references tab_item_anuncio (cd_item)
);

create table tab_reset_senha(
	cd_reset_senha int not null auto_increment,
    tx_email_usuario varchar(200),
    cd_situacao int,
    tx_token varchar(200),
    dt_validade_token datetime,
    primary key (cd_reset_senha)
);

create table tab_autenticacao_email(
	cd_autenticacao_email int not null auto_increment,
    tx_email_usuario varchar(200),
    cd_situacao int,
    tx_token varchar(200),
    primary key (cd_autenticacao_email)
);