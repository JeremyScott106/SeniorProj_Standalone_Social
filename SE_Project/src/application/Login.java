package application;
import Project.SystemManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

@SuppressWarnings("serial")
public class Login extends JDialog {
	private JTextArea txaMessage;
	private JTextField txfName;
	private JTextField txfPass;
	private SystemManager manager;
	private JFrame Login;

	public Login(SystemManager manager) {
		initialize();
		this.manager = manager;
	}
	
	public void initialize() {
		try {
			Login = new JFrame();
		
			Login.setDefaultCloseOperation(HIDE_ON_CLOSE);
			Login.setSize(250,150);
			Login.setLayout(new BorderLayout(10,5)); 				//Figure out getLayout!!!//
			Login.setLocationRelativeTo(null);
			Login.add(makeButtons(), BorderLayout.SOUTH);
			Login.add(makeUser(), BorderLayout.NORTH);
			Login.add(makePass(), BorderLayout.CENTER);

			Login.setVisible(true);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private JPanel makeUser() {
		JPanel username = new JPanel();
		JLabel lblName = new JLabel("Username:");
		txfName = new JTextField(10);
		username.add(lblName);
		username.add(txfName);
		return username;
	}

	private JPanel makePass() {
		JPanel password = new JPanel();
		JLabel lblPass = new JLabel(" Password:");
		txfPass = new JTextField(10);
		password.add(lblPass);
		password.add(txfPass);

		return password;
	}
	
	private JPanel makeButtons() {
		JPanel btnPanel = new JPanel();
		JButton btnSignIn = new JButton("Sign in");
		JButton btnGuest = new JButton("Continue as Guest");
		btnPanel.add(btnSignIn);
		btnPanel.add(btnGuest);
		
		btnGuest.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				closeWindow();
			}
			
		});
		
		btnSignIn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(txfName.getText().isBlank() || txfPass.getText().isBlank()) {
					System.out.println("Username or Pass blank");
				}
				else {
					boolean status = manager.login(txfName.getText(), txfPass.getText());
					loginMsg(status);
				}
			}
		});
		return btnPanel;
	}
	
	private void loginMsg(boolean status) {
		if (status) {
			closeWindow();
		}
		else {
			System.out.println("Incorrect username or pass");
		}
	}
	
	private void closeWindow() {
		Login.dispose();
	}
	
}