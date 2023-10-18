package Project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFile {
	
	static boolean currentlyReadingData;
	
	
	public static void readFile(SystemManager manager, String fileName) throws FileNotFoundException, incorrectFileFormatException {
		
		
		
		File dataFile = new File(fileName);
		try {
			Scanner reader = new Scanner(dataFile);
			
			currentlyReadingData = false;
			
			
			while (reader.hasNextLine()) {
				String line = reader.nextLine();
				
				if (line.equals("@START")) {
					if (currentlyReadingData) {
						throw new incorrectFileFormatException();
					}
					else {
						currentlyReadingData = true;
						continue;
					}
				}
				else if (line.equals("@ADMIN")) {
					readAdmin(manager, reader);
				}
				else if (line.equals("@USER")) {
					readUser(manager, reader);
				}
				else if (line.equals("")) {
					continue;
				}
				else if (line.equals("@ENDFILE")) {
					break;
				}
				else {
					throw new incorrectFileFormatException();
				}
			}
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new FileNotFoundException();
		} catch (incorrectFileFormatException e) {
			// TODO Auto-generated catch block
			throw new incorrectFileFormatException();
		}
		
	}
	
	
	private static void readAdmin(SystemManager manager, Scanner reader) throws incorrectFileFormatException {
		String name = "";
		boolean gotName = false;
		String birthdate = "";
		boolean gotBday = false;
		String city = "";
		boolean gotCity = false;
		String state = "";
		boolean gotState = false;
		String username = "";
		boolean gotUsername = false;
		String password = "";
		boolean gotPassword = false;
		String regDate = "";
		boolean gotRegDate = false;
		
		
		
		while(currentlyReadingData) {
			
			String line = reader.nextLine();
			
			
			if (line.equals("@END")) {
				break;
			}
			String sub = line.substring(0, 5);
			if (sub.equals("@NAME")) {
				name = line.substring(6);
				gotName = true;
				continue;
			}
			else if (sub.equals("@BIRT")) {
				birthdate = line.substring(11);
				gotBday = true;
				continue;
			}
			else if (sub.equals("@CITY")) {
				city = line.substring(6);
				gotCity = true;
				continue;
			}
			else if (sub.equals("@STAT")) {
				state = line.substring(7);
				gotState = true;
				continue;
			}
			else if (sub.equals("@USER")) {
				username = line.substring(10);
				gotUsername = true;
				continue;
			}
			else if (sub.equals("@PASS")) {
				password = line.substring(10);
				gotPassword = true;
				continue;
			}
			else if (sub.equals("@REGI")) {
				regDate = line.substring(17);
				gotRegDate = true;
				continue;
			}
			else {
				throw new incorrectFileFormatException();
			}
			
		}
		
		if (gotName && gotBday && gotCity && gotState && gotUsername && gotPassword && gotRegDate) {
			Admin a = new Admin(name, username, password, birthdate, city, state, regDate);
			manager.addAdmin(a);
			currentlyReadingData = false;
		}
		else {
			throw new incorrectFileFormatException();
		}
		
	}
	
	
	private static void readUser(SystemManager manager, Scanner reader) throws incorrectFileFormatException {
		String name = "";
		boolean gotName = false;
		String bday = "";
		boolean gotBday = false;
		String city = "";
		boolean gotCity = false;
		String state = "";
		boolean gotState = false;
		String username = "";
		boolean gotUsername = false;
		String password = "";
		boolean gotPassword = false;
		String regDate = "";
		boolean gotRegDate = false;
		
		while(currentlyReadingData) {
			
			String line = reader.nextLine();
			
			
			if (line.equals("@END")) {
				break;
			}
			String sub = line.substring(0, 5);
			if (sub.equals("@NAME")) {
				name = line.substring(6);
				gotName = true;
				continue;
			}
			else if (sub.equals("@BIRT")) {
				bday = line.substring(11);
				gotBday = true;
				continue;
			}
			else if (sub.equals("@CITY")) {
				city = line.substring(6);
				gotCity = true;
				continue;
			}
			else if (sub.equals("@STAT")) {
				state = line.substring(7);
				gotState = true;
				continue;
			}
			else if (sub.equals("@USER")) {
				username = line.substring(10);
				gotUsername = true;
				continue;
			}
			else if (sub.equals("@PASS")) {
				password = line.substring(10);
				gotPassword = true;
				continue;
			}
			else if (sub.equals("@REGI")) {
				regDate = line.substring(17);
				gotRegDate = true;
				continue;
			}
			else {
				throw new incorrectFileFormatException();
			}
		}
		
		
		if (gotName && gotBday && gotCity && gotState && gotUsername && gotPassword && gotRegDate) {
			User a = new User(name, username, password, bday, city, state, regDate);
			currentlyReadingData = false;
			manager.addUser(a);
		}
		else {
			throw new incorrectFileFormatException();
		}
	}

}



class incorrectFileFormatException extends Exception {
	
	public incorrectFileFormatException() {}
	
	public incorrectFileFormatException(String msg) {
		super(msg);
	}
	
}

