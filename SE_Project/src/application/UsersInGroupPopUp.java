package application;

import Project.SystemManager;
import Project.User;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

@SuppressWarnings("serial")
public class UsersInGroupPopUp extends JDialog {
	private SystemManager manager;
	private JFrame listUsers;
	private JPanel usersPanel;

	public UsersInGroupPopUp(SystemManager manager) {
		this.manager = manager;
		initialize();
	}
	
		// Creates the panel that houses the list of all users
		// Admins will have ability to ban/suspend users from here
	private JPanel createUserList() {
		
		JPanel usersPanel = new JPanel();
		usersPanel.setLayout(null);
		
		int gridYLoc = 10;					// used to make infinite length list
		int padding = 3;					// adjust the gap between items
		
			// checks if group is null, displays error.  This can happen if the user changes screen while open, no refresh button, should be no issue
		if (manager.getCurrentGroup() == null) {
			JOptionPane.showMessageDialog(null, "Current Group Is NULL, check backend for issues");
			return null;
		}

		ArrayList<User> alUsers = manager.getUsersInGroup(manager.getCurrentGroup());

			// adds users to panel
		for (User u : alUsers) {
			JLabel lblUid = new JLabel(u.getId());
			lblUid.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblUid.setForeground(Color.BLACK);
			lblUid.setBounds(10, gridYLoc, lblUid.getPreferredSize().width+10, 25);

			gridYLoc += lblUid.getSize().height + padding;				// adjust YLoc to next location
			
			usersPanel.add(lblUid);
		}
		
		
		return usersPanel;
	}
	
		// Main method that creates, builds and displays gui
	private void initialize() {

		listUsers = new JFrame();
		listUsers.setDefaultCloseOperation(HIDE_ON_CLOSE);
		listUsers.setTitle("All users in this group");
		listUsers.setLayout(null);
		listUsers.setLocationRelativeTo(null);
		listUsers.setSize(350, 500);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout(0,0));
		
		usersPanel = createUserList();		
		mainPanel.add(usersPanel, BorderLayout.CENTER);	
		mainPanel.setSize(getPreferredSize());
		
		JScrollPane scrollPanel = new JScrollPane(mainPanel);
		scrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPanel.setSize(listUsers.getSize().width-15, listUsers.getSize().height-36);
		
		listUsers.getContentPane().add(scrollPanel, BorderLayout.CENTER);
		listUsers.setVisible(true);
	}
}