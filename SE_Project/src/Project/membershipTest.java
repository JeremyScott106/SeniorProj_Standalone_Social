package Project;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;

public class membershipTest {

	@Test
	void testMembershipConstructor() {
		User u = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia", "12/17/2007");
		Group testGroup1 = new Group("Standard Name");
		membership m = new membership(u, testGroup1);

		boolean actual = true;
		if (m == null) {
			actual = false;
		}
		
		assertEquals(true, actual);
	}
	
	@Test
	void testGetUser() {
		User u = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia", "12/17/2007");
		Group testGroup1 = new Group("Standard Name");
		membership m = new membership(u, testGroup1);

		User actual = m.getUser();
		assertEquals(u, actual);
	}	
	
	@Test
	void testGetGroup() {
		User u = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia", "12/17/2007");
		Group testGroup1 = new Group("Standard Name");
		membership m = new membership(u, testGroup1);

		Group actual = m.getGroup();
		assertEquals(testGroup1, actual);
	}	
	
	@Test
	void testGetDate() {
		User u = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia", "12/17/2007");
		Group testGroup1 = new Group("Standard Name");
		
		// Create a Date object for the registration date.
	    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
	    Date registrationDate = null;
	    try {
	        registrationDate = dateFormat.parse("01/12/12");
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
		membership m = new membership(u, testGroup1, registrationDate);

		Date actual = m.getDate();
		assertEquals(registrationDate, actual);
	}	
	
}