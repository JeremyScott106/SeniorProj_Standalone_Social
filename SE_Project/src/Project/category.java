package Project;
import java.util.ArrayList;
import java.util.Collections;


public class category {

	String name;

	ArrayList<Group> groups;

	public category(String name) {
		this.name = name;
		this.groups = new ArrayList<Group>();
	}


	public String getName() {
		return name;
	}

	public boolean addGroup(Group g) {
		if (Validator.validateGroupNameExists(groups, g.getGroupName())) {	//If the name of group g already exists within the list of Groups
			return false;	//do not add the group and return false
		}
		else {
			groups.add(g);	//else, add the group	NOTICE: This may require more variables as the Group class is updated
			return true;	//and return true
		}
	}

	public boolean createGroup(String name) {
		if(Validator.validateGroupNameExists(groups, name)) {	//If there exists a group with the given name
			return false;	//Return false
		}
		else {
			Group g = new Group(name);	//else, create the group	NOTICE: This may require more variables as the Group class is updated
			groups.add(g);		//add group
			return true;		//and return true
		}
	}


	public ArrayList<Group> getGroupsAlphabetically() {
		Collections.sort(groups, new SortGroupsByName());

		return groups;
	}
	
    //Checks to see if a Group is already a part of a category
    public boolean isGroupInCategory (String groupName) {
        for(Group g : groups) {
            if(g.getGroupName().equals(groupName)) {
                return true;
            }
        }
        return false;
        
    }

	@Override
	public String toString() {
		return "category [name=" + name + ", groups=" + groups + "]";
	}



	public ArrayList<Group> getGroups() {
		return groups;
	}
	
	
	/*
	 * Formats Category data to be written
	 * Format:
	 * 
	 * @START
	 * @CATEGORY
	 * @NAME="name_of_category"
	 * @GROUP="name_of_group_under_category"
	 * ...
	 * @GROUP="name_of_group_under_category"
	 * @END
	 * 
	 */
	public String getCategoryWriteData() {
		String categoryData = "@START\n" + 
								"@CATEGORY\n" + 
								"@NAME=" + name + "\n";
		
		for (Group g : groups) {
			categoryData = categoryData.concat("@GROUP=" + g.getGroupName() + "\n");
		}
		
		categoryData = categoryData.concat("@END\n\n");
		
		return categoryData;
	}

}
