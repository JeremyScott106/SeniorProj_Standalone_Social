package Project;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;

public class SuspendedTest {

	@Test
	void testSuspendedConstructor() {
		User u = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia", "12/17/2007");
		Group testGroup1 = new Group("Standard Name");
		Suspended s = new Suspended(u, testGroup1);

		boolean actual = true;

		
		assertEquals(true, actual);
	}
	
	@Test
	void testGetSuspended() {
		User u = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia", "12/17/2007");
		Group testGroup1 = new Group("Standard Name");
		Suspended s = new Suspended(u, testGroup1);

		User actual = s.getUser();
		assertEquals(u, actual);
	}	
	
	@Test
	void testGetGroup() {
		User u = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia", "12/17/2007");
		Group testGroup1 = new Group("Standard Name");
		Suspended b = new Suspended(u, testGroup1);

		Group actual = b.getGroup();
		assertEquals(testGroup1, actual);
	}	
	
	@Test
	void testGetDate() {
		
		User u = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia", "12/17/2007");
		Group testGroup1 = new Group("Standard Name");
		

		// Create a Date object for the registration date.
		Suspended m = new Suspended(u, testGroup1);

		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		String actual = df.format(m.getDate());
		
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String expected = currentDate.format(formatter);
		
		assertEquals(expected, actual);
	}	
	
	// need to update REGISTEREDDATE and expiredSuspensionDate
	@Ignore
	@Test
	void testGetSuspendedWriteData() {
		User u = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia", "12/17/2007");
		Group testGroup1 = new Group("Standard Name");
		
		Suspended s = new Suspended(u, testGroup1, "01/12/2012 20:20", "01/13/2012 20:20");
				
		String actual = s.getSuspendedWriteData();
		
		String expected = "@START\n" + 
							"@SUSPENDED\n" +
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
		
		Suspended s1 = new Suspended(u1, testGroup1, "01/12/2012 20:20", "01/13/2012  20:20");
		
		User u2 = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia", "12/17/2007");
		Group testGroup2 = new Group("Standard Name");
		
		Suspended s2 = new Suspended(u2, testGroup2, "01/12/2012 20:20", "01/13/2012 20:20");

			
		int actual = s1.compareTo(s2);
		
		assertEquals(1, actual);
	}
	
	@Test
	void testCompareTo_Failure_DifferentUsernames() {
		
		User u1 = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia", "12/17/2007");
		Group testGroup1 = new Group("Standard Name");
		
		Suspended s1 = new Suspended(u1, testGroup1, "01/12/2012 20:20", "01/13/2012  20:20");
		
		User u2 = new User("Jack", "test", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia", "12/17/2007");
		Group testGroup2 = new Group("Standard Name");
		
		Suspended s2 = new Suspended(u2, testGroup2, "01/12/2012 20:20", "01/13/2012 20:20");
		
		int actual = s1.compareTo(s2);
		
		assertEquals(0, actual);
	}
	
	@Test
	void testCompareTo_Failure_DifferentGruopNames() {
		
		User u1 = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia", "12/17/2007");
		Group testGroup1 = new Group("Standard Name");
		
		Suspended s1 = new Suspended(u1, testGroup1, "01/12/2012 20:20", "01/13/2012 20:20");
		
		User u2 = new User("Jack", "test", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia", "12/17/2007");
		Group testGroup2 = new Group("Test");
		
		Suspended s2 = new Suspended(u2, testGroup2, "01/12/2012 20:20", "01/13/2012 20:20");
		
		int actual = s1.compareTo(s2);
		
		assertEquals(0, actual);
	}
	
}