/**
 * 
 */
package se.face.moviews.domain.dao;

import se.face.moviews.domain.entity.Movie;

/**
 * @author Samuel
 *
 */
public interface MovieDao {

	public int save(Movie movie);

	public void update(Movie movie);
	
	public Movie get(int id);

	public Movie getWithCastAndCrew(int id);
}


