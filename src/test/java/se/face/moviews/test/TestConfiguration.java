package se.face.moviews.test;

import java.util.Properties;

import javax.sql.DataSource;

import org.h2.jdbcx.JdbcConnectionPool;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import se.face.moviews.domain.dao.DaoPackage;
import se.face.moviews.domain.dao.MovieDao;
import se.face.moviews.domain.dao.MovieDaoImpl;
import se.face.moviews.domain.entity.EntityPackage;

@EnableTransactionManagement
@Configuration
@ComponentScan(basePackageClasses = {DaoPackage.class})
public class TestConfiguration {		
	@Bean
	@Autowired
	public LocalSessionFactoryBean sessionFactory(DataSource inMemoryDataSource, Properties hibernateProperties){
		LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
		localSessionFactoryBean.setDataSource(inMemoryDataSource);
		localSessionFactoryBean.setHibernateProperties(hibernateProperties);
		localSessionFactoryBean.setPackagesToScan(EntityPackage.class.getPackage().getName());
		return localSessionFactoryBean;
	}
	
	@Bean 
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory, DataSource inMemoryDataSource){
		
		HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
		hibernateTransactionManager.setDataSource(inMemoryDataSource);
		hibernateTransactionManager.setSessionFactory(sessionFactory);
		return hibernateTransactionManager;
	}
	
	@Bean
	public Properties hibernateProperties(){
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		properties.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
		properties.setProperty("hibernate.connection.url", "jdbc:h2:mem:test");
		properties.setProperty("hibernate.hbm2ddl.auto", "create");
		return properties;
	}
	
	@Bean
	public DataSource inMemoryDataSource(){
		return JdbcConnectionPool.create("jdbc:h2:mem:test", "test", "test");
	}
}
