package Project;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SystemManagerTest {

	@Test
	void testLogin_Success() {
		User u1 = new User("Jack", "jackster3", "HKb@wser!");
		User u2 = new User("Dan", "theWiz", "WartH@g77");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble");
		User u5 = new User("Ethan", "IDK", "WHY#5");
		
		SystemManager.addUser(u1);
		SystemManager.addUser(u2);
		SystemManager.addUser(u3);
		SystemManager.addUser(u4);
		SystemManager.addUser(u5);
		
		boolean tf = SystemManager.login("LegalTrouble", "D@uble&Tr@uble");
		
		assertEquals(true, tf);
	}
	
	@Test
	void testLogin_Failure_Username() {
		User u1 = new User("Jack", "jackster3", "HKb@wser!");
		User u2 = new User("Dan", "theWiz", "WartH@g77");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble");
		User u5 = new User("Ethan", "IDK", "WHY#5");
		
		SystemManager.addUser(u1);
		SystemManager.addUser(u2);
		SystemManager.addUser(u3);
		SystemManager.addUser(u4);
		SystemManager.addUser(u5);
		
		boolean tf = SystemManager.login("Failure", "D@uble&Tr@uble");
		
		assertEquals(false, tf);
	}
	
	
	@Test
	void testLogin_Failure_Password() {
		User u1 = new User("Jack", "jackster3", "HKb@wser!");
		User u2 = new User("Dan", "theWiz", "WartH@g77");
		User u3 = new User("Carol", "WestCarolina", "P!zzaH$t");
		User u4 = new User("Dulaney", "LegalTrouble", "D@uble&Tr@uble");
		User u5 = new User("Ethan", "IDK", "WHY#5");
		
		SystemManager.addUser(u1);
		SystemManager.addUser(u2);
		SystemManager.addUser(u3);
		SystemManager.addUser(u4);
		SystemManager.addUser(u5);
		
		boolean tf = SystemManager.login("LegalTrouble", "Failure");
		
		assertEquals(false, tf);
	}

}
