package Project;

import java.util.ArrayList;

public class Post {
	
	private User user;
	private Group group;
	private String postBody;
	private ArrayList<Response> responses;
    private int score;
    java.util.Date dateTime;
    
    public Post (membership memberships, String postBody) {
    	this.user = memberships.getUser();
    	this.group = memberships.getGroup();
    	this.postBody = postBody;
    	this.responses = new ArrayList<>();
    	this.dateTime = new java.util.Date(); //should save the current date and time.
    	this.score = 0;
    }
	
	public User getUser() {
		return user;
	}
	
	public Group getGroup() {
		return group;
	}

	public String getPostBody() {
		return postBody;
	}
	
	public ArrayList<Response> getResponse() {
		return responses;
	}
	
    //Adds responses into responses
    public void addResponse(Response r) {
        responses.add(r);
    }
	
	public int getScore() {
		return score;
	}
	
	//Returns date saved in dateTime
	public java.util.Date getTime() {
		return dateTime;
	}
	
	//Increases score
	public void addScore() {
		score++;
	}
	
	//Decreases score
	public void subScore() {
		score--;
	}
}