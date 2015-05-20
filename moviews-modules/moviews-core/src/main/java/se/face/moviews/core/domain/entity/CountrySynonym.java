/**
 * 
 */
package se.face.moviews.core.domain.entity;

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
@Table(name = "country_synonym")
@Entity
public class CountrySynonym {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer countrySynonymId;
	
	@ManyToOne
	@JoinColumn(name="countryId", nullable = false)
	private Country country;

	@Column(nullable = false)
	private String synonymText;

	public Integer getCountrySynonymId() {
		return countrySynonymId;
	}

	public void setCountrySynonymId(Integer countrySynonymId) {
		this.countrySynonymId = countrySynonymId;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountryId(Country country) {
		this.country = country;
	}

	public String getSynonymText() {
		return synonymText;
	}

	public void setSynonymText(String synonymText) {
		this.synonymText = synonymText;
	}

	@Override
	public String toString() {
		return "CountrySynonym [countrySynonymId=" + countrySynonymId
				+ ", country=" + country + ", synonymText=" + synonymText + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime
				* result
				+ ((countrySynonymId == null) ? 0 : countrySynonymId.hashCode());
		result = prime * result
				+ ((synonymText == null) ? 0 : synonymText.hashCode());
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
		CountrySynonym other = (CountrySynonym) obj;
		if (country == null) {
			if (other.country != null) {
				return false;
			}
		} else if (!country.equals(other.country)) {
			return false;
		}
		if (countrySynonymId == null) {
			if (other.countrySynonymId != null) {
				return false;
			}
		} else if (!countrySynonymId.equals(other.countrySynonymId)) {
			return false;
		}
		if (synonymText == null) {
			if (other.synonymText != null) {
				return false;
			}
		} else if (!synonymText.equals(other.synonymText)) {
			return false;
		}
		return true;
	}
	
}
