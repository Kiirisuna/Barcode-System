package mainSystem;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import javax.swing.JDialog;

@SuppressWarnings("serial")
public class adminLogin extends JDialog {
	// private adminLogin frame;
	private JPanel contentPane;
	private JPasswordField passwordField;
	private JTextField textField;
	private JLabel lblPassword;
	private JButton btnCancel;

	/**
	 * Create the frame.
	 */

	public void keyPressLogin() {
		String user = textField.getText();
		String pass = new String(passwordField.getPassword());
		if (mongoDB.ifExist(mongoDB.admin, "adminID", user) == 1) {
			String[] temp = mongoDB.returnField(mongoDB.admin, "adminID", user, "password");
			if (pass.equals(temp[0])) {
				// open manager
				manager manLog = new manager();
				manLog.setLocationRelativeTo(GUI.mainGUI);
				manLog.setVisible(true);
				dispose();
			} else {
				JOptionPane.showMessageDialog(contentPane, "Invalid Username or Password. Try again.");
			}
		} else {
			JOptionPane.showMessageDialog(contentPane, "Invalid Username or Password. Try again.");
		}
	}

	public adminLogin() {

		GUI.mainGUI.setEnabled(false);
		setAlwaysOnTop(true);
		setResizable(false);
		setBounds(100, 100, 360, 148);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Username label
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Calibri", Font.BOLD, 14));
		lblUsername.setBounds(24, 16, 78, 14);
		contentPane.add(lblUsername);

		// Password label
		lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Calibri", Font.BOLD, 14));
		lblPassword.setBounds(27, 47, 78, 14);
		contentPane.add(lblPassword);

		passwordField = new JPasswordField(20);
		passwordField.setBounds(112, 42, 212, 20);
		contentPane.add(passwordField);
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					keyPressLogin();
				}
			}
		});

		textField = new JTextField();
		textField.setBounds(112, 11, 212, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					keyPressLogin();
				}
			}
		});

		JButton btnLogin = new JButton("Login");
		btnLogin.setVerticalAlignment(SwingConstants.TOP);
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnLogin.setFocusable(false);
		btnLogin.setBounds(24, 75, 89, 23);
		contentPane.add(btnLogin);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyPressLogin();
			}
		});

		btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCancel.setFocusable(false);
		btnCancel.setVerticalAlignment(SwingConstants.TOP);
		btnCancel.setBounds(122, 75, 89, 23);
		contentPane.add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUI.mainGUI.setEnabled(true);
				dispose();
			}
		});

		JCheckBox chckbxSeePassword = new JCheckBox("See Password");
		chckbxSeePassword.setFont(new Font("Tahoma", Font.BOLD, 11));
		chckbxSeePassword.setFocusable(false);
		chckbxSeePassword.setBounds(217, 75, 107, 23);
		contentPane.add(chckbxSeePassword);
		chckbxSeePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxSeePassword.isSelected()) {
					passwordField.setEchoChar((char) 0);
				} else {
					passwordField.setEchoChar('•');
				}
			}
		});

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				GUI.mainGUI.setEnabled(true);
			}
		});

	}
}
