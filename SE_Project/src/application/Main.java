package application;

import Project.SystemManager;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Main extends JFrame {
	
	private static JMenuBar topBar;
	private static SystemManager manager;
	private String fileName = ".\\SE_Project\\src\\LoadThisFile.txt";
	private JFrame currentFrame;
	
	public Main() {
	}
	
	// Creates the menu bar //
	private JMenuBar createMenus() {
				//Bar that holds Menus//
		JMenuBar menus = new JMenuBar();
				//Menus//
		JMenu file = new JMenu("File");
//		JMenu refresh = new JMenu("Refresh");
				//Sub-menus//
		JMenuItem login = new JMenuItem("Login");
		JMenuItem logout = new JMenuItem("Logout");
		
		JMenuItem switchHome = new JMenuItem("Home");
		JMenuItem switchProfile = new JMenuItem("Profile View");
				//Add menus to bar//
		menus.add(file);
//		menus.add(refresh); // Add if currentView is added.
				//Add sub-menus to menus//
		file.add(login);
		file.add(switchHome);
		file.add(switchProfile);
		file.add(logout);		
		
		login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	new LoginPopUp(manager);
            }
        });
		
//		refresh.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//            	
//            }
//        });
		
		logout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	manager.logout();
            	repaint();
            }
        });

		switchHome.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	onViewChangeClick();
            	manager.setCurrentCategory(null);
            	manager.setCurrentGroup(null);
            	new Home(manager, topBar, currentFrame, currentFrame.getSize());
            }
        });
		
		switchProfile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	onViewChangeClick();
            	new ProfileView(manager, topBar, currentFrame, currentFrame.getSize());
            	
            }
        });
				
		return menus;
	}
	
	private void onViewChangeClick() {
		currentFrame.getContentPane().removeAll();
		currentFrame.repaint();
	}
	
	private void init() {
		
		if (manager == null) {
			startMemory();
		}
		new Home(manager, topBar, currentFrame, currentFrame.getSize());
	}
	
	private void startMemory() {
		
		manager = new SystemManager(fileName);
		topBar = createMenus();
		currentFrame = new JFrame();
		currentFrame.setSize(800,800);
	}
	
	
				// Main //
	public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new Main().init();
            }
        });
	}
}
