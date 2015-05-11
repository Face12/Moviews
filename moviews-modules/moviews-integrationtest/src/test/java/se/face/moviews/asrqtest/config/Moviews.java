/**
 * 
 */
package se.face.moviews.asrqtest.config;

import java.io.File;
import java.net.URL;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;

/**
 * @author Samuel
 *
 */
public final class Moviews {
	private static final String WEB_PROJECT_POM = "META-INF/maven/se.face.moviews/moviews-web/pom.xml";
	
	private Moviews(){}
	
	public static void addProjectClasses(WebArchive webArchive){
		//Adding all classes of the jar that contains this class (could be any class in that jar!)
		//This is a fail-fast and fix-fast design.
		addClassesInJar(se.face.moviews.web.config.WebAppInitializer.class, webArchive);
	}

	public static void addMavenDependencies(WebArchive webArchive) {		
		File[] dependencies = 
				Maven.resolver()
					.loadPomFromClassLoaderResource(WEB_PROJECT_POM)
					.importRuntimeDependencies().resolve().withTransitivity()
					.asFile();
		webArchive.addAsLibraries(dependencies);
	}


	private static void addClassesInJar(Class<?> containingClass, WebArchive webArchive) {
		URL location = containingClass.getProtectionDomain().getCodeSource().getLocation();
		addClassesInJarFile(webArchive, new File(location.getPath()));
	}
	
	private static void addClassesInJarFile(WebArchive webArchive, File file) {
		JarFile jarFile=null;
		try {
			jarFile = new JarFile(file);
			Enumeration<JarEntry> entries = jarFile.entries();
			while(entries.hasMoreElements()){
				JarEntry entry = entries.nextElement();
				if (entry.getName() != null &&
					entry.getName().endsWith(".class")){
					webArchive.addClass(Class.forName(
							entry.getName()
							.replaceAll("\\/", ".")
							.replace(".class", "")));
				}
			}
			
		} catch (Exception e) {
			throw new IllegalStateException("Can't get classes", e);
		}
		finally{
			try {
				jarFile.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
