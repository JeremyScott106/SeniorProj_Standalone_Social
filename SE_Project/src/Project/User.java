package Project;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    public Integer getGroupStatus(String groupName) {	//In the event the given Group is not in the Map, 0 should be returned instead of null
        return groupMemberships.get(groupName);
    }

    public boolean joinGroup(String groupName) {	//This needs to check that the given Group is not already in the Map
        groupMemberships.put(groupName, 1);
        return true;
    }

    public boolean leaveGroup(String groupName) {	//This needs to check that the given Group is in the Map
        groupMemberships.put(groupName, 0);
        return true;
    }

}
