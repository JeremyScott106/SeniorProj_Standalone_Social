package Project;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class categoryTest {

	@Test
	void testCreateGroup_Success() {
		category c = new category("Sports");
		
		group g1 = new group("Football");
		group g2 = new group("Soccer");
		group g3 = new group("Tennis");
		
		c.addGroup(g1);
		c.addGroup(g2);
		c.addGroup(g3);
		
		Boolean actual = c.createGroup("Basketball");
		
		assertEquals(true, actual);
	}
	
	
	@Test
	void testCreateGroup_Failure() {
		category c = new category("Sports");
		
		group g1 = new group("Football");
		group g2 = new group("Soccer");
		group g3 = new group("Tennis");
		
		c.addGroup(g1);
		c.addGroup(g2);
		c.addGroup(g3);
		
		Boolean actual = c.createGroup("Tennis");
		
		assertEquals(false, actual);
	}

}
