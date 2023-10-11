package Project;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class GroupTest {

	//Testing Constructor and getGroupName//
	@Test
	void getGroupNameTest() {
		group testGroup1 = new group("Standard Name");
		group testGroup2 = new group("");
		group testGroup3 = new group("$pecial-C@r@cters!");
		group testGroup4 = new group("num3r5");
		group testGroup5 = new group("   startWithSpaces");
		group testGroup6 = new group("Sentence used as a group name making it very long");
		
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
		group testGroup1 = new group("MembersTest");
		User u1 = new User("name", "0", "pass");
		User u2 = new User("name", "1", "pass");
		User u3 = new User("name", "2", "pass");
		User u4 = new User("name", "3", "pass");
		User u5 = new User("name", "4", "pass");
		
		testGroup1.addMembers(u1.getId());
		testGroup1.addMembers(u2.getId());
		testGroup1.addMembers(u3.getId());
		testGroup1.addMembers(u4.getId());
		testGroup1.addMembers(u5.getId());		
		
		ArrayList<String> memList = new ArrayList<>(Arrays.asList("0", "1", "2", "3", "4"));
		
		assertEquals(memList, testGroup1.getMembers());
		
	}
	
	@Test
	void getPostsTest() {
													// FIXME :: Post Class Needs Added First //
	}
		
		
}
