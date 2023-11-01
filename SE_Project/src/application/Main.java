package application;
import Project.ReadFile;
import Project.SystemManager;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Main extends JFrame {
	
	private static JMenuBar topBar;
	private static SystemManager manager;
	private JFrame currentFrame;
	
	public Main() {
	}
	
	// Creates the menu bar //
	private JMenuBar createMenus() {
				//Bar that holds Menus//
		JMenuBar menus = new JMenuBar();
				//Menus//
		JMenu file = new JMenu("File");
		JMenu test = new JMenu("Test");
		JMenu view = new JMenu("View");
				//Sub-menus//
		JMenuItem login = new JMenuItem("Login");
		
		JMenuItem myInfo = new JMenuItem("My Info");
		JMenuItem fakeUser1 = new JMenuItem("Make Fake User1");
		JMenuItem loadData = new JMenuItem("Load \"ReadFile_Test_Admin.txt\"");
		
		JMenuItem switchHome = new JMenuItem("Home");
		JMenuItem switchCategory = new JMenuItem("Category View");
		JMenuItem switchGroup = new JMenuItem("Group View");
		JMenuItem switchPost = new JMenuItem("Post View");
		JMenuItem switchProfile = new JMenuItem("Profile View");
				//Add menus to bar//
		menus.add(file);
		menus.add(view);
		menus.add(test);
				//Add sub-menus to menus//
		file.add(login);
		test.add(myInfo);
		test.add(fakeUser1);
		test.add(loadData);
		view.add(switchHome);
		view.add(switchCategory);
		view.add(switchGroup);
		view.add(switchPost);
		view.add(switchProfile);
		
		login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	new Login(manager);
            }
        });
		
		fakeUser1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
    			String name = "Testey One";
    			String bday = "01/01/1999";
    			String city = "Home";
    			String state = "Georgia";
    			String username = "DespisesJava123";
    			String password = "abc123";
    			
    			manager.registerUser(name, bday, city, state, username, password);
    			
    			System.out.printf("   /----/\nNew User Created\n   /----/\nUsername: %s\nPassword: %s\n", username, password);
            }
        });
		
		myInfo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
    			System.out.printf("\nUser is signed in: %b\n", manager.isLoggedIn());
    			System.out.printf("User is admin: %b\n", manager.isAdmin());
    			if (manager.isLoggedIn()) {
    				System.out.printf("Current User: %s\n", manager.getCurrentUser().getId());
    			}
            }
        });
		
		loadData.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String fileName = ".\\SE_Project\\src\\Project\\ReadFile_Test_Admin.txt";
            	try {
					ReadFile.readFile(manager, fileName);
				} catch (Exception ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}

            }
        });

		switchHome.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	onViewChangeClick();
            	new Home(manager, topBar, currentFrame);
            }
        });
		
		switchCategory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	onViewChangeClick();
            	new Category(manager, topBar, currentFrame);
            }
        });
		
		switchGroup.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	onViewChangeClick();
            	new Group(manager, topBar, currentFrame);
            }
        });
		
		switchPost.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	onViewChangeClick();
            	new Post(manager, topBar, currentFrame);
            }
        });
		
		switchProfile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	onViewChangeClick();
            	new Profile(manager, topBar, currentFrame);
            	
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
		
		new Home(manager, topBar, currentFrame);
	}
	
	private void startMemory() {
		manager = new SystemManager();
		topBar = createMenus();
		currentFrame = new JFrame();
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
