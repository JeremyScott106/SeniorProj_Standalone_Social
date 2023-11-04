package application;
import Project.Group;
import Project.SystemManager;
import Project.category;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

@SuppressWarnings("serial")
public class CategoryView extends JFrame {

	private JMenuBar topBar;
	private SystemManager manager;
	private JFrame currentFrame;
	private category currentCategory;
	private final int maxHeight = 10;
	
	// Window builder only seems to know how to use the blank constructor -- Use this to develop code then transfer to buildGUI//
	public CategoryView() {
	}
	
	@SuppressWarnings("exports")
	public CategoryView(SystemManager sm,  JMenuBar jmb,  JFrame frame, Dimension dim, category c) {
		this.topBar = jmb;
		this.manager = sm;
		this.currentFrame = frame;
		this.currentFrame.setSize(dim);
		this.currentCategory = c;
		displayGUI();
	}
	
	// This will clear the current frame, allows for rebuilding the frame //
	private void onViewChangeClick() {
		currentFrame.getContentPane().removeAll();
		currentFrame.repaint();
	}
	
	private JPanel createTitlePane() {
		
		JPanel titlePanel = new JPanel();
		
		titlePanel.setPreferredSize(new Dimension(0,50));
		titlePanel.setLayout(null);
		
		JButton btnHome = new JButton("Home");
		btnHome.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnHome.setBounds(10, 10, 85, 25);
		btnHome.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	onViewChangeClick();
            	new Home(manager, topBar, currentFrame, currentFrame.getSize());
            }
        });
		titlePanel.add(btnHome);
		
		JLabel lblSpacer1 = new JLabel("//");
		lblSpacer1.setBounds(100, 10, 10, 25);
		titlePanel.add(lblSpacer1);
		
		JLabel lblCurrentCategory = new JLabel(currentCategory.getName());
		lblCurrentCategory.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCurrentCategory.setBounds(120, 10, 150, 25);
		titlePanel.add(lblCurrentCategory);
		
		return titlePanel;
		
	}
	
	private JPanel createGridPane() {
		
		JPanel groupGridPane = new JPanel();
		GridBagLayout gbl = new GridBagLayout();
		groupGridPane.setLayout(gbl);
		
		ArrayList<Group> alGroups = manager.getGroupsInCategory_Alphabetically(currentCategory);
		
		for (int i = 0, j = 0; i < alGroups.size(); i++) {
			if (i % maxHeight == 0) {
				j++;
			}
			Group g = alGroups.get(i);
			double weightY = 0.0;
			if (i == maxHeight - 1 || (i == alGroups.size() - 1 && i < maxHeight - 1))
				weightY = 1;
			JButton button = new JButton(alGroups.get(i).getGroupName());
			GridBagConstraints gbc = new GridBagConstraints(
		            j, (i % maxHeight),                           //cell x , y
		            1, 1,                           //cell width , cell height
		            1, 								//weightx
		            weightY,			            //weighty
		            GridBagConstraints.PAGE_START,  //where to anchor the component in the cell
		            GridBagConstraints.HORIZONTAL,  //how to fill extra space
		            new Insets(0, 0, 0, 0),         //insets for the cell
		            0, 1);                          //additional padding x,y
			
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					onViewChangeClick();
					new GroupView(manager, topBar, currentFrame, currentFrame.getSize(), manager.getGroupByName(getName()));
				}
			});
			groupGridPane.add(button, gbc);
		}
		
		return groupGridPane;
	}
	
	private void displayGUI() {
		currentFrame.getContentPane().setLayout(new BorderLayout(0, 0));
		currentFrame.getContentPane().add(topBar, BorderLayout.NORTH);
		currentFrame.setTitle("This is the Category view");
		currentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel mainPanel = new JPanel();
		currentFrame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new BorderLayout(0,0));
		
		JPanel topInsidePanel = createTitlePane();
		mainPanel.add(topInsidePanel, BorderLayout.NORTH);
		
		JPanel centerInsidePanel = createGridPane();
		mainPanel.add(centerInsidePanel, BorderLayout.CENTER);		
		
		currentFrame.setVisible(true);
	}
}
