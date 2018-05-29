CREATE DATABASE tp_activity CHARACTER SET utf8;

USE tp_activity;

#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------


#------------------------------------------------------------
# Table: parameters
#------------------------------------------------------------

CREATE TABLE parameters(
        pa_label     Varchar (10) NOT NULL,
        pa_name Varchar (100) ,
	PRIMARY KEY (pa_label)
)ENGINE=InnoDB;

CREATE TABLE activity(
        ac_id INT AUTO_INCREMENT NOT NULL,
		ac_name Varchar (100),
	PRIMARY KEY (ac_id)
)ENGINE=InnoDB;

CREATE TABLE mapping_activity_parameters(
        pa_label     Varchar (10) NOT NULL,
        ac_id     INT AUTO_INCREMENT NOT NULL,
	CONSTRAINT TI_Report_PK PRIMARY KEY (pa_label,ac_id),

	FOREIGN KEY (pa_label) REFERENCES parameters(pa_label),
	FOREIGN KEY (ac_id) REFERENCES activity(ac_id)
)ENGINE=InnoDB;

-- Beau temps ou pas, état de forme et motivation
INSERT INTO parameters (pa_label, pa_name) VALUES ('b', 'Beau'), ('m', 'Moche'),
('f', 'Fatigue'), ('fa', 'Faim'), ('e', 'Plein d\'énergie'),
('r', 'Réfléchir'), ('d', 'Détente'), ('s', 'Sortir'),
('g', 'En groupe'), ('se', 'seul');

INSERT INTO activity (ac_name) VALUES ('Lire'), ('Courir'), ('Restaurant'), ('Cuisiner'), ('Aller voir un film'), ('Lire dehors'), ('Jouer jeux de sociétés'), ('Aller dans un bar'), ('Aller faire un tennis'), ('Aller faire du rugby');

INSERT INTO mapping_activity_parameters (pa_label, ac_name) VALUES ('b', 'Courir'), ('b', 'Restaurant'), ('b', 'Lire dehors'),
('b', 'Aller dans un bar'), ('b', 'Aller faire un tennis'), ('b', 'Aller faire du rugby');

SELECT m.pa_label, a.ac_name FROM activity a  JOIN parameters p JOIN mapping_activity_parameters m ON a.ac_id=m.ac_id AND p.pa_label=m.pa_label AND m.pa_label='b' AND m.pa_label='e';

