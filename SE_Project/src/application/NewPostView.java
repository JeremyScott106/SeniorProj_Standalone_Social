package application;
import Project.Post;
import Project.SystemManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class NewPostView extends JFrame {

	private JMenuBar topBar;
	private SystemManager manager;
	private JFrame currentFrame;
	private JTextField txfPostTitle;
	private JTextField txfPostBody;
	
	// Window builder only seems to know how to use the blank constructor -- Use this to develop code then transfer to buildGUI//	
	public NewPostView() {	
	}
	
	@SuppressWarnings("exports")
	public NewPostView(SystemManager sm,  JMenuBar jmb,  JFrame frame, Dimension dim) {
		this.topBar = jmb;
		this.manager = sm;
		this.currentFrame = frame;
		this.currentFrame.setSize(dim);
		displayGUI();
	}
	
	// This will clear the current frame, allows for rebuilding the frame //
	private void onViewChangeClick() {
		currentFrame.getContentPane().removeAll();
		currentFrame.repaint();
	}
	
	private JPanel createPostForm() {
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblPostTitle = new JLabel("Post Title");
		lblPostTitle.setBounds(10, 10, 45, 13);
		panel.add(lblPostTitle);
		
		JLabel lblPostBody = new JLabel("Message");
		lblPostBody.setBounds(10, 62, 45, 13);
		panel.add(lblPostBody);
		
		txfPostTitle = new JTextField();
		txfPostTitle.setBounds(10, 33, 416, 19);
		panel.add(txfPostTitle);
		txfPostTitle.setColumns(10);
		
		txfPostBody = new JTextField();
		txfPostBody.setBounds(10, 85, 416, 124);
		panel.add(txfPostBody);
		txfPostBody.setColumns(10);
		
		JButton btnPost = new JButton("Post");
		btnPost.setBounds(341, 232, 85, 21);
		btnPost.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if (manager.createNewPost(manager.getCurrentGroup(), txfPostTitle.getText(), txfPostBody.getText())) {
	            	onViewChangeClick();
					new GroupView(manager, topBar, currentFrame, currentFrame.getSize());
						// Change to PostView after PostView is created //
            	}
            	else {
            		JOptionPane.showMessageDialog(null, "An error occured");
            	}
			}
		});
		panel.add(btnPost);
		
		JButton btnBack = new JButton("Cancel");
		btnBack.setBounds(246, 232, 85, 21);
		btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	onViewChangeClick();
				new GroupView(manager, topBar, currentFrame, currentFrame.getSize());
			}
		});
		panel.add(btnBack);
		
		
		return panel;
	}
	
	private void displayGUI() {
		currentFrame.getContentPane().setLayout(new BorderLayout(0, 0));
		currentFrame.getContentPane().add(topBar, BorderLayout.NORTH);
		currentFrame.setTitle("This is the Post view");
		currentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel mainPanel = new JPanel();
		currentFrame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new BorderLayout(0,0));
		
		JPanel postForm = createPostForm();
		mainPanel.add(postForm, BorderLayout.CENTER);
		
		currentFrame.setVisible(true);
	}
}
