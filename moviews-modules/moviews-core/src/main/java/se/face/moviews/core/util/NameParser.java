/**
 * 
 */
package se.face.moviews.core.util;

/**
 * @author Samuel
 *
 */
public final class NameParser {
	private NameParser(){}
	
	public static ParseResult parseFullName(final String fullName){
		String cleanedFullName = cleanName(fullName);
		String[] nameWords = cleanedFullName.split("\\s++");
		for (String nameWord: nameWords){
			nameWord = nameWord.trim();
		}
		if (nameWords.length == 1){
			return new ParseResult(nameWords[0], "");
		}
		else if (nameWords.length == 2){
			return new ParseResult(nameWords[0], nameWords[1]);
		}
		else{
			return new ParseResult(nameWords[0], 
					fullName.substring(fullName.indexOf(nameWords[1])).trim());
		}
	}
	
	private static String cleanName(String name) {
		return name
				.replaceAll("\\([^\\)]*+\\)", "")
				.trim();
	}

	public static class ParseResult{
		private final String firstName;
		private final String lastName;
		
		public ParseResult(String firstName, String lastName) {
			this.firstName = firstName;
			this.lastName = lastName;
		}

		public String getFirstName() {
			return firstName;
		}

		public String getLastName() {
			return lastName;
		}
	}
}
