package Project;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class User {

	private String id;
	private String name;
	private Date birthdate;
	private String city;
	private String state;
	private String password;
	private Date registeredDate;
	private Map<String, Integer> groupMemberships;


	public User(String name, String id, String password) {
		this.name = name;
		this.id = id;
		this.password = password;
        groupMemberships = new HashMap<>();
	}


	public User(String id) {
		this.id = id;
	}


	public String getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public Date getBirthday(){
		return birthdate;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public Date getRegisteredDate() {
		return registeredDate;
	}

    public Integer getGroupStatus(String groupName) {
        return groupMemberships.get(groupName);
    }

    public void joinGroup(String groupName) {
        groupMemberships.put(groupName, 1);
    }

    public void leaveGroup(String groupName) {
        groupMemberships.put(groupName, 0);
    }

}
