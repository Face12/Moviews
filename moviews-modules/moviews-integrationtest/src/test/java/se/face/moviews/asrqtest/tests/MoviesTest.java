/**
 * 
 */
package se.face.moviews.asrqtest.tests;

import static org.junit.Assert.*;
import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.UUID;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;

import com.jayway.restassured.http.ContentType;

import se.face.moviews.asrqtest.config.Moviews;
import se.face.moviews.asrqtest.data.JsonBuilderFromTemplate;
/**
 * @author Samuel
 *
 */
@RunWith(Arquillian.class)
public class MoviesTest {		
	private static final String MOVIE_TO_SAVE = "templates/movieToSave.json";
	private static final String MOVIE_UPDATE = "templates/movieUpdate.json";
	private static final String MOVIE_SAVE_FAILS = "templates/movieSaveFails.json";
	
	private static final String JSON_UTF8 = ContentType.JSON.withCharset(Charset.forName("UTF-8"));
	
	@Deployment(testable = false)
	public static WebArchive deploy(){
		WebArchive webArchive = ShrinkWrap.create(WebArchive.class);
		Moviews.addProjectClasses(webArchive);
		Moviews.addMavenDependencies(webArchive);
		return webArchive;
	}
	
	@ArquillianResource 
	private URL baseURI;
	
	private String getRootPath(){
		return baseURI.toExternalForm();
	}
	
	@Test
	public void moviesPingTest() {
		when().
			get(getRootPath() + "movies/ping")
		.then()
			.body("ping", is("pong"));
	}

	@Test
	public void moviesShouldBeSavedTest() throws IOException{
		given()
			.body(saveMovieJson(movieTitle()))
			.contentType(JSON_UTF8)
		.when()
			.post(getRootPath() + "movies")
		.then()
			.statusCode(HttpStatus.CREATED.value())
			.header("Location", containsString("/movies/"));
	}
	
	@Test
	public void moviesShouldBeUpdatedTest() throws IOException{
		int id = saveNewMovie(movieTitle());
		final String updatedTitle = movieTitle();
		String json = JsonBuilderFromTemplate.file(MOVIE_UPDATE)
				.param("updateMovieTitle", updatedTitle)
				.param("updateMovieId", String.valueOf(id))
				.buildJson();
		given()
			.body(json)
			.contentType(JSON_UTF8)
		.when()
			.put(getRootPath() + "movies")
		.then()
			.statusCode(HttpStatus.NO_CONTENT.value());
		
		when()
			.get(getRootPath() + "movies/"+id)
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("id", is(id))
			.body("originalTitle", is(updatedTitle))
			.body("plot", is("An integration test movie plot. (Updated)"));
	}

	@Test
	public void saveShouldFailTest(){
		String json = JsonBuilderFromTemplate.file(MOVIE_SAVE_FAILS)
			.buildJson();
		given()
			.body(json)
			.contentType(JSON_UTF8)
		.when()
			.post(getRootPath() + "movies")
		.then()
			.statusCode(HttpStatus.BAD_REQUEST.value());
	}
	
	@Test
	public void shouldRetrieveMovie(){
		int id = saveNewMovie(movieTitle());
		
		when()
			.get(getRootPath() + "movies/"+id)
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("id", is(id));
	}
	
	@Test
	public void shouldFindMovie(){
		String movieTitle = movieTitle();
		
		saveNewMovie(movieTitle);
		
		given()
			.parameter("query", movieTitle)
		.when()
			.get(getRootPath() + "movies/search")
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("list.originalTitle", is(Arrays.asList(movieTitle)));
	}
	
	@Test
	public void shouldNotFindMovieAndGet404(){
		when()
			.get(getRootPath() + "movies/1000000")
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}
	
	@Test
	public void shouldFindExternalMovie(){
		final String title = "Terminator";
		given()
			.parameter("query", title)
		.when()
			.get(getRootPath() + "movies/external/search")
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("list", hasSize(greaterThanOrEqualTo(1)));
	}
	
	@Test
	public void shouldGetEmptyListForExternalMovieSearchWithNoMatch(){
		final String title = "TerminatorNOOOOHIIIIT";
		given()
			.parameter("query", title)
		.when()
			.get(getRootPath() + "movies/external/search")
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("list", hasSize(equalTo(0)));
	}
	
	@Test
	public void shouldGetExternalMovieByImdbId(){
		final String imdbId = "tt0103064";
		final String expectedTitle = "Terminator 2: Judgment Day";
		when()
			.get(getRootPath() + "movies/external/"+imdbId)
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("originalTitle", is(expectedTitle));
	}
	
	@Test
	public void shouldGet404ForExternalMovieByImdbId(){
		final String imdbId = "tt9999999";
		when()
			.get(getRootPath() + "movies/external/"+imdbId)
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}
	
	private int saveNewMovie(String originalTitle) {
		String locationHeader = 
				given().body(saveMovieJson(originalTitle)).contentType(JSON_UTF8)
				.post(getRootPath() + "movies").header("Location");
		
		int id = Integer.parseInt(locationHeader.substring(locationHeader.lastIndexOf("/")+1));
		return id;
	}
	
	private String saveMovieJson(String originalTitle) {		
		String json = JsonBuilderFromTemplate.file(MOVIE_TO_SAVE)
			.param("savedMovieTitle", originalTitle)
			.buildJson();
		return json;
	}
	
	private String movieTitle() {
		final String movieTitle = "movieTestMovie";
		return movieTitle +
				UUID.randomUUID().toString().substring(0, 6);
	}
}
