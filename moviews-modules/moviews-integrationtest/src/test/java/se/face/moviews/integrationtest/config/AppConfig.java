/**
 * 
 */
package se.face.moviews.integrationtest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.consol.citrus.validation.json.JsonTextMessageValidator;

/**
 * @author Samuel
 *
 */
@Configuration
@ImportResource("classpath:citrus-context.xml")
public class AppConfig {

	@Bean
	public JsonTextMessageValidator jsonTextMessageValidator(){
		return new JsonTextMessageValidator();
	}

	@Bean
	public DriverManagerDataSource dataSource(){
		DriverManagerDataSource driverManagerDataSource = 
				new DriverManagerDataSource("jdbc:mysql://localhost:3306/test", "root", "MyNewPass");
		driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");

		return driverManagerDataSource;
	}

	@Bean(destroyMethod = "destroy")
	public TomcatRunner tomcatRunner(){
		TomcatRunner tomcatRunner = new TomcatRunner();
		tomcatRunner.createAndStart();
		return tomcatRunner;
	}
}
