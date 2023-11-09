package Project;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;

public class BannedTest {

	@Test
	void testBannedConstructor() {
		User u = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia", "12/17/2007");
		Group testGroup1 = new Group("Standard Name");
		Banned b = new Banned(u, testGroup1);

		boolean actual = true;
		if (b == null) {
			actual = false;
		}
		
		assertEquals(true, actual);
	}
	
	@Test
	void testGetBanned() {
		User u = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia", "12/17/2007");
		Group testGroup1 = new Group("Standard Name");
		Banned b = new Banned(u, testGroup1);

		User actual = b.getUser();
		assertEquals(u, actual);
	}	
	
	@Test
	void testGetGroup() {
		User u = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia", "12/17/2007");
		Group testGroup1 = new Group("Standard Name");
		Banned b = new Banned(u, testGroup1);

		Group actual = b.getGroup();
		assertEquals(testGroup1, actual);
	}	
	
	@Test
	void testGetDate() {
		User u = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia", "12/17/2007");
		Group testGroup1 = new Group("Standard Name");
		

		// Create a Date object for the registration date.
		Banned b = new Banned(u, testGroup1, "01/12/2012");
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		String actual = df.format(b.getDate());
		
		assertEquals("01/12/2012", actual);
	}	
	
	
	@Test
	void testGetBannedWriteData() {
		User u = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia", "12/17/2007");
		Group testGroup1 = new Group("Standard Name");
		
		Banned b = new Banned(u, testGroup1, "01/12/2012");
				
		String actual = b.getBannedWriteData();
		
		String expected = "@START\n" + 
							"@BANNED\n" +
							"@USER=jackster3\n" + 
							"@GROUP=Standard Name\n" + 
							"@REGISTEREDDATE=01/12/2012\n" + 
							"@END\n\n";
		
		assertEquals(expected, actual);
	}
	
	
	@Test
	void testCompareTo_Success() {
		
		User u1 = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia", "12/17/2007");
		Group testGroup1 = new Group("Standard Name");
		
		Banned b1 = new Banned(u1, testGroup1, "01/12/2012");
		
		User u2 = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia", "12/17/2007");
		Group testGroup2 = new Group("Standard Name");
		
		Banned b2 = new Banned(u2, testGroup2, "01/12/2012");

			
		int actual = b1.compareTo(b2);
		
		assertEquals(1, actual);
	}
	
	@Test
	void testCompareTo_Failure_DifferentUsernames() {
		
		User u1 = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia", "12/17/2007");
		Group testGroup1 = new Group("Standard Name");
		
		Banned b1 = new Banned(u1, testGroup1, "01/12/2012");
		
		User u2 = new User("Jack", "test", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia", "12/17/2007");
		Group testGroup2 = new Group("Standard Name");
		
		Banned b2 = new Banned(u2, testGroup2, "01/12/2012");
		
		int actual = b1.compareTo(b2);
		
		assertEquals(0, actual);
	}
	
	@Test
	void testCompareTo_Failure_DifferentGruopNames() {
		
		User u1 = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia", "12/17/2007");
		Group testGroup1 = new Group("Standard Name");
		
		Banned b1 = new Banned(u1, testGroup1, "01/12/2012");
		
		User u2 = new User("Jack", "test", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia", "12/17/2007");
		Group testGroup2 = new Group("Test");
		
		Banned b2 = new Banned(u2, testGroup2, "01/12/2012");
		
		int actual = b1.compareTo(b2);
		
		assertEquals(0, actual);
	}
	
}