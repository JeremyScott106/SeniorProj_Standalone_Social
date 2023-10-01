package Project;

import java.util.ArrayList;

public class Validator {
	
	
	
	public static User validUserName_Users(ArrayList<User> users, String username) {
		User u = null;	//Null User
		
		for (int i = 0; i < users.size(); i++) {	//Loops through Users
			if (users.get(i).getId().equals(username)) {	//Checking Usernames
				u = users.get(i);	//Set User if match is found
				break;
			}
		}
		
		return u;	//Return Null/User
	}
	
	public static User validUserName_Admins(ArrayList<Admin> admins, String username) {
		User u = null;	//Null Admin
		
		for (int i = 0; i < admins.size(); i++) {	//Loops through Admins
			if (admins.get(i).getId().equals(username)) {	//Checking Usernames
				u = admins.get(i);
				break;	//Set Admin if match is found
			}
		}
		
		return u;	//Return Null/Admin
	}
	
	public static Boolean validPassword(User u, String password) {
		if (u.getPassword().equals(password)) {		//Compare Passwords
			return true;
		}
		else {
			return false;
		}
	}
	
	

}
