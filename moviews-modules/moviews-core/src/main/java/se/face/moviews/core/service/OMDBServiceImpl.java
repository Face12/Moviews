/**
 * 
 */
package se.face.moviews.core.service;

import java.io.IOException;
import java.net.URLEncoder;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectReader;
import org.springframework.stereotype.Service;

import se.face.moviews.api.model.Movie;
import se.face.moviews.api.model.Movies;
import se.face.moviews.core.factory.MovieFactory;
import se.face.moviews.core.omdb.MovieResponse;
import se.face.moviews.core.omdb.SearchResponse;

/**
 * @author Samuel
 *
 */
@Service
public class OMDBServiceImpl implements OMDBService{
	private static final String URL_GET_TEMPLATE = "http://www.omdbapi.com/?plot=short&r=json&type=Movie&i={imdbid}";
	private static final String URL_SEARCH_TEMPLATE = "http://www.omdbapi.com/?r=json&type=Movie&s={title}";
	
	@Override
	public Movies searchByTitle(String title) {
		final String http = URL_SEARCH_TEMPLATE.replaceAll("\\{title\\}", urlEncode(title));
		
		SearchResponse searchResponse = get(http, SearchResponse.class);
		return MovieFactory.resultFromExternal(searchResponse);
	}

	@Override
	public Movie getByImdbId(String imdbId) {
		final String http = URL_GET_TEMPLATE.replaceAll("\\{imdbid\\}", urlEncode(imdbId));

		MovieResponse movieResponse = get(http, MovieResponse.class);
		return MovieFactory.resultFromExternal(movieResponse);
	}

	private <T> T get(final String http, Class<T> responseClass) {
		ObjectMapper objectMapper = new ObjectMapper();
		ObjectReader reader = objectMapper.reader(responseClass);
		try (CloseableHttpClient client = createClient()) {
			HttpGet get = new HttpGet(http);
			CloseableHttpResponse response = client.execute(get);
			return reader.readValue(response.getEntity().getContent());
		} catch (IOException e) {
			throw new IllegalStateException("Failed!!!", e);
		}
	}
	
	private CloseableHttpClient createClient() {
		CloseableHttpClient client = HttpClientBuilder
			.create()
			.build();
		return client;
	}

	private String urlEncode(String string) {
		try {
			String encoded = URLEncoder.encode(string, "UTF-8");
			return encoded;
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

}
