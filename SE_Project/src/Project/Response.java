package Project;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Response extends Post{
	
	private int parentalId;
	
	
	public Response(membership membership, String responseBody, int parentalId, int responseID) {
		super(membership, "", responseBody, -1);
		this.parentalId = parentalId;
		this.responseID = responseID;
	}
	
	public Response(User u, Group g, String date, String responseBody, int parentalId, int score, int responseID) {
		super(u, g, date, "", responseBody, parentalId, score, responseID);
		this.parentalId = parentalId;
	}
	
	//FIXME: add tests
	public int getParentalId() {
		return parentalId;
	}
	

	//FIXME: add tests
	public String getResponseWriteData() {
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy h:mm a");
		String date = df.format(dateTime);
		
		String responseData = "@START\n" + 
				"@RESPONSE\n" + 
				"@USERNAME=" + user.getId() + "\n" + 
				"@GNAME=" + group.getGroupName() + "\n" + 
				"@DATETIME=" + date + "\n" + 
				"@BODYSTART\n" + 
				postBody + "\n" + 
				"@BODYEND\n" + 
				"@PARENTALID=" + parentalId + "\n";
		if (flag) {
			responseData += "@FLAG\n";
		}
		responseData += "@SCORE=" + score + "\n" +
						"@RESPONSEID=" + responseID + "\n" +
						"@END\n\n";
		
		return responseData;
	}
	
	public int compareTo(Response r) {
		if (user.compareTo(r.getUser()) == 1 && group.compareTo(r.getGroup()) == 1 &&
				parentalId == r.getParentalId()) {
			
			return 1;
			
		}
		
		return 0;
	}
	
}