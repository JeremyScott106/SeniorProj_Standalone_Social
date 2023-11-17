package Project;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.text.SimpleDateFormat;

public class SystemManager {

	private boolean userSignedIn;		//User sign in status, true if any User is signed in
	private boolean adminSignedIn;		//Admin sign in status, true if an Admin is signed in
	private User currentUser;
	private category currentCategory;
	private Group currentGroup;
	private Post currentPost;
	
	private boolean writable;

	private ArrayList<User> users;
	private ArrayList<Admin> admins;
	private ArrayList<category> categories;
	private ArrayList<String> fileNames;

	public SystemManager() {
		userSignedIn = false;
		adminSignedIn = false;
		users = new ArrayList<User>();
		admins = new ArrayList<Admin>();		
		categories = new ArrayList<category>();
		
		this.writable = false;

	}
	
	//Constructor that will read given file
	public SystemManager(ArrayList<String> fileNames) {
		this.userSignedIn = false;
		this.adminSignedIn = false;
		this.users = new ArrayList<User>();
		this.admins = new ArrayList<Admin>();
		this.categories = new ArrayList<category>();
		this.fileNames = fileNames;
		this.writable = true;
		
		try {
			ReadFile.readFile(this, fileNames);
		} 
		catch (FileNotFoundException | IncorrectFileFormatException e) {
			e.printStackTrace();
		}		
	}
	
	//writes the manager
	public boolean writeManager() {
		
		try {
			WriteFile.writeFile(this, fileNames);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	// add an user
	public boolean addUser(User u) {
		if (Validator.validateUserExists(u, users)) {
			return false;
		}
		else {
			users.add(u);
			return true;
		}
	}

	// adds an admin
	public boolean addAdmin(Admin a) {	
		if (Validator.validateAdminExists(a, admins)) {
			return false;
		}
		else {
			admins.add(a);	
			return true;
		}
	}

	// allows the user to be registered
	public boolean registerUser(String name, String bday, String city,
								String state, String username, String password) {

		User u = Validator.validUserName_Users(users, username);	//Check to see if there exists a User with the given username

		if (u == null) {				//There does not exist a User with the given username

			u = Validator.validUserName_Admins(admins, username);	//Check to see if there exists a Admin with the given username

			if (u == null) {			//There does not exist a Admin with the given username

				u = new User(name, username, password, bday, city, state);	//create new User	NOTICE: this will have to be updated once User class is updated
				users.add(u);			//add new user
				if (writable) {			//If there is a file to write to
					try {					//Try adding the user to the UserFile
						WriteFile.addUserToFile(u, fileNames.get(1));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			return true;				//return true
			}
		}
		return false;					//If there exists a User or Admin with the given username, return false
	}
	
	// allows a user to join a group
	public boolean joinGroup(User user, Group group) {
		membership m = new membership(user, group);
		return (group.addMember(m));
	}
	
	// allows a user to leave a group
	public boolean leaveGroup(User user, Group group) {
		membership m = group.getMembership(user.getId());
		return (group.removeMember(m));
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
			if (writable) {		//If there is a file to write to
				try {				//Try adding the new category
					WriteFile.addCategoryToFile(c, fileNames.get(2));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
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
			
			if (!Validator.validateGroupNameExists(c.getGroupsAlphabetically(), groupName)) {
			
				c.createGroup(groupName);	//create group within category, returns true/false depending on if group was created	NOTICE: This may require more variables as the Group class is updated
				if (writable) {					//If there is a file to write to
					Group g = Validator.getGroupFromName(c.getGroupsAlphabetically(), groupName);	//Get the group
					try {							//Try adding the group to the file
						WriteFile.addGroupToFile(g, fileNames.get(3), categoryName);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				return true;
				
			}
			else {
				return false;
			}
		}
	}
	// allows the user to login
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
	

	// allows a post to be created
	//FIXME : Add Unit Tests, needs to verify membership exists between User and Group
	public boolean createNewPost(Group group, String postTitle, String postBody) {
		String find = group.getGroupWriteData(currentCategory.getName());
		
		membership m = getMembership(group, currentUser);
		int id = group.getPostId();
		Post p = new Post(m, postTitle, postBody, id);
		group.addNewPost(p);
		
		if (writable) {
			try {
				WriteFile.addPostToFile(p, fileNames.get(5));
				
				String replace = group.getGroupWriteData(currentCategory.getName());
				WriteFile.updateGroupinFile(find, replace, fileNames.get(3));
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return true;
	}
	

	//FIXME : Add Unit Tests

	public boolean createNewResponse(Group group, String responseBody, Post post) {
		membership m = getMembership(group, currentUser);
		Response r = new Response(m, responseBody, post.getId());
		return (currentPost.addResponse(r));		
	}
	

	// gets the membership of the group and user inputed
	//FIXME : Add Unit Tests
	public membership getMembership(Group group, User user) {
		ArrayList<membership> memberships = group.getMembers();
		for (membership m : memberships) {
			if (m.getUser() == user) {
				return m;
			}
		}
		return null;
	}

	// returns all category alphabetically
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
	
	// returns all groups alphabetically
	public ArrayList<Group> getAllGroups_Alphabetically() {
		ArrayList<Group> groups = new ArrayList<>();
		groups.addAll(getAllGroups());
		Collections.sort(groups, new SortGroupsByName());

		return groups;
	}

	// returns users alphabetically
	public ArrayList<User> getUsers_Alphabetically() {


		Collections.sort(users, new SortUsersByName());


		return users;
	}
	
	//FIXME : Add Unit Tests
	public ArrayList<Post> getPosts_InGroupByDate(Group g) {
		
		ArrayList<Post> alPosts = g.getPost();
		
		Collections.sort(alPosts, new SortPostsByDate());
		
		return alPosts;
		
	}
	
	// returns the status if the user is logged in
	//FIXME : Add Unit Tests
	public boolean isLoggedIn() {
		return userSignedIn;
	}
	
	//returns the status of the admin
	//FIXME : Add Unit Tests
	public boolean isAdmin() {
		return adminSignedIn;
	}
	
	public boolean isUserAdmin(User u) {
		if (u instanceof Admin) {
			return true;
		}
		
		return false;
	}
	
	// gets current user
	//FIXME : Add Unit Tests
	public User getCurrentUser() {
		return currentUser;
	}
	

	// gets current category
	//FIXME : Add Unit Tests
	public category getCurrentCategory() {
		return currentCategory;
	}


	// sets the current category
	//FIXME : Add Unit Tests
	public void setCurrentCategory(category currentCategory) {
		this.currentCategory = currentCategory;
	}

	// gets the current group
	//FIXME : Add Unit Tests
	public Group getCurrentGroup() {
		return currentGroup;
	}


  // sets the current group
	//FIXME : Add Unit Tests
	public void setCurrentGroup(Group currentGroup) {
		this.currentGroup = currentGroup;
	}
	
	//FIXME : Add Unit Tests
	public Post getCurrentPost() {
		return currentPost;
	}

	//FIXME : Add Unit Tests
	public void setCurrentPost(Post currentPost) {
		this.currentPost = currentPost;
	}

	//FIXME : Add Unit Tests
	// allows a user to logout
	public void logout() {
		currentUser = null;
	}

	// sorts admins alphabetically
	public ArrayList<Admin> getAdmins_Alphabetically() {
		Collections.sort(admins, new SortUsersByName());
		return admins;

	}
	
	// returns true if a user is a member of group, returns false if otherwise
	public Boolean isUserOfGroup(User u, Group g) {
		if (u == null) {
			return false;
		}
		User u1 = g.getMember(u.getId());
		
		if(u1 != null) {
			return true;
		}
		return false;
	}
	
	// returns an arraylist of groups that a user is in
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
	 //returns an arraylist of of all the users in group
	 public ArrayList<User> getUsersInGroup(Group group) {
		 ArrayList<User> userInGroup = new ArrayList<>();
		 for (User u: users) {
			 if (group.isMemberInGroup(u.getId()) == true){
				 userInGroup.add(u);
			 }
		 }
		 return userInGroup;
	 }
	
	 //returns an arraylist of all the groups in category alphabetically
	 public ArrayList<Group> getGroupsInCategory_Alphabetically(category c) {
		 
		 return c.getGroupsAlphabetically();
	}
	 
	//helper method, returns a list of all posts.
	//FIXME : Add Unit Tests
	public ArrayList<Post> getAllPost(){
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
	

	//FIXME : Add Unit Tests
	public ArrayList<Response> viewAllPostResponses (Post p) {
		
		ArrayList<Response> alResponses = p.getResponse();
			
		Collections.sort(alResponses, new SortPostsByDate());
		
		return alResponses;
  }

	public ArrayList<Response> viewAllPostResponses () {
		if (currentPost != null) {
			return currentPost.getResponse();
		}
		return null;
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
				 User u = r1.getUser();
				 if(user == u){
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
					 User u = r1.getUser();
					 if(user == u){
						 results.add(r1);
					 }	
				 }
			 }
		 }
		 return results;
	 }

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
	 
	 //loops through the post arrayList and records all the posts of a given group
	 public ArrayList<Post> viewPostsInGroup(Group group) {
		 ArrayList<Post> posts = new ArrayList<>();
		 posts.addAll(group.getPost());
		 return posts;
	}
	 
	 //User story 25
	 //checks if the post has the user if so it gets the responses from the post and returns an arraylist of responses.
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

	 // uses the validator class to get the category by name
	 public category getCategoryByName(String catName) {
		 return Validator.getCategoryFromName(categories, catName);
	 }
	 
	 // returns the users alphabetically by the username
	 public ArrayList<User> getUsers_Alphabetically_ByUsername(){
		 Collections.sort(users, new SortUsersByUsername());
		 
		 return users;
	 }
	 
	 // returns the admins alphabetically by the username
	 public ArrayList<Admin> getAdmins_Alphabetically_ByUsername() {
		 Collections.sort(admins, new SortUsersByUsername());
		 
		 return admins;
	 }
	 
	 // uses the validator class to get the group by name
	 public Group getGroupByName(String name) {
		 ArrayList<Group> allGroups = this.getAllGroups();
		 
		 return Validator.getGroupFromName(allGroups, name);
	 }
	 
	 // uses the Validator class to sort the user by username
	 public User getUserByUsername(String username) {
		 return Validator.getUserFromUsername(users, username);
	 }

	 	//FIXME: add tests
	 public Post getPostByGroupId(Group g, int id) {
		 return Validator.getPostFromId(g.getPost(), id);
	}

	 public String getSimpleDate(Date date) {
			String pattern = "dd MMM yyyy";
			SimpleDateFormat df = new SimpleDateFormat(pattern);
			return df.format(date);
	 }
	 
	 	//FIXME: Add unit tests
	 public String getSimpleTime(Date date) {
			String pattern = "h:mm a";
			SimpleDateFormat df = new SimpleDateFormat(pattern);
			return df.format(date);
	 }
	 
	//FIXME: add test methods
	public ArrayList<membership> getAllMemberships() {
		 ArrayList<membership> memberships = new ArrayList<membership>();

		 for (Group g : this.getAllGroups_Alphabetically()) {

			 memberships.addAll(g.getMembers());

		 }

		 return memberships;
	}
}

