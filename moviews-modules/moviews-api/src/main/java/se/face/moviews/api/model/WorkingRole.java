/**
 * 
 */
package se.face.moviews.api.model;

/**
 * @author Samuel
 *
 */
public class WorkingRole implements Resource{
	private Integer id;
	private Person person;
	private String role;
	
	public WorkingRole(){}
	
	public WorkingRole(Person person, String role) {
		this(null, person, role);
	}
	
	public WorkingRole(Integer id) {
		this(id, null, null);
	}
	
	public WorkingRole(Integer id, Person person, String role) {
		this.id = id;
		this.person = person;
		this.role = role;
	}
	
	@Override
	public Integer getId() {
		return id;
	}

	public String getRole() {
		return role;
	}

	public Person getPerson() {
		return person;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "WorkingRole [id=" + id + ", person=" + person + ", role="
				+ role + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((person == null) ? 0 : person.hashCode());
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
		WorkingRole other = (WorkingRole) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
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
		return true;
	}
}
