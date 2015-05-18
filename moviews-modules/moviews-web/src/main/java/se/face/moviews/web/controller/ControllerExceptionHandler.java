/**
 * 
 */
package se.face.moviews.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import se.face.moviews.api.model.Error;
import se.face.moviews.core.exception.ErrorInRequestException;

/**
 * @author Samuel
 *
 */
@ControllerAdvice
public class ControllerExceptionHandler {
	
	@ExceptionHandler(ErrorInRequestException.class)
	public ResponseEntity<Error> errorInRequestExceptionHandler(ErrorInRequestException exception){
		Error error = new Error(exception.getErrorCode().value(), exception.getMessage());
		return new ResponseEntity<Error>(error, HttpStatus.BAD_REQUEST);
	}
}
