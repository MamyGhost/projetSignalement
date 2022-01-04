create database signalement;

create table region(
	Id INT NOT NULL AUTO_INCREMENT primary key,
	Nom varchar(250)
)ENGINE=InnoDB;


create table utilisateur(
	Id INT NOT NULL AUTO_INCREMENT primary key,
	Username varchar(125),
	Password varchar(250)
)ENGINE=InnoDB;

create table admin(
	Id INT NOT NULL AUTO_INCREMENT primary key,
	Username varchar(125),
	Password varchar(250)

)ENGINE=InnoDB;

create table userfront(
	Id INT NOT NULL AUTO_INCREMENT primary key,
	Username varchar(125),
	Password varchar(250),
	Region int ,
	FOREIGN KEY(Region) REFERENCES region(Id)
)ENGINE=InnoDB;


create table type(
	Id INT NOT NULL AUTO_INCREMENT primary key,
	Nom varchar(250)
)ENGINE=InnoDB;


create table signalNew(
	Id INT NOT NULL AUTO_INCREMENT primary key,
	Titre varchar(250)
)ENGINE=InnoDB;

create table statut(
	Id INT NOT NULL AUTO_INCREMENT primary key,
	Etat Boolean
)ENGINE=InnoDB;

create table signalement(
	Id INT NOT NULL AUTO_INCREMENT primary key,
	SignalNew int ,
	Description varchar(1000),
	Statut int ,
	Region int ,
	Type int ,
	daty date,
	Latitude decimal,
	Longitude decimal,
	FOREIGN KEY(Type) REFERENCES type(Id),
	FOREIGN KEY(Region) REFERENCES region(Id),
	FOREIGN KEY(Statut) REFERENCES statut(Id),
	FOREIGN KEY(SignalNew) REFERENCES signalNew(Id)
)ENGINE=InnoDB;


create table photo(
	Id INT NOT NULL AUTO_INCREMENT primary key,
	Signalement int ,
	Photo varchar(500),
	FOREIGN KEY(Signalement) REFERENCES signalement(Id)
)ENGINE=InnoDB;



