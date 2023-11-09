package Project;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Group implements Comparable<Group> {

    private String groupName;
    private ArrayList<membership> memberships;
    private ArrayList<Post> posts;
    private ArrayList<Banned> bans;
    private ArrayList<Suspended> suspensions;
    
    public Group (String groupName) {
        this.groupName = groupName;
        this.memberships = new ArrayList<>();
        this.posts = new ArrayList<>();
        this.bans = new ArrayList<>();
        this.suspensions = new ArrayList<>();
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
    }
    
    //Removes members into groups
    public boolean removeMember(membership m) {

        if (Validator.validateMemberExistsInGroup(m, memberships)) {
        	memberships.remove(m);
        	return false;
        }
        else {
        	return true;
        }
    }


    //Gets the list of member in the group
    public ArrayList<membership> getMembers() {
        return memberships;
    }

    //Find members using their userID
    public User getUserInMembership (String userId) {
        for(membership m : memberships) {
        	User u = m.getUser();
            if(u.getId().equals(userId)) {
                return u;
            }
        }
        return null;
    }
    
  //Find memberships using their userID
    public membership getMembership (String userId) {
        for(membership m : memberships) {
        	User u = m.getUser();
            if(u.getId().equals(userId)) {
                return m;
            }
        }
        return null;
    }

    //Checks to see if a member is already a part of a group
    public boolean isMemberInGroupInMembership (String memberId) {
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
    
    //removes a post from the group
    public boolean removePost(Post p) {
        boolean isMember = true;
        for (Post p1 : posts) {
            if (p1.equals(p)) {
                posts.remove(p);
                isMember = false;
            }
        }
        return isMember;
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
	
	
	public boolean compareName(String other) {
		
		if (other.equals(groupName)) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
    //Adds members into groups
    public boolean addBanned(Banned b) {

        if (Validator.validateBannedExistsInGroup(b, bans)) {
        	return false;
        }
        else {
        	bans.add(b);
        	return true;
        }
    }
    
    //Gets the list of member in the group
    public ArrayList<Banned> getBanned() {
        return bans;
    }

    //Find members using their userID
    public User getUserInBanned (String userId) {
        for(Banned b : bans) {
        	User u = b.getUser();
            if(u.getId().equals(userId)) {
                return u;
            }
        }
        return null;
    }
    
  //Find memberships using their userID
    public Banned getBanned (String userId) {
        for(Banned b : bans) {
        	User u = b.getUser();
            if(u.getId().equals(userId)) {
                return b;
            }
        }
        return null;
    }

    //Checks to see if a member is already a part of a group
    public boolean isMemberInGroupInBanned (String memberId) {
        for(Banned b : bans) {
        	User u = b.getUser();
            if(u.getId().equals(memberId)) {
                return true;
            }
        }
        return false;

    }

    //Gets the list of member in the group
    public ArrayList<Suspended> getSuspended() {
        return suspensions;
    }
    
    //Find members using their userID
    public User getUserInSuspended (String userId) {
        for(Suspended s : suspensions) {
        	User u = s.getUser();
            if(u.getId().equals(userId)) {
                return u;
            }
        }
        return null;
    }
    
  //Find memberships using their userID
    public Suspended getSuspended (String userId) {
        for(Suspended s : suspensions) {
        	User u = s.getUser();
            if(u.getId().equals(userId)) {
                return s;
            }
        }
        return null;
    }

    //Checks to see if a member is already a part of a group
    public boolean isMemberInGroupInSuspended (String memberId) {
        for(Suspended s : suspensions) {
        	User u = s.getUser();
            if(u.getId().equals(memberId)) {
                return true;
            }
        }
        return false;

    }
    
    //Adds members into suspensions
    public boolean addSuspended(Suspended s) {

        if (Validator.validateSuspendedExistsInGroup(s, suspensions)) {
        	return false;
        }
        else {
        	suspensions.add(s);
        	return true;
        }
    }
    
    //Removes members into groups
    public boolean removeSuspended(Suspended s) {

        if (Validator.validateSuspendedExistsInGroup(s, suspensions)) {
        	suspensions.remove(s);
        	return false;
        }
        else {
        	return true;
        }
    }

}