/**
 * 
 */
package se.face.moviews.core.test;

import se.face.moviews.api.model.Person;
import se.face.moviews.core.domain.entity.WorkingRole;
import se.face.moviews.core.domain.entity.Movie;

/**
 * @author Samuel
 *
 */
public class TestObjectFactory {

	public static Movie movieEntity() {
		Movie movie = new Movie("The usual suspects (Entity)");
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
