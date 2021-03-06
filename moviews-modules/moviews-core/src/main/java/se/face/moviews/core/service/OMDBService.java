/**
 * 
 */
package se.face.moviews.core.service;

import se.face.moviews.api.model.Movie;
import se.face.moviews.api.model.Movies;

/**
 * @author Samuel
 *
 */
public interface OMDBService {
	Movies searchByTitle(String title);
	
	Movie getByImdbId(String imdbId);
}
