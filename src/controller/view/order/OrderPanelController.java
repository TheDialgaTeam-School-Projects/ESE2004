package controller.view.order;

import javax.swing.JOptionPane;

import controller.BaseController;
import controller.Controller;
import controller.MainFrame;
import model.Bread;
import model.Ingredient;
import model.Order;

public class OrderPanelController extends BaseController
{
	public OrderPanelController(Controller controller, MainFrame mainFrame)
	{
		super(controller, mainFrame);
	}

	public void selectBread(String type)
	{
		Bread bread = getController().getOrderStorage().getAvailableBread(type);
		getController().getOrderStorage().currentOrder = new Order(getController().getAccountStorage().getCurrentSession(), bread);
		getController().getOrderStorage().currentOrder.ingredients.clear();
		getMainFrame().showOrderScreen(1);
	}

	public void selectIngredient(String name)
	{
		Ingredient ingredient = getController().getOrderStorage().getAvailableIngredient(name);
		getController().getOrderStorage().currentOrder.ingredients.add(ingredient);

		if (JOptionPane.showConfirmDialog(null, "Ingredient selected.\nDo you wish to continue selecting ingredients?", "Order confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 1)
			getMainFrame().showPreviewOrderScreen();
	}
}
