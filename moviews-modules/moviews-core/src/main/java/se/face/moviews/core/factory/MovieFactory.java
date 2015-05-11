/**
 * 
 */
package se.face.moviews.core.factory;

import java.util.ArrayList;
import java.util.List;
import se.face.moviews.api.model.Movies;
import se.face.moviews.api.model.Movies.MovieHit;
import se.face.moviews.core.domain.entity.CastAndCrewMember;
import se.face.moviews.core.domain.entity.Movie;

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
		if (apiMovie.getCastAndCrew() != null){
			for (se.face.moviews.api.model.CastAndCrewMember apiCastAndCrewMember: apiMovie.getCastAndCrew()){
				movie.addCastAndCrewMember(
						CastAndCrewMemberFactory.convertFromApi(apiCastAndCrewMember));
			}
		}
		return movie;
	}

	public static se.face.moviews.api.model.Movie convertFromEntity(
			Movie movieEntity) {
		if (movieEntity == null) return null;
		se.face.moviews.api.model.Movie apiMovie = 
				new se.face.moviews.api.model.Movie(movieEntity.getOriginalTitle(), movieEntity.getMovieId());
		if (movieEntity.getCastAndCrew() != null){
			for (CastAndCrewMember castAndCrewMemberEntity: movieEntity.getCastAndCrew()){
				apiMovie.addCastAndCrew(
						CastAndCrewMemberFactory.convertFromEntity(castAndCrewMemberEntity));
			}
		}
		return apiMovie;
	}
	
	public static Movies createSearchResult(List<Movie> resultList){
		List<MovieHit> list = new ArrayList<MovieHit>();
		resultList.forEach(m -> list.add(new MovieHit(m.getMovieId(), m.getOriginalTitle())));
		return new Movies(list);
	}
}
