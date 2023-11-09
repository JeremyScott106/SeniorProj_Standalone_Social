package Project;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import org.junit.jupiter.api.Test;

public class GroupTest {

	//Testing Constructor and getGroupName//
	@Test
	void getGroupNameTest() {
		Group testGroup6 = new Group("Sentence used as a group name making it very long");
		Group testGroup1 = new Group("Standard Name");
		Group testGroup2 = new Group("");
		Group testGroup3 = new Group("$pecial-C@r@cters!");
		Group testGroup4 = new Group("num3r5");
		Group testGroup5 = new Group("   startWithSpaces");

		assertEquals("Standard Name", testGroup1.getGroupName());
		assertEquals("", testGroup2.getGroupName());
		assertEquals("$pecial-C@r@cters!", testGroup3.getGroupName());
		assertEquals("num3r5", testGroup4.getGroupName());
		assertEquals("   startWithSpaces", testGroup5.getGroupName());
		assertEquals("Sentence used as a group name making it very long", testGroup6.getGroupName());
	}

	//Testing addMember and getMember//
	@Test
	void addMemberTest() throws ParseException {
		Group testGroup1 = new Group("MembersTest");
		
		User u1 = new User("name", "0", "pass", "10/10/1997", "Valdosta", "Georgia");
		User u2 = new User("name", "1", "pass", "10/10/1997", "Valdosta", "Georgia");
		User u3 = new User("name", "2", "pass", "10/10/1997", "Valdosta", "Georgia");
		User u4 = new User("name", "3", "pass", "10/10/1997", "Valdosta", "Georgia");
		User u5 = new User("name", "4", "pass", "10/10/1997", "Valdosta", "Georgia");

		membership m1 = new membership(u1, testGroup1);
		membership m2 = new membership(u2, testGroup1);
		membership m3 = new membership(u3, testGroup1);
		membership m4 = new membership(u4, testGroup1);
		membership m5 = new membership(u5, testGroup1);


		testGroup1.addMember(m1);
		testGroup1.addMember(m2);
		testGroup1.addMember(m3);
		testGroup1.addMember(m4);
		testGroup1.addMember(m5);
		
		User actual = testGroup1.getUserInMembership("0");
		User expected = u1;

		assertEquals(expected, actual);
	}
	
	//Testing addMember and getMember//
	@Test
	void removeMemberTest(){
		Group testGroup1 = new Group("MembersTest");
		
		User u1 = new User("name", "0", "pass", "10/10/1997", "Valdosta", "Georgia");
		User u2 = new User("name", "1", "pass", "10/10/1997", "Valdosta", "Georgia");
		User u3 = new User("name", "2", "pass", "10/10/1997", "Valdosta", "Georgia");
		User u4 = new User("name", "3", "pass", "10/10/1997", "Valdosta", "Georgia");
		User u5 = new User("name", "4", "pass", "10/10/1997", "Valdosta", "Georgia");

		membership m1 = new membership(u1, testGroup1);
		membership m2 = new membership(u2, testGroup1);
		membership m3 = new membership(u3, testGroup1);
		membership m4 = new membership(u4, testGroup1);
		membership m5 = new membership(u5, testGroup1);


		testGroup1.addMember(m1);
		testGroup1.addMember(m2);
		testGroup1.addMember(m3);
		testGroup1.addMember(m4);
		testGroup1.addMember(m5);
		testGroup1.removeMember(m5);
		
		ArrayList<membership> actual = testGroup1.getMembers();
		
		ArrayList<membership> expected = new ArrayList<>();
		expected.add(m1);
		expected.add(m2);
		expected.add(m3);
		expected.add(m4);


		assertEquals(expected, actual);
	}
	
	@Test
	void testGetGroupWriteData() {
		
		Group g = new Group("Football");
		
		String catName = "Sports";
		
		String actual = g.getGroupWriteData(catName);
		
		String expected = "@START\n" + 
							"@GROUP\n" + 
							"@NAME=Football\n" + 
							"@CATEGORY=Sports\n" + 
							"@END\n\n";
		
		assertEquals(expected, actual);
		
	}
	
	
	@Test
	void testCompareTo_Success() {
		
		Group g1 = new Group("Standard Name");
		Group g2 = new Group("Standard Name");
		
		int actual = g1.compareTo(g2);
		
		assertEquals(1, actual);
		
	}
	
	@Test
	void testCompareTo_Failure() {
		
		Group g1 = new Group("Standard Name");
		Group g2 = new Group("Test");
		
		int actual = g1.compareTo(g2);
		
		assertEquals(0, actual);
		
	}
	
	
	@Test
	void testCompareName_Success() {
		
		Group g1 = new Group("Standard Name");
		
		boolean actual = g1.compareName("Standard Name");
		
		assertEquals(true, actual);
		
	}
	
	@Test
	void testCompareName_Failure() {
		
		Group g1 = new Group("Standard Name");
		
		boolean actual = g1.compareName("Unstandard Name");
		
		assertEquals(false, actual);
		
	}
	
	//Testing addBanned and getBanned//
	@Test
	void addBannedTest(){
		Group testGroup1 = new Group("MembersTest");
		
		User u1 = new User("name", "0", "pass", "10/10/1997", "Valdosta", "Georgia");
		User u2 = new User("name", "1", "pass", "10/10/1997", "Valdosta", "Georgia");
		User u3 = new User("name", "2", "pass", "10/10/1997", "Valdosta", "Georgia");
		User u4 = new User("name", "3", "pass", "10/10/1997", "Valdosta", "Georgia");
		User u5 = new User("name", "4", "pass", "10/10/1997", "Valdosta", "Georgia");
		
		Banned b1 = new Banned(u1, testGroup1);
		Banned b2 = new Banned(u2, testGroup1);
		Banned b3 = new Banned(u3, testGroup1);
		Banned b4 = new Banned(u4, testGroup1);
		Banned b5 = new Banned(u5, testGroup1);


		testGroup1.addBanned(b1);
		testGroup1.addBanned(b2);
		testGroup1.addBanned(b3);
		testGroup1.addBanned(b4);
		testGroup1.addBanned(b5);
		
		ArrayList<Banned> actual = new ArrayList<>();
		actual.addAll(testGroup1.getBanned());
		ArrayList<Banned> expected = new ArrayList<>();
		expected.add(b1);
		expected.add(b2);
		expected.add(b3);
		expected.add(b4);
		expected.add(b5);


		assertEquals(expected, actual);
	}
	
	//Testing addMember and getMember//
	@Test
	void addSuspendedTest() throws ParseException {
		Group testGroup1 = new Group("MembersTest");
		
		User u1 = new User("name", "0", "pass", "10/10/1997", "Valdosta", "Georgia");
		User u2 = new User("name", "1", "pass", "10/10/1997", "Valdosta", "Georgia");
		User u3 = new User("name", "2", "pass", "10/10/1997", "Valdosta", "Georgia");
		User u4 = new User("name", "3", "pass", "10/10/1997", "Valdosta", "Georgia");
		User u5 = new User("name", "4", "pass", "10/10/1997", "Valdosta", "Georgia");
		
		Suspended b1 = new Suspended(u1, testGroup1, "01/12/2012 20:20", "01/12/2012 20:25");
		Suspended b2 = new Suspended(u2, testGroup1, "01/12/2012 20:20", "01/12/2012 20:25");
		Suspended b3 = new Suspended(u3, testGroup1, "01/12/2012 20:20", "01/12/2012 20:25");
		Suspended b4 = new Suspended(u4, testGroup1, "01/12/2012 20:20", "01/12/2012 20:25");
		Suspended b5 = new Suspended(u5, testGroup1, "01/12/2012 20:20", "01/12/2012 20:25");


		testGroup1.addSuspended(b1);
		testGroup1.addSuspended(b2);
		testGroup1.addSuspended(b3);
		testGroup1.addSuspended(b4);
		testGroup1.addSuspended(b5);
		
		ArrayList<Suspended> actual = new ArrayList<>();
		actual.addAll(testGroup1.getSuspended());
		ArrayList<Suspended> expected = new ArrayList<>();
		expected.add(b1);
		expected.add(b2);
		expected.add(b3);
		expected.add(b4);
		expected.add(b5);


		assertEquals(expected, actual);
	}

	@Test
	void getPostsTest() {
													// FIXME :: Post Class Needs Added First //
	}


}
