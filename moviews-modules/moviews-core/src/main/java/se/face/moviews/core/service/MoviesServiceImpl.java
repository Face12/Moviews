/**
 * 
 */
package se.face.moviews.core.service;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import se.face.moviews.api.model.Movie;
import se.face.moviews.api.model.Movies;
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
	
	private static final Logger logger = LoggerFactory.getLogger(MoviesServiceImpl.class);
	
	@Transactional
	@Override
	public Movie saveMovie(Movie movie) {	
		logger.debug("Saving movie: "+movie);
		se.face.moviews.core.domain.entity.Movie entity = MovieFactory.convertFromApi(movie);
		int id = movieDao.save(entity);
		logger.debug("Movie saved with id: "+id);
		return getMovieByIdWithCaCs(id);
	}

	@Transactional(readOnly = true)
	@Override
	public Movie getMovieById(int id) {
		logger.debug("Getting movie with id: "+id);
		Movie movie = getMovieByIdWithCaCs(id);
		logger.debug("Returning movie for id="+id+": "+movie);
		return movie;
	}

	@Transactional(readOnly = true)
	@Override
	public Movies search(String query) {
		logger.debug("Searching for movies by query: "+query);
		List<se.face.moviews.core.domain.entity.Movie> list = movieDao.searchByOriginalTitle(query);
		logger.debug("Query "+query+" returns: "+list);
		return MovieFactory.createSearchResult(list);
	}


	private Movie getMovieByIdWithCaCs(int id) {
		se.face.moviews.core.domain.entity.Movie movie = movieDao.getWithCastAndCrew(id);
		Movie response = MovieFactory.convertFromEntity(movie);
		return response;
	}
}
