package Project;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Response extends Post{
	
	private int parentalId;
	
	public Response(membership membership, String responseBody, int parentalId) {
		super(membership, "", responseBody, -1);
		this.parentalId = parentalId;
	}
	
	public Response(User u, Group g, String date, String responseBody, int parentalId) {
		super(u, g, date, "", responseBody, parentalId);
		this.parentalId = parentalId;
	}
	
	//FIXME: add tests
	public int getParentalId() {
		return parentalId;
	}
	

	
	public String getResponseWriteData() {
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm a");
		String date = df.format(dateTime);
		
		String responseData = "@START\n" + 
				"@RESPONSE\n" + 
				"@USERNAME=" + user.getId() + "\n" + 
				"@GNAME=" + group.getGroupName() + "\n" + 
				"@DATETIME=" + date + "\n" + 
				"@BODY=" + postBody + "\n" + 
				"@PARENTALID=" + parentalId + "\n" +
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