/**
 * 
 */
package se.face.moviews.core.domain.dao;

import java.util.List;

import se.face.moviews.core.domain.entity.WorkingRole;

/**
 * @author Samuel
 *
 */
public interface WorkingRoleDao {
	List<WorkingRole> searchByName(String name);
}
