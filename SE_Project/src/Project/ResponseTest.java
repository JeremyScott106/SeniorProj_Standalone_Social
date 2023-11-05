package Project;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class ResponseTest {
	
	//Tests getting the responseBody of the response object.
	@Test
	void getResponseBody() {
		Group testGroup1 = new Group("Standard Name");
		User testUser2 = new User("Bob", "ID", "pw", "11/11/2001", "Valdosta", "GA");
		membership m = new membership(testUser2, testGroup1);
		Response testResponse1 = new Response(m, "I disagree.");
		
		assertEquals("I disagree.", testResponse1.getResponseBody());
	}

}
