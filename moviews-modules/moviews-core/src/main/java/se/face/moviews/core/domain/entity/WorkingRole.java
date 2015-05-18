/**
 * 
 */
package se.face.moviews.core.domain.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



/**
 * @author Samuel
 *
 */
@Table(name = "working_role")
@Entity
public class WorkingRole {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer workingRoleId;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="personId", nullable = false)
	private Person person;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="roleId")
	private Role role;
	
	@ManyToOne
	@JoinColumn(name="movieId", nullable = false)
	private Movie inMovie;
	
	public WorkingRole() {}

	public WorkingRole(int id) {
		this.workingRoleId = id;
	}
	
	public WorkingRole(String firstName, String lastName, String role) {
		this(null, firstName, lastName, role);
	}
	
	public WorkingRole(Integer id, String firstName, String lastName, String role) {
		this.workingRoleId = id;
		if (firstName != null || lastName != null){
			this.person = new Person(firstName, lastName);
			this.person.addAssignedRole(this);
		}
		if (role != null){
			this.role = new Role(role);
			this.role.addWorkingRole(this);
		}
	}

	public Integer getWorkingRoleId() {
		return workingRoleId;
	}

	public void setWorkingRoleId(Integer workingRoleId) {
		this.workingRoleId = workingRoleId;
	}

	public Movie getInMovie() {
		return inMovie;
	}

	public void setInMovie(Movie inMovie) {
		this.inMovie = inMovie;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "WorkingRole [workingRoleId=" + workingRoleId + ", person="
				+ person + ", role=" + role.getRoleText() + ", inMovie=" + inMovie.getOriginalTitle() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((inMovie == null) ? 0 : inMovie.hashCode());
		result = prime * result + ((person == null) ? 0 : person.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result
				+ ((workingRoleId == null) ? 0 : workingRoleId.hashCode());
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
		WorkingRole other = (WorkingRole) obj;
		if (inMovie == null) {
			if (other.inMovie != null) {
				return false;
			}
		} else if (!inMovie.equals(other.inMovie)) {
			return false;
		}
		if (person == null) {
			if (other.person != null) {
				return false;
			}
		} else if (!person.equals(other.person)) {
			return false;
		}
		if (role == null) {
			if (other.role != null) {
				return false;
			}
		} else if (!role.equals(other.role)) {
			return false;
		}
		if (workingRoleId == null) {
			if (other.workingRoleId != null) {
				return false;
			}
		} else if (!workingRoleId.equals(other.workingRoleId)) {
			return false;
		}
		return true;
	}
	
}
