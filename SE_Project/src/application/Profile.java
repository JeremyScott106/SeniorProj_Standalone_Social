package application;
import Project.SystemManager;

import java.awt.*;
import java.text.SimpleDateFormat;

import javax.swing.*;

@SuppressWarnings("serial")
public class Profile extends JFrame {

	private JMenuBar topBar;
	private SystemManager manager;
	private JFrame currentFrame;
	private JPanel homeView;
	
	@SuppressWarnings("exports")
	public Profile(SystemManager sm,  JMenuBar jmb,  JFrame frame) {
		topBar = jmb;
		manager = sm;
		currentFrame = frame;
		displayGUI();
	}
	
				// ************ Home View ************ //
	private class categoryPanel extends JPanel {

		private JPanel homePane;
		
		public categoryPanel(JPanel p, Profile info) {
			homePane = p;
			setOpaque(true);
			setBackground(Color.orange.darker());		
		}
		
	    @Override
	    public Dimension getPreferredSize() {
	        return (new Dimension(500, 500));
	    }
	}
	
	
	private void displayGUI() {
		currentFrame.setLayout(new BorderLayout(0, 0));
		
		currentFrame.add(topBar, BorderLayout.NORTH);
		
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
		if (manager.isLoggedIn()) {
			if (manager.isAdmin()) {
				JLabel lblNewLabel_5 = new JLabel("(ADMIN) " + manager.getCurrentUser().getId());
				lblNewLabel_5.setBounds(137, 45, 207, 26);
				panel.add(lblNewLabel_5);
			}
			else {
				JLabel lblNewLabel_5 = new JLabel(manager.getCurrentUser().getId());
				lblNewLabel_5.setBounds(137, 45, 207, 26);
				panel.add(lblNewLabel_5);
			}
			
			
			JLabel lblNewLabel_5_1 = new JLabel(manager.getCurrentUser().getName());
			lblNewLabel_5_1.setBounds(137, 81, 207, 26);
			panel.add(lblNewLabel_5_1);
			
			JLabel lblNewLabel_5_2 = new JLabel(manager.getCurrentUser().getCity() + ", " + manager.getCurrentUser().getState());
			lblNewLabel_5_2.setBounds(137, 117, 207, 26);
			panel.add(lblNewLabel_5_2);
			
			SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
			String bday = formatter.format(manager.getCurrentUser().getBirthday());
			JLabel lblNewLabel_5_3 = new JLabel(bday);
			lblNewLabel_5_3.setBounds(137, 153, 207, 26);
			panel.add(lblNewLabel_5_3);
		}
		
//		currentFrame.pack();
		currentFrame.setVisible(true);
	}
}
