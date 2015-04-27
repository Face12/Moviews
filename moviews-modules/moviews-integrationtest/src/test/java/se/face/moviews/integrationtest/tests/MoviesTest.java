package se.face.moviews.integrationtest.tests;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.Test;

import se.face.moviews.api.model.Movie;
import se.face.moviews.integrationtest.config.AppConfig;
import se.face.moviews.integrationtest.config.TomcatRunner;
import se.face.moviews.integrationtest.validation.HTTPStatusValidationCallback;
import se.face.moviews.integrationtest.validation.JsonValidationCallback;

import com.consol.citrus.dsl.annotations.CitrusTest;
import com.consol.citrus.dsl.TestNGCitrusTestBuilder;
import com.consol.citrus.message.MessageType;

import static org.testng.Assert.*;
/**
 * This is a sample Citrus integration test for loading XML syntax test case.
 *
 * @author Citrus
 */
@Test
@ContextConfiguration(classes = AppConfig.class)
public class MoviesTest extends TestNGCitrusTestBuilder {
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private TomcatRunner tomcatRunner;
	
	private static final String SAVED_MOVIE_TITLE = "movieTestMovie";
	private static final String SAVED_MOVIE_SELECT_WHERE = "where originalTitle='"+SAVED_MOVIE_TITLE+"'";
	
    @CitrusTest(name = "MoviesPingTest")
    public void moviesPingTest() {    	
    	send("moviewsclient")
			.header("citrus_endpoint_uri", "${endpoint.url}/movies/ping")
			.header("citrus_http_method", "GET")
			.messageType(MessageType.JSON);
	
    	receive("moviewsclient")
			.header("citrus_http_status_code", "200")
			.messageType(MessageType.JSON)
			.payload(new ClassPathResource("templates/ping_response.json"))
			;
    }
    
    @CitrusTest(name = "MoviesShouldBeSavedTest")
    public void moviesShouldBeSavedTest(){
    	variable("savedMovieTitle", SAVED_MOVIE_TITLE);
    	send("moviewsclient")
			.header("citrus_endpoint_uri", "${endpoint.url}/movies")
			.header("citrus_http_method", "POST")
			.messageType(MessageType.JSON)
			.payload(new ClassPathResource("templates/movieToSave.json"));
    	
    	receive("moviewsclient").validationCallback(new JsonValidationCallback<Movie>(Movie.class) {
			@Override
			public void validate(Movie movie, Map<String, Object> headers) {
				assertEquals(headers.get("citrus_http_status_code"), 201);
				assertNotNull(headers.get("Location"));
				assertNotNull(movie.getId());
				assertEquals(movie.getCastAndCrew().size(), 2, "Should have cast and crew");
				movie.getCastAndCrew()
					.forEach(c -> assertNotNull(c.getId()));
			}
		});
		
    	query(dataSource)
			.statement("select mov.movieId from movie mov"+
					" inner join cast_and_crew_in_movie cacim on cacim.movieId=mov.movieId"+
					" inner join cast_and_crew_member cac on cac.castAndCrewMemberId=cacim.castAndCrewMemberId"+
					" "+SAVED_MOVIE_SELECT_WHERE
					)
			.validate("movieId", "@ignore@", "@ignore@");
    }
    
    @CitrusTest(name = "SaveShouldFailTest")
    public void saveShouldFailTest(){
    	send("moviewsclient")
			.header("citrus_endpoint_uri", "${endpoint.url}/movies")
			.header("citrus_http_method", "POST")
			.messageType(MessageType.JSON)
			.payload(new ClassPathResource("templates/movieSaveFails.json"));
    	receive("moviewsclient")
    		.validationCallback(new HTTPStatusValidationCallback(HttpStatus.BAD_REQUEST));
    }
}
