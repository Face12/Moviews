/**
 * 
 */
package se.face.moviews.core.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Samuel
 *
 */
@Table(name = "country")
@Entity
public class Country {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer countryId;
	
	@Column(nullable = false, length = 2, columnDefinition="CHAR(2)", unique = true, updatable = false)
	private String countryCode;

	@Column(nullable = false)
	private String countryText;

	@Override
	public String toString() {
		return "Country [countryId=" + countryId + ", countryCode="
				+ countryCode + ", countryText=" + countryText + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((countryCode == null) ? 0 : countryCode.hashCode());
		result = prime * result
				+ ((countryId == null) ? 0 : countryId.hashCode());
		result = prime * result
				+ ((countryText == null) ? 0 : countryText.hashCode());
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
		Country other = (Country) obj;
		if (countryCode == null) {
			if (other.countryCode != null) {
				return false;
			}
		} else if (!countryCode.equals(other.countryCode)) {
			return false;
		}
		if (countryId == null) {
			if (other.countryId != null) {
				return false;
			}
		} else if (!countryId.equals(other.countryId)) {
			return false;
		}
		if (countryText == null) {
			if (other.countryText != null) {
				return false;
			}
		} else if (!countryText.equals(other.countryText)) {
			return false;
		}
		return true;
	}
	
	
	
}
