/**
 * 
 */
package se.face.moviews.core.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import se.face.moviews.api.model.WorkingRole;
import se.face.moviews.core.test.TestConfiguration;

/**
 * @author Samuel
 *
 */
@Ignore("Unstable integration test")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
public class IMDBServiceTest {
	@Autowired
	private IMDBService imdbService;
	
	@Test
	public void shouldGetWorkingRolesForT2(){
		List<WorkingRole> allWorkingRolesForMovie = imdbService.getAllWorkingRolesForMovie("tt0103064");
		assertEquals("Director count", 1, allWorkingRolesForMovie.stream()
				.filter(w -> "Director".equals(w.getRole())).count());
		assertEquals("Writer count", 2, allWorkingRolesForMovie.stream()
				.filter(w -> "Writer".equals(w.getRole())).count());
		assertEquals("Producer count", 1, allWorkingRolesForMovie.stream()
				.filter(w -> "Producer".equals(w.getRole())).count());
		assertEquals("Actor count", 48, allWorkingRolesForMovie.stream()
			.filter(w -> "Actor".equals(w.getRole())).count());
		assertTrue("No Arnold???", allWorkingRolesForMovie.stream()
			.anyMatch(w -> "Arnold".equals(w.getPerson().getFirstName())));
	}
	
	@Test
	public void noHitShouldYieldEmpty(){
		List<WorkingRole> response = imdbService.getAllWorkingRolesForMovie("tt999O99");
		assertEquals(0, response.size());
	}
}
