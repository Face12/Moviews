/**
 * 
 */
package se.face.moviews.core.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import se.face.moviews.core.domain.entity.Person;
import se.face.moviews.core.domain.entity.Role;
import se.face.moviews.core.domain.entity.WorkingRole;
import se.face.moviews.core.domain.entity.Movie;

/**
 * @author Samuel
 *
 */
@Service
public class DBInspectorServiceImpl implements DBInspectorService{

	@Autowired
	SessionFactory sessionFactory;
	
	private static final Logger logger = LoggerFactory.getLogger(MoviesServiceImpl.class);
	
	@Transactional(readOnly  = true)
	@Override
	public DBStatus checkMovieStatus(Movie movie) {
		Map<String, Object> movieParameters = new HashMap<String, Object>();
		movieParameters.put("originalTitle", movie.getOriginalTitle());
		if (getAllreadyExisting(movieParameters, Movie.class) != null){
			return DBStatus.PRESENT;
		}
		else{
			return DBStatus.NOT_PRESENT;
		}
	}
	
	@Transactional(readOnly  = true)
	@Override
	public void pickupExistingCollectionEntries(Movie movie) {
		if (movie.getWorkingRoles() != null){
			checkWorkingRoles(movie.getWorkingRoles());
		}
	}

	private void checkWorkingRoles(Set<WorkingRole> workingRoles) {
		Set<WorkingRole> newCollection = new HashSet<WorkingRole>();
		for (WorkingRole workingRole : workingRoles){
			Person person = workingRole.getPerson();
			Role role = workingRole.getRole();
			Map<String, Object> personParameters = new HashMap<String, Object>();	
			
			personParameters.put("firstName", person.getFirstName());
			personParameters.put("lastName", person.getLastName());
			
			Person personExisting = getAllreadyExisting(personParameters, Person.class);
			if (personExisting != null){
				workingRole.setPerson(personExisting);
			}
			Map<String, Object> roleParameters = new HashMap<String, Object>();
			roleParameters.put("roleText", role.getRoleText());
			Role roleExisting = getAllreadyExisting(roleParameters, Role.class);
			if (roleExisting != null){
				workingRole.setRole(roleExisting);
			}	
			newCollection.add(workingRole);
		}
		workingRoles.clear();
		workingRoles.addAll(newCollection);
	}

	@SuppressWarnings("unchecked")
	private <T> T getAllreadyExisting(Map<String, Object> parameters, Class<T> clazz) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(clazz)
		.add(Restrictions.allEq(parameters));
		
		List<T> list = criteria.list();
		
		if (list.size() > 1){
			throw new IllegalStateException("DUPLICATES ALLREADY EXISTS");
		}
		else if (list.size() == 1){
			return list.get(0);
		}
		return null;
	}

}
