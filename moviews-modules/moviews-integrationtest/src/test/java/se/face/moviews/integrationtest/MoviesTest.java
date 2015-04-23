package se.face.moviews.integrationtest;

import org.springframework.core.io.ClassPathResource;
import org.testng.annotations.Test;

import com.consol.citrus.dsl.annotations.CitrusTest;
import com.consol.citrus.dsl.TestNGCitrusTestBuilder;
import com.consol.citrus.message.MessageType;

/**
 * This is a sample Citrus integration test for loading XML syntax test case.
 *
 * @author Citrus
 */
@Test
public class MoviesTest extends TestNGCitrusTestBuilder {

    @CitrusTest(name = "MoviesPingTest")
    public void pingTest() {    	
    	send("moviewsclient")
			.header("citrus_endpoint_uri", "${endpoint.url}/movies/ping")
			.messageType(MessageType.JSON);
	
    	receive("moviewsclient")
			.messageType(MessageType.JSON)
			.header("citrus_http_status_code", "200")
			.payload(new ClassPathResource("templates/ping.json"))
			;
    }
}
