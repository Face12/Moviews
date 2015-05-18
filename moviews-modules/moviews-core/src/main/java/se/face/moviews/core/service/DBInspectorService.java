/**
 * 
 */
package se.face.moviews.core.service;

import se.face.moviews.core.domain.entity.Movie;


/**
 * @author Samuel
 *
 */
public interface DBInspectorService {

	DBStatus checkMovieStatus(Movie movie);
	
	void pickupExistingCollectionEntries(Movie movie);

}
