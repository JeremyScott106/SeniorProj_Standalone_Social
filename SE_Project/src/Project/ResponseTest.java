package Project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ResponseTest {
	
	//Tests getting the ID of the parent post.
	@Test
	void getPaternalIDTest() {
		Group testGroup1 = new Group("Standard Name");
		User testUser1 = new User("Bob", "ID", "pw");
		User testUser2 = new User("Bob", "ID", "pw");
		Post testPost1 = new Post (testUser1, testGroup1, 1, "I'm posting.");
		Response testResponse1 = new Response(testUser2, testGroup1, 2, "I disagree.", testPost1);
		
		assertEquals(1, testResponse1.getPaternalPostID());
	}

}
