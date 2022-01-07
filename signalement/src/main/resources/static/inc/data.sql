create database signalement;
use signalement;

create table province(
	Id INT NOT NULL AUTO_INCREMENT primary key,
	Nom varchar(250)
)ENGINE=InnoDB;

create table region(
	Id INT NOT NULL AUTO_INCREMENT primary key,
	Nom varchar(250),
	Province INT,
	FOREIGN KEY(Province) REFERENCES province(Id)
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
	Etat varchar(75)
)ENGINE=InnoDB;

create table signalement(
	Id INT NOT NULL AUTO_INCREMENT primary key,
	Signalnew int ,
	Description varchar(1000),
	Statut int ,
	Region int ,
	Type int ,
	daty date,
	Latitude decimal(10,8),
	Longitude decimal(10,8),
	FOREIGN KEY(Type) REFERENCES type(Id),
	FOREIGN KEY(Region) REFERENCES region(Id),
	FOREIGN KEY(Statut) REFERENCES statut(Id),
	FOREIGN KEY(Signalnew) REFERENCES signalNew(Id)
)ENGINE=InnoDB;


create table photo(
	Id INT NOT NULL AUTO_INCREMENT primary key,
	Signalement int ,
	Photo varchar(500),
	FOREIGN KEY(Signalement) REFERENCES signalement(Id)
)ENGINE=InnoDB;



insert into admin values
	(null,"admin","0000");

insert into type values
	(null,"vol"),
	(null,"accident"),
	(null,"autres");

insert into province values
	(null,"Antsiranana"),
	(null,"Antananarivo"),
	(null,"Mahajanga"),
	(null,"Toamasina"),
	(null,"Fianarantsoa"),
	(null,"Toliara");

insert into region values
	(null,"Diana",1),
	(null,"Sava",1),
	(null,"Itasy",2),
	(null,"Analamanga",2),
	(null,"Vakinakaratra",2),
	(null,"Bongolava",2),
	(null,"Sofia",3),
	(null,"Boeny",3),
	(null,"Betsiboka",3),
	(null,"Melaky",3),
	(null,"Alaotra Mangoro",4),
	(null,"Antsinana",4),
	(null,"Analanjorofo",4),
	(null,"Amoron'i Mania",5),
	(null,"Haute Matsiatra",5),
	(null,"Vatovavy Fitovinany",5),
	(null,"Atsimo Atsinana",5),
	(null,"Ihorombe",5),
	(null,"Menabe",6),
	(null,"Atsime Andrefana",6),
	(null,"Androy",6),
	(null,"Anosy",6);

insert into signalNew values(null,"Accident de voitures su la route de RN2");

insert into statut values
	(null,"Nouveau"),
	(null,"En cours"),
	(null,"Terminé");

insert into signalement values(null,1,"Aucun description",1,null,2,"2022-01-15",-19.715,46.75781);

drop database signalement;




<script type="javascript">
            // On initialise la latitude et la longitude de Paris (centre de la carte)
            var lat = 48.852969;
            var lon = 2.349903;
            var macarte = null;
         //   var signalement = [[${signalement}]];
            
            // Fonction d'initialisation de la carte
            function initMap() {
                // Créer l'objet "macarte" et l'insèrer dans l'élément HTML qui a l'ID "map"
                macarte = L.map('map').setView([lat, lon], 11);
                // Leaflet ne récupère pas les cartes (tiles) sur un serveur par défaut. Nous devons lui préciser où nous souhaitons les récupérer. Ici, openstreetmap.fr
                L.tileLayer('https://{s}.tile.openstreetmap.fr/osmfr/{z}/{x}/{y}.png', {
                    // Il est toujours bien de laisser le lien vers la source des données
                    attribution: 'données © <a href="//osm.org/copyright">OpenStreetMap</a>/ODbL - rendu <a href="//openstreetmap.fr">OSM France</a>',
                    minZoom: 1,
                    maxZoom: 20
                }).addTo(macarte);
//                for (s in signalement) {
//		var marker = L.marker([signalement[s].latitude, [signalement[s].longitude]).addTo(macarte);
	}    
            }
            window.onload = function(){
		// Fonction d'initialisation qui s'exécute lorsque le DOM est chargé
		initMap();
                //clicktr();
            };
            
//            function clicktr(){
//                let tr=$(".clicktr");
//                tr.click(function(){
//                // Holds the product ID of the clicked element
//                let latnew=tr.find(".lat").text();
//                let lonnew=tr.find(".long").text();
//                macarte.setView([latnew, lonnew], 11);
//              });
//            }
            </script>