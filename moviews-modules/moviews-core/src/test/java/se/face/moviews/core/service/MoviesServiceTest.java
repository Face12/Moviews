/**
 * 
 */
package se.face.moviews.core.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import se.face.moviews.api.model.Movie;
import se.face.moviews.api.model.Person;
import se.face.moviews.api.model.WorkingRole;
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
	
	@Test
	public void savingMovieWithExistingCastAndCrewMemberShould_NOT_DuplicateCastAndCrewMember(){
		Movie movie = moviesService.getMovieById(100);
		WorkingRole workingRoleInAnotherMovie = movie.getWorkingRoles().get(0);
		Movie movieToSave = new Movie("Other test movie");
		movieToSave.addWorkingRole(new WorkingRole(
				new Person(workingRoleInAnotherMovie.getPerson().getFirstName(), 
						   workingRoleInAnotherMovie.getPerson().getLastName()),
				workingRoleInAnotherMovie.getRole()));
		
		Movie savedMovie = moviesService.saveMovie(movieToSave);
		
		assertEquals(workingRoleInAnotherMovie.getPerson().getId(), savedMovie.getWorkingRoles().get(0).getPerson().getId());
	}

	private void assertMovieWithTwoCaCs(Movie movie) {
		assertNotNull(movie.getId());
		assertEquals(2, movie.getWorkingRoles().size());
		movie.getWorkingRoles().forEach(c -> assertNotNull(c.getId()));
	}
}
