/**
 * 
 */
package se.face.moviews.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import se.face.moviews.api.model.Movie;
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
	
	@RequestMapping(value = "/ping", method = RequestMethod.GET)
	public PingResponse ping(){
		return new PingResponse();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Movie> saveMovie(@RequestBody Movie movie, UriComponentsBuilder uriBuilder){
		System.out.println("Saving movie: "+movie.toString());		
		Movie savedMovie = moviesService.saveMovie(movie);
		return new ResponseBuilder<Movie>(savedMovie)
					.buildCreatedResponse(uriBuilder, PATH);
	}
}