package Project;

import java.util.ArrayList;

public class Validator {


	//returns a User from given list if there exists a User with the inputed username, returns null otherwise
	public static User validUserName_Users(ArrayList<User> users, String username) {
		User u = null;	//Null User

		for (User user : users) {	//Loops through Users
			if (user.getId().equals(username)) {	//Checking Usernames
				u = user;	//Set User if match is found
				break;
			}
		}

		return u;	//Return Null/User
	}

	//returns a Admin from given list if there exists a Admin with the inputed username, returns null otherwise
	public static User validUserName_Admins(ArrayList<Admin> admins, String username) {
		User u = null;	//Null Admin

		for (Admin admin : admins) {	//Loops through Admins
			if (admin.getId().equals(username)) {	//Checking Usernames
				u = admin;	//Set Admin if match is found
				break;
			}
		}

		return u;	//Return Null/Admin
	}


	//returns true if the password of the given User matches the inputed password, returns false otherwise

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
		for (category element : categories) {	//Loop through categories
			if (element.getName().equals(name)) {	//Compare names
				return true;	//return true if names match
			}
		}

		return false;	//return false otherwise
	}

	//returns a category that has a matching name as given if it exists, returns null otherwise
	public static category getCategoryFromName(ArrayList<category> categories, String name) {
		for (category element : categories) {	//Loop through categories
			if (element.getName().equals(name)) {	//Compare names
				return element;	//return category if names match
			}
		}

		return null;	//return null otherwise
	}



	//returns true if there exists a group name from the given list that matches the given name, returns false otherwise
	public static boolean validateGroupNameExists(ArrayList<Group> groups, String name) {

		for (Group group : groups) {	//Loop through groups
			if (group.getGroupName().equals(name)) {	//Compare names
				return true;	//return true if names match
			}
		}

		return false;	//return false otherwise
	}
	//returns true if there exists a group name from the given list that matches the given name, returns false otherwise

}
