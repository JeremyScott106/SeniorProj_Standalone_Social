package Project;

import java.util.ArrayList;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
public class Post implements Comparable<Post> {
	
	private User user;
	private Group group;
	private String postTitle;
	private String postBody;
	private ArrayList<Response> responses;
	private Date dateTime;
    private int score;
    
    public Post (membership memberships, String postTitle, String postBody) {
    	this.user = memberships.getUser();
    	this.group = memberships.getGroup();
    	this.postBody = postBody;
    	this.postTitle = postTitle;
    	this.responses = new ArrayList<>();
    	Date d=new Date();
    	try {
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			String x = df.format(d);
			this.dateTime = df.parse(x);
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	this.score = 0;
    }
	
    public Post (User u, Group g, String dateTime, String postTitle, String postBody) {
    	this.user = u;
    	this.group = g;
    	this.postTitle = postTitle;
    	this.postBody = postBody;
    	this.responses = new ArrayList<>();
    	try {
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			this.dateTime = df.parse(dateTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	this.score = 0;
    }
    
	public User getUser() {
		return user;
	}
	
	public Group getGroup() {
		return group;
	}
	
	public String getPostTitle() {
		return postTitle;
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
	
	public String getPostWriteData() {
    	
    	String userData = "@START\n" + 
    						"@POST\n" + 
    						"@USERNAME=" + getUser().getId() + "\n" + 
    						"@GNAME=" + getGroup().getGroupName() + "\n" + 
    						"@DATETIME=" + dateTime + "\n" + 
    						"@TITLE=" + postTitle + "n" +
    						"@BODY=" + postBody + "\n" + 
    						"@END\n\n";
    	
    	return userData;
    }

	@Override
	public int compareTo(Post p) {
		
		if (user.compareTo(p.getUser()) == 1 && group.compareTo(p.getGroup()) == 1 &&
				postTitle.equals(p.getPostTitle()) && postBody.equals(p.getPostBody()) ) {
			
			return 1;
			
		}
		
		return 0;
	}

}
