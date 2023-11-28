package Project;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class ResponseTest {
	
	//Tests getting the responseBody of the response object.
	@Test
	void getMemberTest() {
		Group testGroup1 = new Group("Standard Name");
		User testUser2 = new User("Bob", "ID", "pw", "11/11/2001", "Valdosta", "GA");
		membership m = new membership(testUser2, testGroup1);
		Response testResponse1 = new Response(m, "I disagree.", 1);
		
		assertEquals(m.getUser(), testResponse1.getUser());
	}
	
	//Tests getting the responseBody of the response object.
	@Test
	void getResponseBodyTest() {
		Group testGroup1 = new Group("Standard Name");
		User testUser2 = new User("Bob", "ID", "pw", "11/11/2001", "Valdosta", "GA");
		membership m = new membership(testUser2, testGroup1);
		Response testResponse1 = new Response(m, "I disagree.", 1);
		
		assertEquals("I disagree.", testResponse1.getPostBody());
	}
	
	//Tests getting the responseBody of the response object.
	@Test
	void getParentalId() {
		Group testGroup1 = new Group("Standard Name");
		User testUser2 = new User("Bob", "ID", "pw", "11/11/2001", "Valdosta", "GA");
		membership m = new membership(testUser2, testGroup1);
		Response testResponse1 = new Response(m, "I disagree.", 1);
		
		assertEquals(1, testResponse1.getParentalId());
	}
	
	@Test
	void compareToTest_Success() {
		Group testGroup = new Group("Standard Name");
		User testUser = new User("Bob", "ID", "pw", "11/11/2001", "Valdosta", "GA");
		membership m = new membership(testUser, testGroup);
		Response r1 = new Response(m, "noooo", 1);
		
		assertEquals(1, r1.compareTo(r1));
	}
	
	@Test
	void compareToTest_Failure() {
		Group testGroup = new Group("Standard Name");
		User testUser = new User("Bob", "ID", "pw", "11/11/2001", "Valdosta", "GA");
		membership m = new membership(testUser, testGroup);
		Response r1 = new Response(m, "noooo", 1);
		Response r2 = new Response(m, "nooofgo", 0);
		
		assertEquals(0, r1.compareTo(r2));
	}

}
