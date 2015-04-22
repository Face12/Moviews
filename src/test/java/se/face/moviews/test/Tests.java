/**
 * 
 */
package se.face.moviews.test;

import java.util.Properties;

import javax.sql.DataSource;

import org.h2.jdbcx.JdbcConnectionPool;

/**
 * @author Samuel
 *
 */
public class Tests {
	public static Properties propertiesForInMemoryDb(){
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		properties.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
		properties.setProperty("hibernate.connection.url", "jdbc:h2:mem:test");
		properties.setProperty("hibernate.hbm2ddl.auto", "create");
		return properties;
	}
	
	public static DataSource inMemoryDataSource(){
		return JdbcConnectionPool.create("jdbc:h2:mem:test", "test", "test");
	}
}
