package Project;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class UserTest {
	
	@Test
	void testUser_AddingNewUser_Constructor() {
		User u = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia");
		
		boolean actual = true;
		if (u == null) {
			actual = false;
		}
		
		assertEquals(true, actual);
	}
	
	@Test
	void testUser_AddingExistingUser_Constructor() {
		User u = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia", "12/17/2007");
		
		boolean actual = true;
		if (u == null) {
			actual = false;
		}
		
		assertEquals(true, actual);
	}
	
	@Test
	void testjoinGroup_Success() {
		User u = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia", "12/17/2007");
		Group g = new Group("fun");
		ArrayList<Group> group = new ArrayList<>();
		group.add(g);

		Boolean actual = u.joinGroup("Jack", group, u);
		
		assertEquals(true, actual);
	}
	
	@Test
	void testjoinGroup_Fail() {
		User u = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia", "12/17/2007");
		Group g = new Group("fun");
		g.addMember(u);
		ArrayList<Group> group = new ArrayList<>();
		group.add(g);

		Boolean actual = u.joinGroup("Jack", group, u);
		
		assertEquals(false, actual);
	}
	

}
