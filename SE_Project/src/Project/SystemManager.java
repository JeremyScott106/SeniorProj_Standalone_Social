package Project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class SystemManager {

	private boolean userSignedIn;		//User sign in status, true if any User is signed in
	private boolean adminSignedIn;		//Admin sign in status, true if an Admin is signed in
	private ArrayList<User> users;
	private ArrayList<Admin> admins;

	private ArrayList<Group> groups;
	private ArrayList<category> categories;

	public SystemManager() {
		userSignedIn = false;
		adminSignedIn = false;
		users = new ArrayList<>();
		admins = new ArrayList<>();
		groups = new ArrayList<>();
		categories = new ArrayList<>();

	}

	public boolean addUser(User u) {	//This should check to ensure that a new user doesn't have the same username as an existing user
		users.add(u);  					//NOTICE: This will require more variables as the User class is updated
		return true;
	}

	public boolean addAdmin(Admin a) {	//This should check to ensure that a new user doesn't have the same username as an existing user
		admins.add(a);					//NOTICE: This will require more variables as the User/Admin class is updated
		return true;
	}

	public boolean registerUser(String name, String bday, String city,
								String state, String username, String password) {

		User u = Validator.validUserName_Users(users, username);	//Check to see if there exists a User with the given username

		if (u == null) {				//There does not exist a User with the given username

			u = Validator.validUserName_Admins(admins, username);	//Check to see if there exists a Admin with the given username

			if (u == null) {			//There does not exist a Admin with the given username

				Date registeredDate = new Date();	//Get current date
				u = new User(name, username, password);	//create new User	NOTICE: this will have to be updated once User class is updated
				users.add(u);			//add new user

			return true;				//return true
			}
		}
		return false;					//If there exists a User or Admin with the given username, return false
	}

	public boolean addCategory(category c) {	//This should check to ensure that a new category doesn't already exist
		categories.add(c);				//NOTICE: This may require more variables as the Category class is updated

		return true;
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
		groups.add(g);
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
	
	public ArrayList<Group> getGroups_Alphabetically() {

		Collections.sort(groups, new SortGroupsByName());

		return groups;
	}

	public ArrayList<User> getUsers_Alphabetically() {


		Collections.sort(users, new SortUsersByName());


		return users;
	}
	
	public boolean isLoggedIn() {
		if (userSignedIn)
			return true;
		else
			return false;
	}
	
	public boolean isAdmin() {
		if (adminSignedIn)
			return true;
		else
			return false;
	}

	/* IN WORK
	 public ArrayList<String> getGroups(User user) {
		 ArrayList<String> group = new ArrayList<>();
		 for (int i = 0; i<groups.size(); i++) {
			 if (groups[i].isMemberInGroup(user.getId()) == true){
				 group.add(groups.getGroupName());
			 }
		 }
		 return group;
	 }
	 */
}
