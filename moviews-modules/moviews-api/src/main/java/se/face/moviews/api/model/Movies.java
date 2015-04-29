/**
 * 
 */
package se.face.moviews.api.model;

import java.util.List;

/**
 * @author Samuel
 *
 */
public class Movies {
	private List<MovieHit> list;
	
	public Movies(){}
	
	public Movies(List<MovieHit> list){
		this.list =list;
	}

	public List<MovieHit> getList() {
		return list;
	}

	public static class MovieHit{
		private Integer id;
		private String originalTitle;
		
		public MovieHit(Integer id, String originalTitle) {
			this.id = id;
			this.originalTitle = originalTitle;
		}
		
		public String getOriginalTitle() {
			return originalTitle;
		}
		public Integer getId() {
			return id;
		}

		@Override
		public String toString() {
			return "MovieHit [id=" + id + ", originalTitle=" + originalTitle
					+ "]";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((id == null) ? 0 : id.hashCode());
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
			MovieHit other = (MovieHit) obj;
			if (id == null) {
				if (other.id != null) {
					return false;
				}
			} else if (!id.equals(other.id)) {
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

	@Override
	public String toString() {
		return "Movies [list=" + list + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((list == null) ? 0 : list.hashCode());
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
		Movies other = (Movies) obj;
		if (list == null) {
			if (other.list != null) {
				return false;
			}
		} else if (!list.equals(other.list)) {
			return false;
		}
		return true;
	}
}
