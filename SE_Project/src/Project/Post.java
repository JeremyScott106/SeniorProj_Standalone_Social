package Project;

import java.util.ArrayList;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
public class Post implements Comparable<Post> {
	
	protected User user;
	protected Group group;
	protected String postTitle;
	protected String postBody;
	private ArrayList<Response> responses;
	protected Date dateTime;
    private int score;
    private int id;
    
    public Post (membership memberships, String postTitle, String postBody, int Id) {
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
    	this.id = Id;
    }
	
    public Post (User u, Group g, String dateTime, String postTitle, String postBody, int id) {
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
    	this.id = id;
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
	
	//FIXME: add tests
	public int getId() {
		return id;
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
    						"@PSTID=" + id + "\n" + 
    						"@END\n\n";
    	
    	return userData;
    }

	//FIXME: add tests
	@Override
	public int compareTo(Post p) {
		
		if (user.compareTo(p.getUser()) == 1 && group.compareTo(p.getGroup()) == 1 &&
				id == p.getId()) {
			
			return 1;
			
		}
		
		return 0;
	}

}
