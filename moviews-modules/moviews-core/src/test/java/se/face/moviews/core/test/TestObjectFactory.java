/**
 * 
 */
package se.face.moviews.core.test;

import java.util.Date;

import se.face.moviews.api.model.Person;
import se.face.moviews.core.domain.entity.DateQuality;
import se.face.moviews.core.domain.entity.WorkingRole;
import se.face.moviews.core.domain.entity.Movie;

/**
 * @author Samuel
 *
 */
public class TestObjectFactory {

	public static Movie movieEntity() {
		Movie movie = new Movie("The usual suspects (Entity)");
		movie.setPlot("A kinda bief plot");
		movie.setImdbId("ttHejOchHå");
		movie.setImdbRating(8.300f);
		movie.setImdbVotes(12345L);
		movie.setReleaseDate(new Date());
		movie.setDateQuality(DateQuality.DAY);
		movie.setRuntimeMinutes((short) 1000);
		movie.setLastUpdated(new Date());
		movie.addWorkingRole(new WorkingRole("Bryan E", "Singer E", "Director E"));
		movie.addWorkingRole(new WorkingRole("Kevin E", "Spacey E", "Actor E"));
		return movie;
	}

	public static WorkingRole workingRoleEntity() {
		WorkingRole workingRole = new WorkingRole("John", "Doe", "Director");
		return workingRole;
	}
	
	public static se.face.moviews.api.model.Movie movieApi(){
		se.face.moviews.api.model.Movie movie = 
				new se.face.moviews.api.model.Movie("The usual suspects (API)");
		movie.setPlot("A kinda bief plot");
		movie.setImdbId("ttHejOchHå");
		movie.setImdbRating(8.300f);
		movie.setImdbVotes(12345L);
		movie.setReleaseDate(new Date());
		movie.setDateQuality(se.face.moviews.api.model.DateQuality.DAY);
		movie.setRuntimeMinutes((short) 1000);
		movie.addGenre("Thriller");
		movie.addGenre("Crime");
		movie.addCountry("USA");
		movie.addLanguage("English");
		movie.addWorkingRole(new se.face.moviews.api.model.WorkingRole(new Person("Bryan A", "Singer A"), "Director A"));
		movie.addWorkingRole(new se.face.moviews.api.model.WorkingRole(new Person("Kevin A", "Spacey A"), "Actor A"));
		
		return movie;
	}

	public static se.face.moviews.api.model.WorkingRole workingRoleApi() {
		se.face.moviews.api.model.WorkingRole workingRole = 
				new se.face.moviews.api.model.WorkingRole(
						new Person("John", "Doe"), "Actor"
						);
		return workingRole;
	}

}
