/**
 * 
 */
package se.face.moviews.core.util;

/**
 * @author Samuel
 *
 */
public enum ErrorCode {
	ENTITY_ALLREADY_EXISTS(100)
	;
	private final int value;
	
	private ErrorCode(int value){
		this.value = value;
	}

	public int value() {
		return value;
	}
}
