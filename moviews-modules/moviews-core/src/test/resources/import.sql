INSERT INTO country(countryId, countryCode, countryText) values(10, 'US', 'United States of America');
INSERT INTO country_synonym(countrySynonymId, countryId, synonymText) values(11, 10, 'USA');
INSERT INTO country(countryId, countryCode, countryText) values(12, 'AE', 'United Arab Emirates');
INSERT INTO language(languageId, languageCode, languageText) values(13, 'en', 'English');
INSERT INTO genre(genreId, genreText) values(14, 'Thriller');

INSERT INTO movie (movieId, originalTitle, imdbRating, lastUpdated) values(100, 'testMovieJUnit', 8.3, '2015-05-21');
INSERT INTO person(personId, firstName, lastName) values(105, 'John', 'Doe');
INSERT INTO person(personId, firstName, lastName) values(106, 'Jane', 'Doe');
INSERT INTO role(roleId, roleText) values(107, 'Actor');
INSERT INTO role(roleId, roleText) values(108, 'Director');
INSERT INTO working_role (workingRoleId, personId, roleId, movieId) values(101, 105, 107, 100);
INSERT INTO working_role (workingRoleId, personId, roleId, movieId) values(102, 106, 108, 100);
