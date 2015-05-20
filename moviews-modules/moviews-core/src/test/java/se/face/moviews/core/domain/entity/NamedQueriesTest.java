/**
 * 
 */
package se.face.moviews.core.domain.entity;

import static org.junit.Assert.*;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import se.face.moviews.core.test.TestConfiguration;

/**
 * @author Samuel
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
public class NamedQueriesTest {
	@Autowired
	SessionFactory sessionFactory;
	
	@Transactional
	@Test
	public void findByNameShouldFindTest(){
		Person person = (Person) findPersonQuery("John", "Doe").uniqueResult();
		assertNotNull("Exact match", person);
		
		person = (Person) findPersonQuery("joHn", "DoE").uniqueResult();
		assertNotNull("Different letter case", person);
	}
	@Transactional
	@Test
	public void findByNameShouldNotFindTest(){
		Person person = (Person) findPersonQuery("John", "Do").uniqueResult();
		assertNull(person);
	}
	private Query findPersonQuery(String firstName, String lastName) {
		return sessionFactory.getCurrentSession().getNamedQuery(Person.FIND_BY_NAME)
			.setParameter("firstName", firstName)
			.setParameter("lastName", lastName);
	}
	
	@Transactional
	@Test
	public void findCountryShouldFindByStandardName(){
		Country country = (Country) findCountryQuery("United States of America").uniqueResult();
		assertNotNull("Exact match", country);
		
		country = (Country) findCountryQuery("United States Of America").uniqueResult();
		assertNotNull("Different letter case", country);
		
		country = (Country) findCountryQuery("United Arab Emirates").uniqueResult();
		assertNotNull("Country with no synonyms present", country);
	}
	
	@Transactional
	@Test
	public void findCountryShouldFindBySynonym(){
		Country country = (Country) findCountryQuery("USA").uniqueResult();
		assertNotNull("Exact match", country);
		
		country = (Country) findCountryQuery("usa").uniqueResult();
		assertNotNull("Different letter case", country);
	}
	
	@Transactional
	@Test
	public void findCountryShouldNotFind(){
		Country country = (Country) findCountryQuery("United States of").uniqueResult();
		assertNull(country);
	}
	private Query findCountryQuery(String countryText) {
		return sessionFactory.getCurrentSession().getNamedQuery(Country.FIND_BY_TEXT_OR_SYNONYM)
				.setParameter("countryText", countryText);
	}
}
