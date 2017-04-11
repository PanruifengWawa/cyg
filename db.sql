drop table if exists photo_wall;
create table `photo_wall` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`content` text DEFAULT NULL,
	`upload_date` date not NULL,
	`src` varchar(256) DEFAULT NULL,
	`year`  mediumint(8),
	 PRIMARY KEY (`id`)
)ENGINE=MyISAM DEFAULT CHARSET=utf8;


drop table if exists mooc;
create table `mooc` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`title` varchar(512) not NULL,
	`upload_date` date not NULL,
	`src` varchar(256) DEFAULT NULL,
	 PRIMARY KEY (`id`)
)ENGINE=MyISAM DEFAULT CHARSET=utf8;
