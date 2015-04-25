/**
 * 
 */
package se.face.moviews.core.factory;

import se.face.moviews.core.domain.entity.CastAndCrewMember;


/**
 * @author Samuel
 *
 */
public class CastAndCrewMemberFactory {

	public static CastAndCrewMember convertFromApi(se.face.moviews.api.model.CastAndCrewMember apiCastAndCrewMember) {
		CastAndCrewMember castAndCrewMember = new CastAndCrewMember();
		castAndCrewMember.setCastAndCrewMemberId(apiCastAndCrewMember.getId());
		castAndCrewMember.setFirstName(apiCastAndCrewMember.getFirstName());
		castAndCrewMember.setLastName(apiCastAndCrewMember.getLastName());
		castAndCrewMember.setRole(apiCastAndCrewMember.getRole());
		return castAndCrewMember;
	}

	public static se.face.moviews.api.model.CastAndCrewMember convertFromEntity(
			CastAndCrewMember castAndCrewMemberEntity) {
		se.face.moviews.api.model.CastAndCrewMember castAndCrewMember = 
				new se.face.moviews.api.model.CastAndCrewMember(
						castAndCrewMemberEntity.getCastAndCrewMemberId(), 
						castAndCrewMemberEntity.getFirstName(),
						castAndCrewMemberEntity.getLastName(), 
						castAndCrewMemberEntity.getRole());
		return castAndCrewMember;
	}
	
}
