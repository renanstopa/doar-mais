drop database if exists doarmais;
create database doarmais;
use doarmais;

create table tab_situacao(
	cd_situacao int not null auto_increment,
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
    fl_tipo_documento char(1),
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
    tx_cep varchar(9),
    tx_uf char(2),
    tx_cidade varchar(150),
    tx_logradouro varchar(200),
    cd_numero int,
    tx_complemento varchar(100),
    primary key (cd_endereco, cd_usuario),
    foreign key (cd_usuario) references tab_usuario (cd_usuario)
);

create table tab_denuncia(
	cd_denuncia int not null auto_increment,
    cd_usuario int,
    cd_situacao int,
    desc_denuncia varchar(400),
    dt_denuncia date,
    primary key (cd_denuncia, cd_usuario),
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
    dt_criacao_anuncio date,
    desc_anuncio varchar(400),
    tx_cep varchar(9),
    tx_cidade varchar(150),
    tx_uf char(2),
    primary key (cd_anuncio),
    foreign key (cd_tipo_anuncio) references tab_tipo_anuncio (cd_tipo_anuncio)
);

create table tab_proposta(
	cd_usuario int,
    cd_anuncio int,
    cd_usuario_aceito int,
    cd_situacao int,
    dt_validade date,
    primary key (cd_usuario, cd_anuncio, cd_usuario_aceito, cd_situacao),
    foreign key (cd_usuario) references tab_usuario (cd_usuario),
    foreign key (cd_usuario_aceito) references tab_usuario (cd_usuario),
    foreign key (cd_anuncio) references tab_anuncio (cd_anuncio),
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
    quantidade_item int,
    primary key (cd_item, cd_anuncio, cd_categoria_item),
    foreign key (cd_anuncio) references tab_anuncio (cd_anuncio),
    foreign key (cd_categoria_item) references tab_categoria_item (cd_categoria_item)
);
-- tirar role do usuário