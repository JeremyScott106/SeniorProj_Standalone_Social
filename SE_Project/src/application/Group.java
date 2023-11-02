package application;
import Project.SystemManager;

import java.awt.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class Group extends JFrame {

	private JMenuBar topBar;
	private SystemManager manager;
	private JFrame currentFrame;
	
	// Window builder only seems to know how to use the blank constructor -- Use this to develop code then transfer to buildGUI//
	public Group() {
		
	}
	
	
	@SuppressWarnings("exports")
	public Group(SystemManager sm,  JMenuBar jmb,  JFrame frame, Dimension dim) {
		topBar = jmb;
		manager = sm;
		currentFrame = frame;
		currentFrame.setSize(dim);
		displayGUI();
	}
	
	private void displayGUI() {
		currentFrame.setLayout(new BorderLayout(0, 0));
		currentFrame.add(topBar, BorderLayout.NORTH);
		currentFrame.setTitle("This is the Group view");
		currentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Put Code Here //
		
		currentFrame.setVisible(true);
	}
}
