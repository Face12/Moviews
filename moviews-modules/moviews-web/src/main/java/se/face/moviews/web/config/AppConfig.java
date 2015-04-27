/**
 * 
 */
package se.face.moviews.web.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import se.face.moviews.core.service.ServicePackage;
import se.face.moviews.web.controller.ControllerPackage;

/**
 * @author Samuel
 *
 */
@EnableWebMvc
@Configuration
@Import(se.face.moviews.core.config.AppConfig.class)
@ComponentScan(basePackageClasses = {ControllerPackage.class, ServicePackage.class})
public class AppConfig {

}
