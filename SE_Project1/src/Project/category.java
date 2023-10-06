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
	
	public boolean addGroup(group g) {	//Should check through list of groups and make sure this one has a unique name
		groups.add(g);
		return true;
	}
	
	public ArrayList<group> getGroupsAlphabetically() {
		Collections.sort(groups, new SortGroupsByName());
		
		return groups;
	}

}
