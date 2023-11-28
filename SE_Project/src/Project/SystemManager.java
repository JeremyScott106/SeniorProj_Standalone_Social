package Project;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.text.SimpleDateFormat;

public class SystemManager {

	private ArrayList<Admin> admins;
	private boolean adminSignedIn;		//Admin sign in status, true if an Admin is signed in
	private User currentUser;
	private category currentCategory;
	private Group currentGroup;
	private Post currentPost;
	
	private ArrayList<category> categories;
	private ArrayList<String> fileNames;

	private ArrayList<User> users;
	private boolean userSignedIn;		//User sign in status, true if any User is signed in
	private boolean writable;

	//test:2
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

	//test:2
	public boolean addCategory(category c) {
		
		if (Validator.validateCategoryExists(c, categories)) {
			return false;
		}
		else {
			categories.add(c);
			return true;
		}
		
	}

	//test:2
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

    //test:1
	//US31 - Administrator can ban a user from a group
	public void banUser(Banned b) {
		 User u = b.getUser();
		 Group g = b.getGroup();
		 membership m = g.getMembership(u.getId());
		 g.removeMember(m);
		 g.addBanned(b);
	 }

	//test:2
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

	//test:3									//Assumes GUI will send over just category name, not category object
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

	//test:1
	//US39 - User can request a new group
	public boolean createNewGroup(category c, String groupName) {
		Group g = new Group(groupName);
		return(c.addGroup(g));
	}

	//test:1
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

	//test:1
	// gets the membership of the group and user inputted
	public boolean createNewResponse(Group group, String responseBody, Post post) {
		membership m = getMembership(group, currentUser);
		Response r = new Response(m, responseBody, post.getId());
		return (currentPost.addResponse(r));		
	}

	
	//test:1
	// gets the membership of the group and user inputed
	public membership getMembership(Group group, User user) {
		ArrayList<membership> memberships = group.getMembers();
		for (membership m : memberships) {
			if (m.getUser() == user) {
				return m;
			}
		}
		return null;
	}


	//test:1
	// allows a post to be deleted
	//US36 - Administrator can remove a post
	public boolean deleteNewPost(Post p) {
		Group g = p.getGroup();
		return(g.removePost(p));
	}

	public void downVotePost(Post P) {
		P.subScore();
	}

	public void downVoteResponse(Response R) {
		R.subScore();
	}

	//test:2
	//US33 - User can flag a post or response that I find problematic
	public void flagPost(Post p) {
		p.setFlagTrue();
	}

	public void flagResponse(Response r) {
		r.setFlagTrue();
	}

	//test:1
	// sorts admins alphabetically
	public ArrayList<Admin> getAdmins_Alphabetically() {
		Collections.sort(admins, new SortUsersByName());
		return admins;
	
	}

	//test:1
	 // returns the admins alphabetically by the username
	 public ArrayList<Admin> getAdmins_Alphabetically_ByUsername() {
		 Collections.sort(admins, new SortUsersByUsername());
		 
		 return admins;
	 }

	public User getAdminByUsername(String username) {
		 return Validator.getAdminFromUsername(admins, username);
	 }

	//test:1
	 // return a list of names of members
	 //US29 - Administrator can view a list of Users and the corresponding group(s) they are suspendend from
	 public ArrayList<Suspended> getAllSuspensions(){
		ArrayList<Group> groups = getAllGroups();
		ArrayList<Suspended> suspensions = new ArrayList<>();
		for(Group g : groups) {
			suspensions.addAll(g.getSuspended());
		}
		return suspensions;
	 }

	//test:1 
	//US32 - Administrator can view a list of Users and the corresponding group(s) they are banned from
	public ArrayList<Banned> getAllBans(){
		ArrayList<Group> groups = getAllGroups();
		ArrayList<Banned> bans = new ArrayList<>();
		for(Group g : groups) {
			bans.addAll(g.getBanned());
		}
		return bans;
	}

	//test:1

	public ArrayList<Post> getPosts_InGroupByDate(Group g) {
		
		ArrayList<Post> alPosts = g.getPost();
		
		Collections.sort(alPosts, new SortPostsByDate());
		
		return alPosts;
		
	}

	//test:1
	// returns the status if the user is logged in
	//FIXME : Add Unit Tests
	public boolean isLoggedIn() {
		return userSignedIn;
  }
  
	// return a list of bans sorted by username
	public ArrayList<Banned> getAllBans_ByUsername(){
		ArrayList<Banned> bans = getAllBans();
		Collections.sort(bans, new SortBannedByUsername());
		return bans;

	}

	//test:1
	//US34 - Administrator can view all flagged post or responses
	public ArrayList<Object> getAllFlaggedPostAndResponses(){
		ArrayList<Post> posts = getAllPost();
		ArrayList<Response> responses = new ArrayList<>();
		ArrayList<Object> objects = new ArrayList<>();
		for(Post p : posts) {
			responses.addAll(p.getResponse());
			if(p.getFlag() == true) {
				objects.add(p);
			}
		}

		
		return false;
	}

	//test:1
	// gets current user
	//FIXME : Add Unit Tests
	public User getCurrentUser() {
		return currentUser;
  }
  
		for(Response r : responses) {
			if(r.getFlag() == true) {
				objects.add(r);
			}
		}
		return objects;

	}

	//test:1
	// gets all flagged posts
	public ArrayList<Post> getAllFlaggedPost(){
		ArrayList<Post> posts = getAllPost();
		ArrayList<Post> results = new ArrayList<>();
		for(Post p : posts) {
			if(p.getFlag() == true) {
				results.add(p);
			}
		}
		return results;
	}

	//test:1
	// gets all flagged responses
	public ArrayList<Post> getAllFlaggedResponses(){
		ArrayList<Post> posts = getAllPost();
		ArrayList<Response> responses = new ArrayList<>();
		ArrayList<Post> results = new ArrayList<>();
		for(Post p : posts) {
			responses.addAll(p.getResponse());
		}
		for(Response r : responses) {
			if(r.getFlag() == true) {
				results.add(r);
			}
		}
		return results;
	}

	//helper method, returns a list of all groups.
	private ArrayList<Group> getAllGroups(){
		ArrayList<Group> groups = new ArrayList<>();
		for(category c : categories) {
			groups.addAll(c.getGroupsAlphabetically());
		}
		return groups;
	}

	//test:1
	// returns all groups alphabetically
	public ArrayList<Group> getAllGroups_Alphabetically() {
		ArrayList<Group> groups = getAllGroups();
		Collections.sort(groups, new SortGroupsByName());
	
		return groups;
	}

	//FIXME: add test methods
	public ArrayList<membership> getAllMemberships() {
		 ArrayList<membership> memberships = new ArrayList<membership>();
	
		 for (Group g : this.getAllGroups_Alphabetically()) {
	
			 memberships.addAll(g.getMembers());
	
		 }
	
		 return memberships;
	}

	//test:3
	//helper method, returns a list of all posts.
	//FIXME : Add Unit Tests
	public ArrayList<Post> getAllPost(){
		ArrayList<Group> groups = getAllGroups_Alphabetically();
		ArrayList<Post> posts = new ArrayList<>();
		for(Group g: groups) {
			posts.addAll(g.getPost());
		}
		return posts;
	}

	//test:1
	 // return a list of suspensions sorted by username
	 public ArrayList<Suspended> getAllSuspensions_ByUsername(){
		 ArrayList<Suspended> suspensions = getAllSuspensions();
		 Collections.sort(suspensions, new SortSuspensionsByUsername());
		 return suspensions;
	 }

	//test:1
	// returns all category alphabetically
	public ArrayList<category> getCategories_Alphabetically() {
	
		Collections.sort(categories, new SortCategoriesByName());
	
		return categories;
	}

	//test:1
	 // uses the validator class to get the category by name
	 public category getCategoryByName(String catName) {
		 return Validator.getCategoryFromName(categories, catName);
	 }


	//test:1
	// gets current category
	public category getCurrentCategory() {
		return currentCategory;
	}

	//test:1
	public Group getCurrentGroup() {
		return currentGroup;
	}

	//FIXME: Add Unit Tests

	 public category getCategoryByGroup(Group group) {
		 for (category c : this.getCategories_Alphabetically()) {
			 for (Group g : c.getGroups()) {
				 if (g == group) {
					 return c;
				 }
			 }
		 }
		 return null;
	 }

	// gets current user
	//FIXME : Add Unit Tests
	public User getCurrentUser() {
		return currentUser;
	}

	//test:1
	public Post getCurrentPost() {
		return currentPost;
	}

	//test:1
	 //returns an arraylist of all the groups in category alphabetically
	 public ArrayList<Group> getGroupsInCategory_Alphabetically(category c) {
		 
		 return c.getGroupsAlphabetically();
	}

	//test:2
	 // uses the validator class to get the group by name
	 public Group getGroupByName(String name) {
		 ArrayList<Group> allGroups = this.getAllGroups();
		 
		 return Validator.getGroupFromName(allGroups, name);
	 }

		//test:1
		// returns an arraylist of groups that a user is in
		 public ArrayList<Group> getGroupsByUser(User user) {
		 ArrayList<Group> group = new ArrayList<>();
		 ArrayList<Group> groups = getAllGroups();
		 for (Group g: groups) {
			 if (g.isMemberInGroupInMembership(user.getId()) == true){
				 group.add(g);
			 }
		 }
		 return group;
	}

	// returns the posts with the largest up votes this includes responses
	public ArrayList<Post> getLargestUpVotes(){
		ArrayList<Post> posts = getPosts_ByScore();
		return posts;
		 
	}


	//test:1
	public ArrayList<Response> viewAllPostResponses () {
		if (currentPost != null) {
			return currentPost.getResponse();
		}
		return null;
	}
	
	//test:1
	//User story 22
	//takes in a user and loops through all the posts. If a post was created by the user it records the postBody. Also checks each post for Responces. if the users are the same records ResponceBody to the string.
	 public ArrayList<Object> viewUsersPostsResponses(User user) {
		 ArrayList<Object> results = new ArrayList<Object>();
		 ArrayList<Post> posts = new ArrayList<>();
		 posts.addAll(getAllPost());
		 for(Post p : posts) {
			 if(user == p.getUser()) {
				  results.add(p);
			 }
			 ArrayList<Response> r = p.getResponse();
			 for (Response r1 : r){
				 User u = r1.getUser();
				 if(user == u){
					  results.add(r1);
				 }	
			 }
		 }
		 Collections.sort(results, new SortObjectsByDate());
		 return results;
	 }
	 
	 //test:1
	 //User story 23
	 // takes in user and a group. if the post is in the group given then it records all the posts and responses created by the user in that group. 
	 public ArrayList<Object> viewUsersPostsResponsesInGroup(User user, Group group) {
		 ArrayList<Object> results = new ArrayList<>();
		 ArrayList<Post> posts = getAllPost();
		 for(Post p : posts) {
			 if(group == p.getGroup()) {
				 if(user == p.getUser()) {
					 results.add(p);
				 }
				 ArrayList<Response> r = p.getResponse();
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

     //test:1
	 //User story 24
	 //loops through the post arrayList and records all the posts and responses of a given group
	 public ArrayList<Object> viewPostsResponsesInGroup(Group group) {
		 ArrayList<Object> results = new ArrayList<>();
		 ArrayList<Post> posts = getAllPost();
		 for(Post p : posts){
			 if(group == p.getGroup()) {
				 results.add(p);
				 ArrayList<Response> r = p.getResponse();
				 for (Response r1 : r){
					 results.add(r1);
				 }
			}
		 }
		 return results;
	}
	 
     //test:1
	 //loops through the post arrayList and records all the posts of a given group
	 public ArrayList<Post> viewPostsInGroup(Group group) {
		 ArrayList<Post> posts = group.getPost();
		 return posts;
	}
	 
     //test:1
	 //User story 25
	 //checks if the post has the user if so it gets the responses from the post and returns an arraylist of responses.
	 public ArrayList<Object> viewMyResponses(User user, Post post) {
		 ArrayList<Response> pr = post.getResponse();
		 ArrayList<Object> results = new ArrayList<>();
		 for (Response r1 : pr){
			 User u = r1.getUser();
			 if(user == u){
				 results.add(r1);
			}	
		}
		return results;
	} 

	 //test:1
	 // uses the validator class to get the category by name
	 public category getCategoryByName(String catName) {
		 return Validator.getCategoryFromName(categories, catName);
	 }
	 
     //test:1
	 // returns the users alphabetically by the username
	 public ArrayList<User> getUsers_Alphabetically_ByUsername(){
		 Collections.sort(users, new SortUsersByUsername());
		 
		 return users;
	 }
	 
     //test:1
	 // returns the admins alphabetically by the username
	 public ArrayList<Admin> getAdmins_Alphabetically_ByUsername() {
		 Collections.sort(admins, new SortUsersByUsername());
		 
		 return admins;
	 }
	 
     //test:2
	 // uses the validator class to get the group by name
	 public Group getGroupByName(String name) {
		 ArrayList<Group> allGroups = this.getAllGroups();
		 
		 return Validator.getGroupFromName(allGroups, name);
	 }
	 
     //test:2
	 // uses the Validator class to sort the user by username
	 public User getUserByUsername(String username) {
		 return Validator.getUserFromUsername(users, username);
	 }
	 
	 //test:1
	 //US28 - Administrator can suspend a User from a group and they will have a cooling period
	 //TODO add cooling period
	 public void suspendUser(Suspended s) {
		 User u = s.getUser();
		 Group g = s.getGroup();
		 membership m = g.getMembership(u.getId());
		 g.removeMember(m);
		 g.addSuspended(s);
 	 }
	 
	 //test:1
	 // return a list of names of members
	 //US29 - Administrator can view a list of Users and the corresponding group(s) they are suspendend from
	 public ArrayList<Suspended> getAllSuspensions(){
		ArrayList<Group> groups = getAllGroups();
		ArrayList<Suspended> suspensions = new ArrayList<>();
		for(Group g : groups) {
			suspensions.addAll(g.getSuspended());
		}
		return suspensions;
	 }
	
	 //test:1
	 // return a list of suspensions sorted by username
	 public ArrayList<Suspended> getAllSuspensions_ByUsername(){
		 ArrayList<Suspended> suspensions = getAllSuspensions();
		 Collections.sort(suspensions, new SortSuspensionsByUsername());
		 return suspensions;
	 }
	 
	 //test:1
	 //FIXME: add tests
	 public Post getPostByGroupId(Group g, int id) {
		 return Validator.getPostFromId(g.getPost(), id);
	 }
	 
	 //test:1
	 public String getSimpleDate(Date date) {

	// gets the membership of the group and user inputed
	public membership getMembership(Group group, User user) {
		ArrayList<membership> memberships = group.getMembers();
		for (membership m : memberships) {
			if (m.getUser() == user) {
				return m;
			}
		}
		return null;
	}

	//test:2
	//US41 - User can view a list of posts with the largest number of up-votes summed across a post and all its responses
	// sorts post by score
	public ArrayList<Post> getPosts_ByScore() {
		ArrayList<Post> posts = getAllPost();
		Collections.sort(posts, new SortPostsByCombinedScore());
		return posts;
	}

	//FIXME: add tests
	 public Post getPostByGroupId(Group g, int id) {
		 return Validator.getPostFromId(g.getPost(), id);
	 }

	//test:1
	public ArrayList<Post> getPosts_InGroupByDate(Group g) {
		
		ArrayList<Post> alPosts = g.getPost();
		
		Collections.sort(alPosts, new SortPostsByDate());
		
		return alPosts;
		
	}

	public String getSimpleDate(Date date) {

			String pattern = "dd MMM yyyy";
			SimpleDateFormat df = new SimpleDateFormat(pattern);
			return df.format(date);
	 }

	 public String getSimpleTime(Date date) {
			String pattern = "h:mm a";
			SimpleDateFormat df = new SimpleDateFormat(pattern);
			return df.format(date);
	 }

	 
	//test:1
	//FIXME: add test methods
	public ArrayList<membership> getAllMemberships() {
		 ArrayList<membership> memberships = new ArrayList<membership>();
  }

	//test:1
	// returns users alphabetically
	public ArrayList<User> getUsers_Alphabetically() {
	
	
		Collections.sort(users, new SortUsersByName());
	
	
		return users;
	}

	//test:1
	 // returns the users alphabetically by the username
	 public ArrayList<User> getUsers_Alphabetically_ByUsername(){
		 Collections.sort(users, new SortUsersByUsername());
		 
		 return users;
	 }

	//test:2
	 // uses the Validator class to sort the user by username
	 public User getUserByUsername(String username) {
		 return Validator.getUserFromUsername(users, username);
	 }

	//test:1
	 //returns an arraylist of of all the users in group
	 public ArrayList<User> getUsersInGroup(Group group) {
		 ArrayList<User> userInGroup = new ArrayList<>();
		 for (User u: users) {
			 if (group.isMemberInGroupInMembership(u.getId()) == true){
				 userInGroup.add(u);
			 }
		 }
		 return userInGroup;
	 }

	//test:1
	//returns the status of the admin
	//FIXME : Add Unit Tests
	public boolean isAdmin() {
		return adminSignedIn;
	}

	// returns the status if the user is logged in
	//FIXME : Add Unit Tests
	public boolean isLoggedIn() {
		return userSignedIn;
	}

	//test:1
	public boolean isUserAdmin(User u) {
		if (u instanceof Admin) {
			return true;
		}
		
		return false;
	}

	//test:1
	// returns true if a user is a member of group, returns false if otherwise
	public Boolean isUserOfGroup(User u, Group g) {
		if (u == null) {
			return false;
		}
		User u1 = g.getUserInMembership(u.getId());
		
		if(u1 != null) {
			return true;
		}
		return false;
	}

	//test:1
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

    //test:6
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
	//test:1
	// allows a user to logout
	public void logout() {
		currentUser = null;
	}

	//test:2
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

	//test:1
	//US30 - Administrator can reinstate a suspended user to good standing in a group
	public void reinstateSuspendedUser(Suspended s, membership m) {
		Group g = s.getGroup();
		g.addMember(m);
		g.removeSuspended(s);
	}

	//test:2
	//US35 - Administrator can remove all flags on a post or response
	public void removeFlagOnPost(Post p) {
		p.setFlagFalse();
	}

	public void removeFlagOnResponse(Response r) {
		r.setFlagFalse();
	}

	//test:1
	//US37 - Administrator can remove a response to a post
	//US38 - User can remove a response to a post
	public void removeResponseToPost(Post p, Response r) {
		if(p.getResponse().contains(r)){
			p.removeResponse(r);
		}
	}
	
	//test:1
	// sets the current category
	public void setCurrentCategory(category currentCategory) {
		this.currentCategory = currentCategory;
	}

	//test:1
	// sets the current group
	public void setCurrentGroup(Group currentGroup) {
		this.currentGroup = currentGroup;
	}

	//test:1
	public void setCurrentPost(Post currentPost) {
		this.currentPost = currentPost;
	}

	//test:1
	 //US28 - Administrator can suspend a User from a group and they will have a cooling period
	 //TODO add cooling period
	 public void suspendUser(Suspended s) {
		 User u = s.getUser();
		 Group g = s.getGroup();
		 membership m = g.getMembership(u.getId());
		 g.removeMember(m);
		 g.addSuspended(s);
	 }

	public SystemManager() {
		userSignedIn = false;
		adminSignedIn = false;
		users = new ArrayList<User>();
		admins = new ArrayList<Admin>();		
		categories = new ArrayList<category>();
		
		this.writable = false;
	
	}

	//test:1
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

	//test:4
	// US40 - User can up-vote or down-vote a post or response other than my own
	// add a validator

	public ArrayList<Voted> getAllVotes(){
		ArrayList<Voted> votes = new ArrayList<>();
		for(User u : users) {
			votes.addAll(u.getVotedList());
		}
		return votes;
	}
	
	//test:2
	 public boolean upvote(Voted v) {
		 if (Validator.validateVotedExists(v, v.getUser().getVotedList()) == true) {
			 return false;
		 }
		 else {
				 v.up();
				 v.getUser().addVoted(v);
				 v.getPost().addScore();
				 return true;
		 }
	}
	 
	 //test:2
	 public boolean downvote(Voted v) {
		 if (Validator.validateVotedExists(v, v.getUser().getVotedList()) == true) {
			 return false;
		 }
		 else {
				 v.down();
				 v.getUser().addVoted(v);
				 v.getPost().subScore();
				 return true;
		 }
	}
	
	//test:2
	//US41 - User can view a list of posts with the largest number of up-votes summed across a post and all its responses
	// sorts post by score
	public ArrayList<Post> getPosts_ByScore() {
		ArrayList<Post> posts = getAllPost();
		Collections.sort(posts, new SortPostsByCombinedScore());
		return posts;
	}
	
	public void upVotePost(Post P) {
		P.addScore();
	}

	public void upVoteResponse(Response R) {
		R.addScore();
	}

	//test:1
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
	 
	//test:1
	 //User story 25
	 //checks if the post has the user if so it gets the responses from the post and returns an arraylist of responses.
	 public ArrayList<Object> viewMyResponses(User user, Post post) {
		 ArrayList<Response> pr = post.getResponse();
		 ArrayList<Object> results = new ArrayList<>();
		 for (Response r1 : pr){
			 User u = r1.getUser();
			 if(user == u){
				 results.add(r1);
			}	
		}
		return results;
	}


	//test:1
	// US42 - User can view a list of most up-voted Users
	public ArrayList<User> viewMostUpVotedUsers(){
		ArrayList<Post> posts = getPosts_ByScore();
		ArrayList<User> users = new ArrayList<>();
		for(Post p: posts) {
			users.add(p.getUser());
		}
		return users;
	}

	//test:1
	 //User story 24
	 //loops through the post arrayList and records all the posts and responses of a given group
	 public ArrayList<Object> viewPostsResponsesInGroup(Group group) {
		 ArrayList<Object> results = new ArrayList<>();
		 ArrayList<Post> posts = getAllPost();
		 for(Post p : posts){
			 if(group == p.getGroup()) {
				 results.add(p);
				 ArrayList<Response> r = p.getResponse();
				 for (Response r1 : r){
					 results.add(r1);
				 }
			}
		 }
		 return results;
	}
	 
   //test:1
	 //loops through the post arrayList and records all the posts of a given group
	 public ArrayList<Post> viewPostsInGroup(Group group) {
		 ArrayList<Post> posts = group.getPost();
		 return posts;
	}
	 
   //User story 22
	//takes in a user and loops through all the posts. If a post was created by the user it records the postBody. Also checks each post for Responces. if the users are the same records ResponceBody to the string.
	 public ArrayList<Object> viewUsersPostsResponses(User user) {
		 ArrayList<Object> results = new ArrayList<Object>();
		 ArrayList<Post> posts = new ArrayList<>();
		 posts.addAll(getAllPost());
		 for(Post p : posts) {
			 if(user == p.getUser()) {
				  results.add(p);
			 }
			 ArrayList<Response> r = p.getResponse();
			 for (Response r1 : r){
				 User u = r1.getUser();
				 if(user == u){
					  results.add(r1);
				 }	
			 }
		 }
		 Collections.sort(results, new SortObjectsByDate());
		 return results;
	 }

	//test:1
	 //User story 23
	 // takes in user and a group. if the post is in the group given then it records all the posts and responses created by the user in that group. 
	 public ArrayList<Object> viewUsersPostsResponsesInGroup(User user, Group group) {
		 ArrayList<Object> results = new ArrayList<>();
		 ArrayList<Post> posts = getAllPost();
		 for(Post p : posts) {
			 if(group == p.getGroup()) {
				 if(user == p.getUser()) {
					 results.add(p);
				 }
				 ArrayList<Response> r = p.getResponse();
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

	//test:1
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
}