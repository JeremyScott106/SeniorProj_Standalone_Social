package Project;

import java.util.ArrayList;

public class SystemManager {
	
	static ArrayList<User> users = new ArrayList<User>();
	
	
	public static boolean addUser(User u) {	//This should check to unsure that a new user doesn't have the same username as an existing user
		users.add(u);
		return true;
	}
	
	
	
	public static boolean login(String username, String password) {
		boolean signIn = false;
		
		User u = Validator.validUserName(users, username);
		if (u == null) {
			return signIn;	//will be false
		}
		else {
			signIn = Validator.validPassword(u, password);
			
			return signIn;	
		}
	}
	
	

}
