/**
 * 
 */
package se.face.moviews.core.domain.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Samuel
 *
 */
@Table(name = "role")
@Entity
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer roleId;
	
	@Column
	private String roleText;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
	private Set<WorkingRole> workingRoles;

	public Role(){}
	
	public Role(String roleText){
		this.roleText = roleText;
	}
	
	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleText() {
		return roleText;
	}

	public void setRoleText(String roleText) {
		this.roleText = roleText;
	}

	public Set<WorkingRole> getWorkingRoles() {
		return workingRoles;
	}

	public void addWorkingRole(WorkingRole workingRole) {
		if (this.workingRoles == null){
			this.workingRoles = new HashSet<WorkingRole>();
		}		
		this.workingRoles.add(workingRole);
	}

	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", roleText=" + roleText + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
		result = prime * result
				+ ((roleText == null) ? 0 : roleText.hashCode());
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
		Role other = (Role) obj;
		if (roleId == null) {
			if (other.roleId != null) {
				return false;
			}
		} else if (!roleId.equals(other.roleId)) {
			return false;
		}
		if (roleText == null) {
			if (other.roleText != null) {
				return false;
			}
		} else if (!roleText.equals(other.roleText)) {
			return false;
		}
		return true;
	}
}
