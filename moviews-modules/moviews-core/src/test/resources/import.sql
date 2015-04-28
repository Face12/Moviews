INSERT INTO movie (movieId, originalTitle) values(100, 'testMovieJUnit')
INSERT INTO cast_and_crew_member (castAndCrewMemberId, firstName, lastName, role) values(101, 'John', 'Doe', 'Actor');
INSERT INTO cast_and_crew_member (castAndCrewMemberId, firstName, lastName, role) values(102, 'Jane', 'Doe', 'Director');
INSERT INTO cast_and_crew_in_movie (movieId, castAndCrewMemberId) values(100, 101);
INSERT INTO cast_and_crew_in_movie (movieId, castAndCrewMemberId) values(100, 102);