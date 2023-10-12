package application;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Main extends JFrame {
	JMenuBar menubar;
	JMenu Menu;
	JMenuItem Login;
	
	public Main() {
		setLayout(new FlowLayout());
		
		menubar = new JMenuBar();
		add(menubar);
		
		Menu = new JMenu("Menu");
		menubar.add(Menu);
		
		Login = new JMenuItem("Login");
		Menu.add(Login);
		setJMenuBar(menubar);
		
		LoginEvent e = new LoginEvent();
		Login.addActionListener(e);
	}
	
	public class LoginEvent implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new Login();
		}
	}
	
	public static void main(String args[]) {
		Main gui = new Main();
		
		gui.setDefaultCloseOperation(EXIT_ON_CLOSE);
		gui.setSize(500,500);
		gui.setVisible(true);
		gui.setTitle("The End of the Internet");
		
	}
}
