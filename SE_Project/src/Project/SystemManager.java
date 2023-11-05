package Project;

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
		users = new ArrayList<>();
		admins = new ArrayList<>();		
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

				u = new User(name, username, password, bday, city, state);	//create new User	NOTICE: this will have to be updated once User class is updated
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
	
	public boolean addPost(Post p) {	//This should check to ensure that a new category doesn't already exist
		ArrayList<Post> posts = new ArrayList<>();
		posts.addAll(getAllPost());
		posts.add(p);				//NOTICE: This may require more variables as the Category class is updated

		return true;
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
	 
		//helper method, returns a list of all groups.
		private ArrayList<Post> getAllPost(){
			ArrayList<Group> groups = new ArrayList<>();
			ArrayList<Post> posts = new ArrayList<>();
			for(category c : categories) {
				groups.addAll(c.getGroupsAlphabetically());
			}
			for(Group g: groups) {
				posts.addAll(g.getPost());
			}
			return posts;
		}
	 
	//User story 22
	//takes in a user and loops through all the posts. If a post was created by the user it records the postBody. Also checks each post for Responces. if the users are the same records ResponceBody to the string.
	 public ArrayList<Object> viewUsersPostsResponses(User user) {
		 ArrayList<Object> results = new ArrayList<>();
		 ArrayList<Post> posts = new ArrayList<>();
		 posts.addAll(getAllPost());
		 for(Post p : posts) {
			 if(user == p.getUser()) {
				  results.add(p);
			 }
			 ArrayList<Response> r = new ArrayList<>();
			 r.addAll(p.getResponse());
			 for (Response r1 : r){
				 membership m = r1.getMember();
				 if(user == m.getUser()){
					  results.add(r1);
				 }	
			 }
		 }
		 return results;
	 }
	 
	 //User story 23
	 // takes in user and a group. if the post is in the group given then it records all the posts and responses created by the user in that group. 
	 public ArrayList<Object> viewUsersPostsResponsesInGroup(User user, Group group) {
		 ArrayList<Object> results = new ArrayList<>();
		 ArrayList<Post> posts = new ArrayList<>();
		 posts.addAll(getAllPost());
		 for(Post p : posts) {
			 if(group == p.getGroup()) {
				 if(user == p.getUser()) {
					 results.add(p);
				 }
				 ArrayList<Response> r = new ArrayList<>();
				 r.addAll(p.getResponse());
				 for (Response r1 : r){
					 membership m = r1.getMember();
					 if(user == m.getUser()){
						 results.add(r1);
					 }	
				 }
			 }
		 }
		 return results;
	 }
	 // recursion
	 //User story 24
	 //loops through the post arrayList and records all the posts and responses of a given group
	 public ArrayList<Object> viewPostsResponsesInGroup(Group group) {
		 ArrayList<Object> results = new ArrayList<>();
		 ArrayList<Post> posts = new ArrayList<>();
		 posts.addAll(getAllPost());
		 for(Post p : posts){
			 if(group == p.getGroup()) {
				 results.add(p);
				 ArrayList<Response> r = new ArrayList<>();
				 r.addAll(p.getResponse());
				 for (Response r1 : r){
					 results.add(r1);
				 }
			}
		 }
		 return results;
	}
	 
	 //User story 25
	 //checks if the post has the user if so it gets the responses from the post and returns the message.
	 public ArrayList<Object> viewMyResponses(User user, Post post) {
		 ArrayList<Response> pr = new ArrayList<>();
		 pr.addAll(post.getResponse());
		 ArrayList<Object> results = new ArrayList<>();
		 for (Response r1 : pr){
			 User u = r1.getUser();
			 if(user == u){
				 results.add(r1);
			}	
		}
		return results;
	} 
	 
}