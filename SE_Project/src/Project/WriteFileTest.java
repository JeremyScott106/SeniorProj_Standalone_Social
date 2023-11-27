package Project;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

class WriteFileTest {
	
	
	private String getFileData(ArrayList<String> fileNames) {

		String totalStr = "";
		
		for (String fileName : fileNames) {

			File dataFile = new File(fileName);
			try {
	
				Scanner reader = new Scanner(dataFile);
	
				while (reader.hasNextLine()) {
	
					String next = reader.nextLine();
					totalStr += next + "\n";
	
	//				if (next.equals("@END")) {
	//					totalStr += "\n";
	//				}
	
				}
	
				reader.close();
	
			} 
			catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
		}

		return totalStr;

	}
	
	
	@Test
	void testWriteFile_Admins() {
		
		Admin a1 = new Admin("Jeremy", "unth!nk@b1e", "password#3", "04/27/1992", "New York City", "New York", "11/11/2011");
		Admin a2 = new Admin("Grayson", "theGr@yS0n", "r@inB0wR0@DL0s3r", "03/09/2012", "Macon", "Georgia", "03/09/2011");
		Admin a3 = new Admin("Ryan", "RKScandrol", "N0tT311iN", "08/08/1999", "Pensacola", "Florida", "07/07/2010");
		Admin a4 = new Admin("John", "J0hnW!ck", "n0tth3D0g", "01/01/0001", "Boring", "Oregon", "12/29/2020");
		Admin a5 = new Admin("Himanshu", "the01dM@n", "L3t5D0Th!s", "07/23/1836", "Climax", "Michigan", "12/09/2007");
		
		SystemManager manager = new SystemManager();
		
		manager.addAdmin(a1);
		manager.addAdmin(a2);
		manager.addAdmin(a3);
		manager.addAdmin(a4);
		manager.addAdmin(a5);
		
		ArrayList<String> fileNames = new ArrayList<String>();
		fileNames.add(".\\SE_Project\\src\\Project\\TextFiles\\WriteFile_Test\\WriteFile_Test_Admins.txt");
		
		try {
			
			WriteFile.writeFile(manager, fileNames);
			
		} 
		catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		
		String actual = getFileData(fileNames);
		String expected = "";

		for (Admin a : manager.getAdmins_Alphabetically_ByUsername()) {

			expected = expected.concat(a.getAdminWriteData());

		}

		assertEquals(expected, actual);
		
		
	}

	@Test
	void testWriteFile_Users() {
		
		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		User u2 = new User("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		User u5 = new User("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");
		
		SystemManager manager = new SystemManager();
		
		manager.addUser(u1);
		manager.addUser(u2);
		manager.addUser(u3);
		manager.addUser(u4);
		manager.addUser(u5);
		
		ArrayList<String> fileNames = new ArrayList<String>();
		fileNames.add(".\\SE_Project\\src\\Project\\TextFiles\\WriteFile_Test\\WriteFile_Test_Users.txt");
		
		try {
			
			WriteFile.writeFile(manager, fileNames);
			
		} 
		catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		
		
		String actual = getFileData(fileNames);
		String expected = "";

		for (User u : manager.getUsers_Alphabetically_ByUsername()) {

			expected = expected.concat(u.getUserWriteData());

		}

		assertEquals(expected, actual);
		
	}
	
	
	@Test
	void testWriteFile_Categories() {
		
		SystemManager manager = new SystemManager();
		
		category c1 = new category("Sports");
		category c2 = new category("Foods");
		category c3 = new category("Games");
		
		manager.addCategory(c1);
		manager.addCategory(c2);
		manager.addCategory(c3);
		
		ArrayList<String> fileNames = new ArrayList<String>();
		fileNames.add(".\\SE_Project\\src\\Project\\TextFiles\\WriteFile_Test\\WriteFile_Test_Categories.txt");
		
		try {
			
			WriteFile.writeFile(manager, fileNames);
			
		} 
		catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		
		
		String actual = getFileData(fileNames);
		String expected = "";

		for (category c : manager.getCategories_Alphabetically()) {

			expected = expected.concat(c.getCategoryWriteData());

		}

		assertEquals(expected, actual);
		
	}
	
	
	@Test
	void testWriteFile_Groups() {
		
		SystemManager manager = new SystemManager();
		
		category c1 = new category("Sports");
		Group g1 = new Group("Football");
		Group g2 = new Group("Soccer");
		c1.addGroup(g1);
		c1.addGroup(g2);
		
		category c2 = new category("Foods");
		Group g3 = new Group("Pizza");
		Group g4 = new Group("Tacos");
		Group g5 = new Group("Steak");
		c2.addGroup(g3);
		c2.addGroup(g4);
		c2.addGroup(g5);
		
		manager.addCategory(c1);
		manager.addCategory(c2);
		
		ArrayList<String> fileNames = new ArrayList<String>();
		fileNames.add(".\\SE_Project\\src\\Project\\TextFiles\\WriteFile_Test\\WriteFile_Test_Groups.txt");
		
		
		try {
			
			WriteFile.writeFile(manager, fileNames);
			
		} 
		catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		
		
		String actual = getFileData(fileNames);

		String expected = "";

		for (category c : manager.getCategories_Alphabetically()) {

			for (Group g : c.getGroupsAlphabetically()) {

				expected += g.getGroupWriteData(c.getName());

			}

		}

		assertEquals(expected, actual);
		
		
	}
	
	
	@Test
	void testWriteFile_Memberships() {

		SystemManager manager = new SystemManager();

		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		User u2 = new User("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		User u5 = new User("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");

		category c1 = new category("Sports");
		Group g1 = new Group("Football");
		Group g2 = new Group("Soccer");

		membership m1 = new membership(u1, g1);
		membership m2 = new membership(u2, g1);
		membership m3 = new membership(u3, g1);
		membership m4 = new membership(u4, g2);
		membership m5 = new membership(u5, g2);

		g1.addMember(m1);
		g1.addMember(m2);
		g1.addMember(m3);
		g2.addMember(m4);
		g2.addMember(m5);

		c1.addGroup(g1);
		c1.addGroup(g2);

		manager.addUser(u1);
		manager.addUser(u2);
		manager.addUser(u3);
		manager.addUser(u4);
		manager.addUser(u5);
		manager.addCategory(c1);

		ArrayList<String> fileNames = new ArrayList<String>();
		fileNames.add(".\\SE_Project\\src\\Project\\TextFiles\\WriteFile_Test\\WriteFile_Test_Memberships.txt");

		try {

			WriteFile.writeFile(manager, fileNames);

		} 
		catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		
		
		String actual = getFileData(fileNames);

		String expected = "";

		for (membership m : manager.getAllMemberships()) {

			expected += m.getMembershipWriteData();

		}

		assertEquals(expected, actual);

	}


	@Test
	void testWriteFile_Posts() {

		SystemManager manager = new SystemManager();

		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		User u2 = new User("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		User u5 = new User("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");

		category c1 = new category("Sports");
		Group g1 = new Group("Football");
		Group g2 = new Group("Soccer");

		membership m1 = new membership(u1, g1);
		membership m2 = new membership(u2, g1);
		membership m3 = new membership(u3, g1);
		membership m4 = new membership(u4, g2);
		membership m5 = new membership(u5, g2);

		Post p1 = new Post(m1, "Test#1", "testing", 0);
		Post p2 = new Post(m2, "Test#2", "testing", 1);
		Post p3 = new Post(m3, "Test#3", "testing", 2);
		Post p4 = new Post(m4, "Test#4", "testing", 3);
		Post p5 = new Post(m5, "Test#5", "testing", 4);

		g1.addMember(m1);
		g1.addMember(m2);
		g1.addMember(m3);
		g2.addMember(m4);
		g2.addMember(m5);

		g1.addNewPost(p1);
		g1.addNewPost(p2);
		g1.addNewPost(p3);
		g2.addNewPost(p4);
		g2.addNewPost(p5);

		c1.addGroup(g1);
		c1.addGroup(g2);

		manager.addUser(u1);
		manager.addUser(u2);
		manager.addUser(u3);
		manager.addUser(u4);
		manager.addUser(u5);
		manager.addCategory(c1);

		ArrayList<String> fileNames = new ArrayList<String>();
		fileNames.add(".\\SE_Project\\src\\Project\\TextFiles\\WriteFile_Test\\WriteFile_Test_Posts.txt");

		try {

			WriteFile.writeFile(manager, fileNames);

		} 
		catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		
		
		String actual = getFileData(fileNames);

		String expected = "";

		for (Post p : manager.getAllPost()) {

			expected += p.getPostWriteData();

		}

		assertEquals(expected, actual);

	}
	
	
	@Test
	void testWriteFile_Responses() {

		SystemManager manager = new SystemManager();

		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		User u2 = new User("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		User u5 = new User("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");

		category c1 = new category("Sports");
		Group g1 = new Group("Football");
		Group g2 = new Group("Soccer");

		membership m1 = new membership(u1, g1);
		membership m2 = new membership(u2, g1);
		membership m3 = new membership(u3, g1);
		membership m4 = new membership(u4, g2);
		membership m5 = new membership(u5, g2);

		Post p1 = new Post(m1, "Test#1", "testing", 0);
		Post p2 = new Post(m2, "Test#2", "testing", 1);
		Post p3 = new Post(m3, "Test#3", "testing", 2);
		Post p4 = new Post(m4, "Test#4", "testing", 3);
		Post p5 = new Post(m5, "Test#5", "testing", 4);
		
		Response r1 = new Response(m1, "r1", 0);
		Response r2 = new Response(m2, "r1", 1);
		Response r3 = new Response(m3, "r1", 2);
		Response r4 = new Response(m4, "r1", 3);
		Response r5 = new Response(m5, "r1", 4);
		
		p1.addResponse(r1);
		p2.addResponse(r2);
		p3.addResponse(r3);
		p4.addResponse(r4);
		p5.addResponse(r5);

		g1.addMember(m1);
		g1.addMember(m2);
		g1.addMember(m3);
		g2.addMember(m4);
		g2.addMember(m5);

		g1.addNewPost(p1);
		g1.addNewPost(p2);
		g1.addNewPost(p3);
		g2.addNewPost(p4);
		g2.addNewPost(p5);

		c1.addGroup(g1);
		c1.addGroup(g2);

		manager.addUser(u1);
		manager.addUser(u2);
		manager.addUser(u3);
		manager.addUser(u4);
		manager.addUser(u5);
		manager.addCategory(c1);

		ArrayList<String> fileNames = new ArrayList<String>();
		fileNames.add(".\\SE_Project\\src\\Project\\TextFiles\\WriteFile_Test\\WriteFile_Test_Responses.txt");

		try {

			WriteFile.writeFile(manager, fileNames);

		} 
		catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		
		
		String actual = getFileData(fileNames);

		String expected = "";

		for (Post p : manager.getAllPost()) {

			for (Response r : p.getResponse()) {
				expected += r.getResponseWriteData();
			}

		}

		assertEquals(expected, actual);

	}
	
	
	
	
	
	@Test
	void testAddAdminToFile() {
		
		Admin a1 = new Admin("Jeremy", "unth!nk@b1e", "password#3", "04/27/1992", "New York City", "New York", "11/11/2011");
		Admin a2 = new Admin("Grayson", "theGr@yS0n", "r@inB0wR0@DL0s3r", "03/09/2012", "Macon", "Georgia", "03/09/2011");
		Admin a3 = new Admin("Ryan", "RKScandrol", "N0tT311iN", "08/08/1999", "Pensacola", "Florida", "07/07/2010");
		Admin a4 = new Admin("John", "J0hnW!ck", "n0tth3D0g", "01/01/0001", "Boring", "Oregon", "12/29/2020");
		Admin a5 = new Admin("Himanshu", "the01dM@n", "L3t5D0Th!s", "07/23/1836", "Climax", "Michigan", "12/09/2007");
		
		SystemManager manager = new SystemManager();
		
		manager.addAdmin(a1);
		manager.addAdmin(a2);
		manager.addAdmin(a3);
		manager.addAdmin(a4);
		manager.addAdmin(a5);
		
		ArrayList<String> fileNames = new ArrayList<String>();
		fileNames.add(".\\SE_Project\\src\\Project\\TextFiles\\WriteFile_Test\\WriteFile_Test_AddAdmin.txt");
		
		Admin test = new Admin("Testing", "AddTest", "Testing1234", "10/10/10", "Valdosta", "Georgia", "04/02/1978");
		
		try {
			WriteFile.writeFile(manager, fileNames);
			
			WriteFile.addAdminToFile(test, fileNames.get(0));
			
		} 
		catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		
		
		String actual = getFileData(fileNames);

		String expected = "";

		for (Admin a : manager.getAdmins_Alphabetically_ByUsername()) {

			expected += a.getAdminWriteData();

		}
		expected += test.getAdminWriteData();

		assertEquals(expected, actual);
		
	}
	
	
	@Test
	void testAddUserToFile() {
		
		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		User u2 = new User("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		User u5 = new User("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");
		
		SystemManager manager = new SystemManager();
		
		manager.addUser(u1);
		manager.addUser(u2);
		manager.addUser(u3);
		manager.addUser(u4);
		manager.addUser(u5);
		
		ArrayList<String> fileNames = new ArrayList<String>();
		fileNames.add(".\\SE_Project\\src\\Project\\TextFiles\\WriteFile_Test\\WriteFile_Test_AddUser.txt");
		
		User test = new User("Testing", "AddTest", "Testing1234", "10/10/10", "Valdosta", "Georgia", "04/02/1978");
		
		try {
			
			WriteFile.writeFile(manager, fileNames);
			
			WriteFile.addUserToFile(test, fileNames.get(0));
			
		} 
		catch (IOException e) {
			
			e.printStackTrace();
			fail();
		}
		
		
		String actual = getFileData(fileNames);
		String expected = "";

		for (User u : manager.getUsers_Alphabetically_ByUsername()) {

			expected = expected.concat(u.getUserWriteData());

		}
		expected += test.getUserWriteData();

		assertEquals(expected, actual);
		
	}
	
	
	@Test
	void testAddCategoryToFile() {
		
		SystemManager manager = new SystemManager();
		
		category c1 = new category("Sports");
		category c2 = new category("Foods");
		category c3 = new category("Games");
		
		manager.addCategory(c1);
		manager.addCategory(c2);
		manager.addCategory(c3);
		
		ArrayList<String> fileNames = new ArrayList<String>();
		fileNames.add(".\\SE_Project\\src\\Project\\TextFiles\\WriteFile_Test\\WriteFile_Test_AddCategory.txt");
		
		category test = new category("Tests");
		
		try {
			
			WriteFile.writeFile(manager, fileNames);
			
			WriteFile.addCategoryToFile(test, fileNames.get(0));
			
			assertEquals(true, true);
			
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		
		
		String actual = getFileData(fileNames);
		String expected = "";

		for (category c : manager.getCategories_Alphabetically()) {

			expected = expected.concat(c.getCategoryWriteData());

		}
		expected += test.getCategoryWriteData();

		assertEquals(expected, actual);
		
	}
	
	
	@Test
	void testAddGroupToFile() {
		
		SystemManager manager = new SystemManager();
		
		category c1 = new category("Sports");
		Group g1 = new Group("Football");
		Group g2 = new Group("Soccer");
		c1.addGroup(g1);
		c1.addGroup(g2);
		
		category c2 = new category("Foods");
		Group g3 = new Group("Pizza");
		Group g4 = new Group("Tacos");
		Group g5 = new Group("Steak");
		c2.addGroup(g3);
		c2.addGroup(g4);
		c2.addGroup(g5);
		
		manager.addCategory(c1);
		manager.addCategory(c2);
		
		ArrayList<String> fileNames = new ArrayList<String>();
		fileNames.add(".\\SE_Project\\src\\Project\\TextFiles\\WriteFile_Test\\WriteFile_Test_AddGroup.txt");
		
		Group test = new Group("Donuts");
		
		try {
			
			WriteFile.writeFile(manager, fileNames);
			
			WriteFile.addGroupToFile(test, fileNames.get(0), c2.getName());
			
		} 
		catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		
		
		String actual = getFileData(fileNames);

		String expected = "";

		for (category c : manager.getCategories_Alphabetically()) {

			for (Group g : c.getGroupsAlphabetically()) {

				expected += g.getGroupWriteData(c.getName());

			}

		}
		expected += test.getGroupWriteData(c2.getName());

		assertEquals(expected, actual);
		
	}
	
	
	@Test
	void testAddMembershipToFile() {
		
		SystemManager manager = new SystemManager();

		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		User u2 = new User("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		User u5 = new User("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");

		category c1 = new category("Sports");
		Group g1 = new Group("Football");
		Group g2 = new Group("Soccer");

		membership m1 = new membership(u1, g1);
		membership m2 = new membership(u2, g1);
		membership m3 = new membership(u3, g1);
		membership m4 = new membership(u4, g2);
		membership m5 = new membership(u5, g2);

		g1.addMember(m1);
		g1.addMember(m2);
		g1.addMember(m3);
		g2.addMember(m4);
		g2.addMember(m5);

		c1.addGroup(g1);
		c1.addGroup(g2);

		manager.addUser(u1);
		manager.addUser(u2);
		manager.addUser(u3);
		manager.addUser(u4);
		manager.addUser(u5);
		manager.addCategory(c1);

		ArrayList<String> fileNames = new ArrayList<String>();
		fileNames.add(".\\SE_Project\\src\\Project\\TextFiles\\WriteFile_Test\\WriteFile_Test_AddMembership.txt");
		
		membership test = new membership(u1, g2);

		try {

			WriteFile.writeFile(manager, fileNames);
			
			WriteFile.addMembershipToFile(test, fileNames.get(0));

		} 
		catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		
		
		String actual = getFileData(fileNames);

		String expected = "";

		for (membership m : manager.getAllMemberships()) {

			expected += m.getMembershipWriteData();

		}
		expected += test.getMembershipWriteData();

		assertEquals(expected, actual);

	}
	
	
	@Test
	void testAddPostToFile() {
		
		
		SystemManager manager = new SystemManager();

		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		User u2 = new User("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		User u5 = new User("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");

		category c1 = new category("Sports");
		Group g1 = new Group("Football");
		Group g2 = new Group("Soccer");

		membership m1 = new membership(u1, g1);
		membership m2 = new membership(u2, g1);
		membership m3 = new membership(u3, g1);
		membership m4 = new membership(u4, g2);
		membership m5 = new membership(u5, g2);

		Post p1 = new Post(m1, "Test#1", "testing", 0);
		Post p2 = new Post(m2, "Test#2", "testing", 1);
		Post p3 = new Post(m3, "Test#3", "testing", 2);
		Post p4 = new Post(m4, "Test#4", "testing", 3);
		Post p5 = new Post(m5, "Test#5", "testing", 4);

		g1.addMember(m1);
		g1.addMember(m2);
		g1.addMember(m3);
		g2.addMember(m4);
		g2.addMember(m5);

		g1.addNewPost(p1);
		g1.addNewPost(p2);
		g1.addNewPost(p3);
		g2.addNewPost(p4);
		g2.addNewPost(p5);

		c1.addGroup(g1);
		c1.addGroup(g2);

		manager.addUser(u1);
		manager.addUser(u2);
		manager.addUser(u3);
		manager.addUser(u4);
		manager.addUser(u5);
		manager.addCategory(c1);

		ArrayList<String> fileNames = new ArrayList<String>();
		fileNames.add(".\\SE_Project\\src\\Project\\TextFiles\\WriteFile_Test\\WriteFile_Test_AddPost.txt");
		
		Post test = new Post(m1, "Test#6", "Testing", 5);

		try {

			WriteFile.writeFile(manager, fileNames);
			
			WriteFile.addPostToFile(test, fileNames.get(0));

		} 
		catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		
		
		String actual = getFileData(fileNames);

		String expected = "";

		for (Post p : manager.getAllPost()) {

			expected += p.getPostWriteData();

		}
		expected += test.getPostWriteData();

		assertEquals(expected, actual);
	}
	
	
	
	
	@Test
	void testRemoveAdminFromFile() {
		
		Admin a1 = new Admin("Jeremy", "unth!nk@b1e", "password#3", "04/27/1992", "New York City", "New York", "11/11/2011");
		Admin a2 = new Admin("Grayson", "theGr@yS0n", "r@inB0wR0@DL0s3r", "03/09/2012", "Macon", "Georgia", "03/09/2011");
		Admin a3 = new Admin("Ryan", "RKScandrol", "N0tT311iN", "08/08/1999", "Pensacola", "Florida", "07/07/2010");
		Admin a4 = new Admin("John", "J0hnW!ck", "n0tth3D0g", "01/01/0001", "Boring", "Oregon", "12/29/2020");
		Admin a5 = new Admin("Himanshu", "the01dM@n", "L3t5D0Th!s", "07/23/1836", "Climax", "Michigan", "12/09/2007");
		
		SystemManager manager = new SystemManager();
		
		manager.addAdmin(a1);
		manager.addAdmin(a2);
		manager.addAdmin(a3);
		manager.addAdmin(a4);
		manager.addAdmin(a5);
		
		ArrayList<String> fileNames = new ArrayList<String>();
		fileNames.add(".\\SE_Project\\src\\Project\\TextFiles\\WriteFile_Test\\WriteFile_Test_RemoveAdmin.txt");

		
		try {
			
			WriteFile.writeFile(manager, fileNames);
			
			WriteFile.removeAdminFromFile(a4, fileNames.get(0));
			manager.getAdmins_Alphabetically_ByUsername().remove(a4);
			
		} 
		catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		
		
		String actual = getFileData(fileNames);
		String expected = "";

		for (Admin a : manager.getAdmins_Alphabetically_ByUsername()) {

			expected = expected.concat(a.getAdminWriteData());

		}

		assertEquals(expected, actual);
		
	}
	
	
	@Test
	void testRemoveUserFromFile() {
		
		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");
		User u2 = new User("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		User u3 = new User("Carol", "WestCarolina", "P!zzaHut", "10/10/1997", "Valdosta", "Georgia");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		User u5 = new User("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");
		
		SystemManager manager = new SystemManager();
		
		manager.addUser(u1);
		manager.addUser(u2);
		manager.addUser(u3);
		manager.addUser(u4);
		manager.addUser(u5);
		
		ArrayList<String> fileNames = new ArrayList<String>();
		fileNames.add(".\\SE_Project\\src\\Project\\TextFiles\\WriteFile_Test\\WriteFile_Test_RemoveUser.txt");
		
		try {
			
			WriteFile.writeFile(manager, fileNames);
			
			WriteFile.removeUserFromFile(u3, fileNames.get(0));
			manager.getUsers_Alphabetically_ByUsername().remove(u3);
						
		} 
		catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		
		
		String actual = getFileData(fileNames);
		String expected = "";

		for (User u : manager.getUsers_Alphabetically_ByUsername()) {

			expected = expected.concat(u.getUserWriteData());

		}

		assertEquals(expected, actual);
		
	}
	
	
	@Test
	void testRemoveCategoryFromFile() {
		
		SystemManager manager = new SystemManager();
		
		category c1 = new category("Sports");
		category c2 = new category("Foods");
		category c3 = new category("Games");
		
		manager.addCategory(c1);
		manager.addCategory(c2);
		manager.addCategory(c3);
		
		ArrayList<String> fileNames = new ArrayList<String>();
		fileNames.add(".\\SE_Project\\src\\Project\\TextFiles\\WriteFile_Test\\WriteFile_Test_RemoveCategory.txt");
		
		try {
			
			WriteFile.writeFile(manager, fileNames);
			
			WriteFile.removeCategoryFromFile(c1, fileNames.get(0));
			manager.getCategories_Alphabetically().remove(c1);
			
		} 
		catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		
		
		String actual = getFileData(fileNames);
		String expected = "";

		for (category c : manager.getCategories_Alphabetically()) {

			expected = expected.concat(c.getCategoryWriteData());

		}

		assertEquals(expected, actual);
		
	}
	
	
	@Test
	void testRemoveGroupFromFile() {
		
		SystemManager manager = new SystemManager();
		
		category c1 = new category("Sports");
		Group g1 = new Group("Football");
		Group g2 = new Group("Soccer");
		c1.addGroup(g1);
		c1.addGroup(g2);
		
		category c2 = new category("Foods");
		Group g3 = new Group("Pizza");
		Group g4 = new Group("Tacos");
		Group g5 = new Group("Steak");
		c2.addGroup(g3);
		c2.addGroup(g4);
		c2.addGroup(g5);
		
		manager.addCategory(c1);
		manager.addCategory(c2);
		
		ArrayList<String> fileNames = new ArrayList<String>();
		fileNames.add(".\\SE_Project\\src\\Project\\TextFiles\\WriteFile_Test\\WriteFile_Test_RemoveGroup.txt");
		
		
		try {
			
			WriteFile.writeFile(manager, fileNames);
			
			WriteFile.removeGroupFromFile(g2, fileNames.get(0), c1.getName());
			manager.getCategoryByName("Sports").getGroupsAlphabetically().remove(g2);
			
		} 
		catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		
		
		String actual = getFileData(fileNames);

		String expected = "";

		for (category c : manager.getCategories_Alphabetically()) {

			for (Group g : c.getGroupsAlphabetically()) {

				expected += g.getGroupWriteData(c.getName());

			}

		}

		assertEquals(expected, actual);
	}
	
	
	@Test

	void testUpdateGroupInFile() {

		
		SystemManager manager = new SystemManager();

		User u1 = new User("Jack", "jackster3", "HKb@wser!", "10/10/1997", "Valdosta", "Georgia");

		User u2 = new User("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		User u5 = new User("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");

		category c1 = new category("Sports");
		Group g1 = new Group("Football");
		Group g2 = new Group("Soccer");

		membership m1 = new membership(u1, g1);
		membership m2 = new membership(u2, g1);
		membership m3 = new membership(u3, g1);
		membership m4 = new membership(u4, g2);
		membership m5 = new membership(u5, g2);

		Post p1 = new Post(m1, "Test#1", "testing", 0);
		Post p2 = new Post(m2, "Test#2", "testing", 1);
		Post p3 = new Post(m3, "Test#3", "testing", 2);
		Post p4 = new Post(m4, "Test#4", "testing", 3);
		Post p5 = new Post(m5, "Test#5", "testing", 4);

		g1.addMember(m1);
		g1.addMember(m2);
		g1.addMember(m3);
		g2.addMember(m4);
		g2.addMember(m5);

		c1.addGroup(g1);
		c1.addGroup(g2);

		manager.addUser(u1);
		manager.addUser(u2);
		manager.addUser(u3);
		manager.addUser(u4);
		manager.addUser(u5);
		manager.addCategory(c1);

		ArrayList<String> fileNames = new ArrayList<String>();
		fileNames.add(".\\SE_Project\\src\\Project\\TextFiles\\WriteFile_Test\\WriteFile_Test_RemovePost.txt");

		try {

			WriteFile.writeFile(manager, fileNames);
			
			g2.getPost().remove(p4);
			WriteFile.removePostFromFile(p4, fileNames.get(0));

		} 
		catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		
		
		String actual = getFileData(fileNames);

		String expected = "";

		for (Post p : manager.getAllPost()) {

			expected += p.getPostWriteData();

		}

		assertEquals(expected, actual);
		
	}

	
	
	@Test
	void testUpdatePostInFile() {
		
		SystemManager manager = new SystemManager();

		User u2 = new User("Dan", "theWiz", "WartH@g77", "10/10/1997", "Valdosta", "Georgia");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t", "10/10/1997", "Valdosta", "Georgia");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble", "10/10/1997", "Valdosta", "Georgia");
		User u5 = new User("Ethan", "IDK", "WHY#5", "10/10/1997", "Valdosta", "Georgia");

		category c1 = new category("Sports");
		Group g1 = new Group("Football");
		Group g2 = new Group("Soccer");


		membership m2 = new membership(u2, g1);
		membership m3 = new membership(u3, g1);
		membership m4 = new membership(u4, g2);
		membership m5 = new membership(u5, g2);

		Post p2 = new Post(m2, "Test#2", "testing", 1);



		g1.addMember(m2);
		g1.addMember(m3);
		g2.addMember(m4);
		g2.addMember(m5);

		c1.addGroup(g1);
		c1.addGroup(g2);


		manager.addUser(u2);
		manager.addUser(u3);
		manager.addUser(u4);
		manager.addUser(u5);
		manager.addCategory(c1);

		ArrayList<String> fileNames = new ArrayList<String>();
		fileNames.add(".\\SE_Project\\src\\Project\\TextFiles\\WriteFile_Test\\WriteFile_Test_UpdatePost.txt");

		try {

			WriteFile.writeFile(manager, fileNames);
			
			String find = p2.getPostWriteData();
			p2.addScore();
			String replace = p2.getPostWriteData();
			WriteFile.updateGroupinFile(find, replace, fileNames.get(0));

		}
		
		catch (IOException e) {
			e.printStackTrace();
			fail();
		}


		c1.addGroup(g1);
		c1.addGroup(g2);
		
		category c2 = new category("Foods");
		Group g3 = new Group("Pizza");
		Group g4 = new Group("Tacos");
		Group g5 = new Group("Steak");
		c2.addGroup(g3);
		c2.addGroup(g4);
		c2.addGroup(g5);
		
		manager.addCategory(c1);
		manager.addCategory(c2);
		
		fileNames.add(".\\SE_Project\\src\\Project\\TextFiles\\WriteFile_Test\\WriteFile_Test_UpdateGroup.txt");
		
		
		
		try {
			
			WriteFile.writeFile(manager, fileNames);
			
			
			String find = g1.getGroupWriteData(c1.getName());

			String replace = g1.getGroupWriteData(c1.getName());
			WriteFile.updateGroupinFile(find, replace, fileNames.get(0));
			
			

		} 
		catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		
		
		String actual = getFileData(fileNames);

		String expected = "";


		for (Post p : manager.getAllPost()) {

			expected += p.getPostWriteData();

		for (category c : manager.getCategories_Alphabetically()) {

			for (Group g : c.getGroupsAlphabetically()) {

				expected += g.getGroupWriteData(c.getName());

			}


		}

		assertEquals(expected, actual);
		
		}
	}
}
