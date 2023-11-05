package Project;

import java.lang.reflect.Member;

public class Response extends Post{
	private membership membership;
	private String responseBody;
	
	public Response(membership membership, String responseBody) {
		super(membership, responseBody);
		this.membership = membership;
		this.responseBody = responseBody;
	}
	
	public membership getMember() {
		return membership;
	}
	
	public String getResponseBody() {
		return responseBody;
	}
	
}