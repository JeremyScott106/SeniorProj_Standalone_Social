package Project;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Group implements Comparable<Group> {

    private String groupName;
    private ArrayList<membership> memberships;
    private ArrayList<Post> posts;

    public Group (String groupName) {
        this.groupName = groupName;
        this.memberships = new ArrayList<>();
        this.posts = new ArrayList<>();

    }

    //Gets the name of the groups that are created
    public String getGroupName() {
        return groupName;
    }

    //Adds members into groups
    public boolean addMember(membership m) {

        if (Validator.validateMemberExistsInGroup(m, memberships)) {
        	return false;
        }
        else {
        	memberships.add(m);
        	return true;
        }
        return false;
    }


    //Gets the list of member in the group
    public ArrayList<membership> getMembers() {
        return memberships;
    }

    //Find members using their userID
    public User getMember (String userId) {
        for(membership m : memberships) {
        	User u = m.getUser();
            if(u.getId().equals(userId)) {
                return u;
            }
        }
        return null;
    }

    //Checks to see if a member is already a part of a group
    public boolean isMemberInGroup (String memberId) {
        for(membership m : memberships) {
        	User u = m.getUser();
            if(u.getId().equals(memberId)) {
                return true;
            }
        }
        return false;

    }

    //Adds a new post to the group
    public boolean addPost(Post p) {
        boolean isMember = false;
        for (Post p1 : posts) {
            if (p1.equals(p)) {
                isMember = true;
                break;
            }
        }
        if (!isMember) {
            posts.add(p);
            return true;
        }
        return false;
    }

    //Gets the list of post made within a group
    public ArrayList<Post> getPost() {
        return posts;
    }
    
    
    
    public String getGroupWriteData(String catName) {
    	
    	String groupData = "@START\n" + 
    						"@GROUP\n" + 
    						"@NAME=" + groupName + "\n" + 
    						"@CATEGORY=" + catName + "\n" + 
    						"@END\n\n";
    	
    	return groupData;
    	
    }

	@Override
	public int compareTo(Group g) {
		if (g.getGroupName().equals(groupName)) {
			return 1;
		}
		return 0;
	}

}