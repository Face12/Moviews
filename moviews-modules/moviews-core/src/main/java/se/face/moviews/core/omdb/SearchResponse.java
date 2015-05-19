
package se.face.moviews.core.omdb;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Generated with tool: http://jsongen.byingtondesign.com
 * @author Samuel
 *
 */
public class SearchResponse{
	@JsonProperty("Search")
   	private List<Search> search;
	
	//Added These
	@JsonProperty("Response")
	private String response;
	@JsonProperty("Error")
	private String error;
	
 	public List<Search> getSearch(){
		return this.search;
	}
	public void setSearch(List<Search> search){
		this.search = search;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
}
