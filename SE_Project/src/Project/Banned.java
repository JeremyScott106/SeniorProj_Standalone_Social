package Project;

public class Banned implements Comparable<Banned> {
	private User user;
	private Group group;

	//test:1
    public Banned(User user, Group group) {
        this.user = user;
        this.group = group;
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
	public String getBannedWriteData() {
		
		String bannedData = "@START\n" + 
								"@BANNED\n" + 
								"@USER=" + user.getId() + "\n" + 
								"@GROUP=" + group.getGroupName() + "\n" +
								"@END\n\n";
		return bannedData;
	}
	
	//test:3
	public int compareTo(Banned b) {
		
		if (user.compareTo(b.getUser()) == 1) {
			
			if (group.compareTo(b.getGroup()) == 1) {
				return 1;
			}
		}
		return 0;
		
	}

}
