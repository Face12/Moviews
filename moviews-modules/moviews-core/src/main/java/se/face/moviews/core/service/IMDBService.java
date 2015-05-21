/**
 * 
 */
package se.face.moviews.core.service;

import java.util.List;

import se.face.moviews.api.model.WorkingRole;

/**
 * @author Samuel
 *
 */
public interface IMDBService {
	List<WorkingRole> getAllWorkingRolesForMovie(String imdbId);
}
