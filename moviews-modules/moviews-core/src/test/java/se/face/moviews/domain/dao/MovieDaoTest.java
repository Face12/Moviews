/**
 * 
 */
package se.face.moviews.domain.dao;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import se.face.moviews.domain.dao.MovieDao;
import se.face.moviews.domain.entity.Movie;
import se.face.moviews.test.TestConfiguration;
import se.face.moviews.test.TestObjectFactory;

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
	public void shouldSaveThenGet(){
		int id = movieDao.save(TestObjectFactory.newMovie());
		Movie movie = movieDao.get(id);
		assertEquals(id, movie.getMovieId());
	}
	
	@Transactional
	@Test
	public void savedMovieShouldHaveCastAndCrewMembers(){
		int id = movieDao.save(TestObjectFactory.newMovie());
		Movie movie = movieDao.getWithCastAndCrew(id);
		assertTrue(movie.getCastAndCrew().size() > 0);
	}
}
