/**
 * 
 */
package se.face.moviews.api.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Samuel
 *
 */
public class Movie {	
	private Integer id;
	private String originalTitle;
	private List<CastAndCrewMember> castAndCrew = new ArrayList<CastAndCrewMember>();

	public Movie(){}
	
	public Movie(String originalTitle){
		this(originalTitle, null);
	}
	
	public Movie(String originalTitle, Integer id){
		this.originalTitle = originalTitle;
		this.id = id;
	}
	
	public void addCastAndCrew(CastAndCrewMember castAndCrewMember) {
		this.castAndCrew.add(castAndCrewMember);
	}

	public Integer getId() {
		return id;
	}

	public List<CastAndCrewMember> getCastAndCrew() {
		return castAndCrew;
	}

	public String getOriginalTitle() {
		return originalTitle;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", originalTitle=" + originalTitle
				+ ", castAndCrew=" + castAndCrew + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((castAndCrew == null) ? 0 : castAndCrew.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((originalTitle == null) ? 0 : originalTitle.hashCode());
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
		Movie other = (Movie) obj;
		if (castAndCrew == null) {
			if (other.castAndCrew != null) {
				return false;
			}
		} else if (!castAndCrew.equals(other.castAndCrew)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (originalTitle == null) {
			if (other.originalTitle != null) {
				return false;
			}
		} else if (!originalTitle.equals(other.originalTitle)) {
			return false;
		}
		return true;
	}

}
