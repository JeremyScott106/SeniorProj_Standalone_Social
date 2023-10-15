package Project;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
		User u = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", 
							"Valdosta", "Georgia", "12/17/2007");
		
		boolean actual = true;
		if (u == null) {
			actual = false;
		}
		
		assertEquals(true, actual);
	}
	
	@Test
	void test_getUserWriteData() {
		User u = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", 
							"Valdosta", "Georgia", "12/17/2007");
		
		String actual = u.getUserWriteData();
		
		String expected = "@START\n" + 
							"@USER\n" + 
							"@NAME=Jack\n" + 
							"@BIRTHDATE=06/17/2000\n" + 
							"@CITY=Valdosta\n" + 
							"@STATE=Georgia\n" + 
							"@USERNAME=jackster3\n" + 
							"@PASSWORD=HKb@wser!\n" + 
							"@REGISTERED_DATE=12/17/2007\n" + 
							"@END\n\n";
		
		assertEquals(expected, actual);
	}

}
