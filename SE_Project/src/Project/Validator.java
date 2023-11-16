package Project;

import java.util.ArrayList;

public class Validator {

	//test:2
	//returns a User from given list if there exists a User with the inputed username, returns null otherwise
	public static User validUserName_Users(ArrayList<User> users, String username) {
		User u = null;	//Null User

		for (User user : users) {	//Loops through Users
			if (user.compareId(username)) {	//Checking Usernames
				u = user;	//Set User if match is found
				break;
			}
		}

		return u;	//Return Null/User
	}

	//test:2
	//returns a Admin from given list if there exists a Admin with the inputed username, returns null otherwise
	public static User validUserName_Admins(ArrayList<Admin> admins, String username) {
		User u = null;	//Null Admin

		for (Admin admin : admins) {	//Loops through Admins
			if (admin.compareId(username)) {	//Checking Usernames
				u = admin;	//Set Admin if match is found
				break;
			}
		}

		return u;	//Return Null/Admin
	}

	//test:2
	//returns true if the password of the given User matches the inputed password, returns false otherwise
	public static boolean validPassword(User u, String password) {
		if (u.comparePassword(password)) {		//Compare Passwords
			return true;
		}
		else {
			return false;
		}
	}

	//test:2
	//returns true if there exists a category name from the given list that matches the given name, returns false otherwise
	public static boolean validateCategoryNameExists(ArrayList<category> categories, String name) {
		for (category cat : categories) {	//Loop through categories
			if (cat.compareName(name)) {	//Compare names
				return true;	//return true if names match
			}
		}

		return false;	//return false otherwise
	}
	
	//test:2
	//returns a category that has a matching name as given if it exists, returns null otherwise
	public static category getCategoryFromName(ArrayList<category> categories, String name) {
		for (category cat : categories) {	//Loop through categories
			if (cat.compareName(name)) {	//Compare names
				return cat;	//return category if names match
			}
		}

		return null;	//return null otherwise
	}


	//test:2
	//returns true if there exists a group name from the given list that matches the given name, returns false otherwise
	public static boolean validateGroupNameExists(ArrayList<Group> groups, String name) {

		for (Group group : groups) {	//Loop through groups
			if (group.compareName(name)) {	//Compare names
				return true;	//return true if names match
			}
		}

		return false;	//return false otherwise
	}
	//returns true if there exists a group name from the given list that matches the given name, returns false otherwise

	//test:2
	//returns true if there exists a group that matches the given user, returns false otherwise
	public static boolean validateUserInGroup(Group g, User user) {

		if (g.isMemberInGroupInMembership(user.getId())) {	//Check if user is in group
			return true;	//return true if user is in group
		}

		return false;	//return false otherwise
	}
	//returns true if there exists a user is in a group, returns false otherwise
	
	//test:2
	// returns true if the admin exists and returns false otherwise
	public static boolean validateAdminExists(Admin a, ArrayList<Admin> admins) {
		
		for (Admin admin : admins) {
			if (a.compareTo(admin) == 1) {
				return true;
			}
		}
		return false;
		
	}
	
	//test:2
	// returns true if the category exists and returns false otherwise
	public static boolean validateCategoryExists(category c, ArrayList<category> categories) {
		
		for (category cat : categories) {
			if (c.compareTo(cat) == 1) {
				return true;
			}
		}
		return false;
	}
	
	//test:2
	// returns true if the user exists and returns false otherwise
	public static boolean validateUserExists(User u, ArrayList<User> users) {
		
		for (User user : users) {
			if (u.compareTo(user) == 1) {
				return true;
			}
		}
		return false;
	}
	
	//test:2
	// returns the group from the name provided
	public static Group getGroupFromName(ArrayList<Group> groups, String name) {
		
		Group g = null;
		
		for (Group g1 : groups) {
			
			if (g1.compareName(name)) {
				g = g1;
			}
		}
		
		return g;
	}
	
	//test:2
	// returns the user from the username provided
	public static User getUserFromUsername(ArrayList<User> users, String username) {
		
		User u = null;
		
		for (User u1 : users) {
			
			if (u1.compareId(username)) {
				u = u1;
			}
		}
		return u;
	}
	
	//test:2
	// returns true if the membership exists and returns false otherwise
	public static boolean validateMemberExistsInGroup(membership m, ArrayList<membership> memberships) {
		
		for (membership m1 : memberships) {
			
			if (m.compareTo(m1) == 1) {
				return true;
			}
		}
		return false;
		
	}
	
	//test:2
	// returns true if the banned exists and returns false otherwise
	public static boolean validateBannedExistsInGroup(Banned b, ArrayList<Banned> bans) {
		
		for (Banned b1 : bans) {
			
			if (b.compareTo(b1) == 1) {
				return true;
			}
		}
		return false;
		
	}
	
	//test:2
	// returns true if the membership exists and returns false otherwise
	public static boolean validateSuspendedExistsInGroup(Suspended s, ArrayList<Suspended> suspensions) {
		
		for (Suspended s1 : suspensions) {
			
			if (s.compareTo(s1) == 1) {
				return true;
			}
		}
		return false;
	}

	//FIXME: add tests
	public static Post getPostFromId(ArrayList<Post> posts, int id) {
		
		for (Post p : posts) {
			if (p.getId() == id) {
				return p;
			}
		}
		return null;
		
	}

}
