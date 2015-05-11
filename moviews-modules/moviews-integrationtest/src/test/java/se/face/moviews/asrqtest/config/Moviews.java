/**
 * 
 */
package se.face.moviews.asrqtest.config;

import java.io.File;

import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;

/**
 * @author Samuel
 *
 */
public final class Moviews {
	private Moviews(){}
	public static void addConfiguration(WebArchive webArchive){
		webArchive.addAsResource("moviews-configuration.properties");
	}
	
	public static void addProjectClasses(WebArchive webArchive){
		webArchive.addPackages(true, "se.face.moviews.web");
	}

	public static void addMavenDependencies(WebArchive webArchive) {		
		File[] dependencies = 
				Maven.resolver()
					.loadPomFromClassLoaderResource("META-INF/maven/se.face.moviews/moviews-web/pom.xml")
					.importRuntimeDependencies().resolve().withTransitivity()
					.asFile();
		webArchive.addAsLibraries(dependencies);
	}
}
