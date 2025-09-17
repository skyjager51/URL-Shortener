DROP SCHEMA IF EXISTS `url_repository`;

CREATE SCHEMA `url_repository`;

use `url_repository`;

CREATE TABLE `urls` (
	`id` int NOT NULL AUTO_INCREMENT,
    `url` VARCHAR(1024) NOT NULL unique,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;