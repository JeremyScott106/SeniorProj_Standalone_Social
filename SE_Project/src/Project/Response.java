package Project;

import java.lang.reflect.Member;

public class Response extends Post{
	private membership membership;
	private String responseBody;
	
	public Response(membership membership, String responseBody) {
		super(membership, responseBody, null);
		this.membership = membership;
		this.responseBody = responseBody;
	}
	
	//test:1
	public membership getMember() {
		return membership;
	}
	
	//test:1
	public String getResponseBody() {
		return responseBody;
	}
	
}