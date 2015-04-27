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
@ComponentScan(basePackageClasses = {DaoPackage.class})
public class AppConfig {	
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
		//TODO Parameteize
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
		properties.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
		properties.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/test");
		properties.setProperty("hibernate.hbm2ddl.auto", "validate");
		return properties;
	}
	
	@Bean
	public DataSource inMemoryDataSource(){
		//TODO Parameteize
		DriverManagerDataSource driverManagerDataSource = 
				new DriverManagerDataSource("jdbc:mysql://localhost:3306/test", "root", "MyNewPass");
		driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
		
		return driverManagerDataSource;
	}
}
