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


		testGroup1.addMember(u1, testGroup1);
		testGroup1.addMember(u2, testGroup1);
		testGroup1.addMember(u3, testGroup1);
		testGroup1.addMember(u4, testGroup1);
		testGroup1.addMember(u5, testGroup1);

		ArrayList<membership> memList = new ArrayList<membership>();
		
		membership m1 = new membership(u1,testGroup1);
		membership m2 = new membership(u2,testGroup1);
		membership m3 = new membership(u3,testGroup1);
		membership m4 = new membership(u4,testGroup1);
		membership m5 = new membership(u5,testGroup1);

		
		memList.add(m1);
		memList.add(m2);
		memList.add(m3);
		memList.add(m4);
		memList.add(m5);

		assertEquals(memList, testGroup1.getMembers());
	}

	@Test
	void getPostsTest() {
													// FIXME :: Post Class Needs Added First //
	}


}
