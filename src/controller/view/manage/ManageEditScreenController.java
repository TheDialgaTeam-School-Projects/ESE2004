package controller.view.manage;

import javax.swing.JOptionPane;

import controller.BaseController;
import controller.Controller;
import controller.MainFrame;

public class ManageEditScreenController extends BaseController
{
	public ManageEditScreenController(Controller controller, MainFrame mainFrame)
	{
		super(controller, mainFrame);
	}

	/**
	 * Edit account in the account database.
	 *
	 * @param oldUsername Username to edit.
	 * @param username Username to edit.
	 * @param password Password to edit.
	 * @param type Account type to edit.
	 */
	public void editAccount(String oldUsername, String username, String password, int type)
	{
		if (JOptionPane.showConfirmDialog(null, "Do you wish to edit the account with the following infomation:\nUsername: " + username + "\nPassword: " + password + "\nAccount Type: " + type, "Edit Account confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0)
			if (getController().getAccountStorage().editAccount(oldUsername, username, password, type))
				getMainFrame().showManageScreen(2);
			else
				JOptionPane.showMessageDialog(null, "Username exist in the database. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * Edit Bread in the availability list.
	 *
	 * @param oldType Type of bread to edit.
	 * @param type Type of bread to edit.
	 * @param price Price of bread to edit.
	 * @param description Description of bread to edit.
	 */
	public void editBread(String oldType, String type, Double price, String description)
	{
		if (JOptionPane.showConfirmDialog(null, "Do you wish to edit the bread with the following infomation:\nBread type: " + type + "\nBread price: " + String.format("$%.2f", price) + "\nBread description: " + description, "Edit bread confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0)
			if (getController().getOrderStorage().editAvailableBread(oldType, type, price, description))
				getMainFrame().showManageScreen(0);
			else
				JOptionPane.showMessageDialog(null, "Bread type exist in the database, please try again.", "Error", JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * Edit ingredient in the availability list.
	 *
	 * @param oldName Name of ingredient to edit.
	 * @param name Name of ingredient to edit.
	 * @param price Price of ingredient to edit.
	 * @param description Description of ingredient to edit.
	 */
	public void editIngredient(String oldName, String name, Double price, String description)
	{
		if (JOptionPane.showConfirmDialog(null, "Do you wish to edit the ingredient with the following infomation:\nIngredient name: " + name + "\nIngredient price: " + String.format("$%.2f", price) + "\nIngredient description: " + description, "Edit ingredient confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0)
			if (getController().getOrderStorage().editAvailableIngredient(oldName, name, price, description))
				getMainFrame().showManageScreen(1);
			else
				JOptionPane.showMessageDialog(null, "Ingredient exist in the database, please try again.", "Error", JOptionPane.ERROR_MESSAGE);
	}
}
