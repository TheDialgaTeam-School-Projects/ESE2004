package controller.view.manage;

import javax.swing.JOptionPane;

import controller.BaseController;
import controller.Controller;
import controller.MainFrame;
import system.ExceptionHelper;

public class ManageScreenController extends BaseController
{
	public ManageScreenController(Controller controller, MainFrame mainFrame)
	{
		super(controller, mainFrame);
	}

	/**
	 * Remove the selected account from the database.
	 *
	 * @param selectedIndex Selected index.
	 */
	public void removeAccount(int selectedIndex)
	{
		try
		{
			if (JOptionPane.showConfirmDialog(null, "Do you wish to remove the following Account:\nUsername: " + getMainFrame().getController().getAccountStorage().getAccounts().getItem(selectedIndex).username, "Remove account confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0)
			{
				getMainFrame().getController().getAccountStorage().removeAccount(selectedIndex);
				getMainFrame().showManageScreen(2);
			}
		}
		catch (Exception ex)
		{
			ExceptionHelper.catchError(ex);
		}
	}

	/**
	 * Remove selected bread from the database.
	 *
	 * @param selectedIndex Selected index.
	 */
	public void removeAvailableBread(int selectedIndex)
	{
		if (JOptionPane.showConfirmDialog(null, "Do you wish to remove the following Bread:\nBread Type: " + getMainFrame().getController().getOrderStorage().getAvailableBread(selectedIndex).type, "Remove bread confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0)
		{
			getMainFrame().getController().getOrderStorage().removeAvailableBread(selectedIndex);
			getMainFrame().showManageScreen(0);
		}
	}

	/**
	 * Remove the selected ingredient from the database.
	 *
	 * @param selectedIndex Selected index.
	 */
	public void removeAvailableIngredient(int selectedIndex)
	{
		if (JOptionPane.showConfirmDialog(null, "Do you wish to remove the following Ingredient:\nIngredient name: " + getMainFrame().getController().getOrderStorage().getAvailableIngredient(selectedIndex).name, "Remove ingredient confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0)
		{
			getMainFrame().getController().getOrderStorage().removeAvailableIngredient(selectedIndex);
			getMainFrame().showManageScreen(1);
		}
	}
}
