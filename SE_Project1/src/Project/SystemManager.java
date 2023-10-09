package Project;

import java.util.ArrayList;
import java.util.List;

public class SystemManager {


	private boolean userSignedIn;	//User sign in status, true if any User is signed in
	private boolean adminSignedIn;	//Admin sign in status, true if an Admin is signed in
	private ArrayList<User> users;
	private ArrayList<Admin> admins;

	static ArrayList<group> groups;
	static ArrayList<category> categories;


	public SystemManager() {
		userSignedIn = false;
		adminSignedIn = false;
		users = new ArrayList<User>();
		admins = new ArrayList<Admin>();
		groups = new ArrayList<group>();
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

	public boolean addGroup(group g) {	//This should check to ensure that a new group doesn't already exist
		groups.add(g);
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


	public ArrayList<group> getGroups_Alphabetically() {

		for (int i = 0; i < groups.size(); i++) {

			for (int j = 0; j < groups.size() - i - 1; j++) {

				String name1 = groups.get(j).getName();
				String name2 = groups.get(j+1).getName();

				if (name1.compareTo(name2) > 0) {
					group temp = groups.get(j);
					groups.set(j, groups.get(j+1));
					groups.set(j+1, temp);

				}

			}

		}

		return groups;
	}

	public ArrayList<category> getCategories_Alphabetically() {

		for (int i = 0; i < categories.size(); i++) {

			for (int j = 0; j < categories.size() - i - 1; j++) {

				String name1 = categories.get(j).getName();
				String name2 = categories.get(j+1).getName();

				if (name1.compareTo(name2) > 0) {
					category temp = categories.get(j);
					categories.set(j, categories.get(j+1));
					categories.set(j+1, temp);

				}

			}

		}

		return categories;
	}

	public ArrayList<group> getGroupsInCategories_Alphabetically() {
        for (category category : categories) {
            ArrayList<group> groups = category.getGroups();

            for (int i = 0; i < groups.size(); i++) {

            	for (int j = 0; j < groups.size() - i - 1; j++) {

            		String name1 = groups.get(j).getName();
            		String name2 = groups.get(j+1).getName();

            		if (name1.compareTo(name2) > 0) {
            			group temp = groups.get(j);
            			groups.set(j, groups.get(j+1));
            			groups.set(j+1, temp);

            		}

            	}

            }
        }

		return groups;
	}

	public ArrayList<User> getUsers_Alphabetically() {

		for (int i = 0; i < users.size(); i++) {

			for (int j = 0; j < users.size() - i - 1; j++) {

				String name1 = users.get(j).getName();
				String name2 = users.get(j+1).getName();

				if (name1.compareTo(name2) > 0) {
					User temp = users.get(j);
					users.set(j, users.get(j+1));
					users.set(j+1, temp);

				}

			}

		}

		return users;
	}

	 public ArrayList<User> getUsersInGroup(String groupName) {
		 ArrayList<User> usersInGroup = new ArrayList<>();

	        for (User user : users) {
	            Integer groupStatus = user.getGroupStatus(groupName);
	            if (groupStatus != null && groupStatus == 1) {
	                usersInGroup.add(user);
	            }
	        }

	        return usersInGroup;
	 }

	 public ArrayList<String> getGroups(User user) {
		 ArrayList<String> group = new ArrayList<>();
		 for (int i = 0; i<groups.size(); i++) {
			 Integer groupStatus = user.getGroupStatus(groups.get(i).getName());
	         if (groupStatus != null && groupStatus == 1) {
	             group.add(groups.get(i).getName());
	         }
		 }

		 return group;
	 }
}
