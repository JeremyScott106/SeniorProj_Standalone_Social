package Project;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class membership implements Comparable<membership> {
	private User user;
	private Group group;
	private Date registeredDate;

	//test:1
    public membership(User user, Group group, String registeredDate) {
        this.user = user;
        this.group = group;
        
		try {
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			this.registeredDate = df.parse(registeredDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	//test:1
	public membership(User user, Group group) {
        this.user = user;
        this.group = group;
		registeredDate = new Date();
	}
	
	//test:1
	public User getUser() {
		return user;
	}
	
	//test:1
	public Group getGroup() {
		return group;
	}
	
	//test:1
	public Date getDate() {
		return registeredDate;
	}
	
	//test:1
	public String getMembershipWriteData() {
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		String regDate = df.format(registeredDate);
		
		String memberData = "@START\n" + 
								"@MEMBERSHIP\n" + 
								"@USER=" + user.getId() + "\n" + 
								"@GROUP=" + group.getGroupName() + "\n" +
								"@REGISTEREDDATE=" + regDate + "\n" + 
								"@END\n\n";
		return memberData;
	}
	
	//test:3
	@Override
	public int compareTo(membership m) {
		
		if (user.compareTo(m.getUser()) == 1) {
			
			if (group.compareTo(m.getGroup()) == 1) {
				return 1;
			}
		}
		return 0;
		
	}

}
