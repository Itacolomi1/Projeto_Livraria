CREATE DATABASE hp_db;

CREATE TABLE hp_db.usuarios(
	cod_usuario int auto_increment,
	nome varchar(255),
	email varchar(255),
	senha varchar(255),
    primary key(cod_usuario)
);

CREATE TABLE hp_db.forum(
	cod_filho int auto_increment,
	descricao varchar(255),
	cod_pai int,
    cod_usuario int,
	data_post date,
    primary key(cod_filho),
    foreign key(cod_usuario) references hp_db.usuarios(cod_usuario)

);

CREATE TABLE hp_db.vendas(
	cod_venda int auto_increment,
    cod_usuario int,
    data_venda date,
    total double,
    primary key(cod_venda),
    foreign key(cod_usuario) references hp_db.usuarios(cod_usuario)
);

CREATE TABLE hp_db.produtos(
	cod_produto int auto_increment,
    descricao varchar(255),
    valor double,
    primary key(cod_produto)
);

CREATE TABLE hp_db.produtos_vendas(
	cod_produto int,
    cod_venda int,
    quantidade int,
    foreign key(cod_produto) references hp_db.produtos(cod_produto),
    foreign key(cod_venda) references hp_db.vendas(cod_venda) 

)
