package application;

import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Login extends JDialog {
	JTextArea txaMessage;
	JTextField txfName;
	JTextField txfPass;

	public Login() {
		initialize();
	}
	
	public void initialize() {
		try {
			JFrame Login = new JFrame();
		
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
		txfName = new JTextField();
		username.add(lblName);
		username.add(txfName);
		return username;
	}

	private JPanel makePass() {
		JPanel password = new JPanel();
		JLabel lblPass = new JLabel(" Password:");
		txfPass = new JTextField();
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
		return btnPanel;
	}
	
}