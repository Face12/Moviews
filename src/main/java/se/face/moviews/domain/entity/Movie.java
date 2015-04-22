/**
 * 
 */
package se.face.moviews.domain.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * @author Samuel
 *
 */
@Table(name = "Movie")
@Entity
public class Movie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int movieId;
	
	@Column
	private String originalTitle;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(
			name = "CastAndCrewInMovie",
			joinColumns = {
				@JoinColumn(name = "movieId", nullable = false, updatable = false)
			},
			inverseJoinColumns = {
				@JoinColumn(name = "castAndCrewMemberId", nullable = false, updatable = false)
			})
	private Set<CastAndCrewMember> castAndCrew;
	
	public Movie() {}
	
	public Movie(String originalTitle) {
		this.originalTitle = originalTitle;
	}
	
	public Movie(int id){
		this.movieId = id;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public String getOriginalTitle() {
		return originalTitle;
	}

	public void setOriginalTitle(String originalTitle) {
		this.originalTitle = originalTitle;
	}
	
	public Set<CastAndCrewMember> getCastAndCrew() {
		return castAndCrew;
	}

	public void setCastAndCrew(Set<CastAndCrewMember> castAndCrew) {
		this.castAndCrew = castAndCrew;
	}

	public void addCastAndCrewMember(CastAndCrewMember castAndCrewMember) {
		if (castAndCrew == null){
			castAndCrew = new HashSet<CastAndCrewMember>();
		}
		castAndCrew.add(castAndCrewMember);		
	}

	@Override
	public String toString() {
		return "Movie [movieId=" + movieId + ", originalTitle=" + originalTitle
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + movieId;
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
		if (movieId != other.movieId) {
			return false;
		}
		return true;
	}

}
