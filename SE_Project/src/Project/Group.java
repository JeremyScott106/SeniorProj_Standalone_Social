package Project;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Group {

    private String groupName;
    private ArrayList<membership> memberships;
    private List<String> posts;


    public Group (String groupName) {
        this.groupName = groupName;
        this.memberships = new ArrayList<>();
    }

    //Gets the name of the groups that are created
    public String getGroupName() {
        return groupName;
    }

    //Adds members into groups
    public void addMember(User user, Group group) {
        membership m1 = new membership(user, group);
        boolean isMember = false;
        for (membership m : memberships) {
            if (m.getUser() == user) {
                isMember = true;
                break;
            }
        }
        if (!isMember) {
            memberships.add(m1);
        }
    }


    //Gets the list of member in the group
    public ArrayList<membership> getMembers() {
        return memberships;
    }

    //Find members using their memberID
    public User getMember (String memberId) {
        for(membership m : memberships) {
        	User u = m.getUser();
            if(u.getId().equals(memberId)) {
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
    public void addPost(String author, String post) {
        //Need the post class to write this
    }

    //Gets the list of post made within a group
    public List<String> getPost() {
        return posts;
    }

}