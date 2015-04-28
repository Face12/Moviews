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

	private static final String EMBEDDED_TOMCAT_PATH = "/embeddedTomcat";
	private static final String WAR_FILE_NAME = "moviews-web.war";
	private static final String CONTEXT_PATH = "/moviews-web";
	
	public void createAndStart(){
		try {
			tomcat = new Tomcat();
			String workingDir = System.getProperty("java.io.tmpdir")+EMBEDDED_TOMCAT_PATH;
			tomcat.setPort(8080);
			tomcat.setBaseDir(workingDir);
			tomcat.getHost().setAppBase(workingDir);
			tomcat.getHost().setAutoDeploy(true);
			tomcat.getHost().setDeployOnStartup(true);
			File webApp = new File(workingDir, WAR_FILE_NAME);
			deleteOLD(workingDir);
			new ZipExporterImpl(createWebArchive()).exportTo(webApp, true);
			tomcat.addWebapp(tomcat.getHost(), CONTEXT_PATH, webApp.getAbsolutePath());
			tomcat.start();
		} catch (Exception e) {
			throw new IllegalStateException("Could not start tomcat!", e);
		}
	}
	
	private WebArchive createWebArchive() throws Exception{
		WebArchive webArchive = ShrinkWrap.create(WebArchive.class, WAR_FILE_NAME);
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

	private void deleteOLD(String workingDir) {		
		File oldWebApp = new File(workingDir, WAR_FILE_NAME);
		File oldWorkingFolder = new File(workingDir, CONTEXT_PATH);
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
	
//	public static void main(String[] args) throws InterruptedException{
//		TomcatRunner tomcatRunner = new TomcatRunner();
//		tomcatRunner.createAndStart();
//		System.out.println("Created sleeping");
//		Thread.sleep(20000);
//		tomcatRunner.destroy();
//	}
}
