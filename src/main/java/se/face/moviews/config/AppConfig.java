/**
 * 
 */
package se.face.moviews.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import se.face.moviews.domain.dao.DaoPackage;

/**
 * @author Samuel
 *
 */
@Configuration
@ComponentScan(basePackageClasses = DaoPackage.class)
public class AppConfig {	
	
}
