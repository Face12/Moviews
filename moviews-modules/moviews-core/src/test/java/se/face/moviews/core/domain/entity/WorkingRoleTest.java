/**
 * 
 */
package se.face.moviews.core.domain.entity;

import static org.junit.Assert.*;

import org.junit.Test;

import se.face.moviews.core.domain.entity.WorkingRole;
import se.face.moviews.core.test.TestObjectFactory;

/**
 * @author Samuel
 *
 */
public class WorkingRoleTest {
	@Test
	public void shouldCreateCastAndCrewMember(){
		TestObjectFactory.workingRoleEntity();
	}
	
	@Test
	public void objectTest(){
		WorkingRole c1a = new WorkingRole(1);
		WorkingRole c1b = new WorkingRole(1);
		WorkingRole c2 = new WorkingRole(2);
		assertTrue("Should be equal", c1a.equals(c1b));
		assertFalse("Should not be equal", c1a.equals(c2));
		assertTrue("Hashcode", c1a.hashCode() == c1b.hashCode());
	}
}
