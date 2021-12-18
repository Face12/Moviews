/**
 * 
 */
package se.face.moviews.api.model;


import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author Samuel
 *
 */
public enum DateQuality {
	YEAR,
	MONTH,
	DAY
	;
	
	@JsonValue
	public int getOrdinal(){
		return this.ordinal();
	}
}
