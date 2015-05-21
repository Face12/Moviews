/**
 * 
 */
package se.face.moviews.core.util;

import java.net.URLEncoder;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 * @author Samuel
 *
 */
public final class HTTPUtils {
	private HTTPUtils() {}
		
	public static CloseableHttpClient createClient() {
		CloseableHttpClient client = HttpClientBuilder
			.create()
			.build();
		return client;
	}

	public static String urlEncode(String string) {
		try {
			String encoded = URLEncoder.encode(string, "UTF-8");
			return encoded;
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}
}
