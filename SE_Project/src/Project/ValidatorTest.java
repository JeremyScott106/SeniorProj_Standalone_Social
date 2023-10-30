package Project;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class ValidatorTest {


	/*
	 * NOTICE:
	 *
	 * Tests will have to be updated as classes are updated to where
	 * constructors take more arguments
	 *
	 */


	@Test
	void testValidUsername_Users_Success() {
		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		User u2 = new User("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		User u5 = new User("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");

		ArrayList<User> users = new ArrayList<>();
		users.add(u1);
		users.add(u2);
		users.add(u3);
		users.add(u4);
		users.add(u5);

		User actual = Validator.validUserName_Users(users, "WestCarolina");
		User expected = u3;

		assertEquals(expected, actual);
	}

	@Test
	void testValidUsername_Users_Failure() {
		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		User u2 = new User("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		User u5 = new User("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");

		ArrayList<User> users = new ArrayList<>();
		users.add(u1);
		users.add(u2);
		users.add(u3);
		users.add(u4);
		users.add(u5);

		User actual = Validator.validUserName_Users(users, "EastCarolina");
		User expected = null;

		assertEquals(expected, actual);
	}

	@Test
	void testValidUsername_Admins_Success() {
		Admin a1 = new Admin("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		Admin a2 = new Admin("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");

		ArrayList<Admin> admins = new ArrayList<>();
		admins.add(a1);
		admins.add(a2);

		User actual = Validator.validUserName_Admins(admins, "theWiz");
		Admin expected = a2;

		assertEquals(expected, actual);
	}

	@Test
	void testValidUsername_Admins_Failure() {
		Admin a1 = new Admin("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		Admin a2 = new Admin("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");

		ArrayList<Admin> admins = new ArrayList<>();
		admins.add(a1);
		admins.add(a2);

		User actual = Validator.validUserName_Admins(admins, "failure");
		Admin expected = null;

		assertEquals(expected, actual);
	}



	@Test
	void testValidateCategoryNameExists_True() {
		ArrayList<category> categories = new ArrayList<>();

		category c1 = new category("Sports");
		category c2 = new category("Games");
		category c3 = new category("Video Games");
		category c4 = new category("Foods");
		category c5 = new category("Apples");

		categories.add(c1);
		categories.add(c2);
		categories.add(c3);
		categories.add(c4);
		categories.add(c5);

		Boolean actual = Validator.validateCategoryNameExists(categories, "Foods");

		assertEquals(true, actual);
	}


	@Test
	void testValidateCategoryNameExists_False() {
		ArrayList<category> categories = new ArrayList<>();

		category c1 = new category("Sports");
		category c2 = new category("Games");
		category c3 = new category("Video Games");
		category c4 = new category("Foods");
		category c5 = new category("Apples");

		categories.add(c1);
		categories.add(c2);
		categories.add(c3);
		categories.add(c4);
		categories.add(c5);

		Boolean actual = Validator.validateCategoryNameExists(categories, "Computers");

		assertEquals(false, actual);
	}


	@Test
	void testValideatGroupNameExists_True() {
		ArrayList<Group> groups = new ArrayList<>();

		Group g1 = new Group("Hockey");
		Group g2 = new Group("Soccer");
		Group g3 = new Group("Football");
		Group g4 = new Group("Basketball");
		Group g5 = new Group("Tennis");

		groups.add(g1);
		groups.add(g2);
		groups.add(g3);
		groups.add(g4);
		groups.add(g5);

		Boolean actual = Validator.validateGroupNameExists(groups, "Basketball");

		assertEquals(true, actual);
	}


	@Test
	void testValideatGroupNameExists_False() {
		ArrayList<Group> groups = new ArrayList<>();

		Group g1 = new Group("Hockey");
		Group g2 = new Group("Soccer");
		Group g3 = new Group("Football");
		Group g4 = new Group("Basketball");
		Group g5 = new Group("Tennis");

		groups.add(g1);
		groups.add(g2);
		groups.add(g3);
		groups.add(g4);
		groups.add(g5);

		Boolean actual = Validator.validateGroupNameExists(groups, "Golf");

		assertEquals(false, actual);
	}


	@Test
	void testGetCategoryFromName_Success() {
		category c1 = new category("Foods");
		category c2 = new category("Sports");
		category c3 = new category("Games");


		ArrayList<category> cats = new ArrayList<>();

		cats.add(c1);
		cats.add(c2);
		cats.add(c3);

		category actual = Validator.getCategoryFromName(cats, "Sports");

		assertEquals(c2, actual);
	}


	@Test
	void testGetCategoryFromName_Failure() {
		category c1 = new category("Foods");
		category c2 = new category("Sports");
		category c3 = new category("Games");


		ArrayList<category> cats = new ArrayList<>();

		cats.add(c1);
		cats.add(c2);
		cats.add(c3);

		category actual = Validator.getCategoryFromName(cats, "Video Games");


		assertEquals(null, actual);
	}
	
	@Test
	void testValidateUserInGroup_Success() {
		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		
		Group g1 = new Group("funny");
		
		g1.addMember(u1);
		
		ArrayList<Group> groups = new ArrayList<>();
		groups.add(g1);


		Boolean actual = Validator.validateUserInGroup(g1, u1);


		assertEquals(true, actual);
	}
	
	@Test
	void testValidateUserInGroup_Failure() {
		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		User u2 = new User("Jack2", "jackster5", "HKb@wser!!", "11/10/1997", "Valdosta", "Georgia");

		Group g1 = new Group("funny");
		
		g1.addMember(u1);
		
		ArrayList<Group> groups = new ArrayList<>();
		groups.add(g1);


		Boolean actual = Validator.validateUserInGroup(g1, u2);


		assertEquals(false, actual);
	}
	
	
	@Test
	void testValidateAdminExist_Success() {
		Admin a1 = new Admin("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		Admin a2 = new Admin("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		
		ArrayList<Admin> admins = new ArrayList<Admin>();
		
		admins.add(a1);
		admins.add(a2);
		
		boolean actual = Validator.validateAdminExists(a2, admins);
		
		assertEquals(true, actual);
	}
	
	@Test
	void testValidateAdminExist_Failure() {
		Admin a1 = new Admin("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		Admin a2 = new Admin("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		
		ArrayList<Admin> admins = new ArrayList<Admin>();
		
		admins.add(a1);
		admins.add(a2);
		
		Admin a3 = new Admin("Jack2", "jackster5", "HKb@wser!!", "11/10/1997", "Valdosta", "Georgia");
		
		boolean actual = Validator.validateAdminExists(a3, admins);
		
		assertEquals(false, actual);
	}
	
	
	@Test
	void testValidateCategoryExists_Success() {
		category c1 = new category("Sports");
		category c2 = new category("Foods");
		
		ArrayList<category> categories = new ArrayList<category>();
		
		categories.add(c1);
		categories.add(c2);
		
		boolean actual = Validator.validateCategoryExists(c2, categories);
		
		assertEquals(true, actual);
	}
	
	@Test
	void testValidateCategoryExists_Failure() {
		category c1 = new category("Sports");
		category c2 = new category("Foods");
		
		ArrayList<category> categories = new ArrayList<category>();
		
		categories.add(c1);
		categories.add(c2);
		
		category c3 = new category("Games");
		
		boolean actual = Validator.validateCategoryExists(c3, categories);
		
		assertEquals(false, actual);
	}
	
	
	@Test
	void testValidateUserExists_Success() {
		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		User u2 = new User("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		User u5 = new User("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");
		
		ArrayList<User> users = new ArrayList<User>();
		
		users.add(u1);
		users.add(u2);
		users.add(u3);
		users.add(u4);
		users.add(u5);
		
		boolean actual = Validator.validateUserExists(u4, users);
		
		assertEquals(true, actual);
		
	}
	
	@Test
	void testValidateUserExists_Failure() {
		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		User u2 = new User("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		
		ArrayList<User>  users = new ArrayList<User>();
		
		users.add(u1);
		users.add(u2);
		users.add(u3);
		users.add(u4);
		
		User u5 = new User("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");
		
		boolean actual = Validator.validateUserExists(u5, users);
		
		assertEquals(false, actual);
		
	}

}
