package Project;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;


public class PostTest {
		
	//Testing Constructor and getGroup//
	@Test
	void getGroupTest() {
		Group testGroup = new Group("Standard Name");
		User testUser = new User("Bob", "ID", "pw", "11/11/2001", "Valdosta", "GA");
		ArrayList<Response> r = new ArrayList<>();
		membership m = new membership(testUser, testGroup);

		Response r1 = new Response(m, "noooo");
		Response r2 = new Response(m, "no0000ooo");
		r.add(r1);
		r.add(r2);
		Post testPost1 = new Post (m, "I'm posting.");

		assertEquals(testGroup, testPost1.getGroup());
	}
	
	//Testing getUser//
	@Test
	void getUserTest() {
		Group testGroup = new Group("Standard Name");
		User testUser = new User("Bob", "ID", "pw", "11/11/2001", "Valdosta", "GA");
		ArrayList<Response> r = new ArrayList<>();
		membership m = new membership(testUser, testGroup);

		Response r1 = new Response(m, "noooo");
		Response r2 = new Response(m, "no0000ooo");
		r.add(r1);
		r.add(r2);
		Post testPost1 = new Post (m, "I'm posting.");
		
		assertEquals(testUser, testPost1.getUser());
	}
	
	//Tests getting the postBody
	@Test
	void getPostBodyTest() {
		Group testGroup = new Group("Standard Name");
		User testUser = new User("Bob", "ID", "pw", "11/11/2001", "Valdosta", "GA");
		ArrayList<Response> r = new ArrayList<>();
		membership m = new membership(testUser, testGroup);

		Response r1 = new Response(m, "noooo");
		Response r2 = new Response(m, "no0000ooo");
		r.add(r1);
		r.add(r2);
		Post testPost1 = new Post (m, "I'm posting.");


		assertEquals("I'm posting.", testPost1.getPostBody());
	}
	
	//Tests getting the response
	@Test
	void getResponse() {
		Group testGroup = new Group("Standard Name");
		User testUser = new User("Bob", "ID", "pw", "11/11/2001", "Valdosta", "GA");
		membership m = new membership(testUser, testGroup);

				
		Post testPost1 = new Post(m, "I'm posting.");
		membership m1 = new membership(testUser, testGroup);
		
		Response r = new Response(m1, "n");
		
		ArrayList<Response> expected = new ArrayList<>();
		expected.add(r);


		testPost1.addResponse(r);
				
		ArrayList<Response> r1 =  testPost1.getResponse();
		
		assertEquals(expected, r1);
	}
	
	//Tests getting the score
	@Test
	void getScoreTest() {
		Group testGroup = new Group("Standard Name");
		User testUser = new User("Bob", "ID", "pw", "11/11/2001", "Valdosta", "GA");
		ArrayList<Response> r = new ArrayList<>();
		membership m = new membership(testUser, testGroup);

		Response r1 = new Response(m, "noooo");
		Response r2 = new Response(m, "no0000ooo");
		r.add(r1);
		r.add(r2);
		Post testPost1 = new Post (m, "I'm posting.");
		
		assertEquals(0, testPost1.getScore());
	}
	
	//Not totally sure how to check this without having us physically just check the time printed, test should always fail because they are different times
	@Test
	void getTimeTest() {
		Group testGroup = new Group("Standard Name");
		User testUser = new User("Bob", "ID", "pw", "11/11/2001", "Valdosta", "GA");
		java.util.Date timeTest = new java.util.Date();;
		ArrayList<Response> r = new ArrayList<>();
		membership m = new membership(testUser, testGroup);

		Response r1 = new Response(m, "noooo");
		Response r2 = new Response(m, "no0000ooo");
		r.add(r1);
		r.add(r2);
		Post testPost1 = new Post (m, "I'm posting.");
		
		assertEquals(timeTest, testPost1.getTime());
	}
	
	//Tests adding the score, end score should be positive 1
	@Test
	void addScoreTest() {
		Group testGroup = new Group("Standard Name");
		User testUser = new User("Bob", "ID", "pw", "11/11/2001", "Valdosta", "GA");
		ArrayList<Response> r = new ArrayList<>();
		membership m = new membership(testUser, testGroup);

		Response r1 = new Response(m, "noooo");
		Response r2 = new Response(m, "no0000ooo");
		r.add(r1);
		r.add(r2);
		Post testPost1 = new Post (m, "I'm posting.");
		testPost1.addScore();
		
		assertEquals(1, testPost1.getScore());
	}
	
	//Tests subtracting from the score, end should be negative 1
	@Test
	void subScoreTest() {
		Group testGroup = new Group("Standard Name");
		User testUser = new User("Bob", "ID", "pw", "11/11/2001", "Valdosta", "GA");
		ArrayList<Response> r = new ArrayList<>();
		membership m = new membership(testUser, testGroup);

		Response r1 = new Response(m, "noooo");
		Response r2 = new Response(m, "no0000ooo");
		r.add(r1);
		r.add(r2);
		Post testPost1 = new Post (m, "I'm posting.");
		testPost1.subScore();
		
		assertEquals(-1, testPost1.getScore());
	}
	
}
