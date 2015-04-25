/**
 * 
 */
package se.face.moviews.api.model;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Samuel
 *
 */
public class MovieTest {

	@Test
	public void objectTest(){
		Movie movie1a = new Movie("Movie1");
		Movie movie1b = new Movie("Movie1");
		Movie movie2 = new Movie("Movie2");
		
		assertTrue("Should be equal", movie1a.equals(movie1b));
		assertFalse("Should not be equal", movie1a.equals(movie2));
		assertTrue("Hashcode", movie1a.hashCode() == movie1b.hashCode());
	}
}
