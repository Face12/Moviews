/**
 * 
 */
package se.face.moviews.core.client;

import org.jsoup.nodes.Document;

/**
 * @author Samuel
 *
 */
public interface IMDBHTMLClient {
	public Document getFullCreditsForMovie(String imdbId);
}
