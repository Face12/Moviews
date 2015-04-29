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
public interface MoviesService {
	public Movie saveMovie(Movie movie);
	
	public Movie getMovieById(int id);
	
	public Movies search(String query);
}
