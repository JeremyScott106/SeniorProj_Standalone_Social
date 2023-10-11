package Project;

import java.util.ArrayList;
import java.util.List;

public class Group {

	private String groupName;
	private List<User> members; 
	private List<String> posts;


	public Group (String groupName) {
		this.groupName = groupName; 
		this.members = new ArrayList<>();
	}

	//Gets the name of the groups that are created
	public String getGroupName() { 
		return groupName;
	}

	//Adds members into groups
	public void addMember(User user) {
		if(!members.contains(user)) {
			members.add(user);
		}
	}

	//Gets the list of member in the group
	public List<User> getMembers() {
		return members;
	}

	//Find members using their memberID
	public User getMember (String memberId) {
		for(User user : members) {
			if(user.getId().equals(memberId)) {
				return user;
			}
		}
		return null;
	}

	//Checks to see if a member is already a part of a group
	public boolean isMemberInGroup (String memberId) {
		for(User user : members) {
			if(user.getId().equals(memberId)) {
				return true;
			}
		}
		return false;

	}


	//Adds a new post to the group
	public void addPost(String author, String post) {
		//Need the post class to write this
	}

	//Gets the list of post made within a group
	public List<String> getPost() {
		return posts;
	}

}
