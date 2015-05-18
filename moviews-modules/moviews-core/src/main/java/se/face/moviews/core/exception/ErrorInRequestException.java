/**
 * 
 */
package se.face.moviews.core.exception;

import se.face.moviews.core.util.ErrorCode;

/**
 * @author Samuel
 *
 */
public class ErrorInRequestException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private final ErrorCode errorCode;
	
	public ErrorInRequestException(String message, ErrorCode errorCode){
		super(message);
		this.errorCode = errorCode;
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}
}
