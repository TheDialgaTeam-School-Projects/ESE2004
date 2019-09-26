package view.account;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import controller.MainFrame;

@SuppressWarnings("serial")
public class LoginScreen extends JPanel
{
	private JButton btnLogin;
	private JPasswordField newPassword;
	private JPasswordField password;
	private JTextField username;

	/**
	 * Initialize login screen.
	 *
	 * @param mainFrame Mainframe.
	 * @param type 0 = Login Screen, 1 = Registration Screen, 2 = Change
	 * Password Screen.
	 */
	public LoginScreen(MainFrame mainFrame, int type)
	{
		String title = "Login";
		String mainScreenDescription = "Welcome to sandwich order system, please enter your credentials to make an order.";
		boolean usernameEditable = true;
		String usernameSetText = "";
		String labelPassword = "Password:";
		String registerSetText = "Register";
		String loginSetText = "Login";

		if (type == 0)
		{
			title = "Login";
			mainScreenDescription = "Welcome to sandwich order system, please enter your credentials to make an order.";
			usernameEditable = true;
			usernameSetText = "";
			labelPassword = "Password:";
			registerSetText = "Register";
			loginSetText = "Login";
		}
		else if (type == 1)
		{
			title = "Register";
			mainScreenDescription = "Please enter the required field to create an account.";
			usernameEditable = true;
			usernameSetText = "";
			labelPassword = "Password:";
			registerSetText = "Back";
			loginSetText = "Register";
		}
		else if (type == 2)
		{
			title = "Change Password";
			mainScreenDescription = "Please enter the required field to change password.";
			usernameEditable = false;
			usernameSetText = mainFrame.getController().getAccountStorage().getCurrentSession().username;
			labelPassword = "Old Password:";
			registerSetText = "Back";
			loginSetText = "Change";
		}

		setSize(MainFrame.getGlobalWidth() - 6, MainFrame.getGlobalHeight() - 48);
		setLayout(null);

		JLabel lblWelcomeToSandwich = new JLabel("Welcome to Sandwich Order System - " + title);
		lblWelcomeToSandwich.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeToSandwich.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblWelcomeToSandwich.setBounds(0, 13, 634, 25);
		add(lblWelcomeToSandwich);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 51, 634, 1);
		add(separator);

		JLabel lblToUseThe = new JLabel(mainScreenDescription);
		lblToUseThe.setBounds(12, 65, 600, 16);
		add(lblToUseThe);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(0, 94, 634, 1);
		add(separator_2);

		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(12, 111, 100, 16);
		add(lblUsername);

		username = new JTextField();
		username.setEditable(usernameEditable);
		username.setText(usernameSetText);
		username.getDocument().addDocumentListener(new DocumentListener()
		{
			@Override
			public void changedUpdate(DocumentEvent e)
			{
				validateInput();
			}

			@Override
			public void insertUpdate(DocumentEvent e)
			{
				validateInput();
			}

			@Override
			public void removeUpdate(DocumentEvent e)
			{
				validateInput();
			}

			public void validateInput()
			{
				if (username.getText().isEmpty() || password.getPassword().length == 0)
					btnLogin.setEnabled(false);
				else
					btnLogin.setEnabled(true);
			}
		});
		username.setBounds(110, 108, 250, 22);
		add(username);

		JLabel lblPassword = new JLabel(labelPassword);
		lblPassword.setBounds(12, 140, 100, 16);
		add(lblPassword);

		password = new JPasswordField();
		password.getDocument().addDocumentListener(new DocumentListener()
		{
			@Override
			public void changedUpdate(DocumentEvent e)
			{
				validateInput();
			}

			@Override
			public void insertUpdate(DocumentEvent e)
			{
				validateInput();
			}

			@Override
			public void removeUpdate(DocumentEvent e)
			{
				validateInput();
			}

			public void validateInput()
			{
				if (type == 0 || type == 1)
				{
					if (username.getText().isEmpty() || password.getPassword().length == 0)
						btnLogin.setEnabled(false);
					else
						btnLogin.setEnabled(true);
				}
				else if (username.getText().isEmpty() || password.getPassword().length == 0 || newPassword.getPassword().length == 0)
					btnLogin.setEnabled(false);
				else
					btnLogin.setEnabled(true);
			}
		});
		password.setBounds(110, 137, 250, 22);
		add(password);

		if (type == 0)
		{
			JTextPane txtpnTest = new JTextPane();
			txtpnTest.setBackground(UIManager.getColor("Label.background"));
			txtpnTest.setDisabledTextColor(UIManager.getColor("Label.foreground"));
			txtpnTest.setEnabled(false);
			txtpnTest.setText("Guest user can login using the following account:\r\n\r\nUsername: guest\r\nPassword: password\r\n\r\nGuest user does not store any order history so please keep a copy of the order you have made.");
			txtpnTest.setBounds(12, 242, 610, 125);
			add(txtpnTest);
		}

		if (type == 2)
		{
			JLabel lblNewPassword = new JLabel("New Password:");
			lblNewPassword.setBounds(12, 169, 100, 16);
			add(lblNewPassword);

			newPassword = new JPasswordField();
			newPassword.getDocument().addDocumentListener(new DocumentListener()
			{
				@Override
				public void changedUpdate(DocumentEvent e)
				{
					validateInput();
				}

				@Override
				public void insertUpdate(DocumentEvent e)
				{
					validateInput();
				}

				@Override
				public void removeUpdate(DocumentEvent e)
				{
					validateInput();
				}

				public void validateInput()
				{
					if (password.getPassword().length == 0 || newPassword.getPassword().length == 0)
						btnLogin.setEnabled(false);
					else
						btnLogin.setEnabled(true);
				}
			});
			newPassword.setBounds(110, 166, 250, 22);
			add(newPassword);
		}

		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(0, 380, 634, 1);
		add(separator_3);

		JButton btnRegister = new JButton(registerSetText);
		btnRegister.addActionListener(e ->
		{
			if (type == 0)
				mainFrame.showLoginScreen(1);
			else if (type == 1)
				mainFrame.showLoginScreen(0);
			else if (type == 2)
				mainFrame.showMainScreen();
		});
		btnRegister.setBounds(416, 394, 97, 25);
		add(btnRegister);

		btnLogin = new JButton(loginSetText);
		btnLogin.setEnabled(false);
		btnLogin.addActionListener(e ->
		{
			if (type == 0)
				mainFrame.getController().getLoginScreenController().login(username.getText(), String.valueOf(password.getPassword()));
			else if (type == 1)
				mainFrame.getController().getLoginScreenController().register(username.getText(), String.valueOf(password.getPassword()));
			else if (type == 2)
				mainFrame.getController().getLoginScreenController().changePassword(username.getText(), String.valueOf(password.getPassword()), String.valueOf(newPassword.getPassword()));
		});
		btnLogin.setBounds(525, 394, 97, 25);
		add(btnLogin);
	}
}
