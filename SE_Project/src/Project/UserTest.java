package Project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.Date;
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

	void testjoinGroup_Success() {
		User u = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia", "12/17/2007");
		Group g = new Group("fun");
		ArrayList<Group> group = new ArrayList<>();
		group.add(g);

		Boolean actual = u.joinGroup("Jack", g, u);
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
		
		DateFormat df = new SimpleDateFormat("mm/dd/yyyy");
		
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
		
		DateFormat df = new SimpleDateFormat("mm/dd/yyyy");
		
		String actual = df.format(regDate);
		
		assertEquals("10/12/2009", actual);
	}
	
	@Test
	void testJoinGroup() {
		User u = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia");
		
		boolean actual = u.joinGroup("Football");
		
		assertEquals(true, actual);
	}
	
	@Test
	void testLeaveGroup() {
		User u = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia");
		
		u.joinGroup("Football");
		
		boolean actual = u.leaveGroup("Football");
		
		assertEquals(true, actual);
	}
	
	@Test
	void testjoinGroup_Fail() {
		User u = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia", "12/17/2007");
		Group g = new Group("fun");
		g.addMember(u);
		ArrayList<Group> group = new ArrayList<>();
		group.add(g);

		Boolean actual = u.joinGroup("Jack", g, u);
		
		assertEquals(false, actual);
	}
	
	void testGetGroupStatus_JoinedStatus() {
		User u = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia");
		
		u.joinGroup("Football");
		
		Integer actual = u.getGroupStatus("Football");
		
		assertEquals(1, actual);
	}
	
	@Test
	void testGetGroupStatus_LeftStatus() {
		User u = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia");
		
		u.joinGroup("Football");
		u.leaveGroup("Football");
		
		Integer actual = u.getGroupStatus("Football");
		
		assertEquals(0, actual);
	}
	
	@Test
	void testGetGroupStatus_NonexistentStatus() {
		User u = new User("Jack", "jackster3", "HKb@wser!", "06/17/2000", "Valdosta", "Georgia");
		
		u.joinGroup("Football");
		
		Integer actual = u.getGroupStatus("Soccer");
		
		assertEquals(null, actual);

	}

}
