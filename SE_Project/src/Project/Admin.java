package Project;

public class Admin extends User{

	//For adding New Admins
	public Admin(String name, String id, String password, String birthdate, String city, String state) {
		super(name, id, password, birthdate, city, state);
	}
	
	//For adding Existing Admins read from the disk
	public Admin(String name, String id, String password, String birthdate, String city, String state, String registeredDate) {
		super(name, id, password, birthdate, city, state, registeredDate);
	}



}
