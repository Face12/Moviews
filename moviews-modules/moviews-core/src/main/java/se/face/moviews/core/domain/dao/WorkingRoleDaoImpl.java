/**
 * 
 */
package se.face.moviews.core.domain.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import se.face.moviews.core.domain.entity.WorkingRole;
import se.face.moviews.core.util.NameParser;
import se.face.moviews.core.util.NameParser.ParseResult;

/**
 * @author Samuel
 *
 */
@Repository
public class WorkingRoleDaoImpl implements WorkingRoleDao {

	@Autowired  
	SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<WorkingRole> searchByName(String name) {
		ParseResult parseResult = NameParser.parseFullName(name);
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(WorkingRole.class)
		.createAlias("person", "p")
	    .add(Restrictions.and(
				Restrictions.ilike("p.firstName", parseResult.getFirstName(), MatchMode.START),
				Restrictions.ilike("p.lastName", parseResult.getLastName(), MatchMode.START)));
		return criteria.list();
	}

}
