package Project;

import java.util.ArrayList;
import java.util.Date;

public class membership {
	private User user;
	private Group group;
	private Date registeredDate;

    public membership(User user, Group group, Date registeredDate) {
        this.user = user;
        this.group = group;
		this.registeredDate = registeredDate;
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
