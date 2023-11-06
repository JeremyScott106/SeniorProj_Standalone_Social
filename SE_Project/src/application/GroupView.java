package application;
import Project.Group;
import Project.Post;
import Project.SystemManager;
import Project.category;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.*;

@SuppressWarnings("serial")
public class GroupView extends JFrame {

	private JMenuBar topBar;
	private SystemManager manager;
	private JFrame currentFrame;

	
	// Window builder only seems to know how to use the blank constructor -- Use this to develop code then transfer to buildGUI//
	public GroupView() {
	}
	
	
	@SuppressWarnings("exports")
	public GroupView(SystemManager sm,  JMenuBar jmb,  JFrame frame, Dimension dim) {
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
	
	private JPanel createTitlePane() {
		
		JPanel titlePanel = new JPanel();

		int gridx = 20;
		int padding = 10;
		
		titlePanel.setPreferredSize(new Dimension(0,80));
		titlePanel.setLayout(null);
		
		JLabel lblHome = new JLabel("Home");
		lblHome.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblHome.setForeground(Color.BLUE.darker());
		lblHome.setBounds(gridx, 10, lblHome.getPreferredSize().width + padding, 25);
		gridx += lblHome.getWidth() + padding;
		lblHome.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblHome.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
            	onViewChangeClick();
            	new Home(manager, topBar, currentFrame, currentFrame.getSize());
            }
        });
		titlePanel.add(lblHome);
		
		JLabel lblSpacer1 = new JLabel("//");
		lblSpacer1.setBounds(gridx, 10, 10, 25);
		gridx += lblSpacer1.getWidth() + padding;
		titlePanel.add(lblSpacer1);
		
		JLabel lblCurrentCategory = new JLabel(manager.getCurrentCategory().getName());
		lblCurrentCategory.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCurrentCategory.setForeground(Color.BLUE.darker());
		lblCurrentCategory.setBounds(gridx, 10, lblCurrentCategory.getPreferredSize().width + padding, 25);
		gridx += lblCurrentCategory.getWidth() + padding;
		lblCurrentCategory.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblCurrentCategory.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
            	onViewChangeClick();
            	manager.setCurrentGroup(null);
            	new CategoryView(manager, topBar, currentFrame, currentFrame.getSize());
            }
        });
		titlePanel.add(lblCurrentCategory);
		
		JLabel lblSpacer2 = new JLabel("//");
		lblSpacer2.setBounds(gridx, 10, 10, 25);
		gridx += lblSpacer2.getWidth() + padding;
		titlePanel.add(lblSpacer2);
		
		JLabel lblCurrentGroup = new JLabel(manager.getCurrentGroup().getGroupName());
		lblCurrentGroup.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCurrentGroup.setBounds(gridx, 10, lblCurrentGroup.getPreferredSize().width + padding, 25);
		titlePanel.add(lblCurrentGroup);
		
		//	SECOND ROW OF LABLES //
		gridx = 20;
		
		if (manager.getCurrentUser() == null){

			JButton btnLogin = new JButton("Login");
			btnLogin.setFont(new Font("Tahoma", Font.BOLD, 15));
			btnLogin.setBounds(gridx, 45, btnLogin.getPreferredSize().width + padding, 25);
			gridx += btnLogin.getWidth() + padding;
			titlePanel.add(btnLogin);
			btnLogin.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
					new LoginPopUp(manager);
				}
			});

		

			
			JButton btnNewUser = new JButton("Register New Account");
			btnNewUser.setFont(new Font("Tahoma", Font.BOLD, 15));
			btnNewUser.setBounds(gridx, 45, btnNewUser.getPreferredSize().width + padding, 25);
			gridx += btnNewUser.getWidth() + padding;
			titlePanel.add(btnNewUser);
			btnNewUser.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
					new CreateUserPopUp(manager);
				}
			});

		}
		
		else if (!manager.isUserOfGroup(manager.getCurrentUser(), manager.getCurrentGroup())) {
			
			JLabel memberStatus = new JLabel("Only Members Can Post In Group");
			memberStatus.setFont(new Font("Tahoma", Font.BOLD, 15));
			memberStatus.setBounds(gridx, 45, memberStatus.getPreferredSize().width + padding + padding, 25);
			gridx += memberStatus.getWidth() + padding;
			titlePanel.add(memberStatus);
			
			JLabel joinGroup = new JLabel("Join This Group");
			joinGroup.setFont(new Font("Tahoma", Font.BOLD, 15));
			joinGroup.setForeground(Color.BLUE.darker());
			joinGroup.setBounds(gridx, 45, joinGroup.getPreferredSize().width + padding, 25);
			gridx += joinGroup.getWidth() + padding;
			joinGroup.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			joinGroup.addMouseListener(new MouseAdapter() {
			    @Override
			    public void mouseClicked(MouseEvent e) {
			    	boolean result = manager.joinGroup(manager.getCurrentUser(), manager.getCurrentGroup());
			    	if (result) {
			    		JOptionPane.showMessageDialog(null, "Successfully Joined Group");
			    	}
			    	else {
			    		JOptionPane.showMessageDialog(null, "Something Went Wrong");
			    	}
	            }
	        });
			titlePanel.add(joinGroup);
		}
		
		else {
			String mbmSince = "Member Since: " + manager.getMembership(manager.getCurrentGroup(), manager.getCurrentUser()).getDate().toString();

			JLabel memberStatus = new JLabel(mbmSince);
			memberStatus.setFont(new Font("Tahoma", Font.BOLD, 15));
			memberStatus.setBounds(gridx, 45, memberStatus.getPreferredSize().width + padding + padding, 25);
			gridx += memberStatus.getWidth() + padding;
			titlePanel.add(memberStatus);
		}
		
		JButton btnRefreshPage = new JButton("Refresh");
		btnRefreshPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onViewChangeClick();
				new GroupView(manager, topBar, currentFrame, currentFrame.getSize());
			}
		});
		btnRefreshPage.setBounds(currentFrame.getBounds().width - 125, 10, 100, 25);
			// FIXME: BUG -> Refresh button disappears if frame shrinks.
		titlePanel.add(btnRefreshPage);
		
		if (manager.isUserOfGroup(manager.getCurrentUser(), manager.getCurrentGroup())) {
			JButton newPost = new JButton("Create New Post");
			newPost.setFont(new Font("Tahoma", Font.BOLD, 15));
			int btnWidth = newPost.getPreferredSize().width + padding;
			newPost.setBounds(currentFrame.getBounds().width - btnWidth - 25, 45, newPost.getPreferredSize().width + padding, 25);
			titlePanel.add(newPost);
			newPost.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	onViewChangeClick();
	            	new NewPostView(manager, topBar, currentFrame, currentFrame.getSize());
				}
			});
		}
		
		return titlePanel;
		
	}
	
private JScrollPane createScrollPane() {
	
		ArrayList<Post> alPost = manager.viewPostsInGroup(manager.getCurrentGroup());
		JScrollPane postScrollPane = new JScrollPane();
		JPanel postGridPane = new JPanel();
		
		GridLayout gl = new GridLayout();
		gl.setColumns(1);
		gl.setRows(alPost.size());
		gl.setVgap(4);
		postGridPane.setLayout(gl);
		postScrollPane.add(postGridPane);
				
		for (Post p : alPost) {
			
			JLabel lblToAdd = new JLabel(p.getPostTitle());
			lblToAdd.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblToAdd.setForeground(Color.BLUE.darker());
			lblToAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			lblToAdd.addMouseListener(new MouseAdapter() {
			    @Override
			    public void mouseClicked(MouseEvent e) {
	            	onViewChangeClick();
	            	new NewPostView(manager, topBar, currentFrame, currentFrame.getSize());
	            }
	        });
			postGridPane.add(lblToAdd);
		}
		
		return postScrollPane;
	}
	
	private void displayGUI() {
		currentFrame.setLayout(new BorderLayout(0, 0));
		currentFrame.add(topBar, BorderLayout.NORTH);
		currentFrame.setTitle("This is the listing of posts in group " + manager.getCurrentGroup().getGroupName());
		currentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel mainPanel = new JPanel();
		currentFrame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new BorderLayout(0,0));
		
		JPanel topInsidePanel = createTitlePane();
		mainPanel.add(topInsidePanel, BorderLayout.NORTH);
		
		JScrollPane centerInsidePanel = createScrollPane();
		mainPanel.add(centerInsidePanel, BorderLayout.CENTER);
		
		currentFrame.setVisible(true);
	}
}
