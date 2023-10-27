package Project;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WriteFile {
	
	
	public static void writeFile(SystemManager manager, String fileName) throws IOException {
		
		try {
			
			File dataFile = new File(fileName);
			
			if (!dataFile.exists()) {
				dataFile.createNewFile();
			}
			
			FileWriter writer = new FileWriter(fileName);
			
			writeUsers(manager, writer);
			
			writer.close();
			
		} catch (IOException e) {
			throw new IOException();
		}
		
	}
	
	
	private static void writeAdmins(SystemManager manager, FileWriter writer) throws IOException {
		
		ArrayList<Admin> admins = manager.getAdmins_Alphabetically();
		
		for (Admin a : admins) {
			String adminData = a.getAdminWriteData();
			
			writer.write(adminData);
		}
		
	}
	
	
	private static void writeUsers(SystemManager manager, FileWriter writer) throws IOException {
		
		ArrayList<User> users = manager.getUsers_Alphabetically();
		
		for (User u : users) {
			String userData = u.getUserWriteData();
			
			writer.write(userData);
		}
		
	}

}
