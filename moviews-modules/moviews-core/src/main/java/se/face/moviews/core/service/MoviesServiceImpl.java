/**
 * 
 */
package se.face.moviews.core.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import se.face.moviews.api.model.Movie;
import se.face.moviews.core.domain.dao.MovieDao;
import se.face.moviews.core.factory.MovieFactory;

/**
 * @author Samuel
 *
 */
@Service
public class MoviesServiceImpl implements MoviesService{

	@Autowired
	private MovieDao movieDao;
	
	@Transactional
	@Override
	public Movie saveMovie(Movie movie) {	
		se.face.moviews.core.domain.entity.Movie entity = MovieFactory.convertFromApi(movie);
		int id = movieDao.save(entity);
		return getMovieByIdWithCaCs(id);
	}

	@Transactional(readOnly = true)
	@Override
	public Movie getMovieById(int id) {
		return getMovieByIdWithCaCs(id);
	}

	private Movie getMovieByIdWithCaCs(int id) {
		se.face.moviews.core.domain.entity.Movie movie = movieDao.getWithCastAndCrew(id);
		Movie response = MovieFactory.convertFromEntity(movie);
		return response;
	}

}
