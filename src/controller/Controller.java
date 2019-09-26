package controller;

import controller.view.account.LoginScreenController;
import controller.view.main.MainScreenController;
import controller.view.manage.ManageAddScreenController;
import controller.view.manage.ManageEditScreenController;
import controller.view.manage.ManageScreenController;
import controller.view.order.OrderHistoryScreenController;
import controller.view.order.OrderPanelController;
import controller.view.order.OrderScreenController;
import controller.view.order.PreviewOrderScreenController;
import model.AccountStorage;
import model.OrderStorage;

public class Controller
{
	private AccountStorage accountStorage = new AccountStorage();
	private LoginScreenController loginScreenController;
	private MainScreenController mainScreenController;
	private ManageAddScreenController manageAddScreenController;
	private ManageEditScreenController manageEditScreenController;
	private ManageScreenController manageScreenController;
	private OrderHistoryScreenController orderHistoryScreenController;
	private OrderPanelController orderPanelController;
	private OrderScreenController orderScreenController;
	private OrderStorage orderStorage = new OrderStorage();
	private PreviewOrderScreenController previewOrderScreenController;

	public Controller(MainFrame mainFrame)
	{
		orderHistoryScreenController = new OrderHistoryScreenController(this, mainFrame);
		mainScreenController = new MainScreenController(this, mainFrame);
		loginScreenController = new LoginScreenController(this, mainFrame);
		previewOrderScreenController = new PreviewOrderScreenController(this, mainFrame);
		orderScreenController = new OrderScreenController(this, mainFrame);
		orderPanelController = new OrderPanelController(this, mainFrame);
		manageAddScreenController = new ManageAddScreenController(this, mainFrame);
		manageEditScreenController = new ManageEditScreenController(this, mainFrame);
		manageScreenController = new ManageScreenController(this, mainFrame);
	}

	/**
	 * Get Account storage model.
	 */
	public AccountStorage getAccountStorage()
	{
		return accountStorage;
	}

	/**
	 * Get Add screen controller.
	 */
	public ManageAddScreenController getAddScreenController()
	{
		return manageAddScreenController;
	}

	/**
	 * Get Login screen controller.
	 */
	public LoginScreenController getLoginScreenController()
	{
		return loginScreenController;
	}

	/**
	 * Get Main screen controller.
	 */
	public MainScreenController getMainScreenController()
	{
		return mainScreenController;
	}

	/**
	 * Get Manage edit screen controller.
	 */
	public ManageEditScreenController getManageEditScreenController()
	{
		return manageEditScreenController;
	}

	/**
	 * Get Manage screen controller.
	 */
	public ManageScreenController getManageScreenController()
	{
		return manageScreenController;
	}

	/**
	 * Get Order history screen controller.
	 */
	public OrderHistoryScreenController getOrderHistoryScreenController()
	{
		return orderHistoryScreenController;
	}

	/**
	 * Get order panel controller.
	 */
	public OrderPanelController getOrderPanelController()
	{
		return orderPanelController;
	}

	/**
	 * Get Order screen controller.
	 */
	public OrderScreenController getOrderScreenController()
	{
		return orderScreenController;
	}

	/**
	 * Get Order storage model.
	 */
	public OrderStorage getOrderStorage()
	{
		return orderStorage;
	}

	/**
	 * Get Preview order screen controller.
	 */
	public PreviewOrderScreenController getPreviewOrderScreenController()
	{
		return previewOrderScreenController;
	}
}
