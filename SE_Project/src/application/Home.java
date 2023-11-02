package application;
import Project.Admin;
import Project.SystemManager;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class Home extends JFrame {

	private JMenuBar topBar;
	private SystemManager manager;
	private JFrame currentFrame;
	
	public Home() {
		getContentPane().setLayout(null);
		
		JButton lblNewLabel = new JButton("Admin Tools");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(274, 10, 152, 19);
		getContentPane().add(lblNewLabel);
		
		JLabel lblCurrentUser = new JLabel("Current User: ");
		lblCurrentUser.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCurrentUser.setBounds(10, 10, 122, 19);
		getContentPane().add(lblCurrentUser);
		
		JLabel lblUid = new JLabel("UID");
		lblUid.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblUid.setBounds(129, 10, 135, 19);
		getContentPane().add(lblUid);
		
	}
	
	
	@SuppressWarnings("exports")
	public Home(SystemManager sm,  JMenuBar jmb,  JFrame frame, Dimension dim) {
		topBar = jmb;
		manager = sm;
		currentFrame = frame;
		currentFrame.setSize(dim);
		displayGUI();
	}
	
	private void onViewChangeClick() {
		currentFrame.getContentPane().removeAll();
		currentFrame.repaint();
	}

	private void displayGUI() {
		currentFrame.getContentPane().setLayout(new BorderLayout(0, 0));
		currentFrame.getContentPane().add(topBar, BorderLayout.NORTH);
		currentFrame.setTitle("This is the Home view");
		currentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		currentFrame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		if (manager.getCurrentUser() instanceof Admin) {
			JButton adminBtn = new JButton("Admin Tools");
			adminBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
			adminBtn.setBounds(274, 10, 152, 19);
			panel.add(adminBtn);
			adminBtn.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
					onViewChangeClick();
					new AdminTools(manager, topBar, currentFrame, currentFrame.getSize());
				}
			});
		}
		
		JLabel lblCurrentUser = new JLabel("Current User: ");
		lblCurrentUser.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCurrentUser.setBounds(10, 10, 122, 19);
		panel.add(lblCurrentUser);
		
		if (manager.getCurrentUser() != null) {
			JLabel lblUid = new JLabel(manager.getCurrentUser().getId());
			lblUid.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblUid.setBounds(129, 10, 95, 19);
			panel.add(lblUid);
		}
																		

		currentFrame.setVisible(true);
	}
}
