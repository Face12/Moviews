/**
 * 
 */
package se.face.moviews.integrationtest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.consol.citrus.validation.json.JsonTextMessageValidator;

/**
 * @author Samuel
 *
 */
@Configuration
@ImportResource("classpath:citrus-context.xml")
@PropertySource("classpath:moviews-configuration.properties")
public class AppConfig {
	
	@Autowired
    Environment env;

	@Bean
	public JsonTextMessageValidator jsonTextMessageValidator(){
		return new JsonTextMessageValidator();
	}

	@Bean
	public DriverManagerDataSource dataSource(){
		DriverManagerDataSource driverManagerDataSource = 
				new DriverManagerDataSource(
						env.getProperty("db.url"), 
						env.getProperty("db.user"), 
						env.getProperty("db.password"));
		driverManagerDataSource.setDriverClassName(env.getProperty("db.driver"));
		
		return driverManagerDataSource;
	}

	@Bean(destroyMethod = "destroy")
	public TomcatRunner tomcatRunner(){
		TomcatRunner tomcatRunner = new TomcatRunner();
		tomcatRunner.createAndStart();
		return tomcatRunner;
	}
}
