/**
 * 
 */
package se.face.moviews.api.model;

import org.codehaus.jackson.annotate.JsonValue;

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
