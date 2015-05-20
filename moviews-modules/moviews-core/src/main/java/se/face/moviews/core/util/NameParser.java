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
					cleanedFullName.substring(cleanedFullName.indexOf(nameWords[1])).trim());
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

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((firstName == null) ? 0 : firstName.hashCode());
			result = prime * result
					+ ((lastName == null) ? 0 : lastName.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			ParseResult other = (ParseResult) obj;
			if (firstName == null) {
				if (other.firstName != null) {
					return false;
				}
			} else if (!firstName.equals(other.firstName)) {
				return false;
			}
			if (lastName == null) {
				if (other.lastName != null) {
					return false;
				}
			} else if (!lastName.equals(other.lastName)) {
				return false;
			}
			return true;
		}
	}
}
