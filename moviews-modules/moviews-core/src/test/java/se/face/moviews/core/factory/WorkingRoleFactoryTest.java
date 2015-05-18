/**
 * 
 */
package se.face.moviews.core.factory;

import static org.junit.Assert.*;

import org.junit.Test;

import se.face.moviews.core.domain.entity.WorkingRole;
import se.face.moviews.core.factory.WorkingRoleFactory;
import se.face.moviews.core.test.TestObjectFactory;

/**
 * @author Samuel
 *
 */
public class WorkingRoleFactoryTest {
	
	@Test
	public void createFromApiTest(){
		se.face.moviews.api.model.WorkingRole workingRoleApi = 
				TestObjectFactory.workingRoleApi();
		WorkingRole workingRole = 
				WorkingRoleFactory.convertFromApi(workingRoleApi);
		assertNotNull(workingRole);
		assertEquals(workingRoleApi.getPerson().getId(), workingRole.getPerson().getPersonId());
		assertEquals(workingRoleApi.getPerson().getFirstName(), workingRole.getPerson().getFirstName());
		assertEquals(workingRoleApi.getPerson().getLastName(), workingRole.getPerson().getLastName());
		assertEquals(workingRoleApi.getRole(), workingRole.getRole().getRoleText());
		
		assertNull(WorkingRoleFactory.convertFromApi(null));
	}
	
	@Test
	public void createFromEntityTest(){
		WorkingRole workingRole = TestObjectFactory.workingRoleEntity();
		se.face.moviews.api.model.WorkingRole workingRoleApi = 
				WorkingRoleFactory.convertFromEntity(workingRole);
		assertEquals(workingRole.getPerson().getPersonId(), workingRoleApi.getPerson().getId());
		assertEquals(workingRole.getPerson().getFirstName(), workingRoleApi.getPerson().getFirstName());
		assertEquals(workingRole.getPerson().getLastName(), workingRoleApi.getPerson().getLastName());
		assertEquals(workingRole.getRole().getRoleText(), workingRoleApi.getRole());
		
		assertNull(WorkingRoleFactory.convertFromEntity(null));
	}
}
