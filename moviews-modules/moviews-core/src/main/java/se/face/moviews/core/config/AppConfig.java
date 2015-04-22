/**
 * 
 */
package se.face.moviews.core.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import se.face.moviews.core.domain.dao.DaoPackage;

/**
 * @author Samuel
 *
 */
@Configuration
@ComponentScan(basePackageClasses = DaoPackage.class)
public class AppConfig {	
	
}
