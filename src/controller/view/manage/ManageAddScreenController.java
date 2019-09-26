package controller.view.manage;

import javax.swing.JOptionPane;

import controller.BaseController;
import controller.Controller;
import controller.MainFrame;

public class ManageAddScreenController extends BaseController
{
	public ManageAddScreenController(Controller controller, MainFrame mainFrame)
	{
		super(controller, mainFrame);
	}

	/**
	 * Add a new account into the database.
	 *
	 * @param username Username to add.
	 * @param password Password to add.
	 * @param type Type to add.
	 */
	public void addAccount(String username, String password, int type)
	{
		if (JOptionPane.showConfirmDialog(null, "Do you wish to add a new account with the following infomation:\nUsername: " + username + "\nPassword: " + password + "\nAccount Type: " + type, "Add Account confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0)
			if (getController().getAccountStorage().addAccount(username, password, type))
				getMainFrame().showManageScreen(2);
			else
				JOptionPane.showMessageDialog(null, "Username exist in the database. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * Add Bread into the availability list.
	 *
	 * @param type Type of bread to add.
	 * @param price Price of bread to add.
	 * @param description Description of bread to add.
	 */
	public void addBread(String type, Double price, String description)
	{
		if (JOptionPane.showConfirmDialog(null, "Do you wish to add a new bread with the following infomation:\nBread type: " + type + "\nBread price: " + String.format("$%.2f", price) + "\nBread description: " + description, "Add bread confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0)
			if (getController().getOrderStorage().addAvailableBread(type, price, description))
				getMainFrame().showManageScreen(0);
			else
				JOptionPane.showMessageDialog(null, "Bread type exist in the database, please try again.", "Error", JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * Add ingredient into the availability list.
	 *
	 * @param name Name of ingredient to add.
	 * @param price Price of ingredient to add.
	 * @param description Description of ingredient to add.
	 */
	public void addIngredient(String name, Double price, String description)
	{
		if (JOptionPane.showConfirmDialog(null, "Do you wish to add a new ingredient with the following infomation:\nIngredient name: " + name + "\nIngredient price: " + String.format("$%.2f", price) + "\nIngredient description: " + description, "Add Ingredient confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0)
			if (getController().getOrderStorage().addAvailableIngredient(name, price, description))
				getMainFrame().showManageScreen(1);
			else
				JOptionPane.showMessageDialog(null, "Ingredient exist in the database, please try again.", "Error", JOptionPane.ERROR_MESSAGE);
	}
}
