package application;
import Project.SystemManager;

import java.awt.*;
import java.awt.event.*;
import java.util.Date;

import javax.swing.*;

@SuppressWarnings("serial")
public class Home extends JFrame {

	private static JMenuBar topBar;
	private static SystemManager manager;
	private JFrame currentFrame;
	private JPanel homeView;
	
	
	public Home(SystemManager sm, @SuppressWarnings("exports") JMenuBar jmb, JFrame frame) {
		
		topBar = jmb;
		manager = sm;
		currentFrame = frame;
		displayGUI();
	}
	
				// ************ Home View ************ //
	private class categoryPanel extends JPanel {

		private JPanel homePane;
		
		public categoryPanel(JPanel p, Home info) {
			homePane = p;
			setOpaque(true);
			setBackground(Color.black);		
		}
		
	    @Override
	    public Dimension getPreferredSize() {
	        return (new Dimension(500, 500));
	    }
	}
	
				//Only a menu so far//
	private void displayGUI() {
		//this.setSize(500,500);
		currentFrame.setTitle("This is the Home view");
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
