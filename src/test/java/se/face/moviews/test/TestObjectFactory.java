/**
 * 
 */
package se.face.moviews.test;

import se.face.moviews.domain.entity.CastAndCrewMember;
import se.face.moviews.domain.entity.Movie;

/**
 * @author Samuel
 *
 */
public class TestObjectFactory {

	public static Movie newMovie() {
		Movie movie = new Movie("The usual suspects");
		movie.addCastAndCrewMember(new CastAndCrewMember("Bryan", "Singer"));
		movie.addCastAndCrewMember(new CastAndCrewMember("Kevin", "Spacey"));
		return movie;
	}

	public static CastAndCrewMember newCastAndCrewMember() {
		CastAndCrewMember castAndCrewMember = new CastAndCrewMember("John", "Doe");
		castAndCrewMember.setRole("Director");
		return castAndCrewMember;
	}

}
