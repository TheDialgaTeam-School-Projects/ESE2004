package controller.view.account;

import javax.swing.JOptionPane;

import controller.BaseController;
import controller.Controller;
import controller.MainFrame;

public class LoginScreenController extends BaseController
{
	public LoginScreenController(Controller controller, MainFrame mainFrame)
	{
		super(controller, mainFrame);
	}

	/**
	 * Change account password.
	 * 
	 * @param username Username to change.
	 * @param password Password to change.
	 * @param newPassword New password to change.
	 */
	public void changePassword(String username, String password, String newPassword)
	{
		if (JOptionPane.showConfirmDialog(null, "Do you wish to change your password with the following infomation:\nUsername: " + username + "\nPassword: " + newPassword, "New password confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0)
			if (!getController().getAccountStorage().changeAccountPassword(username, password, newPassword))
				JOptionPane.showMessageDialog(null, "Password mismatch. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
			else
				getMainFrame().showMainScreen();
	}

	/**
	 * Login account.
	 *
	 * @param username Username to login.
	 * @param password Password to login.
	 */
	public void login(String username, String password)
	{
		if (getController().getAccountStorage().loginAccount(username, password))
			getMainFrame().showMainScreen();
		else
			JOptionPane.showMessageDialog(null, "Invalid username or password. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * Register account.
	 *
	 * @param username Username to register.
	 * @param password Password to register.
	 */
	public void register(String username, String password)
	{
		if (JOptionPane.showConfirmDialog(null, "Do you wish to register with the following infomation:\nUsername: " + username + "\nPassword: " + password, "Registration confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0)
			if (getController().getAccountStorage().registerAccount(username, password))
			{
				JOptionPane.showMessageDialog(null, "Your account have been registered. You may login at the main page now.", "Register completed!", JOptionPane.INFORMATION_MESSAGE);
				getMainFrame().showLoginScreen(0);
			}
			else
				JOptionPane.showMessageDialog(null, "Username exist in the database. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
	}
}
