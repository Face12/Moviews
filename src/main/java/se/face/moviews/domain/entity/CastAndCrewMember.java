/**
 * 
 */
package se.face.moviews.domain.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * @author Samuel
 *
 */
@Table(name = "CastAndCrewMember")
@Entity
public class CastAndCrewMember {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int castAndCrewMemberId;
	
	@Column
	private String firstName;
	
	@Column
	private String lastName;
	
	@Column
	private String role;
	
	@ManyToMany(mappedBy="castAndCrew", fetch = FetchType.LAZY)
	private Set<Movie> inMovies;
	
	public CastAndCrewMember() {}

	public CastAndCrewMember(int id) {
		this.setCastAndCrewMemberId(id);
	}
	
	public CastAndCrewMember(String firstName, String lastName) {
		this.setFirstName(firstName);
		this.setLastName(lastName);
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	public String getRole() {
		return role;
	}

	public int getCastAndCrewMemberId() {
		return castAndCrewMemberId;
	}

	public void setCastAndCrewMemberId(int castAndCrewMemberId) {
		this.castAndCrewMemberId = castAndCrewMemberId;
	}

	public Set<Movie> getInMovies() {
		return inMovies;
	}

	public void setInMovies(Set<Movie> inMovies) {
		this.inMovies = inMovies;
	}

	@Override
	public String toString() {
		return "CastAndCrewMember [castAndCrewMemberId=" + castAndCrewMemberId
				+ ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + castAndCrewMemberId;
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
		CastAndCrewMember other = (CastAndCrewMember) obj;
		if (castAndCrewMemberId != other.castAndCrewMemberId) {
			return false;
		}
		return true;
	}

}
