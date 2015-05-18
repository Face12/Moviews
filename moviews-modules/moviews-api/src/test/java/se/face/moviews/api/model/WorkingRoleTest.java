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
public class WorkingRoleTest {
	@Test
	public void objectTest(){
		WorkingRole castAndCrewMemberJanea = new WorkingRole(new Person("Jane", "Doe"), "Director");
		WorkingRole castAndCrewMemberJaneb = new WorkingRole(new Person("Jane", "Doe"), "Director");
		WorkingRole castAndCrewMemberJohn = new WorkingRole(new Person("John", "Doe"), "Actor");
		
		assertTrue("Should be equal", castAndCrewMemberJanea.equals(castAndCrewMemberJaneb));
		assertFalse("Should not be equal", castAndCrewMemberJanea.equals(castAndCrewMemberJohn));
		assertTrue("Hashcode", castAndCrewMemberJanea.hashCode() == castAndCrewMemberJaneb.hashCode());
	}
}
