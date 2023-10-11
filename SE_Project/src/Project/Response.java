package Project;

public class Response extends Post{

	private int paternalPostID;
	
	public Response(String userName, String groupName, int id, String postBody, int paternalPostID) {
		super(userName, groupName, id, postBody);
		this.paternalPostID=paternalPostID;
	}

	public int getPaternalPostID() {
		return paternalPostID;
	}
	

}
