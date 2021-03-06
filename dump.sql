CREATE DATABASE ROTAS;

USE ROTAS;

CREATE TABLE USUARIO(
	USUARIO VARCHAR(20) UNIQUE, 
	NOME VARCHAR(50),
	EMAIL VARCHAR(50) UNIQUE,
	SENHA VARCHAR(20),
	PRIMARY KEY (USUARIO,EMAIL)
);

CREATE TABLE ROTA(
	ID INT AUTO_INCREMENT,
	USUARIO VARCHAR(20),
	NOME VARCHAR(50), 
	TEMPO INT,
	PRIMARY KEY (ID,USUARIO),
	FOREIGN KEY (USUARIO) REFERENCES USUARIO(USUARIO)
);

CREATE TABLE PONTO(
	ID INT AUTO_INCREMENT PRIMARY KEY,
	NUMERO VARCHAR(10),
	RUA VARCHAR(30), 
	CIDADE VARCHAR(30),
	ESTADO VARCHAR(20),
	CEP VARCHAR(8),
	IDROTA INT,
	FOREIGN KEY (IDROTA) REFERENCES ROTA(ID)
);

DELIMITER $$
CREATE TRIGGER DEL_USER BEFORE DELETE ON USUARIO
FOR EACH ROW
BEGIN
	DELETE FROM ROTA WHERE USUARIO = OLD.USUARIO;
END$$

DELIMITER $$
CREATE TRIGGER TRIGGER_DEL_ROTA BEFORE DELETE ON ROTA
FOR EACH ROW
BEGIN
	DELETE FROM PONTO WHERE IDROTA = OLD.ID;
END$$
	
