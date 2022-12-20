CREATE TABLE Assurance (idAssurance varchar(30) NOT NULL, idAvion varchar(30) NOT NULL, DateDebut date NOT NULL, DateFin date NOT NULL, PRIMARY KEY (idAssurance));
CREATE TABLE Avion (idAvion varchar(30) NOT NULL, Nom varchar(100) NOT NULL UNIQUE, NbrPlace int4 NOT NULL, idModele varchar(30) NOT NULL, PRIMARY KEY (idAvion));
CREATE TABLE DateEntretien (idDateEntretien varchar(30) NOT NULL, idAvion varchar(30) NOT NULL, idEntretien varchar(30) NOT NULL, date date NOT NULL, PRIMARY KEY (idDateEntretien));
CREATE TABLE Entretien (idEntretien varchar(30) NOT NULL, Intitule varchar(100) NOT NULL UNIQUE, PRIMARY KEY (idEntretien));
CREATE TABLE Kilometrage (idKilometrage varchar(30) NOT NULL, idAvion varchar(30) NOT NULL, date date NOT NULL, debutKm float8 NOT NULL, finKm float8 NOT NULL, PRIMARY KEY (idKilometrage));
CREATE TABLE Modele (idModele varchar(30) NOT NULL, Intitule varchar(100) NOT NULL UNIQUE, PRIMARY KEY (idModele));
CREATE TABLE Personne (idPersonne varchar(30) NOT NULL, nom varchar(100) NOT NULL, mail varchar(100) NOT NULL UNIQUE, passwords varchar(100) NOT NULL, PRIMARY KEY (idPersonne));
ALTER TABLE Kilometrage ADD CONSTRAINT FKKilometrag226573 FOREIGN KEY (idAvion) REFERENCES Avion (idAvion);
ALTER TABLE Assurance ADD CONSTRAINT FKAssurance79244 FOREIGN KEY (idAvion) REFERENCES Avion (idAvion);
ALTER TABLE DateEntretien ADD CONSTRAINT FKDateEntret693585 FOREIGN KEY (idAvion) REFERENCES Avion (idAvion);
ALTER TABLE DateEntretien ADD CONSTRAINT FKDateEntret705301 FOREIGN KEY (idEntretien) REFERENCES Entretien (idEntretien);
ALTER TABLE Avion ADD CONSTRAINT FKAvion847253 FOREIGN KEY (idModele) REFERENCES Modele (idModele);



/* drop */
ALTER TABLE Kilometrage DROP CONSTRAINT FKKilometrag226573;
ALTER TABLE Assurance DROP CONSTRAINT FKAssurance79244;
ALTER TABLE DateEntretien DROP CONSTRAINT FKDateEntret693585;
ALTER TABLE DateEntretien DROP CONSTRAINT FKDateEntret705301;
ALTER TABLE Avion DROP CONSTRAINT FKAvion847253;
DROP TABLE IF EXISTS Personne CASCADE;
DROP TABLE IF EXISTS Assurance CASCADE;
DROP TABLE IF EXISTS Avion CASCADE;
DROP TABLE IF EXISTS DateEntretien CASCADE;
DROP TABLE IF EXISTS Entretien CASCADE;
DROP TABLE IF EXISTS Kilometrage CASCADE;
DROP TABLE IF EXISTS Modele CASCADE;


DROP VIEW v_Avion;
create or replace view v_Avion as
    SELECT A.*,M.Intitule FROM Avion A JOIN Modele M  ON (A.idModele=M.idModele);

DROP VIEW v_assuranceencours;
create or replace view v_assuranceencours as 
    SELECT *,EXTRACT (MONTH FROM age(NOW() + interval '3 month',Datefin)) as MoisFin FROM Assurance where datedebut <=now() and datefin >= now() ;
