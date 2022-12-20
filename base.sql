DROP sequence s_Assurance;
DROP sequence s_Avion;
DROP sequence s_DateEntretien;
DROP sequence s_Entretien;
DROP sequence s_Kilometrage;
DROP sequence s_Modele;

DROP TABLE IF EXISTS Personne CASCADE;
DROP TABLE IF EXISTS Assurance CASCADE;
DROP TABLE IF EXISTS Avion CASCADE;
DROP TABLE IF EXISTS DateEntretien CASCADE;
DROP TABLE IF EXISTS Entretien CASCADE;
DROP TABLE IF EXISTS Kilometrage CASCADE;
DROP TABLE IF EXISTS Modele CASCADE;


CREATE sequence s_Assurance;
CREATE sequence s_Avion;
CREATE sequence s_DateEntretien;
CREATE sequence s_Entretien;
CREATE sequence s_Kilometrage;
CREATE sequence s_Modele;

CREATE TABLE Assurance (idAssurance varchar(30) NOT NULL, idAvion varchar(30) NOT NULL, DateDebut date NOT NULL, DateFin date NOT NULL, PRIMARY KEY (idAssurance));
INSERT INTO Assurance(idAssurance,idAvion,datedebut,datefin)values('Assurance_'||nextval('s_Assurance'),'Avion_1','2022-11-10','2023-01-10');
INSERT INTO Assurance(idAssurance,idAvion,datedebut,datefin)values('Assurance_'||nextval('s_Assurance'),'Avion_2','2022-11-10','2023-03-10');

CREATE TABLE Avion (idAvion varchar(30) NOT NULL, Nom varchar(100) NOT NULL UNIQUE, NbrPlace int4 NOT NULL, idModele varchar(30) NOT NULL, Photo text NOT NULL, PRIMARY KEY (idAvion));
INSERT INTO Avion(idAvion,Nom,NbrPlace,idModele,Photo)values('Avion_'||nextval('s_Avion'),'Airbus364',65,'Model_1','Neant');
INSERT INTO Avion(idAvion,Nom,NbrPlace,idModele,Photo)values('Avion_'||nextval('s_Avion'),'Jet2022',5,'Model_2','Neant');

CREATE TABLE DateEntretien (idDateEntretien varchar(30) NOT NULL, idAvion varchar(30) NOT NULL, idEntretien varchar(30) NOT NULL, date date NOT NULL, PRIMARY KEY (idDateEntretien));
INSERT INTO DateEntretien(idDateEntretien,idAvion,idEntretien,date)values('DateEntretien'||nextval('s_DateEntretien'),'Avion_1','Entretient_1','2022-10-12');
INSERT INTO DateEntretien(idDateEntretien,idAvion,idEntretien,date)values('DateEntretien'||nextval('s_DateEntretien'),'Avion_2','Entretient_1','2022-10-15');

CREATE TABLE Entretien (idEntretien varchar(30) NOT NULL, Intitule varchar(100) NOT NULL UNIQUE, PRIMARY KEY (idEntretien));
INSERT into Entretien(idEntretien,Intitule)values('Entretient_'||nextval('s_Entretien'),'Injecteur');
INSERT into Entretien(idEntretien,Intitule)values('Entretient_'||nextval('s_Entretien'),'Carburant');

CREATE TABLE Kilometrage (idKilometrage varchar(30) NOT NULL, idAvion varchar(30) NOT NULL, date date NOT NULL, debutKm float8 NOT NULL, finKm float8 NOT NULL, PRIMARY KEY (idKilometrage));
CREATE TABLE Modele (idModele varchar(30) NOT NULL, Intitule varchar(100) NOT NULL UNIQUE, PRIMARY KEY (idModele));

INSERT into Modele(idModele,Intitule)values('Model_'||nextval('s_Modele'),'AirBus');
INSERT into Modele(idModele,Intitule)values('Model_'||nextval('s_Modele'),'Jet');


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



DROP VIEW v_Avion;
create or replace view v_Avion as
    SELECT A.*,M.Intitule as Modele FROM Avion A JOIN Modele M  ON (A.idModele=M.idModele);

DROP VIEW v_assuranceencours;
create or replace view v_assuranceencours as 
    SELECT *,EXTRACT (MONTH FROM age(NOW() + interval '3 month',Datefin)) as MoisFin FROM Assurance where datedebut <=now() and datefin >= now() ;


DROP VIEW v_Entretient;
create or replace view v_Entretient as
select d.*,e.intitule from dateentretien d JOIN entretien e ON d.identretien = e.identretien;
