/**
 * 
 */
package se.face.moviews.core.service;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import se.face.moviews.api.model.Movie;
import se.face.moviews.core.service.MoviesService;
import se.face.moviews.core.test.TestConfiguration;
import se.face.moviews.core.test.TestObjectFactory;

/**
 * @author Samuel
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
public class MoviesServiceTest {
	@Autowired
	private MoviesService moviesService;
	
	@Test
	public void shouldSaveMovie(){
		Movie movie = moviesService.saveMovie(TestObjectFactory.movieApi());
		
		assertMovieWithTwoCaCs(movie);
	}
	
	@Test
	public void shouldGetMovieById(){
		Movie movie = moviesService.getMovieById(100);
		
		assertMovieWithTwoCaCs(movie);
	}

	private void assertMovieWithTwoCaCs(Movie movie) {
		assertNotNull(movie.getId());
		assertEquals(2, movie.getCastAndCrew().size());
		movie.getCastAndCrew().forEach(c -> assertNotNull(c.getId()));
	}
}
