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
import se.face.moviews.core.service.OMDBService;
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
	
	@RequestMapping(value = "save/external/{id}", method = RequestMethod.POST)
	public ResponseEntity<Movie> saveMovieByExternalId(@PathVariable String id, UriComponentsBuilder uriBuilder){
		Movie movie = moviesService.getByImdbId(id);		
		return saveMovie(movie, uriBuilder);
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
		Movies movies = moviesService.searchInternal(query);
		logger.info("Search "+query+" resulted in "+movies.getList().size()+" hits");
		return new ResponseEntity<Movies>(movies, HttpStatus.OK);
	}
	
	@RequestMapping(value="/external/search", method = RequestMethod.GET)
	public ResponseEntity<Movies> searchExternally(@RequestParam String query){
		logger.info("External search for: "+query);
		Movies movies = moviesService.searchExternal(query);
		logger.info(" External search "+query+" resulted in "+movies.getList().size()+" hits");
		return new ResponseEntity<Movies>(movies, HttpStatus.OK);
	}
	
	@RequestMapping(value="/external/{imdbId}", method = RequestMethod.GET)
	public ResponseEntity<Movie> getExternal(@PathVariable String imdbId){
		logger.info("Getting movie with imdbId: "+imdbId);
		Movie movie = moviesService.getByImdbId(imdbId);
		if (movie != null){
			return new ResponseEntity<Movie>(movie, HttpStatus.OK);
		}
		logger.info("Movie with imdbId: "+imdbId+" was not found");
		return new ResponseEntity<Movie>(movie, HttpStatus.NOT_FOUND);
	}
}
