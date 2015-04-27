/**
 * 
 */
package se.face.moviews.integrationtest.validation;

import java.io.IOException;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.ApplicationContext;
import org.testng.Assert;

import com.consol.citrus.message.Message;
import com.consol.citrus.validation.callback.ValidationCallback;

/**
 * @author Samuel
 *
 */
public abstract class JsonValidationCallback<T> implements ValidationCallback {

	private Class<T> targetClass;
	
	public JsonValidationCallback(Class<T> targetClass){
		this.targetClass = targetClass;
	}
	
	@Override
	public final void validate(Message message) {
        validate(deserialize(message), message.copyHeaders());
    }
	
	private T deserialize(Message message) {
		Object payloadObject = message.getPayload();
		
		if (payloadObject instanceof String){
			ObjectMapper mapper = new ObjectMapper();
			String jsonString = (String)payloadObject;
			try {
				return mapper.readValue(jsonString, targetClass);
			} catch (IOException e) {
				throw new IllegalStateException("Can't deserialize jsonString: "+jsonString, e);
			}
		}
		else{
			Assert.fail("Expected string payload in response to deserialize got: "+payloadObject.getClass().getCanonicalName());
		}
		return null;
	}

	/**
     * Subclasses must override this method for validation.
     * @param message marshalled message payload object.
     * @param headers message headers
     */
    public abstract void validate(T message, Map<String, Object> headers);

	@Override
	public void setApplicationContext(ApplicationContext ctx) {
	}

}
