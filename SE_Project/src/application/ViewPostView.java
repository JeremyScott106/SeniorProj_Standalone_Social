package application;
import Project.*;	// Needed for instanceof and comparing items

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

@SuppressWarnings("serial")
public class ViewPostView extends JFrame {

	private JMenuBar topBar;
	private SystemManager manager;
	private JFrame currentFrame;
	private JTextArea txfPostBody;
	private Dimension dim;

		
	@SuppressWarnings("exports")
	public ViewPostView(SystemManager sm,  JMenuBar jmb,  JFrame frame, Dimension dim) {
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
	
		// All the information at the top of the screen below the post is shown in the titlepane
	private JPanel createTitlePane() {
		
		JPanel titlePanel = new JPanel();

		int gridx = 20;										// used for determining the left/right orientation
		int padding = 10;									// Change this to make the gaps between items larger
		
		titlePanel.setPreferredSize(new Dimension(0,80));	// Defaults to correct width, change second number to make title bar taller
		titlePanel.setLayout(null);							// Allows use of grid points to setup screen
		
			// Labels are mostly similar, set label, font, color, location
		JLabel lblHome = new JLabel("Home");
		lblHome.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblHome.setForeground(Color.BLUE.darker());
		lblHome.setBounds(gridx, 10, lblHome.getPreferredSize().width + padding, 25);
		gridx += lblHome.getWidth() + padding;
		lblHome.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));	// creates hand when hovering over item
		lblHome.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
            	onViewChangeClick();
            	new Home(manager, topBar, currentFrame, currentFrame.getSize());
            }
        });
		titlePanel.add(lblHome);							// build button before adding it to the Panel
		
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
		lblCurrentGroup.setForeground(Color.BLUE.darker());
		lblCurrentGroup.setBounds(gridx, 10, lblCurrentGroup.getPreferredSize().width + padding, 25);
		lblCurrentGroup.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		titlePanel.add(lblCurrentGroup);
		lblCurrentGroup.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
            	onViewChangeClick();
            	new GroupView(manager, topBar, currentFrame, currentFrame.getSize());
            }
        });
		
		//	SECOND ROW OF LABLES //
		gridx = 20;											// resets gridx back to 20
			// checks if user is logged in, if not, show login, otherwise, give login information
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
			// Checks if member of group, if not, give option to join group.  Need to add option to look if user is banned or suspended
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
		
			// General information
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
				new ViewPostView(manager, topBar, currentFrame, currentFrame.getSize());
			}
		});
		int x1 = currentFrame.getBounds().width - (btnRefreshPage.getPreferredSize().width + padding + 50);
		btnRefreshPage.setBounds(x1, 10, btnRefreshPage.getPreferredSize().width + padding, 25);
		titlePanel.add(btnRefreshPage);
		
			// May remove, not really needed anymore, this was an original need with old setup.
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 15));
		int x2 = currentFrame.getBounds().width - (btnBack.getPreferredSize().width + padding + 50);
		btnBack.setBounds(x2, 45, btnBack.getPreferredSize().width + padding, 25);;
		titlePanel.add(btnBack);
		btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	onViewChangeClick();
            	new GroupView(manager, topBar, currentFrame, currentFrame.getSize());
			}
		});
	
		return titlePanel;									// Panel complete, return
		
	}
	
		// Main Post Creation at top of screen 
	private JPanel createPostViewForm() {
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(null);
		int gridy = 10;
		int padding = 10;
		
			// fail safe, probably need to add a return statement to this to prevent building further
		if (manager.getCurrentPost() != null) {
		
			JLabel lblTitle = new JLabel(manager.getCurrentPost().getPostTitle());
			lblTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
			lblTitle.setBounds(40, gridy, currentFrame.getBounds().width + 25, lblTitle.getPreferredSize().height + padding);
			gridy += lblTitle.getHeight() + padding;
			panel.add(lblTitle);
			
			JTextArea textArea = new JTextArea(manager.getCurrentPost().getPostBody());
			textArea.setFont(new Font("Tahoma", Font.PLAIN, 15));
			textArea.setBounds(40, gridy, 550, textArea.getPreferredSize().height + padding);
			gridy += textArea.getHeight() + padding;
			textArea.setEditable(false);
			panel.add(textArea);
		}

			// if member of the group, show response box
		if (manager.isUserOfGroup(manager.getCurrentUser(), manager.getCurrentGroup())){	
			txfPostBody = new JTextArea();
			txfPostBody.setColumns(10);
			JScrollPane scrollPane= new JScrollPane(txfPostBody);
			scrollPane.setBounds(60, gridy, 416, 124);
			gridy += scrollPane.getHeight() + padding;
			scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			panel.add(scrollPane);		
				
			JButton btnRespond = new JButton("Respond");
			btnRespond.setBounds(341, gridy, 85, 21);
			btnRespond.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
			    	if (manager.createNewResponse(manager.getCurrentGroup(), txfPostBody.getText(), manager.getCurrentPost())) {
			    		onViewChangeClick();
						new ViewPostView(manager, topBar, currentFrame, currentFrame.getSize());
			        }
	            	else {
	            		JOptionPane.showMessageDialog(null, "An error occured");
	            	}
				}
			});
			
			panel.add(btnRespond);
				
				// Probably can remove cancel button, seems pointless to have a back button here
			JButton btnBack = new JButton("Cancel");
			btnBack.setBounds(246, gridy, 85, 21);
			gridy += scrollPane.getHeight() + padding;
			btnBack.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    	onViewChangeClick();
			    	new GroupView(manager, topBar, currentFrame, currentFrame.getSize());
			    }
			});
			panel.add(btnBack);
		}
		
				// Add Flag to the main post only if user is an admin
				// Tried to add these two files into the main file so only loads once, but would not appear when I did that
				//		I suspect this is due to the fact that all labels were technically the same item.
		if(manager.getCurrentUser() instanceof Admin) {
			JLabel picLabel = new JLabel();						// Establish button
			try {
				if (manager.getCurrentPost().getFlag()) {
					BufferedImage isFlagPic;
					isFlagPic = ImageIO.read(new File(".\\SE_Project\\src\\application\\Images\\RedFlagSmall.png"));
					JLabel isFlaggedPic = new JLabel(new ImageIcon(isFlagPic));
					picLabel = isFlaggedPic;
				}
				else {
					BufferedImage notFlagPic;
					notFlagPic = ImageIO.read(new File(".\\SE_Project\\src\\application\\Images\\RedFlagSmallTransparent.png"));
					JLabel notFlaggedPic = new JLabel(new ImageIcon(notFlagPic));
					picLabel = notFlaggedPic;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			picLabel.setBounds(10, 20, 25, 25);			// Location of the flag in the panel
			picLabel.addMouseListener(new MouseAdapter() {
			    @Override
			    public void mouseClicked(MouseEvent e) {
			    	if (manager.getCurrentPost().getFlag()) {
			    		manager.getCurrentPost().setFlagFalse();
			    		onViewChangeClick();
			    		new ViewPostView(manager, topBar, currentFrame, currentFrame.getSize());
			    	}
			    	else {
			    		manager.getCurrentPost().setFlagTrue();
			    		onViewChangeClick();
			    		new ViewPostView(manager, topBar, currentFrame, currentFrame.getSize());
			    	}
			    }
		    });
			panel.add(picLabel);
		}
			// panel size, Not sure how gridy is the height, but it is.
		panel.setPreferredSize(new Dimension(currentFrame.getWidth(), gridy));
		
		
		return panel;
	}
	
		// Create an individual box for a response
	private JPanel createResponseBox(Response r) {
		JPanel panel = new JPanel();
		int gridYLoc = 5;
		
		panel.setBounds(0, 0, 600, 100); // Needs to be adjusted based on size of text ... does this at the end of the panel, not sure if this is needed anymore
		getContentPane().add(panel);
		panel.setLayout(null);
		panel.setBorder(BorderFactory.createEtchedBorder());			// Change the type of border for each response here
		
		JTextArea txResponseBox = new JTextArea(r.getPostBody());
		txResponseBox.setWrapStyleWord(true);
		txResponseBox.setLineWrap(true);
		txResponseBox.setFocusable(true);								// allows copy/paste
		txResponseBox.setOpaque(false);
		txResponseBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txResponseBox.setSize(getPreferredSize());						// set size first, then setbounds.  otherwise will get wonkey
		txResponseBox.setBounds(40, gridYLoc, 545, txResponseBox.getPreferredSize().height);
		gridYLoc += txResponseBox.getHeight() + 20;						// moves grid setpoint
		panel.add(txResponseBox);
			
			// Self explanatory on the names of the labels and buttons here
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
		
			// Builds across the bottom of the panel with user and post time information
		JLabel lblUidLable = new JLabel("By:");
		lblUidLable.setBounds(60, gridYLoc, 34, 13);
		panel.add(lblUidLable);
			
			// Adjust color of the label based on who the user is.  Admins are dark green
		JLabel lblUserId = new JLabel();
		if (manager.isUserAdmin(r.getUser())) {
			lblUserId = new JLabel("(ADMIN) " + r.getUser().getId());
			lblUserId.setForeground(Color.GREEN.darker().darker());
		}
		else {
			lblUserId = new JLabel(r.getUser().getId());
			lblUserId.setForeground(Color.BLUE.darker());
		}
		lblUserId.setBounds(86, gridYLoc, lblUserId.getPreferredSize().width + 10, 13);
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
		lblPostedLabel.setBounds(410, gridYLoc, 45, 13);
		panel.add(lblPostedLabel);
		
		JLabel lblPostedDate = new JLabel(manager.getSimpleDate(r.getTime()) + ", " + manager.getSimpleTime(r.getTime()));
		lblPostedDate.setBounds(460, gridYLoc, 130, 13);
		panel.add(lblPostedDate);
		
		panel.setBounds(0, 0, 600, Math.max(gridYLoc-10, 40));		// Minimum height of box is 40, that is why this is looking for max between the desired size and 40
		
		return panel;
	}
	
	
		// create the entire pane below the post.  This cycles all responses and adds them to the page.
	private JPanel createResponsesPane() {
		
		int gridLocY = 10;										// Used to expand the panel to infinite length
		int padding = 20;										// change this to change distances between items

		ArrayList<Response> alResponse = manager.viewAllPostResponses(manager.getCurrentPost());
		JPanel responsePane = new JPanel();
		responsePane.setLayout(null);

		for (Response r : alResponse) {
			JPanel responseBox = createResponseBox(r);
			responseBox.setBounds(40, gridLocY, responseBox.getSize().width + padding, responseBox.getSize().height + padding);
			
			// As before, only admins see the flags
			if(manager.getCurrentUser() instanceof Admin) {
				JLabel picLabel = new JLabel();
				try {
					if (r.getFlag()) {
						BufferedImage isFlagPic;
						isFlagPic = ImageIO.read(new File(".\\SE_Project\\src\\application\\Images\\RedFlagSmall.png"));
						JLabel isFlaggedPic = new JLabel(new ImageIcon(isFlagPic));
						picLabel = isFlaggedPic;
					}
					else {
						BufferedImage notFlagPic;
						notFlagPic = ImageIO.read(new File(".\\SE_Project\\src\\application\\Images\\RedFlagSmallTransparent.png"));
						JLabel notFlaggedPic = new JLabel(new ImageIcon(notFlagPic));
						picLabel = notFlaggedPic;
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				picLabel.setBounds(10, gridLocY, 25, 25);		// Flag Location
				
					// Add ability to change the value of a flag, then refresh the screen
				picLabel.addMouseListener(new MouseAdapter() {
				    @Override
				    public void mouseClicked(MouseEvent e) {
				    	if (r.getFlag()) {
				    		r.setFlagFalse();
				    		onViewChangeClick();
				    		new ViewPostView(manager, topBar, currentFrame, currentFrame.getSize());
				    	}
				    	else {
				    		r.setFlagTrue();
				    		onViewChangeClick();
				    		new ViewPostView(manager, topBar, currentFrame, currentFrame.getSize());
				    	}
				    }
			    });
				responsePane.add(picLabel);
			}
			gridLocY += responseBox.getHeight() + padding;			
			responsePane.add(responseBox);
		}
		responsePane.setPreferredSize(new Dimension(currentFrame.getWidth(), gridLocY));
		return responsePane;
	}
	
		// Main method, build and display gui
	private void displayGUI() {
		
		currentFrame.getContentPane().setLayout(new BorderLayout(0, 0));
		currentFrame.getContentPane().add(topBar, BorderLayout.NORTH);
		currentFrame.setTitle("This is the Post view");
		currentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
			// main panel is the entire page
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout(0,0));
		
			// topInsidePanel is the very top of the page, the title bar
		JPanel topInsidePanel = createTitlePane();
		mainPanel.add(topInsidePanel, BorderLayout.NORTH);
		
			// viewPostForm is the post at the top of the screen
		JPanel viewPostForm = createPostViewForm();
		mainPanel.add(viewPostForm, BorderLayout.CENTER);
		
			// viewResponsePanel is the list of all responses below the post
		JPanel viewResponsePanel = createResponsesPane();
		mainPanel.add(viewResponsePanel, BorderLayout.SOUTH);
		
			// Runs invokeLater in order to update the scroll bars properly.  Not sure what is
			// 		making it change after setting it to 0.
		JScrollPane scrollPanel = new JScrollPane(mainPanel);
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() { 
			   	scrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			   	scrollPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			   	scrollPanel.setSize((int) dim.getWidth()-15, (int) dim.getHeight()-60);
			   	scrollPanel.getVerticalScrollBar().setValue(0);
			   	scrollPanel.getVerticalScrollBar().setUnitIncrement(16);
			}
		});
		currentFrame.getContentPane().add(scrollPanel, BorderLayout.CENTER);
		
			// Allow user to see the Frame with all attached panels.
		currentFrame.setVisible(true);
	}
}
