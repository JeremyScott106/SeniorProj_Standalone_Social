package Project;

import java.util.ArrayList;

public class SystemManager {
	
	
	static boolean userSignedIn = false;	//User sign in status, true if any User is signed in
	static boolean adminSignedIn = false;	//Admin sign in status, true if an Admin is signed in
	static ArrayList<User> users = new ArrayList<User>();
	static ArrayList<Admin> admins = new ArrayList<Admin>();
	
	
	public static boolean addUser(User u) {	//This should check to unsure that a new user doesn't have the same username as an existing user
		users.add(u);
		return true;
	}
	
	public static boolean addAdmin(Admin a) {	//This should check to unsure that a new user doesn't have the same username as an existing user
		admins.add(a);
		return true;
	}
	
	
	
	public static boolean login(String username, String password) {
		boolean signIn = false;
		
		User u = Validator.validUserName_Users(users, username);	//Checks under Users
		
		if (u == null) {	//If the username is not of a user
			
			u = Validator.validUserName_Admins(admins, username);	//Checks under Admins
			
			if (u == null) {	//If the username is not of a Admin
				return signIn;	//Will return false
			}
			else {	//If the username is of an Admin
				signIn = Validator.validPassword(u, password);	//Check Password
				
				if (signIn == true) {	//If the password was correct
					userSignedIn = true;	//Set User sign in status to true
					adminSignedIn = true;	//Set Admin sign in status to true
				}
				return signIn;	//Will return true/false depending on the password
			}
		}
		else {	//If the username is of a User
			signIn = Validator.validPassword(u, password);	//Check Password
			
			
			if (signIn == true) {	//If the password was correct
				userSignedIn = true;	//Set User sign in status to true
			}
			return signIn;	//Will return true/false depending on the password
		}
	}
	
	

}
