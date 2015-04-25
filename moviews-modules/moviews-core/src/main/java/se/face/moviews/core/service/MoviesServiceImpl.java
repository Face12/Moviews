/**
 * 
 */
package se.face.moviews.core.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		se.face.moviews.core.domain.entity.Movie saved = movieDao.getWithCastAndCrew(id);
		Movie response = MovieFactory.convertFromEntity(saved);
		return response;
	}

}
