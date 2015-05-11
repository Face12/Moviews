/**
 * 
 */
package se.face.moviews.core.factory;

import static org.junit.Assert.*;

import org.junit.Test;

import se.face.moviews.core.domain.entity.CastAndCrewMember;
import se.face.moviews.core.domain.entity.Movie;
import se.face.moviews.core.factory.CastAndCrewMemberFactory;
import se.face.moviews.core.factory.MovieFactory;
import se.face.moviews.core.test.TestObjectFactory;

/**
 * @author Samuel
 *
 */
public class CastAndCrewFactoryTest {
	
	@Test
	public void createFromApiTest(){
		se.face.moviews.api.model.CastAndCrewMember castAndCrewMemberApi = 
				TestObjectFactory.castAndCrewMemberApi();
		CastAndCrewMember castAndCrewMember = 
				CastAndCrewMemberFactory.convertFromApi(castAndCrewMemberApi);
		assertNotNull(castAndCrewMember);
		assertEquals(castAndCrewMemberApi.getFirstName(), castAndCrewMember.getFirstName());
		assertEquals(castAndCrewMemberApi.getLastName(), castAndCrewMember.getLastName());
		assertEquals(castAndCrewMemberApi.getRole(), castAndCrewMember.getRole());
		
		assertNull(CastAndCrewMemberFactory.convertFromApi(null));
	}
	
	@Test
	public void createFromEntityTest(){
		CastAndCrewMember castAndCrewMember = TestObjectFactory.castAndCrewMemberEntity();
		se.face.moviews.api.model.CastAndCrewMember castAndCrewMemberApi = 
				CastAndCrewMemberFactory.convertFromEntity(castAndCrewMember);
		assertEquals(castAndCrewMember.getFirstName(), castAndCrewMemberApi.getFirstName());
		assertEquals(castAndCrewMember.getLastName(), castAndCrewMemberApi.getLastName());
		assertEquals(castAndCrewMember.getRole(), castAndCrewMemberApi.getRole());
		
		assertNull(CastAndCrewMemberFactory.convertFromEntity(null));
	}
}
