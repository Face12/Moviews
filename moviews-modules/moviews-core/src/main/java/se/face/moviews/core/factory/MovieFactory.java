/**
 * 
 */
package se.face.moviews.core.factory;

import se.face.moviews.core.domain.entity.CastAndCrewMember;
import se.face.moviews.core.domain.entity.Movie;

/**
 * @author Samuel
 *
 */
public final class MovieFactory {
	public static Movie convertFromApi(se.face.moviews.api.model.Movie apiMovie){
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
}