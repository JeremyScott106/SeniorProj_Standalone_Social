package application;
import Project.Admin;
import Project.Group;
import Project.SystemManager;
import Project.User;
import Project.category;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.*;

@SuppressWarnings("serial")
public class ProfileView extends JFrame {

	private JMenuBar topBar;
	private SystemManager manager;
	private JFrame currentFrame;
	private User displayedUser;
	
		// If no user is pushed, get currently logged in user and push it to form (null checks in place) //
	@SuppressWarnings("exports")
	public ProfileView(SystemManager sm,  JMenuBar jmb,  JFrame frame, Dimension dim) {
		this(sm, jmb, frame, dim, sm.getCurrentUser());
	}

	@SuppressWarnings("exports")
	public ProfileView(SystemManager sm,  JMenuBar jmb,  JFrame frame, Dimension dim, User u) {
		this.topBar = jmb;
		this.manager = sm;
		this.currentFrame = frame;
		this.currentFrame.setSize(dim);
		this.displayedUser = u;
		displayGUI();
	}
	
	// This will clear the current frame, allows for rebuilding the frame //
	private void onViewChangeClick() {
		currentFrame.getContentPane().removeAll();
		currentFrame.repaint();
	}
	
	private JPanel createTitlePane() {
		
		JPanel titlePanel = new JPanel();

		int gridx = 20;
		int padding = 10;
		
		titlePanel.setPreferredSize(new Dimension(0,50));
		titlePanel.setLayout(null);
		
		JLabel lblHome = new JLabel("Home");
		lblHome.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblHome.setForeground(Color.BLUE.darker());
		lblHome.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblHome.setBounds(gridx, 10, lblHome.getPreferredSize().width + padding, 25);
		gridx += lblHome.getWidth() + padding;
		lblHome.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
            	onViewChangeClick();
            	new Home(manager, topBar, currentFrame, currentFrame.getSize());
            }
        });
		titlePanel.add(lblHome);
		
		JLabel lblMyGroups = new JLabel("My Groups");
		lblMyGroups.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblMyGroups.setForeground(Color.BLUE.darker());
		lblMyGroups.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblMyGroups.setBounds(gridx, 10, lblMyGroups.getPreferredSize().width + padding, 25);
		gridx += lblMyGroups.getWidth() + padding;
		lblMyGroups.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
            	onViewChangeClick();
            	category c = new category("My Groups");
            	ArrayList<Group> myGroups = manager.getGroupsByUser(manager.getCurrentUser());
            	for (Group g : myGroups) {
            		c.addGroup(g);
            	}
            	new CategoryView(manager, topBar, currentFrame, currentFrame.getSize(), c);
            }
        });
		titlePanel.add(lblMyGroups);

		return titlePanel;
		
	}
	
	private JPanel createForm() {
		JPanel panel = new JPanel();
		
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
		
		
		return panel;
	}
	
		// Build The GUI //
	private void displayGUI() {
		currentFrame.setLayout(new BorderLayout(0, 0));
		currentFrame.add(topBar, BorderLayout.NORTH);
		currentFrame.setTitle("This is the Profile view");
		currentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel mainPanel = new JPanel();
		currentFrame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new BorderLayout(0,0));
		
		JPanel titlePanel = createTitlePane();
		mainPanel.add(titlePanel, BorderLayout.NORTH);
		
		JPanel profilePanel = createForm();
		mainPanel.add(profilePanel, BorderLayout.CENTER);

		currentFrame.setVisible(true);
	}
}
