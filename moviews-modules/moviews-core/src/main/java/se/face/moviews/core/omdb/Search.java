
package se.face.moviews.core.omdb;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Generated with tool: http://jsongen.byingtondesign.com
 * @author Samuel
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Search{
	@JsonProperty("Title")
   	private String title;
	@JsonProperty("Type")
   	private String type;
	@JsonProperty("Year")
   	private String year;
	@JsonProperty("imdbID")
   	private String imdbID;

 	public String getTitle(){
		return this.title;
	}
	public void setTitle(String title){
		this.title = title;
	}
 	public String getType(){
		return this.type;
	}
	public void setType(String type){
		this.type = type;
	}
 	public String getYear(){
		return this.year;
	}
	public void setYear(String year){
		this.year = year;
	}
 	public String getImdbID(){
		return this.imdbID;
	}
	public void setImdbID(String imdbID){
		this.imdbID = imdbID;
	}
}
