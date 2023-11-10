package application;

import Project.Post;
import Project.SystemManager;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

@SuppressWarnings("serial")
public class GroupView extends JFrame {

	private JMenuBar topBar;
	private SystemManager manager;
	private JFrame currentFrame;
	private Dimension dim;

	
	// Window builder only seems to know how to use the blank constructor -- Use this to develop code then transfer to buildGUI//
	public GroupView() {
	}
	
	
	@SuppressWarnings("exports")
	public GroupView(SystemManager sm,  JMenuBar jmb,  JFrame frame, Dimension dim) {
		this.topBar = jmb;
		this.manager = sm;
		this.currentFrame = frame;
		this.currentFrame.setSize(dim);
		this.dim = dim;
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
		
		JButton btnRefreshPage = new JButton("Refresh");
		btnRefreshPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onViewChangeClick();
				new GroupView(manager, topBar, currentFrame, currentFrame.getSize());
			}
		});
		int x1 = currentFrame.getBounds().width - (btnRefreshPage.getPreferredSize().width + padding + 50);
		btnRefreshPage.setBounds(x1, 10, btnRefreshPage.getPreferredSize().width + padding, 25);
		titlePanel.add(btnRefreshPage);
		
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
			    		btnRefreshPage.doClick();
			    	}
			    	else {
			    		JOptionPane.showMessageDialog(null, "Something Went Wrong");
			    	}
	            }
	        });
			titlePanel.add(joinGroup);
		}
		
		else {
			String mbrDate = manager.getSimpleDate(manager.getMembership(manager.getCurrentGroup(), manager.getCurrentUser()).getDate());
			String mbmSince = "Member Since: " + mbrDate;

			JLabel memberStatus = new JLabel(mbmSince);
			memberStatus.setFont(new Font("Tahoma", Font.BOLD, 15));
			memberStatus.setBounds(gridx, 45, memberStatus.getPreferredSize().width + padding + padding, 25);
			gridx += memberStatus.getWidth() + padding;
			titlePanel.add(memberStatus);
			
			JLabel leaveGroup = new JLabel("Leave Group");
			leaveGroup.setFont(new Font("Tahoma", Font.BOLD, 15));
			leaveGroup.setForeground(Color.BLUE.darker());
			leaveGroup.setBounds(gridx, 45, leaveGroup.getPreferredSize().width + padding, 25);
			gridx += leaveGroup.getWidth() + padding;
			leaveGroup.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			leaveGroup.addMouseListener(new MouseAdapter() {
			    @Override
			    public void mouseClicked(MouseEvent e) {
			    	if (manager.leaveGroup(manager.getCurrentUser(), manager.getCurrentGroup())) {
			    		JOptionPane.showMessageDialog(null, "Successfully Left Group");
			    		btnRefreshPage.doClick();
			    	}
			    	else {
			    		JOptionPane.showMessageDialog(null, "Something Went Wrong");
			    	}
	            }
	        });
			titlePanel.add(leaveGroup);
		}
		
		if (manager.isUserOfGroup(manager.getCurrentUser(), manager.getCurrentGroup())) {
			JButton newPost = new JButton("Create New Post");
			newPost.setFont(new Font("Tahoma", Font.BOLD, 15));
			int x2 = currentFrame.getBounds().width - (newPost.getPreferredSize().width + padding + 50);

			newPost.setBounds(x2, 45, newPost.getPreferredSize().width + padding, 25);
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
	
	private JPanel createPostBox(Post p) {
		JPanel panel = new JPanel();
		panel.setBounds(50, 38, 600, 70);
		getContentPane().add(panel);
		panel.setLayout(null);
		panel.setBorder(BorderFactory.createRaisedBevelBorder());
		
		JTextArea lblTitle = new JTextArea(p.getPostTitle());
		lblTitle.setWrapStyleWord(true);
		lblTitle.setRows(2);
		lblTitle.setLineWrap(true);
		lblTitle.setFocusable(false);
		lblTitle.setOpaque(false);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTitle.setBounds(50, 5, 545, 40);
		panel.add(lblTitle);
		lblTitle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				onViewChangeClick();
				manager.setCurrentPost(p);
				new ViewPostView(manager, topBar, currentFrame, currentFrame.getSize());					
			}
		});
		
		JLabel lblScore = new JLabel("" + p.getScore());
		lblScore.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblScore.setVerticalAlignment(SwingConstants.CENTER);
		lblScore.setBounds(10, 30, 20, 10);
		panel.add(lblScore);
		
		JButton btnUpVote = new JButton("Up");
		btnUpVote.setBounds(10, 5, 20, 20);
		panel.add(btnUpVote);
		
		JButton btnDownVote = new JButton("Down");
		btnDownVote.setBounds(10, 45, 20, 20);
		panel.add(btnDownVote);
		
		JLabel lblUidLable = new JLabel("By:");
		lblUidLable.setBounds(60, 49, 34, 13);
		panel.add(lblUidLable);
		
		JLabel lblUserId = new JLabel();
		if (manager.isUserAdmin(p.getUser())) {
			lblUserId = new JLabel("(ADMIN) " + p.getUser().getId());
		}
		else {
			lblUserId = new JLabel(p.getUser().getId());
		}
		lblUserId.setBounds(86, 49, lblUserId.getPreferredSize().width + 10, 13);
		lblUserId.setForeground(Color.BLUE.darker());
		lblUserId.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblUserId.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				onViewChangeClick();
				manager.setCurrentCategory(null);
				manager.setCurrentGroup(null);
				new ProfileView(manager, topBar, currentFrame, currentFrame.getSize(), p.getUser());					
			}
		});
		panel.add(lblUserId);
		
		JLabel lblPostedLabel = new JLabel("Posted:");
		lblPostedLabel.setBounds(410, 49, 45, 13);
		panel.add(lblPostedLabel);
		
		JLabel lblPostedDate = new JLabel(manager.getSimpleDate(p.getTime()) + ", " + manager.getSimpleTime(p.getTime()));
		lblPostedDate.setBounds(460, 49, 130, 13);
		panel.add(lblPostedDate);
		
		return panel;
	}
	

	private JPanel createInsidePane() {
	
		int gridLocY = 10;
		int padding = 10;
	
		ArrayList<Post> alPost = manager.getPosts_InGroupByDate(manager.getCurrentGroup());
		JPanel postPane = new JPanel();
		postPane.setLayout(null);

		for (Post p : alPost) {
			JPanel jp = createPostBox(p);
			jp.setBounds(20, gridLocY, jp.getSize().width, jp.getSize().height);
			gridLocY += jp.getHeight() + padding;
			
			jp.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					onViewChangeClick();
					manager.setCurrentPost(p);
					new ViewPostView(manager, topBar, currentFrame, currentFrame.getSize());					
				}
			});
			postPane.add(jp);
		}
		postPane.setPreferredSize(new Dimension(currentFrame.getWidth()-50, gridLocY));
		
		return postPane;
	}
	
	private void displayGUI() {
		currentFrame.getContentPane().setLayout(new BorderLayout(0, 0));
		currentFrame.getContentPane().add(topBar, BorderLayout.NORTH);
		currentFrame.setTitle("This is the listing of posts in group " + manager.getCurrentGroup().getGroupName());
		currentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout(0,0));
		
		JPanel topInsidePanel = createTitlePane();
		mainPanel.add(topInsidePanel, BorderLayout.NORTH);
		
		JPanel centerInsidePanel = createInsidePane();
		mainPanel.add(centerInsidePanel, BorderLayout.CENTER);	

		mainPanel.add(centerInsidePanel);
		mainPanel.setSize(getPreferredSize());

		JScrollPane scrollPanel = new JScrollPane(mainPanel);
		scrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPanel.setSize(dim);
		
		currentFrame.getContentPane().add(scrollPanel, BorderLayout.CENTER);
		
		currentFrame.setVisible(true);
	}
}
