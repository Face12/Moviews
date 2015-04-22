package se.face.moviews.domain.entity;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Test;

import se.face.moviews.domain.entity.CastAndCrewMember;
import se.face.moviews.domain.entity.Movie;
import se.face.moviews.test.TestObjectFactory;

public class MovieTest {
	@Test
	public void shouldCreateMovie(){
		TestObjectFactory.newMovie();
	}
	
	@Test
	public void shouldListCastAndCrew(){
		Movie movie = TestObjectFactory.newMovie();
		Set<CastAndCrewMember> list = movie.getCastAndCrew();
		assertTrue("No cast and crew members", list.size() > 0);
	}
	
	@Test
	public void objectTest(){
		Movie movie1a = new Movie(1);
		Movie movie1b = new Movie(1);
		Movie movie2 = new Movie(2);
		
		assertTrue("Should be equal", movie1a.equals(movie1b));
		assertFalse("Should not be equal", movie1a.equals(movie2));
		assertTrue("Hashcode", movie1a.hashCode() == movie1b.hashCode());
	}
}
