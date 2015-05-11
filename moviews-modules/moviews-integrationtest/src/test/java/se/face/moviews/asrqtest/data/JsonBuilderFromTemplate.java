/**
 * 
 */
package se.face.moviews.asrqtest.data;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;


/**
 * To build a Json structure from a template.
 * 
 * @author Samuel
 *
 */
public class JsonBuilderFromTemplate{
	private final String path;
	private final String jsonTemplate;
	private final Map<String, String> assignedParameters = new HashMap<String, String>();
	
	private static final Pattern hasNonAssigned = Pattern.compile("\\{[^\\:\\s]+\\}");
	private static final String UTF8 = "UTF-8";
	
	/**
	 * Creates a new template from a file.
	 * @param path
	 * @return
	 */
	public static JsonBuilderFromTemplate file(String path){
		return new JsonBuilderFromTemplate(path, readFileAsString(path));
	}
		
	private JsonBuilderFromTemplate(String path, String jsonTemplate) {
		this.path = path;
		this.jsonTemplate = jsonTemplate;
	}
	
	/**
	 * Set's a parameter in the template. The template must have a placeholder {...} for the parameter<br>
	 * E.g.<br>
	 * Template is : { "firstName" : "{testFirstName}" }<br>
	 * Then "testFirstName" will be set to "John" for param("testFirstName", "John").
	 * 
	 * @param key - the parameter name
	 * @param value - the value to set key to
	 * @throws IllegalArgumentException if the template does not contain a placeholder for key
	 * @return
	 */
	public JsonBuilderFromTemplate param(String key, String value){
		if (!jsonTemplate.contains("{"+key+"}")){
			throw new IllegalArgumentException("Found no placeholder for\""+key+"\" in template "+path+".");
		}
		assignedParameters.put(key, value);
		return this;
	}
	
	/**
	 * Builds json string and clears the assigned parameters.
	 * @return
	 * @throws IllegalStateException if not all parameters have been assigned a value.
	 */
	public String buildJson(){
		String json = jsonTemplate;
		for (Entry<String, String> entry : assignedParameters.entrySet()){
			json = json.replaceAll("\\{"+entry.getKey()+"\\}", entry.getValue());
		}
		Matcher matcher = hasNonAssigned.matcher(json);
		if (matcher.find()){
			String nonAssigned = matcher.group();
			throw new IllegalStateException("Can't build json since not all parameters are assigned. Found: "+nonAssigned);
		}
		assignedParameters.clear();
		return json;
	}

	private static String readFileAsString(String path){
		try{
			File file = new ClassPathResource(path).getFile();
			return FileUtils.readFileToString(file, UTF8);
		} catch (Exception e){
			throw new IllegalArgumentException("Can't read file: "+path, e);
		}
	}
}
