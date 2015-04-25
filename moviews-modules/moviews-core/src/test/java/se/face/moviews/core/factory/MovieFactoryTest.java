/**
 * 
 */
package se.face.moviews.core.factory;

import static org.junit.Assert.*;

import org.junit.Test;

import se.face.moviews.core.domain.entity.Movie;
import se.face.moviews.core.factory.MovieFactory;
import se.face.moviews.core.test.TestObjectFactory;

/**
 * @author Samuel
 *
 */
public class MovieFactoryTest {
	
	@Test
	public void createFromApiTest(){
		se.face.moviews.api.model.Movie movieApi = TestObjectFactory.movieApi();
		Movie movieEntity = MovieFactory.convertFromApi(movieApi);
		assertNotNull(movieEntity);
		assertEquals(movieEntity.getOriginalTitle(), movieEntity.getOriginalTitle());
		assertEquals("Should have 2 cast and crew members", 2, movieEntity.getCastAndCrew().size());
	}
	
	@Test
	public void createFromEntityTest(){
		Movie movieEntity = TestObjectFactory.movieEntity();
		se.face.moviews.api.model.Movie movieApi = MovieFactory.convertFromEntity(movieEntity);
		assertNotNull(movieApi);
		assertEquals(movieApi.getOriginalTitle(), movieEntity.getOriginalTitle());
		assertEquals("Should have 2 cast and crew members", 2, movieApi.getCastAndCrew().size());
	}
}
