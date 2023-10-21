package application;
import Project.SystemManager;

import java.awt.*;
import java.awt.event.*;
import java.util.Date;

import javax.swing.*;

@SuppressWarnings("serial")
public class Main extends JFrame {
	private JFrame Main;
	private JMenuBar menubar;
	private JMenu Menu;
	private JMenuItem Login;
	public SystemManager manager = new SystemManager();
	
	public Main() {
		setLayout(new FlowLayout());
		
		menubar = new JMenuBar();
		add(menubar);
		
		Menu = new JMenu("Menu");
		JMenu Status = new JMenu("Status");
		menubar.add(Menu);
		menubar.add(Status);
		
		Login = new JMenuItem("Login");
		JMenuItem amLoggedIn = new JMenuItem("Login Status");
		Menu.add(Login);
		Status.add(amLoggedIn);
		
		setJMenuBar(menubar);
		
		LoginEvent e = new LoginEvent();
		Login.addActionListener(e);
		
		amLoggedIn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.printf("User is signed in: %b\n", manager.isLoggedIn());
				System.out.printf("User is admin: %b\n", manager.isAdmin());
				if (manager.isLoggedIn()) {
					System.out.printf("Current User: %s\n", manager.getCurrentUser().getId());
				}
			}
			
		});
	}
	
	public class LoginEvent implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			makeFakeUser();
			new Login(manager);
		}
	}
	
	private void makeFakeUser() {
		String name = "Testey One";
		String bday = "01/01/1999";
		String city = "Home";
		String state = "Georgia";
		String username = "DespisesJava123";
		String password = "abc123";
		
		manager.registerUser(name, bday, city, state, username, password);
		
	}
	
	public static void main(String args[]) {
		Main gui = new Main();
		
		gui.setDefaultCloseOperation(EXIT_ON_CLOSE);
		gui.setSize(500,500);
		gui.setVisible(true);
		gui.setTitle("The End of the Internet");
		
	}
}
