/**
 * 
 */
package se.face.moviews.core.factory;

import se.face.moviews.api.model.Person;
import se.face.moviews.core.domain.entity.WorkingRole;
import se.face.moviews.core.util.NameParser;
import se.face.moviews.core.util.NameParser.ParseResult;


/**
 * @author Samuel
 *
 */
public class WorkingRoleFactory {

	public static WorkingRole convertFromApi(se.face.moviews.api.model.WorkingRole workingRole) {
		if (workingRole == null) return null;
		WorkingRole workingRoleEntity = new WorkingRole(
				workingRole.getId(), 
				workingRole.getPerson().getFirstName(), 
				workingRole.getPerson().getLastName(), 
				workingRole.getRole());
		return workingRoleEntity;
	}

	public static se.face.moviews.api.model.WorkingRole convertFromEntity(
			WorkingRole workingRoleEntity) {
		if (workingRoleEntity == null) return null;
		
		Person person = new Person(workingRoleEntity.getPerson().getPersonId(), 
				workingRoleEntity.getPerson().getFirstName(), 
				workingRoleEntity.getPerson().getLastName());
		return new se.face.moviews.api.model.WorkingRole(
				workingRoleEntity.getWorkingRoleId(), 
				person, 
				workingRoleEntity.getRole().getRoleText());
	}
	
	public static se.face.moviews.api.model.WorkingRole createApiByFullName(String fullName, String role){
		ParseResult result = NameParser.parseFullName(fullName);
		
		return new se.face.moviews.api.model.WorkingRole(
				new Person(result.getFirstName(), result.getLastName()), role);
	}
	
}
