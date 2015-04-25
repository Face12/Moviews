/**
 * 
 */
package se.face.moviews.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import se.face.moviews.api.model.Movie;
import se.face.moviews.api.model.PingResponse;
import se.face.moviews.core.service.MoviesService;

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
		UriComponents uriComponents = 
				uriBuilder.path(PATH+"/{id}").buildAndExpand(savedMovie.getId());
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setLocation(uriComponents.toUri());
		return new ResponseEntity<Movie>(savedMovie, responseHeaders, HttpStatus.CREATED);
	}
}
