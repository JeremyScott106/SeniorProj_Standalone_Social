package Project;

import java.util.ArrayList;
import java.util.Collections;

public class category {
	
	String name;
	ArrayList<group> groups;

	public category(String name) {
		this.name = name;
		this.groups = new ArrayList<group>();
	}
	
	
	public String getName() {
		return name;
	}
	
	public boolean addGroup(group g) {	
		if (Validator.validateGroupNameExists(groups, g.getName())) {	//If the name of group g already exists within the list of Groups
			return false;	//do not add the group and return false
		}
		else {
			groups.add(g);	//else, add the group
			return true;	//and return true
		}
	}
	
	public ArrayList<group> getGroupsAlphabetically() {
		Collections.sort(groups, new SortGroupsByName());
		
		return groups;
	}


	@Override
	public String toString() {
		return "category [name=" + name + ", groups=" + groups + "]";
	}

}
