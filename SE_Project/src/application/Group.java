package application;
import Project.SystemManager;

import java.awt.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class Group extends JFrame {

	private JMenuBar topBar;
	private SystemManager manager;
	private JFrame currentFrame;
	private JPanel homeView;
	
	
	@SuppressWarnings("exports")
	public Group(SystemManager sm,  JMenuBar jmb,  JFrame frame) {
		
		topBar = jmb;
		manager = sm;
		currentFrame = frame;
		displayGUI();
	}
	
				// ************ Home View ************ //
	private class categoryPanel extends JPanel {

		private JPanel homePane;
		
		public categoryPanel(JPanel p, Group info) {
			homePane = p;
			setOpaque(true);
			setBackground(Color.blue.darker());		
		}
		
	    @Override
	    public Dimension getPreferredSize() {
	        return (new Dimension(500, 500));
	    }
	}
	
				//Only a menu so far//
	private void displayGUI() {
		//this.setSize(500,500);
		currentFrame.setTitle("This is the Group view");
		currentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
																		
		JPanel mainPane = new JPanel();
		mainPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		mainPane.setLayout(new CardLayout());
		
		homeView = new categoryPanel(mainPane, this);
		
		mainPane.add(homeView);
		currentFrame.getContentPane().add(mainPane, BorderLayout.CENTER);   

		
		setJMenuBar(topBar);
		currentFrame.getContentPane().add(topBar, BorderLayout.NORTH);
		
		currentFrame.pack();
		currentFrame.setVisible(true);
	}
}
