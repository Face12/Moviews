/**
 * 
 */
package se.face.moviews.core.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@ComponentScan(basePackageClasses = {DaoPackage.class, AppConfig.class})
public class AppConfig {	

	private static final Logger logger = LoggerFactory.getLogger(AppConfig.class);

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
		logger.info("Created transaction manager");
		return hibernateTransactionManager;
	}

	@Bean
	public Properties hibernateProperties(){
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", env.getProperty("db.hibernate.dialect"));
		properties.setProperty("hibernate.connection.driver_class", env.getProperty("db.driver"));
		properties.setProperty("hibernate.connection.url", env.getProperty("db.url"));
		properties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("db.hibernate.auto"));
		properties.setProperty("hibernate.show_sql", env.getProperty("db.hibernate.showsql"));
		return properties;
	}

	@Bean
	public DataSource dataSource(){
		final String dbURL = env.getProperty("db.url");
		logger.info("Using database url: "+dbURL);
		DriverManagerDataSource driverManagerDataSource = 
				new DriverManagerDataSource(
						dbURL, 
						env.getProperty("db.user"), 
						env.getProperty("db.password"));
		driverManagerDataSource.setDriverClassName(env.getProperty("db.driver"));

		return driverManagerDataSource;
	}
}
