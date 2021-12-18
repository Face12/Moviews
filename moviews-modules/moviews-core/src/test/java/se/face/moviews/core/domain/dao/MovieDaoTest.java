/**
 * 
 */
package se.face.moviews.core.domain.dao;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import se.face.moviews.core.domain.dao.MovieDao;
import se.face.moviews.core.domain.entity.Movie;
import se.face.moviews.core.test.TestConfiguration;
import se.face.moviews.core.test.TestObjectFactory;

/**
 * @author Samuel
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
public class MovieDaoTest { 
	
	@Autowired
	MovieDao movieDao;
	
	@Transactional
	@Test
	@Ignore("Problem with auto-generate, insert sends null")
	public void shouldSaveThenGet(){
		Integer id = movieDao.save(TestObjectFactory.movieEntity());
		Movie movie = movieDao.get(id);
		assertEquals(id, movie.getMovieId());
	}
	
	@Transactional
	@Test
	@Ignore("Problem with auto-generate, insert sends null")
	public void savedMovieShouldHaveWorkingRoles(){
		int id = movieDao.save(TestObjectFactory.movieEntity());
		Movie movie = movieDao.getAndFetchCollections(id);
		assertTrue(movie.getWorkingRoles().size() > 0);
	}
}
