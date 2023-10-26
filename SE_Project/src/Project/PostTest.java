package Project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;


public class PostTest {
		
	//Testing Constructor and getGroup//
	@Test
	void getGroupTest() {
		Group testGroup = new Group("Standard Name");
		User testUser = new User("Bob", "ID", "pw", "11/11/2001", "Valdosta", "GA");
		Post testPost1 = new Post (testUser, testGroup, 1, "I'm posting.");

		assertEquals(testGroup, testPost1.getGroup());
	}
	
	
	//Testing getUser//
	@Test
	void getUserTest() {
		Group testGroup = new Group("Standard Name");
		User testUser = new User("Bob", "ID", "pw", "11/11/2001", "Valdosta", "GA");
		Post testPost1 = new Post (testUser, testGroup, 1, "I'm posting.");
		
		assertEquals(testUser, testPost1.getUser());
		}
	//Tests getting the ID
	@Test
	void getIDTest() {
		Group testGroup = new Group("Standard Name");
		User testUser = new User("Bob", "ID", "pw", "11/11/2001", "Valdosta", "GA");
		Post testPost1 = new Post (testUser, testGroup, 1, "I'm posting.");
		
		assertEquals(1, testPost1.getId());
		}
	
	//Tests getting the postbody
	@Test
	void getPostBodyTest() {
		Group testGroup = new Group("Standard Name");
		User testUser = new User("Bob", "ID", "pw", "11/11/2001", "Valdosta", "GA");
		Post testPost1 = new Post (testUser, testGroup, 1, "I'm posting.");
		
		assertEquals("I'm posting.", testPost1.getPostBody());
		}
	
	//Tests getting the score
	@Test
	void getScoreTest() {
		Group testGroup = new Group("Standard Name");
		User testUser = new User("Bob", "ID", "pw", "11/11/2001", "Valdosta", "GA");
		Post testPost1 = new Post (testUser, testGroup, 1, "I'm posting.");
		
		assertEquals(0, testPost1.getScore());
		}
	
	//Not totally sure how to check this without having us physically just check the time printed, test should always fail because they are different times
	@Test
	void getTimeTest() {
		Group testGroup = new Group("Standard Name");
		User testUser = new User("Bob", "ID", "pw", "11/11/2001", "Valdosta", "GA");
		java.util.Date timeTest = new java.util.Date();;
		Post testPost1 = new Post (testUser, testGroup, 1, "I'm posting.");
		
		assertEquals(timeTest, testPost1.getTime());
		}
	
	//Tests adding the score, end score should be positive 1
	@Test
	void addScoreTest() {
		Group testGroup = new Group("Standard Name");
		User testUser = new User("Bob", "ID", "pw", "11/11/2001", "Valdosta", "GA");
		Post testPost1 = new Post (testUser, testGroup, 1, "I'm posting.");
		testPost1.addScore();
		
		assertEquals(1, testPost1.getScore());
		}
	
	//Tests subtracting from the score, end should be negative 1
	@Test
	void subScoreTest() {
		Group testGroup = new Group("Standard Name");
		User testUser = new User("Bob", "ID", "pw", "11/11/2001", "Valdosta", "GA");
		Post testPost1 = new Post (testUser, testGroup, 1, "I'm posting.");
		testPost1.subScore();
		
		assertEquals(-1, testPost1.getPostBody());
		}
	
}
