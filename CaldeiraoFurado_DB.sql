CREATE DATABASE HP_DB;

CREATE TABLE HP_DB.Usuarios(
	Codigo_Usuario int auto_increment,
	Nome varchar(255),
	Email varchar(255),
	Senha varchar(255),
    primary key(Codigo_Usuario)
);

CREATE TABLE HP_DB.Forum(
	Codigo_Filho int auto_increment,
	Descricao varchar(255),
	Cod_Pai int,
    Codigo_Usuario int,
	Data_Post date,
    primary key(Codigo_Filho),
    foreign key(Codigo_Usuario) references HP_DB.Usuarios(Codigo_Usuario)

);

CREATE TABLE HP_DB.Vendas(
	Codigo_Venda int auto_increment,
    Codigo_Usuario int,
    Data_Venda date,
    Total double,
    primary key(Codigo_Venda),
    foreign key(Codigo_Usuario) references HP_DB.Usuarios(Codigo_Usuario)
);

CREATE TABLE HP_DB.Produtos(
	Codigo_Produto int auto_increment,
    Descricao varchar(255),
    Valor double,
    primary key(Codigo_Produto)
);

CREATE TABLE HP_DB.Produtos_Vendas(
	Codigo_Produto int,
    Codigo_Venda int,
    Quantidade int,
    foreign key(Codigo_Produto) references HP_DB.Produtos(Codigo_Produto),
    foreign key(Codigo_Venda) references HP_DB.Vendas(Codigo_Venda) 

)
