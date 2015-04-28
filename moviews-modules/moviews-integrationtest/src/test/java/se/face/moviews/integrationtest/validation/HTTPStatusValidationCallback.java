/**
 * 
 */
package se.face.moviews.integrationtest.validation;

import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.testng.Assert;

import com.consol.citrus.message.Message;
import com.consol.citrus.validation.callback.ValidationCallback;

/**
 * @author Samuel
 *
 */
public class HTTPStatusValidationCallback implements ValidationCallback {
	private final HttpStatus httpStatus;
	
	public HTTPStatusValidationCallback(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
	
	@Override
	public void validate(Message message) {
		Assert.assertEquals(String.valueOf(message.getHeader("citrus_http_status_code")), httpStatus.toString(),
				"Unexpected status code");
	}

	@Override
	public void setApplicationContext(ApplicationContext ctx) {
	}

}
