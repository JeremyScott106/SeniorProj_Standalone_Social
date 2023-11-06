package Project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

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
  
	@Test
	void testGetId() {
		User u = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia");
		
		String actual = u.getId();
		
		assertEquals("jackster3", actual);
	}
	
	@Test
	void testGetPassword() {
		User u = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia");
		
		String actual = u.getPassword();
		
		assertEquals("HKb@wser!", actual);
	}
	
	@Test
	void testGetName() {
		User u = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia");
		
		String actual = u.getName();
		
		assertEquals("Jack", actual);
	}
	
	@Test
	void testGetBirthdate() {
		User u = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia");
		
		Date dbay = u.getBirthday();
		
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		
		String actual = df.format(dbay);
		
		assertEquals("06/17/2000", actual);
	}
	
	@Test
	void testGetCity() {
		User u = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia");
		
		String actual = u.getCity();
		
		assertEquals("Valdosta", actual);
	}
	
	@Test
	void testGetState() {
		User u = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia");
		
		String actual = u.getState();
		
		assertEquals("Georgia", actual);
	}
	
	@Test
	void getRegisteredDate() {
		User u = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", 
							"Valdosta", "Georgia", "10/12/2009");
		
		Date regDate = u.getRegisteredDate();
		
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		
		String actual = df.format(regDate);
		
		assertEquals("10/12/2009", actual);
	}
	
	void testGetGroupStatus_JoinedStatus() {
		User u = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia");
		SystemManager sm = new SystemManager();
		category cat = new category("Test");
		Group group = new Group("Football");
		cat.addGroup(group);
		membership m = new membership(u, group);
		sm.addCategory(cat);
		group.addMember(m);
		
		Integer actual = u.getGroupStatus("Football");
		
		assertEquals(1, actual);
	}
	
	@Ignore 	// No way to leave group //
	void testGetGroupStatus_LeftStatus() {
		User u = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia");
		SystemManager sm = new SystemManager();
		category cat = new category("Test");
		Group group = new Group("Football");
		membership m = new membership(u, group);
		cat.addGroup(group);
		sm.addCategory(cat);
		group.addMember(m);
		
		Integer actual = u.getGroupStatus("Football");
		
		assertEquals(0, actual);
	}
	
	@Test
	void testGetGroupStatus_NonexistentStatus() {
		User u = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia");
		Integer actual = u.getGroupStatus("Soccer");
		
		assertEquals(null, actual);

	}

}
