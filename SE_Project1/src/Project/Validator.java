package Project;

import java.util.ArrayList;

public class Validator {
	
	
	
	public static User validUserName(ArrayList<User> users, String username) {
		User u = null;
		
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getId().equals(username)) {
				u = users.get(i);
				break;
			}
		}
		
		return u;
	}
	
	public static Boolean validPassword(User u, String password) {
		if (u.getPassword().equals(password)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	

}
