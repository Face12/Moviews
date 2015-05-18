/**
 * 
 */
package se.face.moviews.api.model;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.junit.Test;

/**
 * @author Samuel
 *
 */
public class PrintJson {
	@Test
	public void printMovieJson() throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		ObjectWriter writer = mapper.writerWithType(Movie.class)
			  .withDefaultPrettyPrinter();
		
		
		writer.writeValue(System.out, movie());
	}

	private Movie movie() {
		Movie movie = new Movie("Apa");
		movie.addWorkingRole(new WorkingRole(new Person("Kalle", "Kula"), "Rolll"));
		return movie;
	}
}
