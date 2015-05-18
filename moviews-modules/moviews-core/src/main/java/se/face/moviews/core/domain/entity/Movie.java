/**
 * 
 */
package se.face.moviews.core.domain.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Samuel
 *
 */
@Table(name = "movie")
@Entity
public class Movie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer movieId;
	
	@Column
	private String imdbId;
	
	@Column(nullable = false)
	private String originalTitle;
	
	@Column
	private Date releaseDate;
	
	@Enumerated(EnumType.ORDINAL)
	private DateQuality dateQuality;
	
	@Column
	private Short runtimeMinutes;
	
	@Column
	private String plot;
	
	@Column(scale = 2, precision = 2)
	private Float imdbRating;
	
	@Column
	private Long imdbVotes;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(
			name = "movie_language",
			joinColumns = {
				@JoinColumn(name = "movieId", nullable = false, updatable = false)
			},
			inverseJoinColumns = {
				@JoinColumn(name = "languageId", nullable = false, updatable = false)
			})
	private Set<Language> languages;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(
			name = "movie_country",
			joinColumns = {
				@JoinColumn(name = "movieId", nullable = false, updatable = false)
			},
			inverseJoinColumns = {
				@JoinColumn(name = "countryId", nullable = false, updatable = false)
			})
	private Set<Country> countries;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(
			name = "movie_genre",
			joinColumns = {
				@JoinColumn(name = "movieId", nullable = false, updatable = false)
			},
			inverseJoinColumns = {
				@JoinColumn(name = "genreId", nullable = false, updatable = false)
			})
	private Set<Genre> genres;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "inMovie")
	private Set<WorkingRole> workingRoles;
	
	public Movie() {}
	
	public Movie(String originalTitle) {
		this.originalTitle = originalTitle;
	}
	
	public Movie(Integer id){
		this.movieId = id;
	}

	public Integer getMovieId() {
		return movieId;
	}

	public void setMovieId(Integer movieId) {
		this.movieId = movieId;
	}

	public String getOriginalTitle() {
		return originalTitle;
	}

	public void setOriginalTitle(String originalTitle) {
		this.originalTitle = originalTitle;
	}
	
	public Set<WorkingRole> getWorkingRoles() {
		return workingRoles;
	}

	public void setCastAndCrew(Set<WorkingRole> castAndCrew) {
		this.workingRoles = castAndCrew;
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

	public Set<Language> getLanguages() {
		return languages;
	}

	public void setLanguages(Set<Language> languages) {
		this.languages = languages;
	}

	public Set<Country> getCountries() {
		return countries;
	}

	public void setCountries(Set<Country> countries) {
		this.countries = countries;
	}

	public Set<Genre> getGenres() {
		return genres;
	}

	public void setGenres(Set<Genre> genres) {
		this.genres = genres;
	}

	public void addCountry(Country country) {
		if (countries == null){
			countries = new HashSet<Country>();
		}
		countries.add(country);		
	}
	
	public void addLanguage(Language language) {
		if (languages == null){
			languages = new HashSet<Language>();
		}
		languages.add(language);		
	}
	
	public void addGenre(Genre genre) {
		if (genres == null){
			genres = new HashSet<Genre>();
		}
		genres.add(genre);		
	}
	
	public void addWorkingRole(WorkingRole workingRole) {
		if (workingRoles == null){
			workingRoles = new HashSet<WorkingRole>();
		}
		workingRole.setInMovie(this);
		workingRoles.add(workingRole);		
	}

	@Override
	public String toString() {
		return "Movie [movieId=" + movieId + ", imdbId=" + imdbId
				+ ", originalTitle=" + originalTitle + ", releaseDate="
				+ releaseDate + ", dateQuality=" + dateQuality
				+ ", runtimeMinutes=" + runtimeMinutes + ", plot=" + plot
				+ ", imdbRating=" + imdbRating + ", imdbVotes=" + imdbVotes
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dateQuality == null) ? 0 : dateQuality.hashCode());
		result = prime * result + ((imdbId == null) ? 0 : imdbId.hashCode());
		result = prime * result
				+ ((imdbRating == null) ? 0 : imdbRating.hashCode());
		result = prime * result
				+ ((imdbVotes == null) ? 0 : imdbVotes.hashCode());
		result = prime * result + ((movieId == null) ? 0 : movieId.hashCode());
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
		if (movieId == null) {
			if (other.movieId != null) {
				return false;
			}
		} else if (!movieId.equals(other.movieId)) {
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
