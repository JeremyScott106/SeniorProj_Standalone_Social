package Project;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.Test;

class ComparatorTest {

	
	@Test
	void testSortCategoriesByName() {
		
		category c1 = new category("Sports");
		category c2 = new category("Games");
		category c3 = new category("Video Games");
		category c4 = new category("Foods");
		category c5 = new category("Apples");
		
		ArrayList<category> actual = new ArrayList<category>();
		
		actual.add(c1);
		actual.add(c2);
		actual.add(c3);
		actual.add(c4);
		actual.add(c5);
		
		String expected[] = {"Apples", "Foods", "Games", "Sports", "Video Games"};
		
		Collections.sort(actual, new SortCategoriesByName());
		
		boolean namesMatch = true;
		
		for (int i = 0 ; i < actual.size() ; i++) {
			
			if (!actual.get(i).getName().equals(expected[i])) {
				namesMatch = false;
				break;
			}
		}
		
		assertEquals(true, namesMatch);
		
	}
	
	
	@Test
	void testSortGroupsByName() {
		
		Group g1 = new Group("Sports");
		Group g2 = new Group("Games");
		Group g3 = new Group("Video Games");
		Group g4 = new Group("Foods");
		Group g5 = new Group("Apples");
		
		ArrayList<Group> actual = new ArrayList<Group>();
		
		actual.add(g1);
		actual.add(g2);
		actual.add(g3);
		actual.add(g4);
		actual.add(g5);
		
		String expected[] = {"Apples", "Foods", "Games", "Sports", "Video Games"};
		
		Collections.sort(actual, new SortGroupsByName());
		
		boolean namesMatch = true;
		
		for (int i = 0 ; i < actual.size() ; i++) {
			
			if (!actual.get(i).getGroupName().equals(expected[i])) {
				namesMatch = false;
				break;
			}
		}
		
		assertEquals(true, namesMatch);
		
	}
	
	
	@Test
	void testSortUsersByName() {
		
		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		User u2 = new User("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		User u5 = new User("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");
		
		ArrayList<User> actual = new ArrayList<User>();
		
		actual.add(u1);
		actual.add(u2);
		actual.add(u3);
		actual.add(u4);
		actual.add(u5);
		
		String expected[] = {"WestCarolina", "theWiz", "LegalTrouble", "IDK", "jackster3"};
		
		Collections.sort(actual, new SortUsersByName());
		
		boolean namesMatch = true;
		
		for (int i = 0; i < actual.size(); i++) {
			
			if (!actual.get(i).getId().equals(expected[i])) {
				namesMatch = false;
				break;
			}
		}
		
		assertEquals(true, namesMatch);
		
	}
	
	
	@Test
	void testSortUsersByUsername() {
		
		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		User u2 = new User("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		User u5 = new User("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");
		
		ArrayList<User> actual = new ArrayList<User>();
		
		actual.add(u1);
		actual.add(u2);
		actual.add(u3);
		actual.add(u4);
		actual.add(u5);
		
		String expected[] = {"IDK", "jackster3", "LegalTrouble", "theWiz", "WestCarolina"};
		
		Collections.sort(actual, new SortUsersByUsername());
		
		boolean namesMatch = true;
		
		for (int i = 0; i < actual.size(); i++) {
			
			if (!actual.get(i).getId().equals(expected[i])) {
				namesMatch = false;
				break;
			}
		}
		
		assertEquals(true, namesMatch);
		
	}

}
