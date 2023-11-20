package Project;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Suspended implements Comparable<Suspended> {
	private User user;
	private Group group;
	private Date suspensionDate;
	private Date expiredSuspensionDate;

	//test:1
    public Suspended(User user, Group group, String suspensionDate) {
        this.user = user;
        this.group = group;
        
		try {
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy h:mm a");
			this.suspensionDate = df.parse(suspensionDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy h:mm a");
			Date parsedExpiredDate = df.parse(suspensionDate);

            // Adding 5 minutes to the expiredSuspensionDate
            Calendar cal = Calendar.getInstance();
            cal.setTime(parsedExpiredDate);
            cal.add(Calendar.MINUTE, 5);

            this.expiredSuspensionDate = cal.getTime();

            }catch (ParseException e) {
            	e.printStackTrace();
            }
	}
	
	//test:1
	public Suspended(User user, Group group) {
        this.user = user;
        this.group = group;
        suspensionDate = new Date();
		expiredSuspensionDate = new Date();
        // Adding 5 minutes to the expiredSuspensionDate
        Calendar cal = Calendar.getInstance();
        cal.setTime(expiredSuspensionDate);
        cal.add(Calendar.MINUTE, 5);

        expiredSuspensionDate = cal.getTime();
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
		return suspensionDate;
	}
	
	//test:1
	public Date getExpiredDate() {
		return expiredSuspensionDate;
	}
	
	//test:1
	public String getSuspendedWriteData() {
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		String regDate = df.format(suspensionDate);
		
		String suspendedData = "@START\n" + 
								"@SUSPENDED\n" + 
								"@USER=" + user.getId() + "\n" + 
								"@GROUP=" + group.getGroupName() + "\n" +
								"@REGISTEREDDATE=" + regDate + "\n" + 
								"@END\n\n";
		return suspendedData;
	}
	
	//test:3
	public int compareTo(Suspended s) {
		
		if (user.compareTo(s.getUser()) == 1) {
			
			if (group.compareTo(s.getGroup()) == 1) {
				return 1;
			}
		}
		return 0;
		
	}

}