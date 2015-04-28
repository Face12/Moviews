/**
 * 
 */
package se.face.moviews.web.util;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import se.face.moviews.api.model.Resource;

/**
 * @author Samuel
 *
 */
public class ResponseBuilderTest {
	final String testPath = "/testPath";
	final String testHost = "http://test";		
	final int id = 2;
	final UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(testHost);
	
	@Test
	public void shouldBuildResponseWithStatusCodeCreatedAndLocationUrl(){	
		TestResource testResource = new TestResource(id, "Hello");
		ResponseBuilder<TestResource> responseBuilder = 
				new ResponseBuilder<TestResource>(testResource);
		ResponseEntity<TestResource> responseEntity = 
				responseBuilder.buildCreatedResponse(uriBuilder, testPath);
		
		assertEquals(testResource, responseEntity.getBody());
		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
		
		String location = responseEntity.getHeaders().getFirst("Location");
		assertNotNull("No location header", location);
		assertEquals(testHost+testPath+"/"+id, location);
	}
	
	@Test(expected = NullPointerException.class)
	public void createdResourceMustHaveId(){
		final TestResource noId = new TestResource(null, "Hello");
		ResponseBuilder<TestResource> responseBuilder = 
				new ResponseBuilder<TestResource>(noId);
		responseBuilder.buildCreatedResponse(uriBuilder, testPath);
	}
	
	private static class TestResource implements Resource{
		private final Integer id;
		private final String string;
		
		public TestResource(Integer id, String string) {
			this.id = id;
			this.string = string;
		}
		
		@Override
		public Integer getId() {
			return id;
		}
		
		@Override
		public boolean equals(Object other){
			return (other instanceof TestResource) && 
				   ((TestResource)other).id == id &&
				   ((TestResource)other).string.equals(string);
		}
		
	}
}
