package controller.view.order;

import javax.swing.JOptionPane;

import controller.BaseController;
import controller.Controller;
import controller.MainFrame;

public class PreviewOrderScreenController extends BaseController
{
	public PreviewOrderScreenController(Controller controller, MainFrame mainFrame)
	{
		super(controller, mainFrame);
	}

	/**
	 * Submit order and add into the database.
	 */
	public void submitOrder()
	{
		if (JOptionPane.showConfirmDialog(null, "Are you sure you want to submit the current order?", "Submit order confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0)
		{
			getController().getOrderStorage().submitOrder();
			getMainFrame().showMainScreen();
		}
	}
}
