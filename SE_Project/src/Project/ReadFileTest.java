package Project;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.Test;

class ReadFileTest {

	@Test
	void testReadFile_Failure_FileNotFound() {
		SystemManager manager = new SystemManager();
		
		String fileName = "fileNotFound.txt";
		boolean fileNotFound = false;
		
		try {
			ReadFile.readFile(manager, fileName);
		}
		catch (FileNotFoundException e) {
			fileNotFound = true;
		}
		catch (incorrectFileFormatException e) {
			fail();
		}
		
		assertEquals(true, fileNotFound);
	}



	@Test
	void testReadFile_Admin_Success() {
		SystemManager manager = new SystemManager();
		
		/*
		 * NOTICE:
		 * For this test I tried to create a comparator to sort a list of Users alphabetically by username
		 * though the comparator never returned a properly sorted list
		 * sorted off of name instead, though username was preferred
		 */
		
		
		//NOTICE: File path will have to be updated for each user/branch/etc
		String fileName = ".\\SE_Project\\src\\Project\\TextFiles\\ReadFile_Test_Admin.txt";
		
		try {
		
			ReadFile.readFile(manager, fileName);
			
			ArrayList<Admin> expected = new ArrayList<Admin>();
			
			Admin a1 = new Admin("Jeremy", "unth!nk@b1e", "password#3", "04/27/1992", 
									"New York City", "New York", "11/11/2011");
			Admin a2 = new Admin("Grayson", "theGr@yS0n", "r@inB0wR0@DL0s3r", "03/09/2012", 
					"Macon", "Georgia", "03/09/2011");
			Admin a3 = new Admin("Ryan", "RKScandrol", "N0tT311iN", "08/08/1999", 
					"Pensacola", "Florida", "07/07/2010");
			Admin a4 = new Admin("John", "J0hnW!ck", "n0tth3D0g", "01/01/0001", 
					"Boring", "Oregon", "12/29/2020");
			Admin a5 = new Admin("Himanshu", "the01dM@n", "L3t5D0Th!s", "07/23/1836", 
					"Climax", "Michigan", "12/09/2007");
			
			expected.add(a2);
			expected.add(a5);
			expected.add(a1);
			expected.add(a4);
			expected.add(a3);
			
			ArrayList<Admin> actual = manager.getAdmins_Alphabetically();
			
			boolean usernamesMatch = true;
			
			for (int i = 0; i < expected.size(); i++) {
				
				//For testing purposes
				//System.out.println(expected.get(i).getId() + ", " + actual.get(i).getId());
				
				if ( !(actual.get(i).getId().equals(expected.get(i).getId())) ) {
					usernamesMatch = false;
					break;
				}
			}
			
			assertEquals(true, usernamesMatch);
			
			/*
			 * When comparing objects, 
			 * assertEquals will only check to see the references point to the same place in memory,
			 * as such, we must personally check through each username
			 */
			
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
			fail();
		}
		catch (incorrectFileFormatException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	void testReadFile_Admin_Failure_IncorrectFormat1() {
		SystemManager manager = new SystemManager();
		
		//NOTICE: File path will have to be updated for each user/branch/etc
		String fileName = ".\\SE_Project\\src\\Project\\TextFiles\\ReadFile_Test_Admin_Failure_IncorrectFormat1.txt";
		boolean incorrectFileFormat = false;
		
		try {
			ReadFile.readFile(manager, fileName);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
			fail();
		}
		catch (incorrectFileFormatException e) {
			incorrectFileFormat = true;
		}
		
		assertEquals(true, incorrectFileFormat);
	}
	
	@Test
	void testReadFile_Admin_Failure_IncorrectFormat2() {
		SystemManager manager = new SystemManager();
		
		//NOTICE: File path will have to be updated for each user/branch/etc
		String fileName = ".\\SE_Project\\src\\Project\\TextFiles\\ReadFile_Test_Admin_Failure_IncorrectFormat2.txt";
		boolean incorrectFileFormat = false;
		
		try {
			ReadFile.readFile(manager, fileName);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
			fail();
		}
		catch (incorrectFileFormatException e) {
			incorrectFileFormat = true;
		}
		
		assertEquals(true, incorrectFileFormat);
	}
	
	@Test
	void testReadFile_Admin_Failure_IncorrectFormat3() {
		SystemManager manager = new SystemManager();
		
		//NOTICE: File path will have to be updated for each user/branch/etc
		String fileName = ".\\SE_Project\\src\\Project\\TextFiles\\ReadFile_Test_Admin_Failure_IncorrectFormat3.txt";
		boolean incorrectFileFormat = false;
		
		try {
			ReadFile.readFile(manager, fileName);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
			fail();
		}
		catch (incorrectFileFormatException e) {
			incorrectFileFormat = true;
		}
		
		assertEquals(true, incorrectFileFormat);
	}
	
	@Test
	void testReadFile_Admin_Failure_IncorrectFormat4() {
		SystemManager manager = new SystemManager();
		
		//NOTICE: File path will have to be updated for each user/branch/etc
		String fileName = ".\\SE_Project\\src\\Project\\TextFiles\\ReadFile_Test_Admin_Failure_IncorrectFormat4.txt";
		boolean incorrectFileFormat = false;
		
		try {
			ReadFile.readFile(manager, fileName);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
			fail();
		}
		catch (incorrectFileFormatException e) {
			incorrectFileFormat = true;
		}
		
		assertEquals(true, incorrectFileFormat);
	}
	
	
	
	
	
	@Test
	void testReadFile_User_Success() {
		
		/*
		 * NOTICE:
		 * For this test I tried to create a comparator to sort a list of Users alphabetically by username
		 * though the comparator never returned a properly sorted list
		 * sorted off of name instead, though username was preferred
		 */
		
		SystemManager manager = new SystemManager();
		
		//NOTICE: File path will have to be changed for each user/branch/etc
		String fileName = ".\\SE_Project\\src\\Project\\TextFiles\\ReadFile_Test_User.txt";
		
		try {
			
			ReadFile.readFile(manager, fileName);
			
			ArrayList<User> actual = manager.getUsers_Alphabetically();
			
			ArrayList<String> expectedUsernames = new ArrayList<String>();
			
			//In order of name from file
			expectedUsernames.add("WestCarolina");
			expectedUsernames.add("theWiz");
			expectedUsernames.add("LegalTrouble");
			expectedUsernames.add("IDK");
			expectedUsernames.add("jackster3");
			
			
			boolean usernamesMatch = true;
			
			for (int i = 0; i < expectedUsernames.size(); i++) {
				
				//For testing purposes
				//System.out.println(expectedUsernames.get(i) + ", " + actual.get(i).getId());
				
				if (!(expectedUsernames.get(i).equals(actual.get(i).getId())) ) {
					usernamesMatch = false;
					
				}
			}
			
			assertEquals(true, usernamesMatch);
			
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
			fail();
		}
		catch (incorrectFileFormatException e) {
			e.printStackTrace();
			fail();
			
		}
	}
	
	@Test
	void testReadFile_User_Failure_IncorrectFormat1() {
		
		SystemManager manager = new SystemManager();
		
		//NOTICE: File path will have to be changed for each user/branch/etc
		String fileName = ".\\SE_Project\\src\\Project\\TextFiles\\ReadFile_Test_User_Failure_IncorrectFormat1.txt";
		boolean incorrectFormat = false;
		
		try {
			ReadFile.readFile(manager, fileName);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
			fail();
		}
		catch (incorrectFileFormatException e) {
			incorrectFormat = true;
			
		}
		
		assertEquals(true, incorrectFormat);
	}
	
	@Test
	void testReadFile_User_Failure_IncorrectFormat2() {
		
		SystemManager manager = new SystemManager();
		
		//NOTICE: File path will have to be changed for each user/branch/etc
		String fileName = ".\\SE_Project\\src\\Project\\TextFiles\\ReadFile_Test_User_Failure_IncorrectFormat2.txt";
		boolean incorrectFormat = false;
		
		try {
			ReadFile.readFile(manager, fileName);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
			fail();
		}
		catch (incorrectFileFormatException e) {
			incorrectFormat = true;
			
		}
		
		assertEquals(true, incorrectFormat);
	}
	
	@Test
	void testReadFile_User_Failure_IncorrectFormat3() {
		
		SystemManager manager = new SystemManager();
		
		//NOTICE: File path will have to be changed for each user/branch/etc
		String fileName = ".\\SE_Project\\src\\Project\\TextFiles\\ReadFile_Test_User_Failure_IncorrectFormat3.txt";
		boolean incorrectFormat = false;
		
		try {
			ReadFile.readFile(manager, fileName);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
			fail();
		}
		catch (incorrectFileFormatException e) {
			incorrectFormat = true;
			
		}
		
		assertEquals(true, incorrectFormat);
	}
	
	@Test
	void testReadFile_User_Failure_IncorrectFormat4() {
		
		SystemManager manager = new SystemManager();
		
		//NOTICE: File path will have to be changed for each user/branch/etc
		String fileName = ".\\SE_Project\\src\\Project\\TextFiles\\ReadFile_Test_User_Failure_IncorrectFormat4.txt";
		boolean incorrectFormat = false;
		
		try {
			ReadFile.readFile(manager, fileName);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
			fail();
		}
		catch (incorrectFileFormatException e) {
			incorrectFormat = true;
			
		}
		
		assertEquals(true, incorrectFormat);
	}

	
	
	
	
	@Test
	void testReadFile_Admin_User_Success() {
		
		/*
		 * NOTICE:
		 * For this test I tried to create a comparator to sort a list of Users alphabetically by username
		 * though the comparator never returned a properly sorted list
		 * sorted off of name instead, though username was preferred
		 */
		
		SystemManager manager = new SystemManager();
		
		//NOTICE: File path will have to be changed for each user/branch/etc
		String fileName = ".\\SE_Project\\src\\Project\\TextFiles\\ReadFile_Test_Admin_User.txt";
		
		try {
			
			ReadFile.readFile(manager, fileName);
			
			ArrayList<Admin> actualAdmins = manager.getAdmins_Alphabetically();
			ArrayList<User> actualUsers = manager.getUsers_Alphabetically();
			
			ArrayList<String> expectedAdminUsernames = new ArrayList<String>();
			ArrayList<String> expectedUserUsernames = new ArrayList<String>();
			
			//In order of name from file
			expectedAdminUsernames.add("theGr@yS0n");
			expectedAdminUsernames.add("the01dM@n");
			expectedAdminUsernames.add("unth!nk@b1e");
			expectedAdminUsernames.add("J0hnW!ck");
			expectedAdminUsernames.add("RKScandrol");
			
			//In order of name from file
			expectedUserUsernames.add("WestCarolina");
			expectedUserUsernames.add("theWiz");
			expectedUserUsernames.add("LegalTrouble");
			expectedUserUsernames.add("IDK");
			expectedUserUsernames.add("jackster3");
			
			
			boolean usernamesMatch = true;
			
			for (int i = 0; i < expectedAdminUsernames.size(); i++) {
				
				//For testing purposes
				//System.out.println(expectedAdminUsernames.get(i) + ", " + actualAdmins.get(i).getId());
				
				if (!(expectedAdminUsernames.get(i).equals(actualAdmins.get(i).getId())) ) {
					usernamesMatch = false;
					break;
				}
			}
			
			if (usernamesMatch) {
				for (int i = 0; i < expectedUserUsernames.size(); i++) {
					
					//For testing purposes
					//System.out.println(expectedUserUsernames.get(i) + ", " + actualUsers.get(i).getId());
					
					if (!(expectedUserUsernames.get(i).equals(actualUsers.get(i).getId())) ) {
						usernamesMatch = false;
						break;
					}
				}
			}
			
			assertEquals(true, usernamesMatch);
			
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
			fail();
		}
		catch (incorrectFileFormatException e) {
			e.printStackTrace();
			fail();
			
		}
	}
	


	
	
	@Test
	void testReadFile_Category_Success() {
		
		SystemManager manager = new SystemManager();
		
		String fileName = ".\\SE_Project\\src\\Project\\TextFiles\\ReadFile_Test_Category.txt";
		
		
		try {
			
			ReadFile.readFile(manager, fileName);
			
			ArrayList<category> actualCats = manager.getCategories_Alphabetically();
			
			ArrayList<String> expectedNames = new ArrayList<String>();
			
			expectedNames.add("Foods");
			expectedNames.add("Games");
			expectedNames.add("Memes");
			expectedNames.add("Sports");
			
			boolean namesMatch = true;
			
			for (int i = 0; i < actualCats.size(); i++) {
				
				if (!actualCats.get(i).getName().equals(expectedNames.get(i))) {
					namesMatch = false;
					break;
				}
				
			}
			
			assertEquals(true, namesMatch);
			
		} catch (FileNotFoundException | incorrectFileFormatException e) {
			
			e.printStackTrace();
			fail();
		}
		
		
	}
	
	
	
	@Test
	void testReadFile_Group_Success() {
		
		SystemManager manager = new SystemManager();
		
		String fileName = ".\\SE_Project\\src\\Project\\TextFiles\\ReadFile_Test_Group.txt";
		
		
		
		try {
			
			ReadFile.readFile(manager, fileName);
			
			ArrayList<Group> actualGroups = manager.getAllGroups_Alphabetically();
			
			ArrayList<String> expectedNames = new ArrayList<String>();
			
			expectedNames.add("Football");
			expectedNames.add("Hockey");
			expectedNames.add("Soccer");
			
			boolean namesMatch = true;
			
			for (int i = 0; i < actualGroups.size(); i++) {
				
				if (!actualGroups.get(i).getGroupName().equals(expectedNames.get(i))) {
					namesMatch = false;
					break;
				}
				
			}
			
			assertEquals(true, namesMatch);
			
			
			
		} catch (FileNotFoundException | incorrectFileFormatException e) {
			e.printStackTrace();
			fail();
		}
		
	}
	
}
