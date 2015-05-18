INSERT INTO movie (movieId, originalTitle) values(100, 'testMovieJUnit')
INSERT INTO person(personId, firstName, lastName) values(105, 'John', 'Doe');
INSERT INTO person(personId, firstName, lastName) values(106, 'Jane', 'Doe');
INSERT INTO role(roleId, roleText) values(107, 'Actor');
INSERT INTO role(roleId, roleText) values(108, 'Director');
INSERT INTO working_role (workingRoleId, personId, roleId, movieId) values(101, 105, 107, 100);
INSERT INTO working_role (workingRoleId, personId, roleId, movieId) values(102, 106, 108, 100);