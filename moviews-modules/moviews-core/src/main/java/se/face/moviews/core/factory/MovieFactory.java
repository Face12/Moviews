/**
 * 
 */
package se.face.moviews.core.factory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import se.face.moviews.api.model.Movies;
import se.face.moviews.api.model.Movies.MovieHit;
import se.face.moviews.core.domain.entity.WorkingRole;
import se.face.moviews.core.domain.entity.Movie;
import se.face.moviews.core.omdb.MovieResponse;
import se.face.moviews.core.util.NameParser;
import se.face.moviews.core.util.NameParser.ParseResult;

/**
 * @author Samuel
 *
 */
public final class MovieFactory {
	
	public static Movie convertFromApi(se.face.moviews.api.model.Movie apiMovie){
		if (apiMovie == null) return null;
		Movie movie = new Movie();
		movie.setMovieId(apiMovie.getId());
		movie.setOriginalTitle(apiMovie.getOriginalTitle());
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
		resultList.forEach(m -> list.add(new MovieHit(m.getMovieId(), m.getOriginalTitle())));
		return new Movies(list);
	}
	
	public static Movie resultFromExternal(MovieResponse externalResponse){
		if ("True".equals(externalResponse.getResponse())){
			Movie movie = new Movie();
			movie.setOriginalTitle(externalResponse.getTitle());
			for (String actor: commaSeparatedToList(externalResponse.getActors())){
				ParseResult result = NameParser.parseFullName(actor);
				movie.addWorkingRole(new WorkingRole(result.getFirstName(), result.getLastName(), "Actor"));
			}
			ParseResult directorNames = NameParser.parseFullName(externalResponse.getDirector());
			movie.addWorkingRole(new WorkingRole(directorNames.getFirstName(), directorNames.getLastName(), "Director"));
			ParseResult writerNames = NameParser.parseFullName(externalResponse.getWriter());
			movie.addWorkingRole(new WorkingRole(writerNames.getFirstName(), writerNames.getLastName(), "Writer"));		
			return movie;
		}
		return null;
	}

	private static List<String> commaSeparatedToList(String commaSeparatedListString) {
		List<String> ret = Arrays.asList(commaSeparatedListString.split(","));
		ret.forEach(string -> string = string.trim());
		return ret;
	}
}
