/**
 * 
 */
package se.face.moviews.integrationtest.config;

import java.io.File;
import java.net.URISyntaxException;
import org.apache.catalina.LifecycleState;
import org.apache.catalina.startup.Tomcat;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.impl.base.exporter.zip.ZipExporterImpl;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;

/**
 * @author Samuel
 *
 */
public class TomcatRunner {
	private Tomcat tomcat;
	
	public void createAndStart(){
		try {
			tomcat = new Tomcat();
			String workingDir = System.getProperty("java.io.tmpdir")+"/embeddedTomcat";
			tomcat.setPort(8080);
			tomcat.setBaseDir(workingDir);
			tomcat.getHost().setAppBase(workingDir);
			tomcat.getHost().setAutoDeploy(true);
			tomcat.getHost().setDeployOnStartup(true);
			String contextPath = "/moviews-web";
			File webApp = new File(workingDir, getWarFileName());
			deleteOLD(workingDir, contextPath);
			new ZipExporterImpl(createWebArchive()).exportTo(webApp, true);
			tomcat.addWebapp(tomcat.getHost(), contextPath, webApp.getAbsolutePath());
			tomcat.start();
		} catch (Exception e) {
			throw new IllegalStateException("Could not start tomcat!", e);
		}
	}
	
	private WebArchive createWebArchive() throws Exception{
		WebArchive webArchive = ShrinkWrap.create(WebArchive.class, getWarFileName());
		webArchive.addPackages(true, "se.face.moviews.web");
		addMavenDependencies(webArchive);
		return webArchive;
	}

	private void addMavenDependencies(WebArchive webArchive) throws URISyntaxException {		
		File[] dependencies = 
				Maven.resolver()
					.loadPomFromClassLoaderResource("META-INF/maven/se.face.moviews/moviews-web/pom.xml")
					.importRuntimeDependencies().resolve().withTransitivity()
					.asFile();
		webArchive.addAsLibraries(dependencies);
	}

	private void deleteOLD(String workingDir, String contextPath) {		
		File oldWebApp = new File(workingDir, getWarFileName());
		File oldWorkingFolder = new File(workingDir, contextPath);
		File oldWorkFolder = new File(workingDir, "/work");
		try {
			oldWebApp.delete();
			oldWorkingFolder.delete();
			oldWorkFolder.delete();
		} catch (SecurityException e) {
			System.err.println("Could not delete old tomcat!");
			e.printStackTrace(System.err);
		}
	}

	public void destroy(){
		try{
			if (tomcat.getServer() != null
		            && tomcat.getServer().getState() != LifecycleState.DESTROYED) {
		        if (tomcat.getServer().getState() != LifecycleState.STOPPED) {
		        	tomcat.stop();
		        }
		        tomcat.destroy();
		    }
		} catch(Exception e){
			throw new IllegalStateException("Tomcat could not be stopped!", e);
		}
	}
	
	private String getWarFileName() {
		return "moviews-web.war";
	}
	
//	public static void main(String[] args) throws InterruptedException{
//		TomcatRunner tomcatRunner = new TomcatRunner();
//		tomcatRunner.createAndStart();
//		System.out.println("Created sleeping");
//		Thread.sleep(20000);
//		tomcatRunner.destroy();
//	}
}
