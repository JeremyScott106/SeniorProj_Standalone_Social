package Project;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

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
	void addMemberTest() {
		Group testGroup1 = new Group("MembersTest");
		
		User u1 = new User("name", "0", "pass", "10/10/1997", "Valdosta", "Georgia");
		User u2 = new User("name", "1", "pass", "10/10/1997", "Valdosta", "Georgia");
		User u3 = new User("name", "2", "pass", "10/10/1997", "Valdosta", "Georgia");
		User u4 = new User("name", "3", "pass", "10/10/1997", "Valdosta", "Georgia");
		User u5 = new User("name", "4", "pass", "10/10/1997", "Valdosta", "Georgia");

		testGroup1.addMember(u1);
		testGroup1.addMember(u2);
		testGroup1.addMember(u3);
		testGroup1.addMember(u4);
		testGroup1.addMember(u5);

		ArrayList<User> memList = new ArrayList<User>();
		
		memList.add(u1);
		memList.add(u2);
		memList.add(u3);
		memList.add(u4);
		memList.add(u5);

		assertEquals(memList, testGroup1.getMembers());
	}

	@Test
	void getPostsTest() {
													// FIXME :: Post Class Needs Added First //
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


}
