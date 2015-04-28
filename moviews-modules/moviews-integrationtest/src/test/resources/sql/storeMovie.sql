INSERT INTO movie values(default, '${movieToStore}');
INSERT INTO cast_and_crew_member values(default, 'John', 'Doe', 'Actor');
INSERT INTO cast_and_crew_member values(default, 'Jane', 'Doe', 'Director');
INSERT INTO cast_and_crew_in_movie values((SELECT movieId FROM movie where originalTitle='${movieToStore}'), (SELECT castAndCrewMemberId FROM cast_and_crew_member WHERE firstName='John' LIMIT 1));
INSERT INTO cast_and_crew_in_movie values((SELECT movieId FROM movie where originalTitle='${movieToStore}'), (SELECT castAndCrewMemberId FROM cast_and_crew_member WHERE firstName='Jane' LIMIT 1));