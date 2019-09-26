package controller.view.main;

import javax.swing.JOptionPane;

import controller.BaseController;
import controller.Controller;
import controller.MainFrame;

public class MainScreenController extends BaseController
{
	public MainScreenController(Controller controller, MainFrame mainFrame)
	{
		super(controller, mainFrame);
	}

	/**
	 * Logout the current session.
	 */
	public void logout()
	{
		if (JOptionPane.showConfirmDialog(null, "Are you sure you wish to logout?", "Logout confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0)
		{
			getController().getAccountStorage().logoutAccount();
			getMainFrame().showLoginScreen(0);
		}
	}
}
