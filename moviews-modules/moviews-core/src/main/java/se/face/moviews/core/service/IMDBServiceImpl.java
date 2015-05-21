/**
 * 
 */
package se.face.moviews.core.service;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.face.moviews.api.model.WorkingRole;
import se.face.moviews.core.client.IMDBHTMLClient;
import se.face.moviews.core.factory.WorkingRoleFactory;

/**
 * @author Samuel
 *
 */
@Service
public class IMDBServiceImpl implements IMDBService {

	private static final String DIRECTOR_HEADER = "Directed by";
	private static final String WRITER_HEADER = "Writing Credits";
	private static final String PRODUCER_HEADER = "Produced by";
	private static final String PRODUCER_TITLE = "producer";
	private static final String ACTOR_HEADER = "Cast";
	private static final String ACTOR_UNCREDITED_START = "Rest of cast listed alphabetically:";
	
	@Autowired
	private IMDBHTMLClient imdbClient;
	
	@Override
	public List<WorkingRole> getAllWorkingRolesForMovie(String imdbId) {
		Document document = imdbClient.getFullCreditsForMovie(imdbId);

		List<WorkingRole> ret = new ArrayList<WorkingRole>();
		if (document != null){
			Elements headers = document.select("h4.dataHeaderWithBorder");
			
			for (Element header: headers){
				final String headerText = trimWhiteSpaceHTML(header.ownText());
				Element table = header.nextElementSibling();
				switch (headerText){
					case DIRECTOR_HEADER:				
						addSimple(ret, table, "Director");
						break;
					case WRITER_HEADER:				
						addSimple(ret, table, "Writer");
						break;
					case PRODUCER_HEADER:
						Elements producerRows = table.select("tr");
						for (Element row: producerRows){
							Element credit = row.select("td.credit").first();
							if (trimWhiteSpaceHTML(credit.ownText()).equalsIgnoreCase(PRODUCER_TITLE)){
								String producerText = row.select("td.name a").first().ownText();
								ret.add(WorkingRoleFactory.createApiByFullName(trimWhiteSpaceHTML(producerText), 
										"Producer"));
							}
						}
						break;
					case ACTOR_HEADER:
						Elements actorRows = table.select("tr");
						for (Element row : actorRows){
							Elements tds = null;
							Element previousElementSibling = row.previousElementSibling();
							if (previousElementSibling != null){
								tds = previousElementSibling.select("td");
							}
							if (tds != null && tds.first().ownText()
								.contains(ACTOR_UNCREDITED_START)){
								break;
							}
							Element actorSpan = row.select("td[itemprop=actor] span[itemprop=name]").first();
							if (actorSpan != null){
								String actorText = actorSpan.ownText();
								ret.add(WorkingRoleFactory.createApiByFullName(trimWhiteSpaceHTML(actorText), "Actor"));
							}
						}
						break;
				}
			}
		}
		return ret;
	}

	private void addSimple(List<WorkingRole> ret, Element table, String role) {
		Elements people = table.select("td.name a");
		for (Element person: people){
			String personText = person.ownText();
			ret.add(WorkingRoleFactory.createApiByFullName(trimWhiteSpaceHTML(personText), role));
		}
	}

	private String trimWhiteSpaceHTML(final String text) {
		return text
				.replaceFirst("^[\\s\\u00A0]++", "")
				.replaceFirst("[\\s\\u00A0]++$", "");
	}

}
