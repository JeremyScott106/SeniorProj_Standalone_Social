package application;
import Project.SystemManager;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Main extends JFrame {
	
	private static JMenuBar topBar;
	private static SystemManager manager;
	private JFrame currentFrame;
	private JPanel homeView;
	
	public Main() {
		//load file//
	}
	
	// Creates the menu bar //
	private JMenuBar createMenus() {
				//Bar that holds Menus//
		JMenuBar menus = new JMenuBar();
				//Menus//
		JMenu file = new JMenu("File");
		JMenu test = new JMenu("Test");
		JMenu view = new JMenu("View");
				//Sub-menus//
		JMenuItem login = new JMenuItem("Login");
		JMenuItem myInfo = new JMenuItem("My Info");
		JMenuItem fakeUser1 = new JMenuItem("Make Fake User1");
		JMenuItem switchCats = new JMenuItem("Category View");
		JMenuItem switchHome = new JMenuItem("Home");
				//Add menus to bar//
		menus.add(file);
		menus.add(view);
		menus.add(test);
				//Add sub-menus to menus//
		file.add(login);
		test.add(myInfo);
		test.add(fakeUser1);
		view.add(switchHome);
		view.add(switchCats);
				//Events for menu clicks//
		LoginEvent e1 = new LoginEvent();
		login.addActionListener(e1);
		MakeFakeUser e2 = new MakeFakeUser();	//when files can be read, this can disappear//
		fakeUser1.addActionListener(e2);
		MyInformation e3 = new MyInformation();
		myInfo.addActionListener(e3);

		switchHome.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	onMenuClick();
            	new Home(manager, topBar, currentFrame);
            }
        });
		
		switchCats.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	onMenuClick();
            	new Category(manager, topBar, currentFrame);
            }
        });
				
		return menus;
	}
	
	private void onMenuClick() {
		currentFrame.getContentPane().removeAll();
		currentFrame.repaint();
	}
	
				// This will create a new window that allows a user to login //
	private class LoginEvent implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new Login(manager);
		}
	}
				// This will create a fake user that can be used for testing, System will show login information //
	private class MakeFakeUser implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String name = "Testey One";
			String bday = "01/01/1999";
			String city = "Home";
			String state = "Georgia";
			String username = "DespisesJava123";
			String password = "abc123";
			
			manager.registerUser(name, bday, city, state, username, password);
			
			System.out.printf("   /----/\nNew User Created\n   /----/\nUsername: %s\nPassword: %s\n", username, password);
		}
	}
				// This will show the current user login information //
	private class MyInformation implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.printf("\nUser is signed in: %b\n", manager.isLoggedIn());
			System.out.printf("User is admin: %b\n", manager.isAdmin());
			if (manager.isLoggedIn()) {
				System.out.printf("Current User: %s\n", manager.getCurrentUser().getId());
			}
		}
	}
	
	private void init() {
		
		if (manager == null) {
			startMemory();
		}
		
		new Home(manager, topBar, currentFrame);
	}
	
	private void startMemory() {
		manager = new SystemManager();
		topBar = createMenus();
		currentFrame = new JFrame();
	}
	
	
				// Main //
	public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new Main().init();
            }
        });
	}
}
