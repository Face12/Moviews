/**
 * 
 */
package se.face.moviews.core.domain.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import se.face.moviews.core.domain.entity.WorkingRole;
import se.face.moviews.core.test.TestConfiguration;

/**
 * @author Samuel
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
public class WorkingRoleDaoTest {

	@Autowired
	WorkingRoleDao workingRoleDao;
	
	@Transactional
	@Test
	public void shouldMatchExactName(){
		List<WorkingRole> johnDoes = workingRoleDao.searchByName("John Doe");
		
		assertEquals(1, johnDoes.size());
		assertEquals(101, johnDoes.get(0).getWorkingRoleId().intValue());
	}
}
