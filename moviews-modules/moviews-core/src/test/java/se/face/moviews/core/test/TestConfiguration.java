package se.face.moviews.core.test;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import se.face.moviews.core.config.AppConfig;
import se.face.moviews.core.domain.dao.DaoPackage;
import se.face.moviews.core.service.ServicePackage;

@Configuration
@Import(AppConfig.class)
@ComponentScan(basePackageClasses = {DaoPackage.class, ServicePackage.class})
public class TestConfiguration {
}
