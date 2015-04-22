/**
 * 
 */
package se.face.moviews.domain.dao;

import static org.junit.Assert.*;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import se.face.moviews.domain.dao.MovieDao;
import se.face.moviews.domain.entity.EntityPackage;
import se.face.moviews.domain.entity.Movie;
import se.face.moviews.test.TestObjectFactory;
import se.face.moviews.test.Tests;

/**
 * @author Samuel
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class MovieDaoTest { 
	
	@Autowired
	MovieDao movieDao;
	
	@Transactional
	@Test
	public void shouldSaveThenGet(){
		int id = movieDao.save(TestObjectFactory.newMovie());
		Movie movie = movieDao.get(id);
		assertEquals(id, movie.getMovieId());
	}
	
	@EnableTransactionManagement
	@Configuration
	static class MovieDaoTestConfiguration {
		DataSource inMemoryDataSource = Tests.inMemoryDataSource();
		Properties hibernateProperties = Tests.propertiesForInMemoryDb();
		
		@Bean
		public MovieDao movieDao() {
			return new MovieDaoImpl();
		}	
		
		@Bean
		public LocalSessionFactoryBean sessionFactory(){
			LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
			localSessionFactoryBean.setDataSource(inMemoryDataSource);
			localSessionFactoryBean.setHibernateProperties(hibernateProperties);
			localSessionFactoryBean.setPackagesToScan(EntityPackage.class.getPackage().getName());
			return localSessionFactoryBean;
		}
		
		@Bean 
		@Autowired
		public HibernateTransactionManager transactionManager(SessionFactory sessionFactory){
			
			HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
			hibernateTransactionManager.setDataSource(inMemoryDataSource);
			hibernateTransactionManager.setSessionFactory(sessionFactory);
			return hibernateTransactionManager;
		}
	}
}
