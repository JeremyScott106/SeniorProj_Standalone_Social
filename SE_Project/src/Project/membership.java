package Project;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class membership {
	private User user;
	private Group group;
	private Date registeredDate;
	
	public membership(User user, Group group, String registeredDate) {
		this.user = user;
		this.group = group;
		try {
			DateFormat df = new SimpleDateFormat("mm/dd/yyyy");
			this.registeredDate = df.parse(registeredDate);
		}catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public membership(User user, Group group) {
        this.user = user;
        this.group = group;
		registeredDate = new Date();
	}
	
	public User getUser() {
		return user;
	}
	
	public Group getGroup() {
		return group;
	}
	
	public Date getDate() {
		return registeredDate;
	}

}
