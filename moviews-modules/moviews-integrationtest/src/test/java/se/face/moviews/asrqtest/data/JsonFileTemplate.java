/**
 * 
 */
package se.face.moviews.asrqtest.data;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;


/**
 * @author Samuel
 *
 */
public class JsonFileTemplate{
	private final String path;
	private final String json;
		
	private static final String UTF8 = "UTF-8";
	
	public JsonFileTemplate(String path){
		this(path, readFileAsString(path));
	}
		
	private JsonFileTemplate(String path, String json) {
		this.path = path;
		this.json = json;
	}
	
	public JsonFileTemplate param(String key, String value){
		if (!json.contains("{"+key+"}")){
			throw new IllegalArgumentException("Found no placeholder for\""+key+"\" in template "+path);
		}
		final String newJson = json.replaceAll("\\{"+key+"\\}", value);
		return new JsonFileTemplate(path, newJson);
	}
	
	public String getJson(){
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
