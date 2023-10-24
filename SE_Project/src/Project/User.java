package Project;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

	
	//Constructor is intended to be used for adding new Users
	public User(String name, String id, String password, String birthdate, String city, String state) {
		this.name = name;
		this.id = id;
		this.password = password;
		this.birthdate = new Date();
		try {
			DateFormat df = new SimpleDateFormat("mm/dd/yyyy");
			this.birthdate = df.parse(birthdate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.city = city;
		this.state = state;
		registeredDate = new Date();
        groupMemberships = new HashMap<>();
	}
	
	//Constructor is intended to be used for adding an Existing User read from the disk
	public User(String name, String id, String password, String birthdate, String city, String state, String registeredDate) {
		this.name = name;
		this.id = id;
		this.password = password;
		this.birthdate = new Date();
		try {
			DateFormat df = new SimpleDateFormat("mm/dd/yyyy");
			this.birthdate = df.parse(birthdate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.city = city;
		this.state = state;
		try {
			DateFormat df = new SimpleDateFormat("mm/dd/yyyy");
			this.registeredDate = df.parse(registeredDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
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

    public boolean joinGroup(String groupName, Group g, User u) {
    	Boolean v = Validator.validateUserInGroup(g, u);
    	if(v == false) {
            groupMemberships.put(groupName, 1);
            return true;
    	}
    	return false;
    }

    public boolean leaveGroup(String groupName, Group g, User u) {
    	Boolean v = Validator.validateUserInGroup(g, u);
    	if(v == true) {
            groupMemberships.put(groupName, 0);
            return true;
    	}
    	return false;
    }
    
}
