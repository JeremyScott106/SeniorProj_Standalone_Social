package application;
import Project.Admin;
import Project.SystemManager;
import Project.User;

import java.awt.*;
import java.text.SimpleDateFormat;
import javax.swing.*;

@SuppressWarnings("serial")
public class Profile extends JFrame {

	private JMenuBar topBar;
	private SystemManager manager;
	private JFrame currentFrame;
	private User displayedUser;
	
		// If no user is pushed, get currently logged in user and push it to form (null checks in place) //
	@SuppressWarnings("exports")
	public Profile(SystemManager sm,  JMenuBar jmb,  JFrame frame, Dimension dim) {
		this(sm, jmb, frame, dim, sm.getCurrentUser());
	}

	@SuppressWarnings("exports")
	public Profile(SystemManager sm,  JMenuBar jmb,  JFrame frame, Dimension dim, User u) {
		topBar = jmb;
		manager = sm;
		currentFrame = frame;
		frame.setSize(dim);
		displayedUser = u;
		displayGUI();
	}
	
		// Build The GUI //
	private void displayGUI() {
		currentFrame.setLayout(new BorderLayout(0, 0));
		currentFrame.add(topBar, BorderLayout.NORTH);
		currentFrame.setTitle("This is the Profile view");
		currentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		currentFrame.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Profile");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(10, 10, 82, 25);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("User ID:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(35, 45, 110, 26);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Name:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(35, 81, 110, 26);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Birthday:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(35, 153, 110, 26);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("City/State:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(35, 117, 110, 26);
		panel.add(lblNewLabel_4);
		
		// Check to see if user exist in call, if so, print user information into form //
		if (displayedUser != null) {
			if (displayedUser instanceof Admin) {
				JLabel lblNewLabel_5 = new JLabel("(ADMIN) " + displayedUser.getId());
				lblNewLabel_5.setBounds(137, 45, 207, 26);
				panel.add(lblNewLabel_5);
			}
			else {
				JLabel lblNewLabel_5 = new JLabel(displayedUser.getId());
				lblNewLabel_5.setBounds(137, 45, 207, 26);
				panel.add(lblNewLabel_5);
			}
			
			JLabel lblNewLabel_5_1 = new JLabel(displayedUser.getName());
			lblNewLabel_5_1.setBounds(137, 81, 207, 26);
			panel.add(lblNewLabel_5_1);
			
			JLabel lblNewLabel_5_2 = new JLabel(displayedUser.getCity() + ", " + displayedUser.getState());
			lblNewLabel_5_2.setBounds(137, 117, 207, 26);
			panel.add(lblNewLabel_5_2);
			
			SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
			String bday = formatter.format(displayedUser.getBirthday());
			JLabel lblNewLabel_5_3 = new JLabel(bday);
			lblNewLabel_5_3.setBounds(137, 153, 207, 26);
			panel.add(lblNewLabel_5_3);
		}

		currentFrame.setVisible(true);
	}
}
