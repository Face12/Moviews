/**
 * 
 */
package se.face.moviews.api.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import se.face.moviews.api.util.JsonDateSerializer;

/**
 * @author Samuel
 *
 */
public class Movie implements Resource {	
	private Integer id;
	private String imdbId;
	private String originalTitle;
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date releaseDate;
	private DateQuality dateQuality;
	private Short runtimeMinutes;
	private String plot;
	private Float imdbRating;
	private Long imdbVotes;
	private Date lastUpdated;
	private List<String> languages = new ArrayList<String>();
	private List<String> countries = new ArrayList<String>();
	private List<String> genres = new ArrayList<String>();
	private List<WorkingRole> workingRoles = new ArrayList<WorkingRole>();

	public Movie(){}
	
	public Movie(String originalTitle){
		this(originalTitle, null);
	}
	
	public Movie(String originalTitle, Integer id){
		this.originalTitle = originalTitle;
		this.id = id;
	}

	@Override
	public Integer getId() {
		return id;
	}

	public String getOriginalTitle() {
		return originalTitle;
	}

	public String getImdbId() {
		return imdbId;
	}

	public void setImdbId(String imdbId) {
		this.imdbId = imdbId;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public DateQuality getDateQuality() {
		return dateQuality;
	}

	public void setDateQuality(DateQuality dateQuality) {
		this.dateQuality = dateQuality;
	}

	public Short getRuntimeMinutes() {
		return runtimeMinutes;
	}

	public void setRuntimeMinutes(Short runtimeMinutes) {
		this.runtimeMinutes = runtimeMinutes;
	}

	public String getPlot() {
		return plot;
	}

	public void setPlot(String plot) {
		this.plot = plot;
	}

	public Float getImdbRating() {
		return imdbRating;
	}

	public void setImdbRating(Float imdbRating) {
		this.imdbRating = imdbRating;
	}

	public Long getImdbVotes() {
		return imdbVotes;
	}

	public void setImdbVotes(Long imdbVotes) {
		this.imdbVotes = imdbVotes;
	}

	public void setOriginalTitle(String originalTitle) {
		this.originalTitle = originalTitle;
	}
	
	public void addLanguage(String language) {
		this.languages.add(language);
	}
	
	public void addCountry(String country) {
		this.countries.add(country);
	}
	
	public void addGenre(String genre) {
		this.genres.add(genre);
	}
	
	public void addWorkingRole(WorkingRole castAndCrewMember) {
		this.workingRoles.add(castAndCrewMember);
	}

	public List<String> getLanguages() {
		return languages;
	}

	public List<String> getCountries() {
		return countries;
	}

	public List<String> getGenres() {
		return genres;
	}
	
	public List<WorkingRole> getWorkingRoles() {
		return workingRoles;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", imdbId=" + imdbId + ", originalTitle="
				+ originalTitle + ", releaseDate=" + releaseDate
				+ ", dateQuality=" + dateQuality + ", runtimeMinutes="
				+ runtimeMinutes + ", plot=" + plot + ", imdbRating="
				+ imdbRating + ", imdbVotes=" + imdbVotes + ", lastUpdated="
				+ lastUpdated + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dateQuality == null) ? 0 : dateQuality.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((imdbId == null) ? 0 : imdbId.hashCode());
		result = prime * result
				+ ((imdbRating == null) ? 0 : imdbRating.hashCode());
		result = prime * result
				+ ((imdbVotes == null) ? 0 : imdbVotes.hashCode());
		result = prime * result
				+ ((lastUpdated == null) ? 0 : lastUpdated.hashCode());
		result = prime * result
				+ ((originalTitle == null) ? 0 : originalTitle.hashCode());
		result = prime * result + ((plot == null) ? 0 : plot.hashCode());
		result = prime * result
				+ ((releaseDate == null) ? 0 : releaseDate.hashCode());
		result = prime * result
				+ ((runtimeMinutes == null) ? 0 : runtimeMinutes.hashCode());
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
		Movie other = (Movie) obj;
		if (dateQuality != other.dateQuality) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (imdbId == null) {
			if (other.imdbId != null) {
				return false;
			}
		} else if (!imdbId.equals(other.imdbId)) {
			return false;
		}
		if (imdbRating == null) {
			if (other.imdbRating != null) {
				return false;
			}
		} else if (!imdbRating.equals(other.imdbRating)) {
			return false;
		}
		if (imdbVotes == null) {
			if (other.imdbVotes != null) {
				return false;
			}
		} else if (!imdbVotes.equals(other.imdbVotes)) {
			return false;
		}
		if (lastUpdated == null) {
			if (other.lastUpdated != null) {
				return false;
			}
		} else if (!lastUpdated.equals(other.lastUpdated)) {
			return false;
		}
		if (originalTitle == null) {
			if (other.originalTitle != null) {
				return false;
			}
		} else if (!originalTitle.equals(other.originalTitle)) {
			return false;
		}
		if (plot == null) {
			if (other.plot != null) {
				return false;
			}
		} else if (!plot.equals(other.plot)) {
			return false;
		}
		if (releaseDate == null) {
			if (other.releaseDate != null) {
				return false;
			}
		} else if (!releaseDate.equals(other.releaseDate)) {
			return false;
		}
		if (runtimeMinutes == null) {
			if (other.runtimeMinutes != null) {
				return false;
			}
		} else if (!runtimeMinutes.equals(other.runtimeMinutes)) {
			return false;
		}
		return true;
	}
}
