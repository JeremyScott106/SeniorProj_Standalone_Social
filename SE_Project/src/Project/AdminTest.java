package Project;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdminTest {

	// tests the constructor
	@Test
	void testConstructor() {
		Admin a = new Admin("Billy", "0010", "billyiscool", "10/10/1997", "Valdosta", "Georgia");

	}
	
	// Test getting Admin data from disk
	@Test
	void testGetAdminWriteData() {
		Admin a = new Admin("Billy", "0010", "billyiscool", "10/10/1997", 
								"Valdosta", "Georgia", "12/05/2008");
		
		String actual = a.getAdminWriteData();
		
		String expected = "@START\n" + 
							"@ADMIN\n" + 
							"@NAME=Billy\n" + 
							"@BIRTHDATE=10/10/1997\n" + 
							"@CITY=Valdosta\n" + 
							"@STATE=Georgia\n" + 
							"@USERNAME=0010\n" + 
							"@PASSWORD=billyiscool\n" + 
							"@REGISTERED_DATE=12/05/2008\n" + 
							"@END\n\n";
		
		assertEquals(expected, actual);
	}

}
