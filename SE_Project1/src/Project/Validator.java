package Project;

import java.util.ArrayList;

public class Validator {
	
	
	//returns a User from given list if there exists a User with the inputed username, returns null otherwise
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
	
	//returns a Admin from given list if there exists a Admin with the inputed username, returns null otherwise
	public static User validUserName_Admins(ArrayList<Admin> admins, String username) {
		User u = null;	//Null Admin
		
		for (int i = 0; i < admins.size(); i++) {	//Loops through Admins
			if (admins.get(i).getId().equals(username)) {	//Checking Usernames
				u = admins.get(i);	//Set Admin if match is found
				break;	
			}
		}
		
		return u;	//Return Null/Admin
	}
	
	//returns true if the password of the given User matches the inputed password, returns false otherwise
	public static Boolean validPassword(User u, String password) {
		if (u.getPassword().equals(password)) {		//Compare Passwords
			return true;
		}
		else {
			return false;
		}
	}
	
	//returns true if there exists a category name from the given list that matches the given name, returns false otherwise 
	public static Boolean validateCategoryNameExists(ArrayList<category> categories, String name) {
		
		for (int i = 0; i < categories.size(); i++) {	//Loop through categories
			if (categories.get(i).getName().equals(name)) {	//Compare names
				return true;	//return true if names match
			}
		}
		
		return false;	//return false otherwise
	}
	
	

}
