package application;
import Project.Admin;
import Project.Post;
import Project.Response;
import Project.SystemManager;
import Project.User;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class ViewAllUsers extends JFrame {

	private JMenuBar topBar;
	private SystemManager manager;
	private JFrame currentFrame;
	private JPanel recentActivity;
	
			// constructor not needed //
	public ViewAllUsers() {
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 786, 80);
		panel.add(panel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 78, 150, 606);
		panel.add(scrollPane);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(147, 78, 639, 606);
		panel.add(panel_2);
		
	}
	
	
	@SuppressWarnings("exports")
	public ViewAllUsers(SystemManager sm,  JMenuBar jmb,  JFrame frame, Dimension dim) {
		this(sm, jmb, frame, dim, null);
	}
	
	@SuppressWarnings("exports")
	public ViewAllUsers(SystemManager sm,  JMenuBar jmb,  JFrame frame, Dimension dim, JPanel recent) {
		this.topBar = jmb;
		this.manager = sm;
		this.currentFrame = frame;
		this.currentFrame.setSize(dim);
		this.recentActivity = recent;
		displayGUI();
	}
	
			// This will clear the current frame, allows for rebuilding the frame //
	private void onViewChangeClick() {
		currentFrame.getContentPane().removeAll();
		currentFrame.repaint();
	}
	
			// This creates the top panel that displays the user login info and refresh button //
	private JPanel createTitlePane() {
		
		JPanel titlePanel = new JPanel();
		
		titlePanel.setPreferredSize(new Dimension(0,80));
		titlePanel.setLayout(null);
		
		JLabel lblHome = new JLabel("Home");
		lblHome.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblHome.setForeground(Color.BLUE.darker());
		lblHome.setBounds(10, 10, lblHome.getPreferredSize().width + 10, 25);
		lblHome.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblHome.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
            	onViewChangeClick();
            	manager.setCurrentCategory(null);
            	manager.setCurrentGroup(null);
            	manager.setCurrentPost(null);
            	new Home(manager, topBar, currentFrame, currentFrame.getSize());
            }
        });
		titlePanel.add(lblHome);
		
		if (manager.getCurrentUser() instanceof Admin) {
			JButton adminBtn = new JButton("Admin Tools");
			adminBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
			adminBtn.setBounds(274, 45, 152, 25);
			titlePanel.add(adminBtn);
			adminBtn.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
					onViewChangeClick();
					new AdminTools(manager, topBar, currentFrame, currentFrame.getSize());
				}
			});
		}

		
		JButton btnRefreshPage = new JButton("Refresh");
		btnRefreshPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onViewChangeClick();
				new ViewAllUsers(manager, topBar, currentFrame, currentFrame.getSize());
			}
		});
		btnRefreshPage.setBounds(currentFrame.getBounds().width - 125, 45, 100, 25);
		titlePanel.add(btnRefreshPage);
		
		JLabel lblCurrentUser = new JLabel("Current Admin: ");
		lblCurrentUser.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCurrentUser.setBounds(10, 45, 122, 25);
		titlePanel.add(lblCurrentUser);
		
		if (manager.getCurrentUser() != null) {
			JLabel lblUid = new JLabel(manager.getCurrentUser().getId());
			lblUid.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblUid.setForeground(Color.BLUE.darker());
			lblUid.setBounds(135, 45, lblUid.getPreferredSize().width+10, 25);
			lblUid.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			lblUid.addMouseListener(new MouseAdapter() {
			    @Override
			    public void mouseClicked(MouseEvent e) {
			    	onViewChangeClick();
			    	new ProfileView(manager, topBar, currentFrame, currentFrame.getSize(), manager.getCurrentUser());
			    }
			});
			
			titlePanel.add(lblUid);
		}
		else {
			new Home(manager, topBar, currentFrame, currentFrame.getSize());
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
				manager.setCurrentCategory(manager.getCategoryByGroup(p.getGroup()));
				manager.setCurrentGroup(p.getGroup());
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
	
	private JPanel createResponseBox(Response r) {
		JPanel panel = new JPanel();
		panel.setBounds(50, 38, 600, 70);
		getContentPane().add(panel);
		panel.setLayout(null);
		panel.setBorder(BorderFactory.createRaisedBevelBorder());
		
		JTextArea lblTitle = new JTextArea(r.getPostBody());
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
				manager.setCurrentCategory(manager.getCategoryByGroup(r.getGroup()));
				manager.setCurrentGroup(r.getGroup());
				manager.setCurrentPost(manager.getPostByGroupId(r.getGroup(), r.getId()));
				new ViewPostView(manager, topBar, currentFrame, currentFrame.getSize());					
			}
		});
		
		JLabel lblScore = new JLabel("" + r.getScore());
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
		if (manager.isUserAdmin(r.getUser())) {
			lblUserId = new JLabel("(ADMIN) " + r.getUser().getId());
		}
		else {
			lblUserId = new JLabel(r.getUser().getId());
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
				new ProfileView(manager, topBar, currentFrame, currentFrame.getSize(), r.getUser());					
			}
		});
		panel.add(lblUserId);
		
		JLabel lblPostedLabel = new JLabel("Posted:");
		lblPostedLabel.setBounds(410, 49, 45, 13);
		panel.add(lblPostedLabel);
		
		JLabel lblPostedDate = new JLabel(manager.getSimpleDate(r.getTime()) + ", " + manager.getSimpleTime(r.getTime()));
		lblPostedDate.setBounds(460, 49, 130, 13);
		panel.add(lblPostedDate);
		
		return panel;
	}
	
	private JPanel createRecentActivity(User u) {
		
		int gridYLoc = 30;
		int padding = 10;
		
		JPanel recentActivity = new JPanel();
		recentActivity.setLayout(null);
		
		JLabel userInfo = new JLabel(u.getId() + "'s Recent Activity");
		userInfo.setBounds(10,10, userInfo.getPreferredSize().width + 10, 15);
		recentActivity.add(userInfo);
		
		for (Object p : manager.viewUsersPostsResponses(u)) {
			
			if (p instanceof Response) {
				Response response = (Response) p;
				String OPTitle = manager.getPostByGroupId(response.getGroup(), response.getId()).getPostTitle();
				if (OPTitle.length() > 15) {
					OPTitle = OPTitle.substring(0,15) + " ...";
				}
				JLabel tag = new JLabel("Response to " + OPTitle);
				tag.setBounds(20, gridYLoc, tag.getPreferredSize().width + padding, 15);
				gridYLoc += tag.getSize().height + padding;
				recentActivity.add(tag);
				
				JPanel jp = createResponseBox((Response) p);
				jp.setBounds(20, gridYLoc, jp.getSize().width, jp.getSize().height);
				gridYLoc += jp.getSize().height + padding;
				recentActivity.add(jp);
			}
			
			else if (p instanceof Post){
				JLabel tag = new JLabel("OP");
				tag.setBounds(20, gridYLoc, tag.getPreferredSize().width + padding, 15);
				gridYLoc += tag.getSize().height + padding;
				recentActivity.add(tag);
				
				JPanel jp = createPostBox((Post) p);
				jp.setBounds(20, gridYLoc, jp.getSize().width, jp.getSize().height);
				gridYLoc += jp.getSize().height + padding;
				recentActivity.add(jp);
			}
			else {
				continue;
			}
		}
		
		recentActivity.setPreferredSize(new Dimension(currentFrame.getWidth()-150, gridYLoc));
		
		return recentActivity;
		
	}
	
	
	private JPanel createUserList() {
		
		int gridYLoc = 0;
		
		JPanel userListPanel = new JPanel();
		
		for (User u : manager.getUsers_Alphabetically()) {
			JLabel lblUserName = new JLabel(u.getId());
			lblUserName.setBounds(10, gridYLoc, 130, 15);
			gridYLoc += 20;
			
			lblUserName.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					JPanel update = createRecentActivity(u);
					onViewChangeClick();
					new ViewAllUsers(manager, topBar, currentFrame, currentFrame.getSize(), update);
				}
			});
			
			userListPanel.add(lblUserName);
		}
		
		userListPanel.setPreferredSize(new Dimension(150, gridYLoc));
		
		return userListPanel;
	}	

	
		// This prints all categories as buttons on the home screen in horizontal fashion //


			// Puts the frame together, called in particular order and location, panels in panels in a frame //
	private void displayGUI() {
		currentFrame.getContentPane().setLayout(new BorderLayout(0, 0));
		currentFrame.getContentPane().add(topBar, BorderLayout.NORTH);
		currentFrame.setTitle("This is the Home view");
		currentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel mainPane = new JPanel();
		mainPane.setLayout(null);
		currentFrame.getContentPane().add(mainPane, BorderLayout.CENTER);
		
		JPanel titlePanel = createTitlePane();
		titlePanel.setBounds(0, 0, currentFrame.getWidth(), 80);
		mainPane.add(titlePanel);
		
		JPanel userListPanel = createUserList();		// Create User listing.  Selecting user shows recent activity in JPanel Recent Activity
		userListPanel.setLayout(null);
		
		JScrollPane scrollUserListPanel = new JScrollPane(userListPanel);
		scrollUserListPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollUserListPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollUserListPanel.setBounds(0, 80, 150, currentFrame.getHeight()-145);
		mainPane.add(scrollUserListPanel);
				
		JScrollPane scrollRecentActivity = new JScrollPane(recentActivity);
		scrollRecentActivity.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollRecentActivity.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollRecentActivity.setBounds(150, 80, currentFrame.getWidth() - 165, currentFrame.getHeight() - 145);
		mainPane.add(scrollRecentActivity);

		currentFrame.setVisible(true);
	}
}
