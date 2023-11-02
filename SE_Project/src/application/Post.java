package application;
import Project.SystemManager;

import java.awt.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class Post extends JFrame {

	private JMenuBar topBar;
	private SystemManager manager;
	private JFrame currentFrame;
	
	// Window builder only seems to know how to use the blank constructor -- Use this to develop code then transfer to buildGUI//	
	public Post() {
		
	}
	
	@SuppressWarnings("exports")
	public Post(SystemManager sm,  JMenuBar jmb,  JFrame frame, Dimension dim) {
		topBar = jmb;
		manager = sm;
		currentFrame = frame;
		currentFrame.setSize(dim);
		displayGUI();
	}
	
				//Only a menu so far//
	private void displayGUI() {
		currentFrame.setLayout(new BorderLayout(0, 0));
		currentFrame.add(topBar, BorderLayout.NORTH);
		currentFrame.setTitle("This is the Post view");
		currentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Put Code Here //
		
		currentFrame.setVisible(true);
	}
}
