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
	
	public static boolean validPassword(User u, String password) {
		if (u.getPassword().equals(password)) {		//Compare Passwords
			return true;
		}
		else {
			return false;
		}
	}
	
	//returns true if there exists a category name from the given list that matches the given name, returns false otherwise 
	public static boolean validateCategoryNameExists(ArrayList<category> categories, String name) {
		
		for (int i = 0; i < categories.size(); i++) {	//Loop through categories
			if (categories.get(i).getName().equals(name)) {	//Compare names
				return true;	//return true if names match
			}
		}
		
		return false;	//return false otherwise
	}
	
	//returns a category that has a matching name as given if it exists, returns null otherwise
	public static category getCategoryFromName(ArrayList<category> categories, String name) {
		for (int i = 0; i < categories.size(); i++) {	//Loop through categories
			if (categories.get(i).getName().equals(name)) {	//Compare names
				return categories.get(i);	//return category if names match
			}
		}
		
		return null;	//return null otherwise
	}
	
	
	
	//returns true if there exists a group name from the given list that matches the given name, returns false otherwise 
	public static boolean validateGroupNameExists(ArrayList<group> groups, String name) {
		
		for (int i = 0; i < groups.size(); i++) {	//Loop through groups
			if (groups.get(i).getName().equals(name)) {	//Compare names
				return true;	//return true if names match
			}
		}
		
		return false;	//return false otherwise
	}
	
	
	
	
	

}
