CREATE DATABASE IF NOT EXISTS GraphCarDesafio;

CREATE USER 'GraphUser'@'%' IDENTIFIED BY 'Graph2023';
GRANT ALL PRIVILEGES ON GraphCarDesafio.* TO 'GraphUser'@'%';
FLUSH PRIVILEGES;

USE GraphCarDesafio;

CREATE TABLE Usuario(
	idUsuario INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50),
    email VARCHAR(100) UNIQUE,
    senha VARCHAR(64),
    cpf CHAR (11) UNIQUE,
    nivelAcesso TINYINT
);

CREATE TABLE Componente(
	idComponente INT PRIMARY KEY AUTO_INCREMENT,
    nomeComponente VARCHAR(10)
);

CREATE TABLE Medida(
	idMedida INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(20),
    unidade VARCHAR(20),
    limiteAlerta DECIMAL(5,2),
    limiteCritico DECIMAL(5,2),
    meta DECIMAL(4,1)
);

CREATE TABLE Servidor(
	idServidor INT PRIMARY KEY AUTO_INCREMENT,
    modeloServidor VARCHAR(45) NOT NULL,
    sistemaOperacional VARCHAR(45),
    hostname VARCHAR(45),
    mac CHAR(12),
    dataCriacao DATETIME
);

CREATE TABLE Dados(
	idDadosServidor INT PRIMARY KEY AUTO_INCREMENT,
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

CREATE TABLE MedidaServidorComponente(
	fkServidorComponente INT,
    fkMedida INT,
    FOREIGN KEY (fkServidorComponente) REFERENCES ServidorComponente(idServidorComponente),
    FOREIGN KEY (fkMedida) REFERENCES Medida(idMedida),
    PRIMARY KEY (fkServidorComponente, fkMedida)
);

CREATE TABLE Chamado(
    idChamado INT PRIMARY KEY AUTO_INCREMENT,
    fkServidor INT,
    fkComponente INT,
    chaveJira VARCHAR(15),
    status VARCHAR(30),
    encerrado TINYINT,
    critico TINYINT,
    dataAbertura DATETIME,
    ultimaMensagemSlack DATETIME,
    FOREIGN KEY (fkServidor) REFERENCES Servidor(idServidor),
    FOREIGN KEY (fkComponente) REFERENCES Componente(idComponente)
);
INSERT INTO Usuario VALUES (NULL, 'ADM', 'admin@graphcar.com', '123456789', '00000000000', 4);

INSERT INTO Componente (idComponente, nomeComponente) VALUES (NULL, "CPU");
INSERT INTO Componente (idComponente, nomeComponente) VALUES (NULL, "RAM");
INSERT INTO Componente (idComponente, nomeComponente) VALUES (NULL, "Disco");
    
CREATE OR REPLACE VIEW metas_dashboard AS
	SELECT (SELECT meta FROM Medida WHERE idMedida = 2) AS meta_cpu, 
	(SELECT meta FROM Medida WHERE idMedida = 2) AS meta_gpu, 
	(SELECT meta FROM Medida WHERE idMedida = 3) AS meta_bat;
    
CREATE OR REPLACE VIEW tempo_chamados AS
	SELECT idChamado, fkServidor, fkComponente,
		CASE WHEN encerrado = 1
			THEN TIMEDIFF(ultimaMensagemSlack, dataAbertura)
            ELSE TIMEDIFF(now(), dataAbertura) END AS tempo
		FROM Chamado;
        
CREATE OR REPLACE VIEW tempo_chamados_porcent AS
	SELECT s.idServidor AS idServidor, tc.fkComponente AS idComponente, COUNT(idChamado) qtdeChamados,
		ROUND(100 * SUM(tc.tempo) / TIMEDIFF(now(), s.dataCriacao),2) AS tempoPorcent
    FROM tempo_chamados tc INNER JOIN Servidor s 
    ON s.idServidor = tc.fkServidor GROUP BY idServidor, idComponente;