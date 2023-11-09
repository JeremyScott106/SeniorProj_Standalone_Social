package Project;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Banned implements Comparable<Banned> {
	private User user;
	private Group group;
	private Date registeredDate;


    public Banned(User user, Group group, String registeredDate) {
        this.user = user;
        this.group = group;
        
		try {
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			this.registeredDate = df.parse(registeredDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public Banned(User user, Group group) {
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
	
	public String getBannedWriteData() {
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		String regDate = df.format(registeredDate);
		
		String bannedData = "@START\n" + 
								"@BANNED\n" + 
								"@USER=" + user.getId() + "\n" + 
								"@GROUP=" + group.getGroupName() + "\n" +
								"@REGISTEREDDATE=" + regDate + "\n" + 
								"@END\n\n";
		return bannedData;
	}
	
	public int compareTo(Banned b) {
		
		if (user.compareTo(b.getUser()) == 1) {
			
			if (group.compareTo(b.getGroup()) == 1) {
				return 1;
			}
		}
		return 0;
		
	}

}
