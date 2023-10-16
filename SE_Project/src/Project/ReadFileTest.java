package Project;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

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
		SystemManager actual = new SystemManager();
		
		//NOTICE: File path will have to be updated
		String fileName = "C:\\Users\\rks11\\git\\Ryan'sBranch4.0\\23fa_team13\\SE_Project\\src\\Project\\ReadFile_Test_Admin.txt";
		
		try {
		
			ReadFile.readFile(actual, fileName);
			
			SystemManager expected = new SystemManager();
			
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
			
			expected.addAdmin(a1);
			expected.addAdmin(a2);
			expected.addAdmin(a3);
			expected.addAdmin(a4);
			expected.addAdmin(a5);
			
			//Need to add getAddmins to SystemManager to check that admins in actual are correct
			
			assertEquals(true, true);
			
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
}
