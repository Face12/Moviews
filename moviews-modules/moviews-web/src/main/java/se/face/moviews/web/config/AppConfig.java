/**
 * 
 */
package se.face.moviews.web.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import se.face.moviews.web.rest.RestPackage;

/**
 * @author Samuel
 *
 */
@EnableWebMvc
@Configuration
@ComponentScan(basePackageClasses = RestPackage.class)
public class AppConfig {

}
