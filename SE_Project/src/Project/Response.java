package Project;

public class Response extends Post{

	private Post paternalPost;
	
	public Response(User user, Group group, int id, String postBody, Post paternalPost) {
		super(user, group, id, postBody);
		this.paternalPost = paternalPost;
	}

	public int getPaternalPostID() {
		return paternalPost.getId();
	}
	

}
