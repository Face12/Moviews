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
@Table(name = "genre")
@Entity
public class Genre {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer genreId;
	
	@Column(nullable = false, unique = true, updatable = false)
	private String genreText;

	public Integer getGenreId() {
		return genreId;
	}

	public void setGenreId(Integer genreId) {
		this.genreId = genreId;
	}

	public String getGenreText() {
		return genreText;
	}

	public void setGenreText(String genreText) {
		this.genreText = genreText;
	}

	@Override
	public String toString() {
		return "Genre [genreId=" + genreId + ", genreText=" + genreText + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((genreId == null) ? 0 : genreId.hashCode());
		result = prime * result
				+ ((genreText == null) ? 0 : genreText.hashCode());
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
		Genre other = (Genre) obj;
		if (genreId == null) {
			if (other.genreId != null) {
				return false;
			}
		} else if (!genreId.equals(other.genreId)) {
			return false;
		}
		if (genreText == null) {
			if (other.genreText != null) {
				return false;
			}
		} else if (!genreText.equals(other.genreText)) {
			return false;
		}
		return true;
	}
}
