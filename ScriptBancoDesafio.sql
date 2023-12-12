CREATE DATABASE IF NOT EXISTS GraphCarDesafio;

CREATE USER IF NOT EXISTS 'GraphUser'@'%' IDENTIFIED BY 'Graph2023';
GRANT ALL PRIVILEGES ON GraphCarDesafio.* TO 'GraphUser'@'%';
FLUSH PRIVILEGES;

USE GraphCarDesafio;

CREATE TABLE Usuario(
	idUsuario INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50),
    email VARCHAR(100) UNIQUE,
    senha VARCHAR(64)
);

CREATE TABLE Componente(
	idComponente INT PRIMARY KEY AUTO_INCREMENT,
    nomeComponente VARCHAR(10)
);

CREATE TABLE Servidor(
	idServidor INT PRIMARY KEY AUTO_INCREMENT,
    modeloServidor VARCHAR(45) NOT NULL,
    sistemaOperacional VARCHAR(45),
    hostname VARCHAR(45),
    mac CHAR(12),
    webhookSlack VARCHAR(500),
    dataCriacao DATETIME
);

CREATE TABLE Dados(
	idDados INT PRIMARY KEY AUTO_INCREMENT,
    cpu DECIMAL(5,2),
    memoria DECIMAL(5,2),
    disco DECIMAL(5,2),
    dateDado DATETIME,
    fkServidor INT,
    FOREIGN KEY (fkServidor) REFERENCES Servidor(idServidor)
);

CREATE TABLE ServidorComponente(
	idServidorComponente INT PRIMARY KEY AUTO_INCREMENT,
    fkComponente INT,
    fkServidor INT,
    FOREIGN KEY (fkComponente) REFERENCES Componente(idComponente),
    FOREIGN KEY (fkServidor) REFERENCES Servidor(idServidor)
);

CREATE TABLE Medida(
	idServidorComponente INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(20),
    unidade VARCHAR(20),
    ativa TINYINT,
    limiteAlerta DECIMAL(5,2),
    limiteCritico DECIMAL(5,2),
    FOREIGN KEY (idServidorComponente) REFERENCES ServidorComponente(idServidorComponente)
);

INSERT INTO Usuario VALUES (NULL, 'ADM', 'admin@graphcar.com', 'urubu100');

INSERT INTO Componente (idComponente, nomeComponente) VALUES (NULL, "CPU");
INSERT INTO Componente (idComponente, nomeComponente) VALUES (NULL, "RAM");
INSERT INTO Componente (idComponente, nomeComponente) VALUES (NULL, "Disco");