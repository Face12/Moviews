/**
 * 
 */
package se.face.moviews.core.domain.dao;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import se.face.moviews.core.domain.entity.Movie;

/**
 * @author Samuel
 *
 */
@Repository
public class MovieDaoImpl implements MovieDao {

	@Autowired  
	SessionFactory sessionFactory;  
	
	@Override
	public int save(Movie movie) {
		Session session = sessionFactory.getCurrentSession();
		return (Integer) session.save(movie);
	}

	@Override
	public void update(Movie movie) {
		Session session = sessionFactory.getCurrentSession();
		session.update(movie);
	}

	@Override
	public Movie get(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (Movie) session.get(Movie.class, id);
	}

	@Override
	public Movie getWithCastAndCrew(int id) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Movie.class)
			.add(Restrictions.idEq(id))
			.setFetchMode("castAndCrew", FetchMode.JOIN);
		return (Movie) criteria.uniqueResult();
	}
}
