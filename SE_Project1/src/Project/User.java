package Project;

import java.util.Date;

public class User {
	
	private String name;
	private Date birthdate;
	private String city;
	private String state;
	private String id;
	private String password;
	private Date registeredDate;
	
	public User(String name, String id, String password) {
		this.name = name;
		this.id = id;
		this.password = password;
	}
	
	public String getId() {
		return id;
	}
	
	public String getPassword() {
		return password;
	}
	

}
