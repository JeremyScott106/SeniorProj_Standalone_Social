package Project;

import java.util.ArrayList;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

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
	
	
	
	public String getMembershipWriteData() {
		DateFormat df = new SimpleDateFormat("mm/dd/yyyy");
		String regDate = df.format(registeredDate);
		
		String memberData = "@START\n" + 
								"@MEMBERSHIP\n" + 
								"@USER=" + user.getId() + "\n" + 
								"@GROUP=" + group.getGroupName() + "\n" +
								"@REGISTEREDDATE=" + regDate + "\n" + 
								"@END\n\n";
		return memberData;
	}

}
