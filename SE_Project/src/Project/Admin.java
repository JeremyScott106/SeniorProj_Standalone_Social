package Project;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Admin extends User {

	//For adding New Admins
	public Admin(String name, String id, String password, String birthdate, 
					String city, String state) {
		super(name, id, password, birthdate, city, state);
	}
	
	//For adding Existing Admins read from the disk
	public Admin(String name, String id, String password, String birthdate, 
					String city, String state, String registeredDate) {
		super(name, id, password, birthdate, city, state, registeredDate);
	}
	
	
	
	/*
     * Formats Admin data to be written
     * Format:
     * 
     * @START
	 * @ADMIN
	 * @NAME="name_of_admin"
	 * @BIRTHDATE=MM/DD/YYYY
	 * @CITY="city_of_admin"
	 * @STATE="state_of_admin"
	 * @USERNAME="username_of_admin"
	 * @PASSWORD="password_of_admin"
	 * @REGISTERED_DATE=MM/DD/YYYY
	 * @END
     *  
     */
    public String getAdminWriteData() {
    	DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
    	String bday = df.format(birthdate);
    	String regDate = df.format(registeredDate);
    	
    	String adminData = "@START\n" + 
    						"@ADMIN\n" + 
    						"@NAME=" + name + "\n" + 
    						"@BIRTHDATE=" + bday + "\n" + 
    						"@CITY=" + city + "\n" + 
    						"@STATE=" + state + "\n" + 
    						"@USERNAME=" + id + "\n" + 
    						"@PASSWORD=" + password + "\n" + 
    						"@REGISTERED_DATE=" + regDate + "\n" + 
    						"@END\n\n";
    	
    	return adminData;
    }
    
    
    
    public int compareTo(Admin a) {
    	if (a.getId().equals(id)) {
    		return 1;
    	}
    	return 0;
    }



}
