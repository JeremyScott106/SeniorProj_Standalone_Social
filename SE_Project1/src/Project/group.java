package Project;

import java.util.ArrayList;
import java.util.List;

public class group {

	private String groupName;
	private List<String> members; 
	private List<String> posts;
	
	
	public group (String groupName) {
		this.groupName = groupName; 
		this.members = new ArrayList<>();
	}
	
	//Gets the name of the groups that are created
	public String getGroupName() { 
		return groupName;
	}
	
	//Adds members into groups
	public void addMembers(String member) {
		members.add(member);
	}
	
	//Gets the list of member in the group
	public List<String> getMembers() {
		return members;
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
