/**
 * 
 */
package se.face.moviews.core.domain.entity;

import java.util.Set;

import javax.persistence.CascadeType;
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
@Table(name = "cast_and_crew_member")
@Entity
public class CastAndCrewMember {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer castAndCrewMemberId;
	
	@Column
	private String firstName;
	
	@Column
	private String lastName;
	
	@Column
	private String role;
	
	@ManyToMany(mappedBy="castAndCrew", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Movie> inMovies;
	
	public CastAndCrewMember() {}

	public CastAndCrewMember(int id) {
		this.castAndCrewMemberId = id;
	}
	
	public CastAndCrewMember(String firstName, String lastName, String role) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
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

	public Integer getCastAndCrewMemberId() {
		return castAndCrewMemberId;
	}

	public void setCastAndCrewMemberId(Integer castAndCrewMemberId) {
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
				+ ", firstName=" + firstName + ", lastName=" + lastName
				+ ", role=" + role + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((castAndCrewMemberId == null) ? 0 : castAndCrewMemberId
						.hashCode());
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
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
		if (castAndCrewMemberId == null) {
			if (other.castAndCrewMemberId != null) {
				return false;
			}
		} else if (!castAndCrewMemberId.equals(other.castAndCrewMemberId)) {
			return false;
		}
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
		if (role == null) {
			if (other.role != null) {
				return false;
			}
		} else if (!role.equals(other.role)) {
			return false;
		}
		return true;
	}

}
