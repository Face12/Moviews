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
	public void shouldParse(){
		ParseResult result = NameParser.parseFullName("Alejandro Gonz�lez I��rritu (idea)");
		assertEquals("Alejandro", result.getFirstName());
		assertEquals("Gonz�lez I��rritu", result.getLastName());
	}
}
