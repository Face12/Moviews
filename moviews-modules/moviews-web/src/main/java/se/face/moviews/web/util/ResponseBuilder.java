/**
 * 
 */
package se.face.moviews.web.util;

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
	
	public ResponseBuilder(T resource){
		this.resource = resource;
	}
	
	public ResponseEntity<T> buildCreatedResponse(UriComponentsBuilder uriBuilder, String path){
		UriComponents uriComponents = 
				uriBuilder.path(path+"/{id}").buildAndExpand(resource.getId());
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setLocation(uriComponents.toUri());
		return new ResponseEntity<T>(resource, responseHeaders, HttpStatus.CREATED);
	}
}
