/**
 * 
 */
package se.face.moviews.core.test;

import se.face.moviews.core.domain.entity.CastAndCrewMember;
import se.face.moviews.core.domain.entity.Movie;

/**
 * @author Samuel
 *
 */
public class TestObjectFactory {

	public static Movie movieEntity() {
		Movie movie = new Movie("The usual suspects (Entity)");
		movie.addCastAndCrewMember(new CastAndCrewMember("Bryan E", "Singer E", "Director E"));
		movie.addCastAndCrewMember(new CastAndCrewMember("Kevin E", "Spacey E", "Actor E"));
		return movie;
	}

	public static CastAndCrewMember castAndCrewMemberEntity() {
		CastAndCrewMember castAndCrewMember = new CastAndCrewMember("John", "Doe", "Director");
		return castAndCrewMember;
	}
	
	public static se.face.moviews.api.model.Movie movieApi(){
		se.face.moviews.api.model.Movie movie = 
				new se.face.moviews.api.model.Movie("The usual suspects (API)");
		movie.addCastAndCrew(new se.face.moviews.api.model.CastAndCrewMember("Bryan A", "Singer A", "Director A"));
		movie.addCastAndCrew(new se.face.moviews.api.model.CastAndCrewMember("Kevin A", "Spacey A", "Actor A"));
		
		return movie;
	}

	public static se.face.moviews.api.model.CastAndCrewMember castAndCrewMemberApi() {
		se.face.moviews.api.model.CastAndCrewMember castAndCrewMember = 
				new se.face.moviews.api.model.CastAndCrewMember(
						"John", "Doe", "Actor"
						);
		return castAndCrewMember;
	}

}
