package Project;

import java.util.ArrayList;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
public class Post {
	
	private User user;
	private Group group;
	private String postTitle;
	private String postBody;
	private ArrayList<Response> responses;
	private Date dateTime;
    private int score;
    private boolean flag;
    
    public Post (membership memberships, String postTitle, String postBody) {
    	this.user = memberships.getUser();
    	this.group = memberships.getGroup();
    	this.postBody = postBody;
    	this.postTitle = postTitle;
    	this.responses = new ArrayList<>();
    	Date d=new Date();
    	try {
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy'T'HH:mm:ssXXX");
			String x = df.format(d);
			this.dateTime = df.parse(x);
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	this.score = 0;
    	this.flag = false;
    }
	
    public Post (User u, Group g, String dateTime, String postTitle, String postBody) {
    	this.user = u;
    	this.group = g;
    	this.postTitle = postTitle;
    	this.postBody = postBody;
    	this.responses = new ArrayList<>();
    	try {
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy'T'HH:mm:ssXXX");
			this.dateTime = df.parse(dateTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	this.score = 0;
    	this.flag = false;
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
	public String getPostTitle() {
		return postTitle;
	}

	//test:1
	public String getPostBody() {
		return postBody;
	}
	
	//test:1
	public ArrayList<Response> getResponse() {
		return responses;
	}
	
	//test:1
    //Adds responses into responses
    public void addResponse(Response r) {
        responses.add(r);
    }
    
    //test:1
    //removes responses into responses
    public void removeResponse(Response r) {
        responses.remove(r);
    }
	
	//test:1
	public int getScore() {
		return score;
	}
	
	//test:1
	public boolean getFlag() {
		return flag;
	}
	
	//test:1
	public void setFlagTrue() {
		flag = true;
	}
	
	//test:1
	public void setFlagFalse() {
		flag = false;
	}
	
	//test:1
	//Returns date saved in dateTime
	public java.util.Date getTime() {
		return dateTime;
	}
	
	//test:1
	//Increases score
	public void addScore() {
		score++;
	}
	
	//test:1
	//Decreases score
	public void subScore() {
		score--;
	}
	
	//test:1
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
	
    public int getTotalScore() {
        int totalScore = this.score;
        for (Response response : responses) {
            totalScore += response.getScore();
        }
        return totalScore;
    }

}
