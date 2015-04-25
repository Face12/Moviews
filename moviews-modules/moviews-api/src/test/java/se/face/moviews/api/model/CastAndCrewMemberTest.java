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
public class CastAndCrewMemberTest {
	@Test
	public void objectTest(){
		CastAndCrewMember castAndCrewMemberJanea = new CastAndCrewMember("Jane", "Doe", "Director");
		CastAndCrewMember castAndCrewMemberJaneb = new CastAndCrewMember("Jane", "Doe", "Director");
		CastAndCrewMember castAndCrewMemberJohn = new CastAndCrewMember("John", "Doe", "Actor");
		
		assertTrue("Should be equal", castAndCrewMemberJanea.equals(castAndCrewMemberJaneb));
		assertFalse("Should not be equal", castAndCrewMemberJanea.equals(castAndCrewMemberJohn));
		assertTrue("Hashcode", castAndCrewMemberJanea.hashCode() == castAndCrewMemberJaneb.hashCode());
	}
}
