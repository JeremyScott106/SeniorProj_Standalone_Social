package application;

import Project.Admin;
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
				// This section creates the username label, non-clickable because it is in a pop-up
			JLabel lblUid = new JLabel(u.getId());
			lblUid.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblUid.setForeground(Color.BLACK);
			lblUid.setBounds(10, gridYLoc, lblUid.getPreferredSize().width+10, 25);

				// This section is for admin tools
			if (manager.getCurrentUser() instanceof Admin) {
				int gridXLoc = 10;
				JButton btnBanUser = new JButton();
				JButton btnSuspendUser = new JButton();
				
				btnBanUser = new JButton("Ban");
				
				
				btnBanUser.setFont(new Font("Tahoma", Font.BOLD, 15));
				btnBanUser.setBounds(gridXLoc, gridYLoc, btnBanUser.getPreferredSize().width + 10, 25);
				btnBanUser.addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent e) {

					}
				});
				gridXLoc += btnBanUser.getSize().width + 5;
				
				btnSuspendUser = new JButton("Suspend");
				btnSuspendUser.setFont(new Font("Tahoma", Font.BOLD, 15));
				btnSuspendUser.setBounds(gridXLoc, gridYLoc, btnSuspendUser.getPreferredSize().width + 10, 25);
				btnSuspendUser.addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent e) {

					}
				});
				gridXLoc += btnSuspendUser.getSize().width + 5;
				
					// move the previous tag over to make space for ban and suspend buttons
				lblUid.setBounds(gridXLoc, gridYLoc, lblUid.getPreferredSize().width+10, 25);
				usersPanel.add(btnBanUser);
				usersPanel.add(btnSuspendUser);
				
			}
			
			usersPanel.add(lblUid);
				// adjust YLoc to next location
			gridYLoc += lblUid.getSize().height + padding;	
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