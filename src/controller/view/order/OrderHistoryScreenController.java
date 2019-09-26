package controller.view.order;

import javax.swing.JOptionPane;

import controller.BaseController;
import controller.Controller;
import controller.MainFrame;

public class OrderHistoryScreenController extends BaseController
{
	public OrderHistoryScreenController(Controller controller, MainFrame mainFrame)
	{
		super(controller, mainFrame);
	}

	public void removeOrderHistory(int selectedIndex)
	{
		if (JOptionPane.showConfirmDialog(null, "Do you wish to remove the selected order.", "Remove order confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0)
		{
			getMainFrame().getController().getOrderStorage().removeOrderHistory(selectedIndex, getController().getAccountStorage().getCurrentSession());
			getMainFrame().showOrderHistoryScreen();
		}
	}
}
