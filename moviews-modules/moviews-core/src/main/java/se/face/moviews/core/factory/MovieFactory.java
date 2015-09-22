/**
 * 
 */
package se.face.moviews.core.factory;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import se.face.moviews.api.model.DateQuality;
import se.face.moviews.api.model.Movies;
import se.face.moviews.api.model.Movies.MovieHit;
import se.face.moviews.core.domain.entity.Country;
import se.face.moviews.core.domain.entity.Genre;
import se.face.moviews.core.domain.entity.Language;
import se.face.moviews.core.domain.entity.WorkingRole;
import se.face.moviews.core.domain.entity.Movie;
import se.face.moviews.core.omdb.MovieResponse;
import se.face.moviews.core.omdb.Search;
import se.face.moviews.core.omdb.SearchResponse;

/**
 * @author Samuel
 *
 */
public final class MovieFactory {
	
	private static final Logger logger = LoggerFactory.getLogger(MovieFactory.class);
	
	private static final Pattern EXT_RUNTIME_PATTERN = Pattern.compile("(?<mins>\\d++) min"); 
	
	public static Movie convertFromApi(se.face.moviews.api.model.Movie apiMovie){
		if (apiMovie == null) return null;
		Movie movie = new Movie();
		movie.setMovieId(apiMovie.getId());
		movie.setOriginalTitle(apiMovie.getOriginalTitle());
		movie.setPlot(apiMovie.getPlot());
		movie.setImdbId(apiMovie.getImdbId());
		movie.setImdbRating(apiMovie.getImdbRating());
		movie.setImdbVotes(apiMovie.getImdbVotes());
		movie.setReleaseDate(apiMovie.getReleaseDate());
		if (apiMovie.getDateQuality() != null){
			movie.setDateQuality(se.face.moviews.core.domain.entity.DateQuality.valueOf(apiMovie.getDateQuality().name()));
		}
		movie.setRuntimeMinutes(apiMovie.getRuntimeMinutes());
		if (apiMovie.getGenres() != null){
			for (String genre : apiMovie.getGenres()){
				Genre entityGenre = new Genre();
				entityGenre.setGenreText(genre);
				movie.addGenre(entityGenre);
			}
		}
		if (apiMovie.getCountries() != null){
			for (String country : apiMovie.getCountries()){
				Country entityCountry = new Country();
				entityCountry.setCountryText(country);
				movie.addCountry(entityCountry);
			}
		}
		if (apiMovie.getLanguages() != null){
			for (String language : apiMovie.getLanguages()){
				Language entityLanguage = new Language();
				entityLanguage.setLanguageText(language);
				movie.addLanguage(entityLanguage);
			}
		}
		if (apiMovie.getWorkingRoles() != null){
			for (se.face.moviews.api.model.WorkingRole apiWorkingRole: apiMovie.getWorkingRoles()){
				movie.addWorkingRole(
						WorkingRoleFactory.convertFromApi(apiWorkingRole));
			}
		}
		return movie;
	}

	public static se.face.moviews.api.model.Movie convertFromEntity(
			Movie movieEntity) {
		if (movieEntity == null) return null;
		se.face.moviews.api.model.Movie apiMovie = 
				new se.face.moviews.api.model.Movie(movieEntity.getOriginalTitle(), movieEntity.getMovieId());
		apiMovie.setPlot(movieEntity.getPlot());
		apiMovie.setImdbId(movieEntity.getImdbId());
		apiMovie.setImdbRating(movieEntity.getImdbRating());
		apiMovie.setImdbVotes(movieEntity.getImdbVotes());
		apiMovie.setReleaseDate(movieEntity.getReleaseDate());
		apiMovie.setLastUpdated(movieEntity.getLastUpdated());
		if (movieEntity.getDateQuality() != null){
			apiMovie.setDateQuality(DateQuality.valueOf(movieEntity.getDateQuality().name()));
		}
		apiMovie.setRuntimeMinutes(movieEntity.getRuntimeMinutes());
		if (movieEntity.getGenres() != null){
			for (Genre genre: movieEntity.getGenres()){
				apiMovie.addGenre(genre.getGenreText());
			}
		}
		if (movieEntity.getCountries() != null){
			for (Country country: movieEntity.getCountries()){
				apiMovie.addCountry(country.getCountryText());
			}
		}
		if (movieEntity.getLanguages() != null){
			for (Language language: movieEntity.getLanguages()){
				apiMovie.addLanguage(language.getLanguageText());
			}
		}
		if (movieEntity.getWorkingRoles() != null){
			for (WorkingRole workingRoleEntity: movieEntity.getWorkingRoles()){
				apiMovie.addWorkingRole(
						WorkingRoleFactory.convertFromEntity(workingRoleEntity));
			}
		}
		return apiMovie;
	}
	
	public static Movies createSearchResult(List<Movie> resultList){
		List<MovieHit> list = new ArrayList<MovieHit>();
		resultList.forEach(m -> list.add(new MovieHit(String.valueOf(m.getMovieId()), m.getOriginalTitle())));
		return new Movies(list);
	}
	
	public static se.face.moviews.api.model.Movie resultFromExternal(MovieResponse movieResponse){
		if ("True".equals(movieResponse.getResponse())){
			se.face.moviews.api.model.Movie movie = new se.face.moviews.api.model.Movie();
			movie.setOriginalTitle(movieResponse.getTitle());
			movie.setPlot(movieResponse.getPlot());
			movie.setImdbId(movieResponse.getImdbID());
			movie.setImdbRating(Double.parseDouble(movieResponse.getImdbRating()));
			movie.setImdbVotes(Long.parseLong(trimNumberString(movieResponse.getImdbVotes())));
			try {
				DateFormat df = new SimpleDateFormat("dd MMM yyyy", 
						DateFormatSymbols.getInstance(new Locale("en-US")));
				movie.setReleaseDate(df.parse(movieResponse.getReleased()));
				movie.setDateQuality(DateQuality.DAY);
			} catch (ParseException e) {
				logger.warn("Could not parse release date", e);
			}
			Matcher runtimeMatcher = EXT_RUNTIME_PATTERN.matcher(movieResponse.getRuntime());
			if (runtimeMatcher.find()){
				movie.setRuntimeMinutes(Short.parseShort(runtimeMatcher.group("mins")));
			}
			if (movieResponse.getGenre() != null){
				List<String> genres = commaSeparatedToList(movieResponse.getGenre());
				for (String genre : genres){
					movie.addGenre(genre);
				}
			}
			if (movieResponse.getCountry() != null){
				List<String> countries = commaSeparatedToList(movieResponse.getCountry());
				for (String country : countries){
					movie.addCountry(country);
				}
			}
			if (movieResponse.getLanguage() != null){
				List<String> languages = commaSeparatedToList(movieResponse.getLanguage());
				for (String language : languages){
					movie.addLanguage(language);
				}
			}
			return movie;
		}
		return null;
	}

	public static Movies resultFromExternal(SearchResponse searchResponse) {
		List<MovieHit> list = new ArrayList<MovieHit>();
		if (searchResponse.getSearch() != null && !searchResponse.getSearch().isEmpty()){
			for (Search search : searchResponse.getSearch()){
				list.add(new MovieHit(search.getImdbID(), search.getTitle()));
			}
		}
		return new Movies(list);
	}

	private static List<String> commaSeparatedToList(String commaSeparatedListString) {
		List<String> list = new ArrayList<String>();
		String[] array = commaSeparatedListString.split(",");
		for (String s:array){
			list.add(s.trim());
		}
		return list;
	}

	private static String trimNumberString(String imdbVotes) {
		return imdbVotes.replaceAll("[,. ]", "");
	}
}
