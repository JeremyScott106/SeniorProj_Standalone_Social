package Project;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class SystemManagerTest {

	@Test
	void testLogin_Success_User() {

		SystemManager sm = new SystemManager();

		User u1 = new User("Jack", "jackster3", "HKb@wser!");
		User u2 = new User("Dan", "theWiz", "WartH@g77");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble");
		User u5 = new User("Ethan", "IDK", "WHY#5");

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

		User u1 = new User("Jack", "jackster3", "HKb@wser!");
		User u2 = new User("Dan", "theWiz", "WartH@g77");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble");
		User u5 = new User("Ethan", "IDK", "WHY#5");

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

		User u1 = new User("Jack", "jackster3", "HKb@wser!");
		User u2 = new User("Dan", "theWiz", "WartH@g77");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble");
		User u5 = new User("Ethan", "IDK", "WHY#5");

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

		Admin a1 = new Admin("Jack", "jackster3", "HKb@wser!");
		Admin a2 = new Admin("Dan", "theWiz", "WartH@g77");

		sm.addAdmin(a1);
		sm.addAdmin(a2);

		boolean tf = sm.login("jackster3", "HKb@wser!");

		assertEquals(true, tf);
	}

	@Test
	void testLogin_Failure_Username_Admin() {

		SystemManager sm = new SystemManager();

		Admin a1 = new Admin("Jack", "jackster3", "HKb@wser!");
		Admin a2 = new Admin("Dan", "theWiz", "WartH@g77");

		sm.addAdmin(a1);
		sm.addAdmin(a2);

		boolean tf = sm.login("jackster7", "HKb@wser!");

		assertEquals(false, tf);
	}

	@Test
	void testLogin_Falilure_Password_Admin() {

		SystemManager sm = new SystemManager();

		Admin a1 = new Admin("Jack", "jackster3", "HKb@wser!");
		Admin a2 = new Admin("Dan", "theWiz", "WartH@g77");

		sm.addAdmin(a1);
		sm.addAdmin(a2);

		boolean tf = sm.login("jackster3", "Failure");

		assertEquals(false, tf);
	}



	@Test
	void testGetGroupsSortedAlphabetically() {

		SystemManager sm = new SystemManager();

		group g1 = new group("Sports");
		group g2 = new group("Games");
		group g3 = new group("Video Games");
		group g4 = new group("Foods");
		group g5 = new group("Apples");

		sm.addGroup(g1);
		sm.addGroup(g2);
		sm.addGroup(g3);
		sm.addGroup(g4);
		sm.addGroup(g5);

		ArrayList<group> actual = sm.getGroups_Alphabetically();

		ArrayList<group> expected = new ArrayList<group>();

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

		ArrayList<category> expected = new ArrayList<category>();

		expected.add(c5);
		expected.add(c4);
		expected.add(c2);
		expected.add(c1);
		expected.add(c3);

		assertEquals(expected, actual);

	}

}
