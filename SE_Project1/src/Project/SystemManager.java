package Project;

import java.util.ArrayList;
import java.util.Collections;

public class SystemManager {
	
	
	private boolean userSignedIn;	//User sign in status, true if any User is signed in
	private boolean adminSignedIn;	//Admin sign in status, true if an Admin is signed in
	private ArrayList<User> users;
	private ArrayList<Admin> admins;
	
	private ArrayList<category> categories;
	
	
	
	public SystemManager() {
		userSignedIn = false;
		adminSignedIn = false;
		users = new ArrayList<User>();
		admins = new ArrayList<Admin>();
		categories = new ArrayList<category>();
		
	}
	
	
	
	public boolean addUser(User u) {	//This should check to ensure that a new user doesn't have the same username as an existing user
		users.add(u);
		return true;
	}
	
	public boolean addAdmin(Admin a) {	//This should check to ensure that a new user doesn't have the same username as an existing user
		admins.add(a);
		return true;
	}
	
	public boolean addCategory(category c) {	//This should check to ensure that a new category doesn't already exist
		categories.add(c);
		return true;
	}
	
	
	
	public boolean login(String username, String password) {
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
	
	
	
	
	public ArrayList<category> getCategories_Alphabetically() {
		
		Collections.sort(categories, new SortCategoriesByName());
		
		return categories;
	}
	
	

}
