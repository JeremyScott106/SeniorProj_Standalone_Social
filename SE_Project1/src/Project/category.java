package Project;
import java.util.ArrayList;

public class category {

	String name;
	private ArrayList<group> groups;

	public category(String name) {
		this.name = name;
		groups = new ArrayList<>();
	}


	public String getName() {
		return name;
	}


	public ArrayList<group> getGroups() {
		return groups;
	}

}
