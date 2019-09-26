package controller.view.order;

import javax.swing.JOptionPane;

import controller.BaseController;
import controller.Controller;
import controller.MainFrame;

public class OrderScreenController extends BaseController
{
	public OrderScreenController(Controller controller, MainFrame mainFrame)
	{
		super(controller, mainFrame);
	}

	/**
	 * Reset selected ingredients.
	 */
	public void reset()
	{
		if (JOptionPane.showConfirmDialog(null, "Do you wish to reset the selected ingredient(s)?", "Reset confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0)
			getController().getOrderStorage().currentOrder.ingredients.clear();
	}
}
