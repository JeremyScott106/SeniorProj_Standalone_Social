package application;
import Project.SystemManager;

import java.awt.*;
import java.awt.event.*;
import java.util.Date;

import javax.swing.*;

@SuppressWarnings("serial")
public class Main extends JFrame {
	
	private JMenuBar topBar;
	private JPanel homeView;
	private JPanel categoryView;
	private JPanel groupView;
	private JPanel postView;
	private JPanel profileView;
	
	private SystemManager manager = new SystemManager();
	
	
	// Creates the menu bar //
	private JMenuBar createMenus() {
				//Bar that holds Menus//
		JMenuBar menus = new JMenuBar();
				//Menus//
		JMenu file = new JMenu("File");
		JMenu test = new JMenu("Test");
				//Sub-menus//
		JMenuItem login = new JMenuItem("Login");
		JMenuItem myInfo = new JMenuItem("My Info");
		JMenuItem fakeUser1 = new JMenuItem("Make Fake User1");
				//Add menus to bar//
		menus.add(file);
		menus.add(test);
				//Add sub-menus to menus//
		file.add(login);
		test.add(myInfo);
		test.add(fakeUser1);
		
				//Events for menu clicks//
		LoginEvent e1 = new LoginEvent();
		login.addActionListener(e1);
		MakeFakeUser e2 = new MakeFakeUser();	//when files can be read, this can disappear//
		fakeUser1.addActionListener(e2);
		MyInformation e3 = new MyInformation();
		myInfo.addActionListener(e3);
		
		
		return menus;
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
				// ************ Home View ************ //
	private class homePanel extends JPanel {

		private JPanel mainPane;
		private JButton jb;
		
		public homePanel(JPanel p, Main info) {
			mainPane = p;
			setOpaque(true);
			setBackground(Color.black);		
		}
		
	    @Override
	    public Dimension getPreferredSize()
	    {
	        return (new Dimension(500, 500));
	    }
	}
	
				//Only a menu so far//
	private void displayGUI() {
		this.setSize(500,500);
		JFrame frame = new JFrame("The Computer Sees Everything You Are Doing");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		JPanel mainPane = new JPanel();
		mainPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		mainPane.setLayout(new CardLayout());
		
		homeView = new homePanel(mainPane, this);
		
		/*
		categoryView;
		groupView;
		postView;
		profileView;
		*/
		mainPane.add(homeView);
		frame.getContentPane().add(mainPane, BorderLayout.CENTER);   
		
		topBar = createMenus();
		
		setJMenuBar(topBar);
		frame.add(topBar, BorderLayout.NORTH);
		
		frame.pack();
		frame.setVisible(true);
	}
	
				// Main //
	public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new Main().displayGUI();
            }
        });
	}
}
