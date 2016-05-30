/**
 * 
 */
package se.face.moviews.core.omdb;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Generated with tool: http://jsongen.byingtondesign.com
 * @author Samuel
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieResponse{
	@JsonProperty("Actors")
	private String actors;
	@JsonProperty("Awards")
	private String awards;
	@JsonProperty("Country")
	private String country;
	@JsonProperty("Director")
	private String director;
	@JsonProperty("Genre")
	private String genre;
	@JsonProperty("Language")
	private String language;
	@JsonProperty("Metascore")
	private String metascore;
	@JsonProperty("Plot")
	private String plot;
	@JsonProperty("Poster")
	private String poster;
	@JsonProperty("Rated")
	private String rated;
	@JsonProperty("Released")
	private String released;
	@JsonProperty("Response")
	private String response;
	@JsonProperty("Runtime")
	private String runtime;
	@JsonProperty("Title")
	private String title;
	@JsonProperty("Type")
	private String type;
	@JsonProperty("Writer")
	private String writer;
	@JsonProperty("Year")
	private String year;
	@JsonProperty("imdbID")
	private String imdbID;
	@JsonProperty("imdbRating")
	private String imdbRating;
	@JsonProperty("imdbVotes")
	private String imdbVotes;
   	
   	//Added These
	@JsonProperty("Error")
   	private String error;

 	public String getActors(){
		return this.actors;
	}
	public void setActors(String actors){
		this.actors = actors;
	}
 	public String getAwards(){
		return this.awards;
	}
	public void setAwards(String awards){
		this.awards = awards;
	}
 	public String getCountry(){
		return this.country;
	}
	public void setCountry(String country){
		this.country = country;
	}
 	public String getDirector(){
		return this.director;
	}
	public void setDirector(String director){
		this.director = director;
	}
 	public String getGenre(){
		return this.genre;
	}
	public void setGenre(String genre){
		this.genre = genre;
	}
 	public String getLanguage(){
		return this.language;
	}
	public void setLanguage(String language){
		this.language = language;
	}
 	public String getMetascore(){
		return this.metascore;
	}
	public void setMetascore(String metascore){
		this.metascore = metascore;
	}
 	public String getPlot(){
		return this.plot;
	}
	public void setPlot(String plot){
		this.plot = plot;
	}
 	public String getPoster(){
		return this.poster;
	}
	public void setPoster(String poster){
		this.poster = poster;
	}
 	public String getRated(){
		return this.rated;
	}
	public void setRated(String rated){
		this.rated = rated;
	}
 	public String getReleased(){
		return this.released;
	}
	public void setReleased(String released){
		this.released = released;
	}
 	public String getResponse(){
		return this.response;
	}
	public void setResponse(String response){
		this.response = response;
	}
 	public String getRuntime(){
		return this.runtime;
	}
	public void setRuntime(String runtime){
		this.runtime = runtime;
	}
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
 	public String getWriter(){
		return this.writer;
	}
	public void setWriter(String writer){
		this.writer = writer;
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
 	public String getImdbRating(){
		return this.imdbRating;
	}
	public void setImdbRating(String imdbRating){
		this.imdbRating = imdbRating;
	}
 	public String getImdbVotes(){
		return this.imdbVotes;
	}
	public void setImdbVotes(String imdbVotes){
		this.imdbVotes = imdbVotes;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
}
