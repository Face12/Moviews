/**
 * 
 */
package se.face.moviews.api.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Test;

/**
 * @author Samuel
 *
 */
public class PrintJson {
	@Test
	public void printMovieJson() throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		ObjectWriter writer = mapper.writerFor(Movie.class)
			  .withDefaultPrettyPrinter();
		
		
		writer.writeValue(System.out, movie());
	}

	private Movie movie() {
		Movie movie = new Movie("Apa");
		movie.addWorkingRole(new WorkingRole(new Person("Kalle", "Kula"), "Rolll"));
		return movie;
	}
}
