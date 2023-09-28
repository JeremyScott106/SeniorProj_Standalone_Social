package Project;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class ValidatorTest {

	@Test
	void validUsernameTest_True() {
		User u1 = new User("Jack", "jackster3", "HKb@wser!");
		User u2 = new User("Dan", "theWiz", "WartH@g77");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble");
		User u5 = new User("Ethan", "IDK", "WHY#5");
		
		ArrayList<User> users = new ArrayList<>();
		users.add(u1);
		users.add(u2);
		users.add(u3);
		users.add(u4);
		users.add(u5);
		
		User actual = Validator.validUserName(users, "WestCarolina");
		User expected = u3;
		
		assertEquals(expected, actual);
	}
	
	@Test
	void validUsernameTest_False() {
		User u1 = new User("Jack", "jackster3", "HKb@wser!");
		User u2 = new User("Dan", "theWiz", "WartH@g77");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble");
		User u5 = new User("Ethan", "IDK", "WHY#5");
		
		ArrayList<User> users = new ArrayList<>();
		users.add(u1);
		users.add(u2);
		users.add(u3);
		users.add(u4);
		users.add(u5);
		
		User actual = Validator.validUserName(users, "EastCarolina");
		User expected = null;
		
		assertEquals(expected, actual);
	}

}
