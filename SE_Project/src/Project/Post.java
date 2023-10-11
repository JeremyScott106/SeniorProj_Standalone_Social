package Project;  


public class Post {
	
	private String userName;
	private String groupName;
	private int id;
	private String postBody;
    private int score;
    java.util.Date dateTime;
    
    public Post (String userName, String groupName, int id, String postBody) {
    	this.userName = userName;
    	this.groupName = groupName;
    	this.id = id;
    	this.postBody = postBody;
    	this.dateTime = new java.util.Date();
    	this.score = 0;
    }
	
	public String getUser() {
		return userName;
	}
	
	public String getGroup() {
		return groupName;
	}
	
	public int getId() {
		return id;
	}
	
	public String getPostBody() {
		return postBody;
	}
	
	public int getScore() {
		return score;
	}
	
	public java.util.Date getTime() {
		return dateTime;
	}
	
	public void addScore() {
		score++;
	}
	
	public void subScore() {
		score--;
	}
	
	
	
}
