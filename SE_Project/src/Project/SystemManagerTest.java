package Project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;

class SystemManagerTest {

	/*
	 * NOTICE:
	 *
	 * Tests will have to be updated as classes are updated to where
	 * constructors take more arguments
	 *
	 */

	@Test
	void testLogin_Success_User() {

		SystemManager sm = new SystemManager();

		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		User u2 = new User("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		User u5 = new User("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");

		sm.addUser(u1);
		sm.addUser(u2);
		sm.addUser(u3);
		sm.addUser(u4);
		sm.addUser(u5);

		boolean tf = sm.login("LegalTrouble", "D@uble&Tr@uble");

		assertEquals(true, tf);
	}

	@Test
	void testLogin_Failure_Username_User() {

		SystemManager sm = new SystemManager();

		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		User u2 = new User("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		User u5 = new User("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");

		sm.addUser(u1);
		sm.addUser(u2);
		sm.addUser(u3);
		sm.addUser(u4);
		sm.addUser(u5);

		boolean tf = sm.login("Failure", "D@uble&Tr@uble");

		assertEquals(false, tf);
	}


	@Test
	void testLogin_Failure_Password_User() {

		SystemManager sm = new SystemManager();

		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		User u2 = new User("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		User u5 = new User("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");

		sm.addUser(u1);
		sm.addUser(u2);
		sm.addUser(u3);
		sm.addUser(u4);
		sm.addUser(u5);

		boolean tf = sm.login("LegalTrouble", "Failure");

		assertEquals(false, tf);
	}

	@Test
	void testLogin_Success_Admin() {

		SystemManager sm = new SystemManager();

		Admin a1 = new Admin("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		Admin a2 = new Admin("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");

		sm.addAdmin(a1);
		sm.addAdmin(a2);

		boolean tf = sm.login("jackster3", "HKb@wser!");

		assertEquals(true, tf);
	}

	@Test
	void testLogin_Failure_Username_Admin() {

		SystemManager sm = new SystemManager();

		Admin a1 = new Admin("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		Admin a2 = new Admin("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");

		sm.addAdmin(a1);
		sm.addAdmin(a2);

		boolean tf = sm.login("jackster7", "HKb@wser!");

		assertEquals(false, tf);
	}

	@Test
	void testLogin_Falilure_Password_Admin() {

		SystemManager sm = new SystemManager();

		Admin a1 = new Admin("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		Admin a2 = new Admin("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");

		sm.addAdmin(a1);
		sm.addAdmin(a2);

		boolean tf = sm.login("jackster3", "Failure");

		assertEquals(false, tf);
	}



	@Ignore
	@Test
	void testGetGroupsSortedAlphabetically() {

		SystemManager sm = new SystemManager();
		
		category c1 = new category("fun");
		
		sm.addCategory(c1);

		Group g1 = new Group("Sports");
		Group g2 = new Group("Games");
		Group g3 = new Group("Video Games");
		Group g4 = new Group("Foods");
		Group g5 = new Group("Apples");
		
		c1.addGroup(g1);
		c1.addGroup(g2);
		c1.addGroup(g3);
		c1.addGroup(g4);
		c1.addGroup(g5);

		ArrayList<Group> actual = sm.getAllGroups_Alphabetically();

		ArrayList<Group> expected = new ArrayList<>();

		expected.add(g5);
		expected.add(g4);
		expected.add(g2);
		expected.add(g1);
		expected.add(g3);

		assertEquals(expected, actual);

	}

	@Test
	void testGetCategoiesSortedAlphabetically() {

		SystemManager sm = new SystemManager();

		category c1 = new category("Sports");
		category c2 = new category("Games");
		category c3 = new category("Video Games");
		category c4 = new category("Foods");
		category c5 = new category("Apples");

		sm.addCategory(c1);
		sm.addCategory(c2);
		sm.addCategory(c3);
		sm.addCategory(c4);
		sm.addCategory(c5);

		ArrayList<category> actual = sm.getCategories_Alphabetically();

		ArrayList<category> expected = new ArrayList<>();

		expected.add(c5);
		expected.add(c4);
		expected.add(c2);
		expected.add(c1);
		expected.add(c3);

		assertEquals(expected, actual);
	}


	@Test
	void testCreateCategory_Failure() {
		SystemManager sm = new SystemManager();

		category c1 = new category("Sports");
		category c2 = new category("Games");
		category c3 = new category("Video Games");
		category c4 = new category("Foods");
		category c5 = new category("Apples");

		sm.addCategory(c1);
		sm.addCategory(c2);
		sm.addCategory(c3);
		sm.addCategory(c4);
		sm.addCategory(c5);

		boolean actual = sm.createCategory("Foods");

		assertEquals(false, actual);
	}


	@Test
	void testCreateGroup_Success() {
		SystemManager sm = new SystemManager();

		category c = new category("Sports");

		Group g1 = new Group("Football");
		Group g2 = new Group("Soccer");

		c.addGroup(g1);
		c.addGroup(g2);

		sm.addCategory(c);

		boolean actual = sm.createGroup("Tennis", "Sports");

		assertEquals(true, actual);
	}


	@Test
	void testCreateGroup_Failure_InvalidCategory() {
		SystemManager sm = new SystemManager();

		category c = new category("Sports");

		Group g1 = new Group("Football");
		Group g2 = new Group("Soccer");

		c.addGroup(g1);
		c.addGroup(g2);

		sm.addCategory(c);

		boolean actual = sm.createGroup("Tennis", "Foods");

		assertEquals(false, actual);
	}


	@Test
	void testCreateGroup_Failure_DuplicateGroup() {
		SystemManager sm = new SystemManager();

		category c = new category("Sports");

		Group g1 = new Group("Football");
		Group g2 = new Group("Soccer");

		c.addGroup(g1);
		c.addGroup(g2);

		sm.addCategory(c);

		boolean actual = sm.createGroup("Soccer", "Sports");

		assertEquals(false, actual);
	}


	@Test
	void testRegisterUser_Success() {
		SystemManager sm = new SystemManager();

		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		User u2 = new User("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		User u5 = new User("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");

		sm.addUser(u1);
		sm.addUser(u2);
		sm.addUser(u3);
		sm.addUser(u4);
		sm.addUser(u5);

		boolean actual = sm.registerUser("Jack", "11/3/99", "Valdosta", "GA", "JackTheWack", "W@ck0#5");

		assertEquals(true, actual);
	}

	@Test
	void testRegisterUser_Failure() {
		SystemManager sm = new SystemManager();

		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		User u2 = new User("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		User u5 = new User("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");

		sm.addUser(u1);
		sm.addUser(u2);
		sm.addUser(u3);
		sm.addUser(u4);
		sm.addUser(u5);

		boolean actual = sm.registerUser("Jack", "11/3/99", "Valdosta", "GA", "jackster3", "W@ck0#5");

		assertEquals(false, actual);

	}
	

	
	@Test
	void testGetAdmins_Alphabetically() {
		SystemManager manager = new SystemManager();
		
		Admin a1 = new Admin("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		Admin a2 = new Admin("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		Admin a3 = new Admin("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		Admin a4 = new Admin("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		Admin a5 = new Admin("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");
		
		manager.addAdmin(a1);
		manager.addAdmin(a2);
		manager.addAdmin(a3);
		manager.addAdmin(a4);
		manager.addAdmin(a5);
		
		ArrayList<Admin> actual = manager.getAdmins_Alphabetically();
		
		ArrayList<Admin> expected = new ArrayList<Admin>();
		
		expected.add(a3);
		expected.add(a2);
		expected.add(a4);
		expected.add(a5);
		expected.add(a1);
		
		assertEquals(expected, actual);
  }
		

	@Test
	void testgetGroupsByUser_success() {
		SystemManager sm = new SystemManager();
		Group g1 = new Group("Funny");
		Group g2 = new Group("Happy");
		category c1 = new category("happy");
		
		c1.addGroup(g1);
		c1.addGroup(g2);
		
		sm.addCategory(c1);

		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/05/12", "10/5/12", "10/5/12");
		User u2 = new User("Dan", "theWiz", "WartH@g77", "10/05/12", "10/5/12", "5/5/5");
		
		g1.addMember(u1);
		g1.addMember(u2);
		g2.addMember(u1);
				
		sm.addGroup(g1);
		sm.addGroup(g2);

		sm.addUser(u1);
		sm.addUser(u2);

		ArrayList<Group> actual = new ArrayList<>();
		actual.addAll(sm.getGroupsByUser(u1));
		
		ArrayList<Group> expected = new ArrayList<>();
		expected.add(g1);
		expected.add(g2);

		assertEquals(expected, actual);
	}
	
	@Test
	void testgetUsersInGroup_Success() {
		SystemManager sm = new SystemManager();
		Group g = new Group("Funny");

		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/05/12", "10/5/12", "10/5/12");
		User u2 = new User("Dan", "theWiz", "WartH@g77", "10/05/12", "10/5/12", "5/5/5");
		
		g.addMember(u1);
		g.addMember(u2);
		
		sm.addGroup(g);

		sm.addUser(u1);
		sm.addUser(u2);

		ArrayList<User> actual = new ArrayList<>();
		actual.addAll(sm.getUsersInGroup(g));
		
		ArrayList<User> expected = new ArrayList<>();
		expected.add(u1);
		expected.add(u2);

		assertEquals(expected, actual);
	}
	
	@Test
	void testGetGroupsInCategory_Alphabetically() {
		SystemManager sm = new SystemManager();
		
		category c1 = new category("test");
		
		sm.addCategory(c1);

		Group g1 = new Group("Hockey");
		Group g2 = new Group("Soccer");
		Group g3 = new Group("Football");
		Group g4 = new Group("Basketball");
		Group g5 = new Group("Tennis");
		
		sm.addGroup(g1);
		sm.addGroup(g2);
		sm.addGroup(g3);
		sm.addGroup(g4);
		sm.addGroup(g5);

		c1.addGroup(g1);
		c1.addGroup(g2);
		c1.addGroup(g3);
		c1.addGroup(g4);
		c1.addGroup(g5);


		ArrayList<Group> expected = new ArrayList<>();
		expected.add(g4);
		expected.add(g3);
		expected.add(g1);
		expected.add(g2);
		expected.add(g5);
		
		 
		ArrayList<Group> actual = new ArrayList<>();
		actual.addAll(sm.getGroupsInCategory_Alphabetically(c1));
		assertEquals(expected, actual);

	}
	
	
	@Test
	void testAddAdmin_Success() {
		SystemManager manager = new SystemManager();
		
		Admin a1 = new Admin("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		Admin a2 = new Admin("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		Admin a3 = new Admin("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		Admin a4 = new Admin("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		Admin a5 = new Admin("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");
		
		manager.addAdmin(a1);
		manager.addAdmin(a2);
		manager.addAdmin(a3);
		manager.addAdmin(a4);
		
		
		boolean actual = manager.addAdmin(a5);
		
		assertEquals(true, actual);
	}
	
	@Test
	void testAddAdmin_Failure() {
		SystemManager manager = new SystemManager();
		
		Admin a1 = new Admin("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		Admin a2 = new Admin("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		Admin a3 = new Admin("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		Admin a4 = new Admin("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		Admin a5 = new Admin("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");
		
		manager.addAdmin(a1);
		manager.addAdmin(a2);
		manager.addAdmin(a3);
		manager.addAdmin(a4);
		manager.addAdmin(a5);
		
		
		boolean actual = manager.addAdmin(a5);
		
		assertEquals(false, actual);
	}
	
	
	@Test
	void testAddCategory_Success() {
		SystemManager manager = new SystemManager();
		
		category c1 = new category("Sports");
		category c2 = new category("Games");
		category c3 = new category("Video Games");
		category c4 = new category("Foods");
		
		manager.addCategory(c1);
		manager.addCategory(c2);
		manager.addCategory(c3);
		manager.addCategory(c4);
		
		category c5 = new category("Apples");
		
		boolean actual = manager.addCategory(c5);
		
		assertEquals(true, actual);
		
	}
	
	@Test
	void testAddCategory_Failure() {
		SystemManager manager = new SystemManager();
		
		category c1 = new category("Sports");
		category c2 = new category("Games");
		category c3 = new category("Video Games");
		category c4 = new category("Foods");
		category c5 = new category("Apples");
		
		manager.addCategory(c1);
		manager.addCategory(c2);
		manager.addCategory(c3);
		manager.addCategory(c4);
		manager.addCategory(c5);
		
		boolean actual = manager.addCategory(c5);
		
		assertEquals(false, actual);
		
	}
	
	
	@Test
	void testAddUser_Success() {
		SystemManager manager = new SystemManager();
		
		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		User u2 = new User("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		
		manager.addUser(u1);
		manager.addUser(u2);
		manager.addUser(u3);
		manager.addUser(u4);
		
		User u5 = new User("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");
		
		boolean actual = manager.addUser(u5);
		
		assertEquals(true, actual);
		
	}
	
	@Test
	void testAddUser_Failure() {
		SystemManager manager = new SystemManager();
		
		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		User u2 = new User("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		
		manager.addUser(u1);
		manager.addUser(u2);
		manager.addUser(u3);
		manager.addUser(u4);
		
		boolean actual = manager.addUser(u3);
		
		assertEquals(false, actual);
		
	}
}
