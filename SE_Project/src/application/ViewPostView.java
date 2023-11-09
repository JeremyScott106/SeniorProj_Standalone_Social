package application;
import Project.Response;
import Project.SystemManager;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
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
				new ViewPostView(manager, topBar, currentFrame, currentFrame.getSize());
			}
		});
		int x1 = currentFrame.getBounds().width - (btnRefreshPage.getPreferredSize().width + padding + 50);
		btnRefreshPage.setBounds(x1, 10, btnRefreshPage.getPreferredSize().width + padding, 25);
		titlePanel.add(btnRefreshPage);
		

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
	
		
		return titlePanel;
		
	}
	
	private JPanel createPostViewForm() {
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(null);
		int gridy = 10;
		int padding = 10;
		
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
		    	if (manager.createNewResponse(manager.getCurrentGroup(), txfPostBody.getText())) {
		    		onViewChangeClick();
					new ViewPostView(manager, topBar, currentFrame, currentFrame.getSize());
		        }
            	else {
            		JOptionPane.showMessageDialog(null, "An error occured");
            	}
			}
		});
		
		panel.add(btnRespond);
			
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
	
		panel.setPreferredSize(new Dimension(currentFrame.getWidth(), gridy));
		return panel;
	}
	
	
	private JPanel createResponsesPane() {
		
		int gridLocY = 10;
		int padding = 30;

		ArrayList<Response> alResponse = manager.viewAllPostResponses(manager.getCurrentPost());
		JPanel responsePane = new JPanel();
		responsePane.setLayout(null);

		for (Response r : alResponse) {
			
			JTextArea responseArea = new JTextArea(r.getResponseBody());
			responseArea.setFont(new Font("Tahoma", Font.PLAIN, 15));
			responseArea.setBounds(40, gridLocY, 550, responseArea.getPreferredSize().height + padding);
			gridLocY += responseArea.getHeight() + padding;
			responseArea.setEditable(false);
			responsePane.add(responseArea);
		}
		responsePane.setPreferredSize(new Dimension(currentFrame.getWidth(), gridLocY));
		return responsePane;
	}
	
	private void displayGUI() {
		currentFrame.getContentPane().setLayout(new BorderLayout(0, 0));
		currentFrame.getContentPane().add(topBar, BorderLayout.NORTH);
		currentFrame.setTitle("This is the Post view");
		currentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout(0,0));
		
		JPanel topInsidePanel = createTitlePane();
		mainPanel.add(topInsidePanel, BorderLayout.NORTH);
		
		JPanel viewPostForm = createPostViewForm();
		mainPanel.add(viewPostForm, BorderLayout.CENTER);
		
		JPanel viewResponsePanel = createResponsesPane();
		mainPanel.add(viewResponsePanel, BorderLayout.SOUTH);
		
		JScrollPane scrollPanel = new JScrollPane(mainPanel);
		scrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPanel.setSize(dim);
		
		currentFrame.getContentPane().add(scrollPanel, BorderLayout.CENTER);
		
		currentFrame.setVisible(true);
	}
}
