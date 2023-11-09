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


    public Banned(User user, Group group) {
        this.user = user;
        this.group = group;
	}
	
	public User getUser() {
		return user;
	}
	
	public Group getGroup() {
		return group;
	}

	
	public String getBannedWriteData() {
		
		String bannedData = "@START\n" + 
								"@BANNED\n" + 
								"@USER=" + user.getId() + "\n" + 
								"@GROUP=" + group.getGroupName() + "\n" +
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
