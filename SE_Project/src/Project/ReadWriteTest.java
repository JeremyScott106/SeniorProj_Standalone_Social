package Project;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.Test;

class ReadWriteTest {

	@Test
	void testReadWrite_Admins() {
		String fileName = ".\\SE_Project\\src\\project\\TextFiles\\ReadWriteFile_Test\\ReadWriteFile_Test_Admins.txt";
		
		SystemManager manager = new SystemManager();
		
		try {
			
			ReadFile.readFile(manager, fileName);
			
			Admin a = new Admin("Ethan", "Testing", "Testing1234", "10/10/10", "Valdosta", "Georgia", "04/02/1978");
			
			boolean successfullAddition = manager.addAdmin(a);
			
			if (!successfullAddition) {
				System.out.println("Admin added in test already exists, either change admin to be added or remove from text file.");
				fail();
			}
			else {
				
				WriteFile.writeFile(manager, fileName);
				
			}
			
		} catch (FileNotFoundException | IncorrectFileFormatException e) {
			e.printStackTrace();
			fail();
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
	}

}
