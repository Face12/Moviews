/**
 * 
 */
package se.face.moviews.core.domain.entity;

import static org.junit.Assert.*;

import org.junit.Test;

import se.face.moviews.core.domain.entity.CastAndCrewMember;
import se.face.moviews.core.test.TestObjectFactory;

/**
 * @author Samuel
 *
 */
public class CastAndCrewMemberTest {
	@Test
	public void shouldCreateCastAndCrewMember(){
		TestObjectFactory.newCastAndCrewMember();
	}
	
	@Test
	public void objectTest(){
		CastAndCrewMember c1a = new CastAndCrewMember(1);
		CastAndCrewMember c1b = new CastAndCrewMember(1);
		CastAndCrewMember c2 = new CastAndCrewMember(2);
		assertTrue("Should be equal", c1a.equals(c1b));
		assertFalse("Should not be equal", c1a.equals(c2));
		assertTrue("Hashcode", c1a.hashCode() == c1b.hashCode());
	}
}
