/**
 * 
 */
package se.face.moviews.core.util;

import static org.junit.Assert.*;

import org.junit.Test;

import se.face.moviews.core.util.NameParser.ParseResult;

/**
 * @author Samuel
 *
 */
public class NameParserTest {
	@Test
	public void shouldRemoveDescription(){
		ParseResult result = NameParser.parseFullName("Alejandro Gonz�lez I��rritu (idea)");
		assertEquals("Alejandro", result.getFirstName());
		assertEquals("Gonz�lez I��rritu", result.getLastName());
	}
	
	@Test
	public void shouldRemoveNick(){
		ParseResult result = NameParser.parseFullName("Tommy 'Tiny' Lister");
		assertEquals("Tommy", result.getFirstName());
		assertEquals("Lister", result.getLastName());
	}
}
