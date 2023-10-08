package Project;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class categoryTest {

	@Test
	void testGetGroupsAlphabetically() {
		category c = new category("test");
		
		group g1 = new group("Hockey");
		group g2 = new group("Soccer");
		group g3 = new group("Football");
		group g4 = new group("Basketball");
		group g5 = new group("Tennis");
		
		c.addGroup(g1);
		c.addGroup(g2);
		c.addGroup(g3);
		c.addGroup(g4);
		c.addGroup(g5);
		
		ArrayList<group> actual = c.getGroupsAlphabetically();
		
		ArrayList<group> expected = new ArrayList<group>();
		
		expected.add(g4);
		expected.add(g3);
		expected.add(g1);
		expected.add(g2);
		expected.add(g5);
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testAddGroup_Success() {
		category c = new category("test");
		
		group g1 = new group("Hockey");
		group g2 = new group("Soccer");
		group g3 = new group("Football");
		group g4 = new group("Basketball");
		group g5 = new group("Tennis");
		
		c.addGroup(g1);
		c.addGroup(g2);
		c.addGroup(g3);
		c.addGroup(g4);
		c.addGroup(g5);
		
		group test = new group("Golf");
		Boolean actual = c.addGroup(test);
		
		assertEquals(true, actual);
	}
	
	@Test
	void testAddGroup_Failure() {
		category c = new category("test");
		
		group g1 = new group("Hockey");
		group g2 = new group("Soccer");
		group g3 = new group("Football");
		group g4 = new group("Basketball");
		group g5 = new group("Tennis");
		
		c.addGroup(g1);
		c.addGroup(g2);
		c.addGroup(g3);
		c.addGroup(g4);
		c.addGroup(g5);
		
		group test = new group("Soccer");
		Boolean actual = c.addGroup(test);
		
		assertEquals(false, actual);
	}

}
