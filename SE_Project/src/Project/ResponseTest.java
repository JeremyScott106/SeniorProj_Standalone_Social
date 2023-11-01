package Project;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class ResponseTest {
	
	//Tests getting the ID of the parent post.
	@Test
	void getUser() {
		Group testGroup1 = new Group("Standard Name");
		User testUser2 = new User("Bob", "ID", "pw", "11/11/2001", "Valdosta", "GA");
		Response testResponse1 = new Response(testUser2, testGroup1, "I disagree.");
		
		assertEquals(testUser2, testResponse1.getUser());
	}
	
	//Tests getting the ID of the parent post.
	@Test
	void getGroup() {
		Group testGroup1 = new Group("Standard Name");
		User testUser2 = new User("Bob", "ID", "pw", "11/11/2001", "Valdosta", "GA");
		Response testResponse1 = new Response(testUser2, testGroup1, "I disagree.");
		
		assertEquals(testGroup1, testResponse1.getGroup());
	}
	
	
	
	//Tests getting the ID of the parent post.
	@Test
	void getResponseBody() {
		Group testGroup1 = new Group("Standard Name");
		User testUser2 = new User("Bob", "ID", "pw", "11/11/2001", "Valdosta", "GA");
		Response testResponse1 = new Response(testUser2, testGroup1, "I disagree.");
		
		assertEquals("I disagree.", testResponse1.getResponceBody());
	}

}
