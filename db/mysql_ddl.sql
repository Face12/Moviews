DROP TABLE `test`.`country`, `test`.`country_synonym`, `test`.`genre`, `test`.`language`, `test`.`movie`, `test`.`movie_country`, `test`.`movie_genre`, `test`.`movie_language`, `test`.`person`, `test`.`role`, `test`.`working_role`;
CREATE TABLE `country` (
  `countryId` int(11) NOT NULL AUTO_INCREMENT,
  `countryCode` char(2) NOT NULL,
  `countryText` varchar(255) NOT NULL,
  PRIMARY KEY (`countryId`),
  UNIQUE KEY `countryCode_UNIQUE` (`countryCode`)
) ENGINE=InnoDB AUTO_INCREMENT=251 DEFAULT CHARSET=utf8;

CREATE TABLE `country_synonym` (
  `countrySynonymId` int(11) NOT NULL AUTO_INCREMENT,
  `countryId` int(11) NOT NULL,
  `synonymText` varchar(255) NOT NULL,
  PRIMARY KEY (`countrySynonymId`),
  KEY `countrySynonymCountryFK_idx` (`countryId`),
  CONSTRAINT `countrySynonym_CountryFK` FOREIGN KEY (`countryId`) REFERENCES `country` (`countryId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

CREATE TABLE `genre` (
  `genreId` int(11) NOT NULL AUTO_INCREMENT,
  `genreText` varchar(45) NOT NULL,
  PRIMARY KEY (`genreId`),
  UNIQUE KEY `genreText_UNIQUE` (`genreText`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

CREATE TABLE `language` (
  `languageId` int(11) NOT NULL AUTO_INCREMENT,
  `languageCode` char(2) NOT NULL,
  `languageText` varchar(45) NOT NULL,
  PRIMARY KEY (`languageId`),
  UNIQUE KEY `languageCode_UNIQUE` (`languageCode`)
) ENGINE=InnoDB AUTO_INCREMENT=150 DEFAULT CHARSET=utf8;

CREATE TABLE `movie` (
  `movieId` int(11) NOT NULL AUTO_INCREMENT,
  `originalTitle` varchar(255) NOT NULL,
  `imdbId` varchar(45) DEFAULT NULL,
  `releaseDate` datetime DEFAULT NULL,
  `dateQuality` int(11) DEFAULT NULL,
  `runtimeMinutes` smallint(6) DEFAULT NULL,
  `plot` varchar(4096) DEFAULT NULL,
  `imdbRating` float DEFAULT NULL,
  `imdbVotes` bigint(20) DEFAULT NULL,
  `lastUpdated` datetime NOT NULL,
  PRIMARY KEY (`movieId`)
) ENGINE=InnoDB AUTO_INCREMENT=489 DEFAULT CHARSET=utf8;

CREATE TABLE `movie_country` (
  `movieId` int(11) NOT NULL,
  `countryId` int(11) NOT NULL,
  PRIMARY KEY (`movieId`,`countryId`),
  KEY `mc_countryId_countryIdFK_idx` (`countryId`),
  CONSTRAINT `mc_movieId_movieIdFK` FOREIGN KEY (`movieId`) REFERENCES `movie` (`movieId`),
  CONSTRAINT `mc_countryId_countryIdFK` FOREIGN KEY (`countryId`) REFERENCES `country` (`countryId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `movie_genre` (
  `movieId` int(11) NOT NULL,
  `genreId` int(11) NOT NULL,
  PRIMARY KEY (`movieId`,`genreId`),
  KEY `mg_genreId_FK_idx` (`genreId`),
  CONSTRAINT `mg_movieId_FK` FOREIGN KEY (`movieId`) REFERENCES `movie` (`movieId`),
  CONSTRAINT `mg_genreId_FK` FOREIGN KEY (`genreId`) REFERENCES `genre` (`genreId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `movie_language` (
  `movieId` int(11) NOT NULL,
  `languageId` int(11) NOT NULL,
  PRIMARY KEY (`movieId`,`languageId`),
  KEY `ml_languageId_FK_idx` (`languageId`),
  CONSTRAINT `ml_movieId_FK` FOREIGN KEY (`movieId`) REFERENCES `movie` (`movieId`),
  CONSTRAINT `ml_languageId_FK` FOREIGN KEY (`languageId`) REFERENCES `language` (`languageId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `person` (
  `personId` int(11) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`personId`)
) ENGINE=InnoDB AUTO_INCREMENT=1028 DEFAULT CHARSET=utf8;

CREATE TABLE `role` (
  `roleId` int(11) NOT NULL AUTO_INCREMENT,
  `roleText` varchar(255) NOT NULL,
  PRIMARY KEY (`roleId`),
  UNIQUE KEY `roleText_UNIQUE` (`roleText`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

CREATE TABLE `working_role` (
  `workingRoleId` int(11) NOT NULL AUTO_INCREMENT,
  `movieId` int(11) NOT NULL,
  `personId` int(11) NOT NULL,
  `roleId` int(11) DEFAULT NULL,
  PRIMARY KEY (`workingRoleId`),
  KEY `workingRoleMovieIdFK_idx` (`movieId`),
  KEY `workingRolePersonFK_idx` (`personId`),
  KEY `workingRoleRoleFK_idx` (`roleId`),
  CONSTRAINT `workingRoleRoleFK` FOREIGN KEY (`roleId`) REFERENCES `role` (`roleId`),
  CONSTRAINT `workingRolePersonFK` FOREIGN KEY (`personId`) REFERENCES `person` (`personId`),
  CONSTRAINT `workingRoleMovieFK` FOREIGN KEY (`movieId`) REFERENCES `movie` (`movieId`)
) ENGINE=InnoDB AUTO_INCREMENT=1825 DEFAULT CHARSET=utf8;