package Project;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class SystemManager {

	private boolean userSignedIn;		//User sign in status, true if any User is signed in
	private boolean adminSignedIn;		//Admin sign in status, true if an Admin is signed in
	private User currentUser;
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
	
	//Constructor that will read given file
	public SystemManager(String filename) {
		userSignedIn = false;
		adminSignedIn = false;
		users = new ArrayList<User>();
		admins = new ArrayList<Admin>();
		categories = new ArrayList<category>();
		
		try {
			ReadFile.readFile(this, filename);
		} catch (FileNotFoundException | incorrectFileFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	
	public boolean writeManager(String fileName) {
		
		try {
			WriteFile.writeFile(this, fileName);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	

	public boolean addUser(User u) {
		if (Validator.validateUserExists(u, users)) {
			return false;
		}
		else {
			users.add(u);
			return true;
		}
	}

	public boolean addAdmin(Admin a) {	
		if (Validator.validateAdminExists(a, admins)) {
			return false;
		}
		else {
			admins.add(a);	
			return true;
		}
	}

	public boolean registerUser(String name, String bday, String city,
								String state, String username, String password) {

		User u = Validator.validUserName_Users(users, username);	//Check to see if there exists a User with the given username

		if (u == null) {				//There does not exist a User with the given username

			u = Validator.validUserName_Admins(admins, username);	//Check to see if there exists a Admin with the given username

			if (u == null) {			//There does not exist a Admin with the given username

				u = new User(name, username, password, bday, city, state);	//create new User	NOTICE: this will have to be updated once User class is updated
				users.add(u);			//add new user

			return true;				//return true
			}
		}
		return false;					//If there exists a User or Admin with the given username, return false
	}

	public boolean addCategory(category c) {
		
		if (Validator.validateCategoryExists(c, categories)) {
			return false;
		}
		else {
			categories.add(c);
			return true;
		}
		
	}



	public boolean createCategory(String name) {
		if (Validator.validateCategoryNameExists(categories, name)) {	//If there exists a category with given name
			return false;				//return false
		}
		else {
			category c = new category(name);	//else, create new category	NOTICE: This may require more variables as the Category class is updated
			categories.add(c);			//add category
			return true;				//return true
		}
	}

	public boolean addGroup(Group g) {	//This should check to ensure that a new Group doesn't already exist
		ArrayList<Group> groups = new ArrayList<>();
		groups.addAll(getAllGroups());
		return true;
	}

										//Assumes GUI will send over just category name, not category object
	public boolean createGroup(String groupName, String categoryName) {
		category c = Validator.getCategoryFromName(categories, categoryName);	//Get category with given name if there exists one, null otherwise

		if (c == null) {				//If validator returned null
			return false;				//return false
		}
		else {							//If validator returned a category
			return c.createGroup(groupName);	//create group within category, returns true/false depending on if group was created	NOTICE: This may require more variables as the Group class is updated
		}
	}

	public boolean login(String username, String password) {
		boolean signIn = false;

		User u = Validator.validUserName_Users(users, username);	//Checks under Users

		if (u == null) {				//If the username is not of a User

			u = Validator.validUserName_Admins(admins, username);	//Checks under Admins

			if (u == null) {			//If the username is not of a Admin
				return false;			//Will return false
			}
			else {						//If the username is of an Admin
				if (Validator.validPassword(u, password)) {	//If the password was correct
					userSignedIn = true;	//Set User sign in status to true
					adminSignedIn = true;	//Set Admin sign in status to true
					this.currentUser = u;
					return true;
				}
				else { 					//If the password was incorrect
					return false;		//return false
				}
			}
		}
		else {							//If the username is of a User
			if (Validator.validPassword(u, password)) {	//If the password was correct
				userSignedIn = true;	//Set User sign in status to true
				this.currentUser = u;
				return true;			//Return true
			}
			else {						//If the password was incorrect
				return false;			//Return false
			}
		}
	}

	public ArrayList<category> getCategories_Alphabetically() {

		Collections.sort(categories, new SortCategoriesByName());

		return categories;
	}
	
	//helper method, returns a list of all groups.
	private ArrayList<Group> getAllGroups(){
		ArrayList<Group> groups = new ArrayList<>();
		for(category c : categories) {
			groups.addAll(c.getGroupsAlphabetically());
		}
		return groups;
	}
	
	public ArrayList<Group> getAllGroups_Alphabetically() {
		ArrayList<Group> groups = new ArrayList<>();
		groups.addAll(getAllGroups());
		Collections.sort(groups, new SortGroupsByName());

		return groups;
	}

	public ArrayList<User> getUsers_Alphabetically() {


		Collections.sort(users, new SortUsersByName());


		return users;
	}
	

	public boolean isLoggedIn() {
		return userSignedIn;
	}
	
	public boolean isAdmin() {
		return adminSignedIn;
	}
	
	public User getCurrentUser() {
		return currentUser;
  }

	public ArrayList<Admin> getAdmins_Alphabetically() {
		Collections.sort(admins, new SortUsersByName());
		return admins;

	}

	 public ArrayList<Group> getGroupsByUser(User user) {
	 ArrayList<Group> group = new ArrayList<>();
	 ArrayList<Group> groups = new ArrayList<>();
	 groups.addAll(getAllGroups());
	 for (Group g: groups) {
		 if (g.isMemberInGroup(user.getId()) == true){
			 group.add(g);
		 }
	 }
	 return group;
}
	 
	 public ArrayList<User> getUsersInGroup(Group group) {
		 ArrayList<User> userInGroup = new ArrayList<>();
		 for (User u: users) {
			 if (group.isMemberInGroup(u.getId()) == true){
				 userInGroup.add(u);
			 }
		 }
		 return userInGroup;
	 }
	
	 public ArrayList<Group> getGroupsInCategory_Alphabetically(category c) {
		 ArrayList<Group> groupInCategory = new ArrayList<>();
		 ArrayList<Group> groups = new ArrayList<>();
		 groups.addAll(getAllGroups_Alphabetically());
		 for (Group g: groups) {
			 if (c.isGroupInCategory(g.getGroupName()) == true){
				 groupInCategory.add(g);
			 }
		 }
		 return groupInCategory;
	 }
	 
	 public category getCategoryByName(String catName) {
		 return Validator.getCategoryFromName(categories, catName);
	 }
	 
	 public ArrayList<User> getUsers_Alphabetically_ByUsername(){
		 Collections.sort(users, new SortUsersByUsername());
		 
		 return users;
	 }
	 
	 public ArrayList<Admin> getAdmins_Alphabetically_ByUsername() {
		 Collections.sort(admins, new SortUsersByUsername());
		 
		 return admins;
	 }
}
