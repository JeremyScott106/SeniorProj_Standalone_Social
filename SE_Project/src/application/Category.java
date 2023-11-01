package application;
import Project.SystemManager;

import java.awt.*;
import java.awt.event.*;
import java.util.Date;

import javax.swing.*;

@SuppressWarnings("serial")
public class Category extends JFrame {

	private static JMenuBar topBar;
	private static SystemManager manager;
	private JFrame categoryFrame;
	private JPanel categoryView;
	
	
	
	public Category(SystemManager sm, @SuppressWarnings("exports") JMenuBar jmb) {
		topBar = jmb;
		manager = sm;
		displayGUI();
	}
	
				// ************ Home View ************ //
	private class categoryPanel extends JPanel {

		private JPanel categoryPane;
		private JButton jb;
		
		public categoryPanel(JPanel p, Category info) {
			categoryPane = p;
			setOpaque(true);
			setBackground(Color.red.darker().darker());		
		}
		
	    @Override
	    public Dimension getPreferredSize()
	    {
	        return (new Dimension(500, 500));
	    }
	}
	
				//Only a menu so far//
	private void displayGUI() {
		this.setSize(500,500);
		categoryFrame = new JFrame("This is the category view");
		categoryFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		JPanel mainPane = new JPanel();
		mainPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		mainPane.setLayout(new CardLayout());
		
		categoryView = new categoryPanel(mainPane, this);
		
		mainPane.add(categoryView);
		categoryFrame.getContentPane().add(mainPane, BorderLayout.CENTER);   

		
		setJMenuBar(topBar);
		categoryFrame.getContentPane().add(topBar, BorderLayout.NORTH);
		
		categoryFrame.pack();
		categoryFrame.setVisible(true);
	}
}
