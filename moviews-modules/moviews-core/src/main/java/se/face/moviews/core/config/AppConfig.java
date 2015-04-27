/**
 * 
 */
package se.face.moviews.core.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import se.face.moviews.core.domain.dao.DaoPackage;
import se.face.moviews.core.domain.entity.EntityPackage;

/**
 * @author Samuel
 *
 */
@EnableTransactionManagement
@Configuration
@PropertySource("classpath:moviews-configuration.properties")
@ComponentScan(basePackageClasses = {DaoPackage.class})
public class AppConfig {	
	
	@Autowired
    Environment env;
	
	@Bean
	@Autowired
	public LocalSessionFactoryBean sessionFactory(DataSource dataSource, Properties hibernateProperties){
		LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
		localSessionFactoryBean.setDataSource(dataSource);
		localSessionFactoryBean.setHibernateProperties(hibernateProperties);
		localSessionFactoryBean.setPackagesToScan(EntityPackage.class.getPackage().getName());
		return localSessionFactoryBean;
	}
	
	@Bean 
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory, DataSource dataSource){		
		HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
		hibernateTransactionManager.setDataSource(dataSource);
		hibernateTransactionManager.setSessionFactory(sessionFactory);
		return hibernateTransactionManager;
	}
	
	@Bean
	public Properties hibernateProperties(){
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", env.getProperty("db.hibernate.dialect"));
		properties.setProperty("hibernate.connection.driver_class", env.getProperty("db.driver"));
		properties.setProperty("hibernate.connection.url", env.getProperty("db.url"));
		properties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("db.hibernate.auto"));
		return properties;
	}
	
	@Bean
	public DataSource dataSource(){
		DriverManagerDataSource driverManagerDataSource = 
				new DriverManagerDataSource(
						env.getProperty("db.url"), 
						env.getProperty("db.user"), 
						env.getProperty("db.password"));
		driverManagerDataSource.setDriverClassName(env.getProperty("db.driver"));
		
		return driverManagerDataSource;
	}
}
