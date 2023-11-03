package application;
import Project.SystemManager;
import Project.category;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class AdminTools extends JFrame {

	private JMenuBar topBar;
	private SystemManager manager;
	private JFrame currentFrame;
	private JTextField newCategoryTextField;
	private JTextField newGroupTextField;
	
			// Window builder only seems to know how to use the blank constructor -- Use this to develop code then transfer to buildGUI//
	public AdminTools() {
	}

	@SuppressWarnings("exports")
	public AdminTools(SystemManager sm,  JMenuBar jmb,  JFrame frame, Dimension dim) {
		topBar = jmb;
		manager = sm;
		currentFrame = frame;
		currentFrame.setSize(dim);
		displayGUI();
	}
	
			// used to reset the screen, clears all items from the current window //
	private void onViewChangeClick() {
		currentFrame.getContentPane().removeAll();
		currentFrame.repaint();
	}
	
			// The only panel currently, can be separated into a title / main panel later if desired //
	private JPanel createMainPanel() {
		
		JPanel panel = new JPanel();		
		panel.setLayout(null);
		
		JLabel lblAdminTools = new JLabel("Admin Tools");
		lblAdminTools.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAdminTools.setBounds(10, 11, 140, 25);
		panel.add(lblAdminTools);
		
		JButton btnRefreshPage = new JButton("Refresh Page");
		btnRefreshPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onViewChangeClick();
				new AdminTools(manager, topBar, currentFrame, currentFrame.getSize());
			}
		});
		btnRefreshPage.setBounds(496, 16, 97, 23);
		panel.add(btnRefreshPage);
		
		JButton btnCreateCategory = new JButton("<html><center>"+"Create New Category"+"</center></html>");
		btnCreateCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(newCategoryTextField.getText() != null)
					if (manager.createCategory(newCategoryTextField.getText())) {
						JOptionPane.showMessageDialog(null, "New Category Created");
					}
					else {
						JOptionPane.showMessageDialog(null, "Create Category Failed Validation");
					}
				else {
					JOptionPane.showMessageDialog(null, "Text Box Empty");
				}
			}
		});
		btnCreateCategory.setBounds(20, 47, 89, 40);
		panel.add(btnCreateCategory);
		
		newCategoryTextField = new JTextField();
		newCategoryTextField.setBounds(137, 57, 154, 20);
		panel.add(newCategoryTextField);
		newCategoryTextField.setColumns(10);
		
		JButton btnCreateGroup = new JButton("<html><center>Create New Group</center></html>");
		btnCreateGroup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnCreateGroup.setBounds(20, 145, 89, 40);
		panel.add(btnCreateGroup);
		
		ArrayList<category> categoryArrayList = manager.getCategories_Alphabetically();
		String[] comboBoxCategoryList = new String[categoryArrayList.size()];
		for (int i = 0; i < categoryArrayList.size(); i++) {
			comboBoxCategoryList[i] = categoryArrayList.get(i).getName();
		}
		JComboBox comboBoxCategories = new JComboBox(comboBoxCategoryList);
		comboBoxCategories.setBounds(322, 155, 154, 22);
		panel.add(comboBoxCategories);
		
		JButton btnViewAllCategories = new JButton("<html><center>View All Categories</center></html>");
		String msgAllCategories = "";
		for (category c : categoryArrayList) {
			msgAllCategories += c.getName() + "\n";
		}
		String m = msgAllCategories; // this is dumb, but showMessageDialog won't let me use it any other way //
		btnViewAllCategories.setBounds(20, 93, 89, 40);
		btnViewAllCategories.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, m);
			}
		});
		panel.add(btnViewAllCategories);
		
		newGroupTextField = new JTextField();
		newGroupTextField.setBounds(137, 155, 154, 20);
		panel.add(newGroupTextField);
		newGroupTextField.setColumns(10);
		return panel;
	}
	
		// Build display, call for panels to be made //
	private void displayGUI() {
		currentFrame.getContentPane().setLayout(new BorderLayout(0, 0));
		currentFrame.getContentPane().add(topBar, BorderLayout.NORTH);
		currentFrame.setTitle("This is the Admin Tools view");
		currentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel mainPanel = createMainPanel();
		currentFrame.add(mainPanel, BorderLayout.CENTER);
		
		currentFrame.setVisible(true);
	}
}
