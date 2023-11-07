package Project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
			
			writeAdmins(manager, writer);
			
			writeUsers(manager, writer);
			
			writeCategories(manager, writer);
			
			writeGroups(manager, writer);
			
			writer.write("\n\n\n");
			
			writer.close();
			
		} catch (IOException e) {
			throw new IOException();
		}
		
	}
	
	
	private static void writeAdmins(SystemManager manager, FileWriter writer) throws IOException {
		
		ArrayList<Admin> admins = manager.getAdmins_Alphabetically_ByUsername();
		
		for (Admin a : admins) {
			String adminData = a.getAdminWriteData();
			
			writer.write(adminData);
		}
		
	}
	
	
	private static void writeUsers(SystemManager manager, FileWriter writer) throws IOException {
		
		ArrayList<User> users = manager.getUsers_Alphabetically_ByUsername();
		
		for (User u : users) {
			String userData = u.getUserWriteData();
			
			writer.write(userData);
		}
		
	}
	
	
	private static void writeCategories(SystemManager manager, FileWriter writer) throws IOException {
		
		ArrayList<category> categories = manager.getCategories_Alphabetically();
		
		for (category c : categories) {
			String catData = c.getCategoryWriteData();
			
			writer.write(catData);
		}
		
	}
	
	private static void writeGroups(SystemManager manager, FileWriter writer) throws IOException {
		
		ArrayList<category> categories = manager.getCategories_Alphabetically();
		
		for (category c : categories) {
			
			ArrayList<Group> groups = c.getGroupsAlphabetically();
			
			String catName = c.getName();
			
			for (Group g : groups) {
				
				String groupData = g.getGroupWriteData(catName);
				
				writer.write(groupData);
				
			}
			
		}
		
	}
	
	public static void addAdminToFile(Admin a, String fileName) throws IOException {
		
		try {
			
			File dataFile = new File(fileName);
			
			FileWriter writer = new FileWriter(dataFile, true);
			
			String msg = a.getAdminWriteData();
			
			writer.write(msg);
			
			writer.close();
			
		}
		catch (IOException e) {
			throw new IOException();
		}
		
	}
	
	
	public static void addUserToFile(User u, String fileName) throws IOException {
		
		try {
			
			File dataFile = new File(fileName);
			
			FileWriter writer = new FileWriter(dataFile, true);
			
			String msg = u.getUserWriteData();
			
			writer.write(msg);
			
			writer.close();
			
		}
		catch (IOException e) {
			throw e;
		}
		
	}
	
	
	public static void addCategoryToFile(category c, String fileName) throws IOException {
		
		try {
			File dataFile = new File(fileName);
			
			FileWriter writer = new FileWriter(dataFile, true);
			
			String msg = c.getCategoryWriteData();
			
			writer.write(msg);
			
			writer.close();
		}
		catch (IOException e) {
			throw e;
		}
		
	}
	
	
	public static void addGroupToFile(Group g, String fileName, String catName) throws IOException {
		
		try {
			
			File dataFile = new File(fileName);
			
			FileWriter writer = new FileWriter(dataFile, true);
			
			String msg = g.getGroupWriteData(catName);
			
			writer.write(msg);
			
			writer.close();
			
		}
		catch (IOException e) {
			throw e;

		}
		
	}
	
	
	
	public static void removeAdminFromFile(Admin a, String fileName) throws IOException {
		
		String find = a.getAdminWriteData();
		String replace = "";
		
		try {
			
			File dataFile = new File(fileName);
			FileReader reader = new FileReader(dataFile);
			
			String s = "";
			String totalStr = "";
			
			BufferedReader br = new BufferedReader(reader);
			
			while ((s = br.readLine()) != null) {
	            totalStr += s + "\n";
	        }
			
			totalStr = totalStr.replaceAll(find, replace);
			
			FileWriter writer = new FileWriter(dataFile);
			
			writer.write(totalStr);
			writer.close();
			
		}
		catch (IOException e) {
			throw e;
		}
		
	}
	
	
	public static void removeUserFromFile(User u, String fileName) throws IOException {
		
		String find = u.getUserWriteData();
		String replace = "";
		
		try {
			
			File dataFile = new File(fileName);
			FileReader reader = new FileReader(dataFile);
			
			String s = "";
			String totalStr = "";
			
			BufferedReader br = new BufferedReader(reader);
			
			while ((s = br.readLine()) != null) {
	            totalStr += s + "\n";
	        }
			
			//FIXME: for whatever reason, this does no replace cannot find and replace the User
			totalStr = totalStr.replaceAll(find, replace);
			
			//For Testing purposes
//			System.out.println(find);
//			System.out.println(totalStr);
			
			FileWriter writer = new FileWriter(dataFile);
			
			writer.write(totalStr);
			writer.close();
			
		}
		catch (IOException e) {
			throw e;
		}
		
	}
	
	
	public static void removeCategoryFromFile(category c, String fileName) throws IOException {
		
		String find = c.getCategoryWriteData();
		String replace = "";
		
		try {
			
			File dataFile = new File(fileName);
			FileReader reader = new FileReader(dataFile);
			
			String s = "";
			String totalStr = "";
			
			BufferedReader br = new BufferedReader(reader);
			
			while ((s = br.readLine()) != null) {
	            totalStr += s + "\n";
	        }
			
			totalStr = totalStr.replaceAll(find, replace);
			
			FileWriter writer = new FileWriter(dataFile);
			
			writer.write(totalStr);
			writer.close();
			
		}
		catch (IOException e) {
			throw e;
		}
		
	}
	
	
	public static void removeGroupFromFile(Group g, String fileName, String catName) throws IOException {
		
		String find = g.getGroupWriteData(catName);
		String replace = "";
		
		try {
			
			File dataFile = new File(fileName);
			FileReader reader = new FileReader(dataFile);
			
			String s = "";
			String totalStr = "";
			
			BufferedReader br = new BufferedReader(reader);
			
			while ((s = br.readLine()) != null) {
	            totalStr += s + "\n";
	        }
			
			totalStr = totalStr.replaceAll(find, replace);
			
			FileWriter writer = new FileWriter(dataFile);
			
			writer.write(totalStr);
			writer.close();
			
		}
		catch (IOException e) {
			throw e;
		}
		
	}

}
