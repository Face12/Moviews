/**
 * 
 */
package se.face.moviews.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import se.face.moviews.api.model.Movie;
import se.face.moviews.api.model.Movies;
import se.face.moviews.api.model.PingResponse;
import se.face.moviews.core.service.MoviesService;
import se.face.moviews.web.util.ResponseBuilder;

/**
 * @author Samuel
 *
 */
@RestController
@RequestMapping(value = MoviewsController.PATH)
public class MoviewsController {
	public static final String PATH = "/movies";
	@Autowired
	private MoviesService moviesService;
	
	private static final Logger logger = LoggerFactory.getLogger(MoviewsController.class);
	
	@RequestMapping(value = "/ping", method = RequestMethod.GET)
	public PingResponse ping(){
		return new PingResponse();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Movie> saveMovie(@RequestBody Movie movie, UriComponentsBuilder uriBuilder){
		logger.info("Saving movie");		
		Movie savedMovie = moviesService.saveMovie(movie);
		logger.info("Movie saved with id "+savedMovie);
		return new ResponseBuilder<Movie>(savedMovie)
					.buildCreatedResponse(uriBuilder, PATH);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Movie> getMovie(@PathVariable int id){
		logger.info("Retrieving movie with id: "+id);		
		Movie movie = moviesService.getMovieById(id);
		if (movie == null){
			logger.info("Found no movie with id: "+id);	
			return new ResponseEntity<Movie>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Movie>(movie, HttpStatus.OK);
	}
	
	@RequestMapping(value="/search", method = RequestMethod.GET)
	public ResponseEntity<Movies> search(@RequestParam String query){
		logger.info("Searching for: "+query);
		Movies movies = moviesService.search(query);
		logger.info("Search "+query+" resulted in "+movies.getList().size()+" hits");
		return new ResponseEntity<Movies>(movies, HttpStatus.OK);
	}
}
