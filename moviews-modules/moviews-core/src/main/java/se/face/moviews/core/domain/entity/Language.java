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
@Table(name = "language")
@Entity
public class Language {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer languageId;
	
	@Column(nullable = false, length = 2, columnDefinition="CHAR(2)", unique = true, updatable = false)
	private String languageCode;
	
	@Column(nullable = false)
	private String languageText;

	public Integer getLanguageId() {
		return languageId;
	}

	public void setLanguageId(Integer languageId) {
		this.languageId = languageId;
	}

	public String getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	public String getLanguageText() {
		return languageText;
	}

	public void setLanguageText(String languageText) {
		this.languageText = languageText;
	}

	@Override
	public String toString() {
		return "Language [languageId=" + languageId + ", languageCode="
				+ languageCode + ", languageText=" + languageText + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((languageCode == null) ? 0 : languageCode.hashCode());
		result = prime * result
				+ ((languageId == null) ? 0 : languageId.hashCode());
		result = prime * result
				+ ((languageText == null) ? 0 : languageText.hashCode());
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
		Language other = (Language) obj;
		if (languageCode == null) {
			if (other.languageCode != null) {
				return false;
			}
		} else if (!languageCode.equals(other.languageCode)) {
			return false;
		}
		if (languageId == null) {
			if (other.languageId != null) {
				return false;
			}
		} else if (!languageId.equals(other.languageId)) {
			return false;
		}
		if (languageText == null) {
			if (other.languageText != null) {
				return false;
			}
		} else if (!languageText.equals(other.languageText)) {
			return false;
		}
		return true;
	}
	
}
