package Project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.Scanner;
import java.util.ArrayList;

public class ReadFile {
	
	static boolean currentlyReadingData;	//True if data is currently being read, false if between sets of data
	
	
	public static void readFile(SystemManager manager, ArrayList<String> fileNames) throws FileNotFoundException, IncorrectFileFormatException {
		
		for (String fileName : fileNames) {		//Loop through each file provided
		
			File dataFile = new File(fileName);		//Create File from name
			
			try {
				Scanner reader = new Scanner(dataFile);
				
				currentlyReadingData = false;	//not Currently reading data
				
				
				while (reader.hasNextLine()) {	//While there are still lines to be read
					String line = reader.nextLine();	//Line currently being read
					
					if (line.equals("@START")) {								//If line is the start of a set of Data
						if (currentlyReadingData) {									//and data is currently being read
							throw new IncorrectFileFormatException();					//throw exception
						}
						else {														//or if data is not being read
							currentlyReadingData = true;								//set currentlyReadingData to true
							continue;													//and continue to next line
						}
					}
					else if (line.equals("@ADMIN") && currentlyReadingData) {	//If current line rules next data set to be a Admin
						readAdmin(manager, reader);									//go read the data in the Admin
					}
					else if (line.equals("@USER") && currentlyReadingData) {	//If current line rules next data set to be a User
						readUser(manager, reader);									//go read the data in the User
					}
					else if(line.equals("@CATEGORY") && currentlyReadingData) {	//If current line rules next data set to be a Category
						readCategory(manager, reader);								//go  read the data in the Category
					}
					else if(line.equals("@GROUP") && currentlyReadingData) {	//If current line rules next data set to be a Group
						readGroup(manager, reader);									//go read the data in the Group
					}
					else if(line.equals("@MEMBERSHIP") && currentlyReadingData) {
						readMembership(manager, reader);
					}
					else if(line.equals("@POST") && currentlyReadingData) {
						readPost(manager, reader);
					}
					else if (line.equals("@RESPONSE") && currentlyReadingData) {
						readResponse(manager, reader);
					}
					else if (line.equals("@BANNED") && currentlyReadingData) {
						readBanned(manager, reader);
					}
					else if (line.equals("")) {									//If the current line is empty
						continue;													//continue to next line
					}
					else {														//If none of the above
						throw new IncorrectFileFormatException();					//throw incorrectFileFormatException
					}
				}
				
				reader.close();
				
				
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				throw new FileNotFoundException();
			} catch (IncorrectFileFormatException e) {
				// TODO Auto-generated catch block
				throw new IncorrectFileFormatException();
			}
		
		}
		
	}
	
	//test:7
	private static void readAdmin(SystemManager manager, Scanner reader) throws IncorrectFileFormatException {
		String name = "";				//Name of the User
		boolean gotName = false;			//Set to true once name is read
		String birthdate = "";			//Birthday of the User
		boolean gotBday = false;			//Set to true once bday is read
		String city = "";				//City of the User
		boolean gotCity = false;			//Set to true once city is read
		String state = "";				//State of the User
		boolean gotState = false;			//Set to true once state is read
		String username = "";			//Username of the User
		boolean gotUsername = false;		//Set to true once username is read
		String password = "";			//password of the User
		boolean gotPassword = false;		//Set to true once password is read
		String regDate = "";			//RegisteredDate of the user
		boolean gotRegDate = false;			//Set to true once regDate is read
		
		
		
		while(currentlyReadingData) {		//While the current data set is being read
			
			String line = reader.nextLine();	//Line currently being read
			
			
			if (line.equals("@END")) {			//If the line rules the end of the data set
				break;								//break the loop
			}
			
			String sub = "";	//For the first part of the line to identify what data is being read
			
			try {
				sub = line.substring(0, 5);	//Set sub to first 5 characters of line
			}
			catch (StringIndexOutOfBoundsException e) {	//If that can't be read
				throw new IncorrectFileFormatException();	//throw incorrectFileFormatException
			}
			
			if (sub.equals("@NAME")) {					//If the substring rules that the line contains the Name
				if(gotName) {								//and if a Name has already been read
					throw new IncorrectFileFormatException();	//throw incorrectFileFormatException
				}
				else {										//or if a Name has not already been read
					name = line.substring(6);					//get the Name from the line
					gotName = true;								//set gotName to true
					continue;									//and continue to next line
				}
				
			}
			else if (sub.equals("@BIRT")) {				//If the substring rules that the line contains the Birthday
				if(gotBday) {								//and if a Bday has already been read
					throw new IncorrectFileFormatException();	//throw incorrectFileFormatException
				}
				else {										//or if a Bbay has not already been read
					birthdate = line.substring(11);				//get the Bday from the line
					gotBday = true;								//set gotBday to true
					continue;									//and continue to next line
				}
				
			}
			else if (sub.equals("@CITY")) {				//If the substring rules that the line contains the City
				if(gotCity) {								//and if the City has already been read
					throw new IncorrectFileFormatException();	//throw incorrectFileFormatException
				}
				else {										//or if the City has not already been read
					city = line.substring(6);					//get the City from the line
					gotCity = true;								//set gotCity to true
					continue;									//and continue to next line
				}
				
			}
			else if (sub.equals("@STAT")) {				//If the substring rules that the line contains the State
				if(gotState) {								//and if the State has already been read
					throw new IncorrectFileFormatException();	//throw incorrectFileFormatException
				}
				else {										//or if the State has not already been read
					state = line.substring(7);					//get the State from the line
					gotState = true;							//set gotState to true
					continue;									//and continue to the next line
				}
				
			}
			else if (sub.equals("@USER")) {				//If the substring rules that the line contains the Username
				if(gotUsername) {							//and if the Username has already been read
					throw new IncorrectFileFormatException();	//throw incorrectFileFormatException
				}
				else {										//or if the Username has not already been read
					username = line.substring(10);				//get Username from the line
					gotUsername = true;							//set gotUsername to true
					continue;									//and continue to the next line
				}
				
			}
			else if (sub.equals("@PASS")) {				//If the substring rules that the line contains the Password
				if(gotPassword) {							//and if the Password has already been read
					throw new IncorrectFileFormatException();	//throw incorrectFileFormatException
				}
				else {										//or if the Password has not already been read
					password = line.substring(10);				//get Password from the line
					gotPassword = true;							//set gotPassword to true
					continue;									//and continue to the next line 
				}
				
			}
			else if (sub.equals("@REGI")) {				//If the substring rules that the line contains the RegisteredDate
				if(gotRegDate) {							//and if the RegDate has already been read
					throw new IncorrectFileFormatException();	//throw incorrectFileFormatException
				}
				else {										//or if the RegDate has nor already been read
					regDate = line.substring(17);				//get RegDate from the line
					gotRegDate = true;							//set gorRegDate to true
					continue;									//and continue to the next line
				}
				
			}
			else {										//If what data is being read cannot be determined
				throw new IncorrectFileFormatException();	//throw incorrectFileFormatException
			}
			
		}
		
		//If all the bits of data were read from the file for the Admin
		if (gotName && gotBday && gotCity && gotState && gotUsername && gotPassword && gotRegDate) {
			Admin a = new Admin(name, username, password, birthdate, city, state, regDate);	//create new Admin
			manager.addAdmin(a);				//Add to manager
			currentlyReadingData = false;		//set currentlyReadingData to false
		}
		else {										//If any bit of data was not collected
			throw new IncorrectFileFormatException();	//throw incorrectFileFormatException
		}
		
	}
	
	//test:6
	private static void readUser(SystemManager manager, Scanner reader) throws IncorrectFileFormatException {
		String name = "";				//Name of the User
		boolean gotName = false;
		String bday = "";				//Bday of the User
		boolean gotBday = false;
		String city = "";				//City of the User
		boolean gotCity = false;
		String state = "";				//State of the User
		boolean gotState = false;
		String username = "";			//Username of the User
		boolean gotUsername = false;
		String password = "";			//Password of the User
		boolean gotPassword = false;
		String regDate = "";			//RegDate of the User
		boolean gotRegDate = false;
		
		while(currentlyReadingData) {		//While current data sat is being read
			
			String line = reader.nextLine();	//current line being read
			
			
			if (line.equals("@END")) {			//If the line rules the end of the data set
				break;								//break the loop
			}
			
			String sub = "";	//For the first part of the line to identify what data is being read
			
			try {
				sub = line.substring(0, 5);			//Set sub to first 5 characters of line
			}
			catch (StringIndexOutOfBoundsException e) {	//If that can't be read
				throw new IncorrectFileFormatException();	//throw incorrectFileFormatException
			}
			
			if (sub.equals("@NAME")) {					//If the substring rules that the line contains the Name
				if(gotName) {								//and if a Name has already been read
					throw new IncorrectFileFormatException();	//throw incorrectFileFormatException
				}
				else {										//or if a Name has not already been read
					name = line.substring(6);					//get the Name from the line
					gotName = true;								//set gotName to true
					continue;									//and continue to next line
				}
				
			}
			else if (sub.equals("@BIRT")) {				//If the substring rules that the line contains the Birthday
				if(gotBday) {								//and if a Bday has already been read
					throw new IncorrectFileFormatException();	//throw incorrectFileFormatException
				}
				else {										//or if a Bbay has not already been read
					bday = line.substring(11);				//get the Bday from the line
					gotBday = true;								//set gotBday to true
					continue;									//and continue to next line
				}
				
			}
			else if (sub.equals("@CITY")) {				//If the substring rules that the line contains the City
				if(gotCity) {								//and if the City has already been read
					throw new IncorrectFileFormatException();	//throw incorrectFileFormatException
				}
				else {										//or if the City has not already been read
					city = line.substring(6);					//get the City from the line
					gotCity = true;								//set gotCity to true
					continue;									//and continue to next line
				}
				
			}
			else if (sub.equals("@STAT")) {				//If the substring rules that the line contains the State
				if(gotState) {								//and if the State has already been read
					throw new IncorrectFileFormatException();	//throw incorrectFileFormatException
				}
				else {										//or if the State has not already been read
					state = line.substring(7);					//get the State from the line
					gotState = true;							//set gotState to true
					continue;									//and continue to the next line
				}
				
			}
			else if (sub.equals("@USER")) {				//If the substring rules that the line contains the Username
				if(gotUsername) {							//and if the Username has already been read
					throw new IncorrectFileFormatException();	//throw incorrectFileFormatException
				}
				else {										//or if the Username has not already been read
					username = line.substring(10);				//get Username from the line
					gotUsername = true;							//set gotUsername to true
					continue;									//and continue to the next line
				}
				
			}
			else if (sub.equals("@PASS")) {				//If the substring rules that the line contains the Password
				if(gotPassword) {							//and if the Password has already been read
					throw new IncorrectFileFormatException();	//throw incorrectFileFormatException
				}
				else {										//or if the Password has not already been read
					password = line.substring(10);				//get Password from the line
					gotPassword = true;							//set gotPassword to true
					continue;									//and continue to the next line 
				}
				
			}
			else if (sub.equals("@REGI")) {				//If the substring rules that the line contains the RegisteredDate
				if(gotRegDate) {							//and if the RegDate has already been read
					throw new IncorrectFileFormatException();	//throw incorrectFileFormatException
				}
				else {										//or if the RegDate has nor already been read
					regDate = line.substring(17);				//get RegDate from the line
					gotRegDate = true;							//set gorRegDate to true
					continue;									//and continue to the next line
				}
				
			}
			else {										//If what data is being read cannot be determined
				throw new IncorrectFileFormatException();	//throw incorrectFileFormatException
			}
		}
		
		//If all the bits of data were read from the file for the Admin
		if (gotName && gotBday && gotCity && gotState && gotUsername && gotPassword && gotRegDate) {
			User a = new User(name, username, password, bday, city, state, regDate);	//Create a new User
			manager.addUser(a);				//Add new User to manager
			currentlyReadingData = false;	//Set currentlyReadingData to false	
		}
		else {										//If any bit of data was not collected
			throw new IncorrectFileFormatException();	//throw incorrectFileFormatException
		}
	}

	//test:5
	private static void readCategory(SystemManager manager, Scanner reader) throws IncorrectFileFormatException {
		
		String name = "";			//Name of the Category
		boolean gotName = false;		//Set gotName to false
		
		while (currentlyReadingData) {			//While current data set is being read
			
			String line = reader.nextLine();	//Current line being read
			
			if (line.equals("@END")) {			//If the line rules the end of the data set
				break;								//break the loop
			}
			
			String sub = "";		//For the first part of the line to identify what data is being read
			
			try {
				sub = line.substring(0, 5);			//Set sub to first 5 characters of line
			}
			catch (StringIndexOutOfBoundsException e) {	//If that can't be read
				throw new IncorrectFileFormatException();	//throw incorrectFileFormatException
			}
			
			if (sub.equals("@NAME")) {					//If the substring rules that the line contains the Name
				if (gotName) {								//and if a Name has already been read
					throw new IncorrectFileFormatException();	//throw incorrectFileFormatException
				}
				else {										//or if a Name has not already been read
					name = line.substring(6);					//get the Name from the line
					gotName = true;								//set gotName to true
					continue;									//and continue to next line
				}
				
			}
			else {										//If what data is being read cannot be determined
				throw new IncorrectFileFormatException();	//throw incorrectFileFormatException
			}
			
		}
		
		if (gotName) {			//If all the bits of data were read from the file for the category
			category c = new category(name);	//Create the category
			manager.addCategory(c);				//Add it to the manager
			currentlyReadingData = false;		//Set currentlyReadingData to false
		}
		else {					//If any bit of data was not collected
			throw new IncorrectFileFormatException();	//throw incorrectFileFormatException
		}
		
	}
	
	//test:1
	private static void readGroup(SystemManager manager, Scanner reader) throws IncorrectFileFormatException {
		
		String name = "";
		boolean gotName = false;
		String catName = "";
		boolean gotCatName = false;
		
		while (currentlyReadingData) {
			
			String line = reader.nextLine();
			
			if (line.equals("@END")) {
				currentlyReadingData = false;
				break;
			}
			
			String sub = "";
			
			try {
				sub = line.substring(0, 5);
			}
			catch (StringIndexOutOfBoundsException e) {
				throw new IncorrectFileFormatException();
			}
			
			if (sub.equals("@NAME")) {
				if (gotName) {
					throw new IncorrectFileFormatException();
				}
				else {
					name = line.substring(6);
					gotName = true;
					continue;
				}
			}
			else if (sub.equals("@CATE")) {
				if (gotCatName) {
					throw new IncorrectFileFormatException();
				}
				else {
					catName = line.substring(10);
					gotCatName = true;
					continue;
				}
			}
			else {
				throw new IncorrectFileFormatException();
			}
			
		}
		
		if (gotName && gotCatName) {
			
			category c = manager.getCategoryByName(catName);
			
			if (!(c==null)) {
				
				Group g = new Group(name);
				
				c.addGroup(g);
				
			}
			
		}
		
	}
	
	//FIXME: add test methods
	private static void readMembership(SystemManager manager, Scanner reader) throws IncorrectFileFormatException {
		
		String userName = "";
		boolean gotUserName = false;
		String groupName = "";
		boolean gotGroupName = false;
		String regDate = "";
		boolean gotRegDate = false;
		
		while (currentlyReadingData) {
			
			String line = reader.nextLine();
			
			if (line.equals("@END")) {
				currentlyReadingData = false;
				 break;
			}
			
			String sub = "";
			
			try {
				sub = line.substring(0, 5);
			}
			catch (StringIndexOutOfBoundsException e) {
				throw new IncorrectFileFormatException();
			}
			
			if (sub.equals("@USER")) {
				 if (gotUserName) {
					 throw new IncorrectFileFormatException();
				 }
				 else {
					 userName = line.substring(6);
					 gotUserName = true;
					 continue;
				 }
			}
			else if (sub.equals("@GROU")) {
				if (gotGroupName) {
					throw new IncorrectFileFormatException();
				}
				else {
					groupName = line.substring(7);
					gotGroupName = true;
					continue;
				}
			}
			else if (sub.equals("@REGI")) {
				if (gotRegDate) {
					throw new IncorrectFileFormatException();
				}
				else {
					regDate = line.substring(16);
					gotRegDate = true;
					continue;
				}
			}
			else {
				throw new IncorrectFileFormatException();
			}
			
		}
		
		if (gotUserName && gotGroupName && gotRegDate) {
			
			User u = manager.getUserByUsername(userName);
			Group g = manager.getGroupByName(groupName);
			
			if (!(u == null) && !(g == null)) {
				
				membership m = new membership(u, g, regDate);
				g.addMember(m);
				
			}
			else {
				throw new IncorrectFileFormatException();
			}
			
		}
		else {
			throw new IncorrectFileFormatException();
		}
		
	}
	
	
	private static void readPost(SystemManager manager, Scanner reader) throws IncorrectFileFormatException {
		
		String userName = "";
		boolean gotUserName = false;
		String groupName = "";
		boolean gotGroupName = false;
		String dateTime = "";
		boolean gotDateTime = false;
		String postTitle = "";
		boolean gotPostTitle = false;
		String postBody = "";
		boolean gotPostBody = false;
		String postId = "";
		boolean gotPostId = false;
		
		while (currentlyReadingData) {
			
			String line = reader.nextLine();
			
			if (line.equals("@END")) {
				currentlyReadingData = false;
				 break;
			}
			
			String sub = "";
			
			try {
				sub = line.substring(0, 5);
			}
			catch (StringIndexOutOfBoundsException e) {
				throw new IncorrectFileFormatException();
			}
			
			if (sub.equals("@USER")) {
				 if (gotUserName) {
					 throw new IncorrectFileFormatException();
				 }
				 else {
					 userName = line.substring(10);
					 gotUserName = true;
					 continue;
				 }
			}
			else if (sub.equals("@GNAM")) {
				if (gotGroupName) {
					throw new IncorrectFileFormatException();
				}
				else {
					groupName = line.substring(7);
					gotGroupName = true;
					continue;
				}
			}
			else if (sub.equals("@DATE")) {
				if (gotDateTime) {
					throw new IncorrectFileFormatException();
				}
				else {
					dateTime = line.substring(10);
					gotDateTime = true;
					continue;
				}
			}
			else if (sub.equals("@TITL")) {
				if (gotPostBody) {
					throw new IncorrectFileFormatException();
				}
				else {
					postTitle = line.substring(7);
					gotPostTitle = true;
					continue;
				}
			}
			else if (sub.equals("@BODY")) {
				if (gotPostBody) {
					throw new IncorrectFileFormatException();
				}
				else {
					postBody = line.substring(6);
					gotPostBody = true;
					continue;
				}
			}
			else if (sub.equals("@PSTI")) {
				if (gotPostId) {
					throw new IncorrectFileFormatException();
				}
				else {
					postId = line.substring(7);
					gotPostId = true;
					continue;
				}
			}
			else {
				throw new IncorrectFileFormatException();
			}
			
		}
		
		if (gotUserName && gotGroupName && gotDateTime && gotPostTitle && gotPostBody && gotPostId) {
			
			Group g = manager.getGroupByName(groupName);
			User u = manager.getUserByUsername(userName);
			int id = Integer.parseInt(postId);
			if (g != null && u != null) {
				Post p=new Post(u, g, dateTime, postTitle, postBody, id);
				manager.getGroupByName(groupName).addPost(p);
			}
		}
		else {
			throw new IncorrectFileFormatException();
		}
		
	}
	
	
	private static void readResponse(SystemManager manager, Scanner reader) throws IncorrectFileFormatException {
		
		String userName = "";
		boolean gotUserName = false;
		String groupName = "";
		boolean gotGroupName = false;
		String dateTime = "";
		boolean gotDateTime = false;
		String responseBody = "";
		boolean gotResponseBody = false;
		String parentalId = "";
		boolean gotParentalId = false;
		
		while (currentlyReadingData) {
			
			String line = reader.nextLine();
			
			if (line.equals("@END")) {
				currentlyReadingData = false;
				break;
			}
			
			String sub = "";
			
			try {
				sub = line.substring(0, 5);
			}
			catch (StringIndexOutOfBoundsException e) {
				throw new IncorrectFileFormatException();
			}
			
			if (sub.equals("@USER")) {
				 if (gotUserName) {
					 throw new IncorrectFileFormatException();
				 }
				 else {
					 userName = line.substring(10);
					 gotUserName = true;
					 continue;
				 }
			}
			else if (sub.equals("@GNAM")) {
				if (gotGroupName) {
					throw new IncorrectFileFormatException();
				}
				else {
					groupName = line.substring(7);
					gotGroupName = true;
					continue;
				}
			}
			else if (sub.equals("@DATE")) {
				if (gotDateTime) {
					throw new IncorrectFileFormatException();
				}
				else {
					dateTime = line.substring(10);
					gotDateTime = true;
					continue;
				}
			}
			else if (sub.equals("@BODY")) {
				if (gotResponseBody) {
					throw new IncorrectFileFormatException();
				}
				else {
					responseBody = line.substring(6);
					gotResponseBody = true;
					continue;
				}
			}
			else if (sub.equals("@PARE")) {
				if (gotParentalId) {
					throw new IncorrectFileFormatException();
				}
				else {
					parentalId = line.substring(12);
					gotParentalId = true;
					continue;
				}
			}
			else {
				throw new IncorrectFileFormatException();
			}
			
		}
		
		
		
		if (gotUserName && gotGroupName && gotDateTime && gotResponseBody && gotParentalId) {
			
			Group g = manager.getGroupByName(groupName);
			User u = manager.getUserByUsername(userName);
			int id = Integer.parseInt(parentalId);
			Post p = manager.getPostByGroupId(g, id);
			if (g != null && u != null && p != null) {
				
				Response r = new Response(u, g, dateTime, responseBody, id);
				p.addResponse(r);
				
			}
		}
		else {
			throw new IncorrectFileFormatException();
		}
		
	}
	


	private static void readBanned(SystemManager manager, Scanner reader) throws IncorrectFileFormatException {
		
		String userName = "";
		boolean gotUserName = false;
		String groupName = "";
		boolean gotGroupName = false;
		
		while (currentlyReadingData) {
			
			String line = reader.nextLine();
			
			if (line.equals("@END")) {
				currentlyReadingData = false;
				break;
			}
			
			String sub = "";
			
			try {
				sub = line.substring(0, 5);
			}
			catch (StringIndexOutOfBoundsException e) {
				throw new IncorrectFileFormatException();
			}
			
			
			if (sub.equals("@USER")) {
				if (gotUserName) {
					throw new IncorrectFileFormatException();
				}
				else {
					userName = line.substring(6);
					gotUserName = true;
					continue;
				}
			}
			else if (sub.equals("@GROU")) {
				if (gotGroupName) {
					throw new IncorrectFileFormatException();
				}
				else {
					groupName = line.substring(7);
					gotGroupName = true;
					continue;
				}
			}
			else {
				throw new IncorrectFileFormatException();
			}
			
		}
		
		
		if (gotUserName && gotGroupName) {
			
			Group g = manager.getGroupByName(groupName);
			User u = manager.getUserByUsername(userName);
			
			if (!(g == null) && !(u == null)) {
				
				Banned b = new Banned(u, g);
				g.addBanned(b);
				
			}
			
		}
		
		
	}
	
}

