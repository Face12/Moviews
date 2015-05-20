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
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import se.face.moviews.core.domain.entity.Country;
import se.face.moviews.core.domain.entity.Genre;
import se.face.moviews.core.domain.entity.Language;
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
	
	private static final Logger logger = LoggerFactory.getLogger(DBInspectorServiceImpl.class);
	
	@Transactional(readOnly  = true)
	@Override
	public DBStatus checkMovieStatus(Movie movie) {
		Map<String, Object> movieParameters = new HashMap<String, Object>();
		movieParameters.put("originalTitle", movie.getOriginalTitle());
		if (getAllreadyExisting(
				sessionFactory.getCurrentSession().createCriteria(Movie.class)
				.add(Restrictions.allEq(movieParameters))) != null){
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
			checkCountries(movie.getCountries());
			checkLanguages(movie.getLanguages());
			checkGenres(movie.getGenres());
			checkWorkingRoles(movie.getWorkingRoles());
		}
	}

	private void checkCountries(Set<Country> countries) {
		if (countries != null){
			Set<Country> newCollection = new HashSet<Country>();
			for (Country country: countries){
				Query query = sessionFactory.getCurrentSession().getNamedQuery(Country.FIND_BY_TEXT_OR_SYNONYM)
						.setParameter("countryText", country.getCountryText());
				Country existingCountry = getAllreadyExisting(query);
				if (existingCountry != null){
					newCollection.add(existingCountry);
				}
				else{
					newCollection.add(country);
				}
			}
			countries.clear();
			countries.addAll(newCollection);
		}
	}

	private void checkLanguages(Set<Language> languages) {
		if (languages != null){
			Map<Language, Map<String, Object>> parameters = new HashMap<Language, Map<String,Object>>();
			for (Language language: languages){
				Map<String, Object> languageParameters = new HashMap<String, Object>();
				languageParameters.put("languageText", language.getLanguageText());
				parameters.put(language, languageParameters);
			}
			checkCollection(languages, parameters, Language.class);
		}
	}

	private void checkGenres(Set<Genre> genres) {
		if (genres != null){
			Map<Genre, Map<String, Object>> parameters = new HashMap<Genre, Map<String,Object>>();
			for (Genre genre: genres){
				Map<String, Object> genreParameters = new HashMap<String, Object>();
				genreParameters.put("genreText", genre.getGenreText());
				parameters.put(genre, genreParameters);
			}
			checkCollection(genres, parameters, Genre.class);
		}
	}

	private void checkWorkingRoles(Set<WorkingRole> workingRoles) {
		Set<WorkingRole> newCollection = new HashSet<WorkingRole>();
		for (WorkingRole workingRole : workingRoles){
			Person person = workingRole.getPerson();
			Role role = workingRole.getRole();
			
			Person personExisting = getAllreadyExisting(
					sessionFactory.getCurrentSession().getNamedQuery(Person.FIND_BY_NAME)
					.setString("firstName", person.getFirstName())
					.setString("lastName", person.getLastName())
					);
			if (personExisting != null){
				workingRole.setPerson(personExisting);
			}
			else{
				for (WorkingRole workingRoleInList: newCollection){
					Person personInList = workingRoleInList.getPerson();
					if (personInList.getFirstName() != null && 
						personInList.getFirstName().equals(person.getFirstName()) &&
						personInList.getLastName() != null &&
						personInList.getLastName().equals(person.getLastName())){
						logger.debug("Person: "+person+" occured more than once");
						workingRole.setPerson(personInList);
					}
				}
			}
			Role roleExisting = getAllreadyExisting(
					sessionFactory.getCurrentSession().createCriteria(Role.class)
					.add(Restrictions.eq("roleText", role.getRoleText())));
			if (roleExisting != null){
				workingRole.setRole(roleExisting);
			}	
			else{
				for (WorkingRole workingRoleInList: newCollection){
					Role roleInList = workingRoleInList.getRole();
					if (roleInList.getRoleText() != null &&
					    roleInList.getRoleText().equals(workingRole.getRole().getRoleText())){
						logger.debug("Role: "+role+" occured more than once");
						workingRole.setRole(roleInList);
					}
				}
			}
			newCollection.add(workingRole);
		}
		
		workingRoles.clear();
		workingRoles.addAll(newCollection);
	}
	

	private <T> void checkCollection(Set<T> collection, Map<T, Map<String, Object>> parameters, Class<T> clazz){
		Set<T> newCollection = new HashSet<T>();
		for (T object: collection){
			Map<String, Object> existingCriteriaParams = parameters.get(object); 
			T existing = getAllreadyExisting(
					sessionFactory.getCurrentSession().createCriteria(clazz)
						.add(Restrictions.allEq(existingCriteriaParams)));
			if (existing != null){
				newCollection.add(existing);
			}
			else{
				newCollection.add(object);
			}
		}
		collection.clear();
		collection.addAll(newCollection);
	}

	@SuppressWarnings("unchecked")
	private <T> T getAllreadyExisting(Criteria criteria) {				
		return (T) getAllreadyExisting(criteria.list());
	}

	@SuppressWarnings("unchecked")
	private <T> T getAllreadyExisting(Query query) {
		return (T) getAllreadyExisting(query.list());
	}
	
	private Object getAllreadyExisting(List<?> list) {
		if (list.size() > 1){
			throw new IllegalStateException("DUPLICATES ALLREADY EXISTS");
		}
		else if (list.size() == 1){
			return list.get(0);
		}
		return null;
	}
}
