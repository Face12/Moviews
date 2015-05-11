/**
 * 
 */
package se.face.moviews.web.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import se.face.moviews.api.model.Resource;

/**
 * @author Samuel
 *
 */
public class ResponseBuilder<T extends Resource> {
	private final T resource;
	
	private static final Logger logger = LoggerFactory.getLogger(ResponseBuilder.class);
	
	public ResponseBuilder(T resource){
		this.resource = resource;
	}
	
	public ResponseEntity<T> buildCreatedResponse(UriComponentsBuilder uriBuilder, String path){
		UriComponents uriComponents = 
				uriBuilder.path(path+"/{id}").buildAndExpand(resource.getId().intValue());
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setLocation(uriComponents.toUri());
		logger.debug("Created reponse build with location: "+uriComponents.toUriString());
		return new ResponseEntity<T>(resource, responseHeaders, HttpStatus.CREATED);
	}
}
