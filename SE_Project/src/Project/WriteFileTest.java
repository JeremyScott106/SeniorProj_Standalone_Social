package Project;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class WriteFileTest {

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
		
		String fileName = ".\\SE_Project\\src\\Project\\TextFiles\\WriteFile_Test_Users.txt";
		
		try {
			
			WriteFile.writeFile(manager, fileName);
			
			assertEquals(true, true);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
		
	}

}
