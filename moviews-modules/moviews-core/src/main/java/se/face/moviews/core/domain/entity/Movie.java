/**
 * 
 */
package se.face.moviews.core.domain.entity;

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
@Table(name = "movie")
@Entity
public class Movie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer movieId;
	
	@Column
	private String originalTitle;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(
			name = "cast_and_crew_in_movie",
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
	
	public Movie(Integer id){
		this.movieId = id;
	}

	public Integer getMovieId() {
		return movieId;
	}

	public void setMovieId(Integer movieId) {
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
		result = prime * result + ((movieId == null) ? 0 : movieId.hashCode());
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
		if (movieId == null) {
			if (other.movieId != null) {
				return false;
			}
		} else if (!movieId.equals(other.movieId)) {
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
