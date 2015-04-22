/**
 * 
 */
package se.face.moviews.web.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import se.face.moviews.api.model.PingResponse;

/**
 * @author Samuel
 *
 */
@RestController
@RequestMapping(value = "/movies")
public class MoviewsController {
	
	@RequestMapping(value = "/ping", method = RequestMethod.GET)
	public PingResponse ping(){
		return new PingResponse();
	}
}
