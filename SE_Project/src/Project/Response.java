package Project;

public class Response{
	private User user;
	private Group group;
	private String responceBody;
	
	public Response(User user, Group group, String responceBody) {
		this.user = user;
		this.group = group;
		this.responceBody = responceBody;
	}
	
	public User getUser() {
		return user;
	}

	public Group getGroup() {
		return group;
	}
	
	public String getResponceBody() {
		return responceBody;
	}
	
}