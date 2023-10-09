package Project;

import java.util.Date;

public class User {
	
	private String id;
	private String name;
	private Date birthdate;
	private String city;
	private String state;
	private String password;
	private Date registeredDate;	

	

	
	public User(String name, String id, String password) {
		this.name = name;
		this.id = id;
		this.password = password;
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
	
	public String name() {
		return name;
	}
	
	public Date birthday(){
		return birthdate;
	}
	
	public String city() {
		return city;
	}
	
	public String state() {
		return state;
	}
	
	public Date registeredDate() {
		return registeredDate;
	}
	

}
