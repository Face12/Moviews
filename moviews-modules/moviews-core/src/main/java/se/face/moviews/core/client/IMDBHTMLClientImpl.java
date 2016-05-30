/**
 * 
 */
package se.face.moviews.core.client;

import java.io.IOException;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

/**
 * @author Samuel
 *
 */
@Component
public class IMDBHTMLClientImpl implements IMDBHTMLClient {
	private static final String IMDB_HOST = "http://www.imdb.com";
	
	private static final String PATH_FULL_CREDITS = "/title/{imdbid}/fullcredits";
	
	@Override
	public Document getFullCreditsForMovie(String imdbId) {		
		try {
			return Jsoup
					.connect(IMDB_HOST+PATH_FULL_CREDITS.replaceAll("\\{imdbid\\}", imdbId))
					.get();
		} catch (HttpStatusException e){
			int statusCode = e.getStatusCode();
			if (statusCode == 404 || statusCode == 403){
				return null;
			}
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
