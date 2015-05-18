/**
 * 
 */
package se.face.moviews.core.service;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectReader;
import org.springframework.stereotype.Service;

import se.face.moviews.api.model.Movies;
import se.face.moviews.api.model.Movies.MovieHit;
import se.face.moviews.core.domain.entity.Movie;
import se.face.moviews.core.factory.MovieFactory;
import se.face.moviews.core.omdb.MovieResponse;

/**
 * @author Samuel
 *
 */
@Service
public class OMDBServiceImpl implements OMDBService{
	private static final String URL_TEMPLATE = "http://www.omdbapi.com/?plot=short&r=json&t={title}";
	
	@Override
	public Movies searchByTitle(String title) {
		final String http = URL_TEMPLATE.replaceAll("\\{title\\}", urlEncode(title));
		ObjectMapper objectMapper = new ObjectMapper();
		ObjectReader reader = objectMapper.reader(MovieResponse.class);
		try (CloseableHttpClient client = createClient()) {
			HttpGet get = new HttpGet(http);
			CloseableHttpResponse response = client.execute(get);
			MovieResponse movieResponse = reader.readValue(response.getEntity().getContent());
			Movie movie = MovieFactory.resultFromExternal(movieResponse);
			if (movie != null){
				Movies movies = new Movies(new ArrayList<MovieHit>());
				movies.getList().add(new MovieHit(0, movie.getOriginalTitle()));
				return movies;
			}
			return new Movies(new ArrayList<MovieHit>());
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
