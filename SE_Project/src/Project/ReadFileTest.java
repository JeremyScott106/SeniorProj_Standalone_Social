package Project;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

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
		
		//NOTICE: File path will have to be updated for each user/branch/etc
		String fileName = "C:\\Users\\rks11\\git\\Ryan'sBranch4.0\\23fa_team13\\SE_Project\\src\\Project\\ReadFile_Test_Admin.txt";
		
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
		String fileName = "C:\\Users\\rks11\\git\\Ryan'sBranch4.0\\23fa_team13\\SE_Project\\src\\Project\\ReadFile_Test_Admin_Failure_IncorrectFormat1.txt";
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
		String fileName = "C:\\Users\\rks11\\git\\Ryan'sBranch4.0\\23fa_team13\\SE_Project\\src\\Project\\ReadFile_Test_Admin_Failure_IncorrectFormat2.txt";
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
		
		SystemManager manager = new SystemManager();
		
		//NOTICE: File path will have to be changed for each user/branch/etc
		String fileName = "C:\\Users\\rks11\\git\\Ryan'sBranch4.0\\23fa_team15\\SE_Project\\src\\Project\\ReadFile_Test_User.txt";
		
		try {
			
			ReadFile.readFile(manager, fileName);
			
			ArrayList<User> actual = manager.getUsers_Alphabetically();
			
			ArrayList<String> expectedUsernames = new ArrayList<String>();
			
			expectedUsernames.add("IDK");
			expectedUsernames.add("jackster3");
			expectedUsernames.add("LegalTrouble");
			expectedUsernames.add("theWiz");
			expectedUsernames.add("WestCarolina");
			
			
			boolean usernamesMatch = true;
			
			for (int i = 0; i < expectedUsernames.size(); i++) {
				if (!(expectedUsernames.get(i).equals(actual.get(i).getName())) ) {
					usernamesMatch = false;
					break;
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
}
