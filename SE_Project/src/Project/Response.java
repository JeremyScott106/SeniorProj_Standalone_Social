package Project;

public class Response{
	private User user;
	private Group group;
	private String responseBody;
	
	public Response(User user, Group group, String responseBody) {
		this.user = user;
		this.group = group;
		this.responseBody = responseBody;
	}
	
	public User getUser() {
		return user;
	}

	public Group getGroup() {
		return group;
	}
	
	public String getResponseBody() {
		return responseBody;
	}
	
}