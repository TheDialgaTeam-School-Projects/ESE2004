package controller;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import view.account.LoginScreen;
import view.main.MainScreen;
import view.manage.ManageAddScreen;
import view.manage.ManageEditScreen;
import view.manage.ManagePreviewScreen;
import view.manage.ManageScreen;
import view.order.OrderHistoryScreen;
import view.order.OrderScreen;
import view.order.PreviewOrderScreen;

@SuppressWarnings("serial")
public class MainFrame extends JFrame
{
	private static int globalHeight = 480;
	private static int globalWidth = 640;
	private static String workingDirectory = System.getProperty("user.dir");

	/**
	 * Get Global height of the window.
	 */
	public static int getGlobalHeight()
	{
		return globalHeight;
	}

	/**
	 * Get Global width of the window.
	 */
	public static int getGlobalWidth()
	{
		return globalWidth;
	}

	/**
	 * Get Working directory.
	 */
	public static String getWorkingDirectory()
	{
		return workingDirectory;
	}

	private CardLayout cardLayout;

	private Controller controller = new Controller(this);

	/**
	 * New MainFrame object.
	 */
	public MainFrame()
	{
		setTitle("Sandwich Order System");
		setPreferredSize(new Dimension(globalWidth, globalHeight));

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((dimension.width - globalWidth) / 2, (dimension.height - globalHeight) / 2, globalWidth, globalHeight);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		cardLayout = new CardLayout();
		getContentPane().setLayout(cardLayout);

		showLoginScreen(0);

		setResizable(false);
		setVisible(true);
	}

	/**
	 * Get Global controller.
	 */
	public Controller getController()
	{
		return controller;
	}

	/**
	 * Show login screen.
	 *
	 * @param type 0 = Login Screen, 1 = Registration Screen, 2 = Change
	 * Password Screen.
	 */
	public void showLoginScreen(int type)
	{
		LoginScreen loginScreen = new LoginScreen(this, type);
		add("LoginScreen", loginScreen);
		cardLayout.show(getContentPane(), "LoginScreen");
	}

	/**
	 * Show main screen.
	 */
	public void showMainScreen()
	{
		MainScreen mainScreen = new MainScreen(this);
		add("MainScreen", mainScreen);
		cardLayout.show(getContentPane(), "MainScreen");
	}

	/**
	 * Show manage add screen.
	 *
	 * @param type 0 = Bread, 1 = Ingredient, 2 = Account.
	 */
	public void showManageAddScreen(int type)
	{
		ManageAddScreen manageAddScreen = new ManageAddScreen(this, type);
		add("ManageAddScreen", manageAddScreen);
		cardLayout.show(getContentPane(), "ManageAddScreen");
	}

	/**
	 * Show manage edit screen.
	 *
	 * @param type 0 = Bread, 1 = Ingredient, 2 = Account.
	 * @param index Selected index.
	 */
	public void showManageEditScreen(int type, int index)
	{
		ManageEditScreen manageEditScreen = new ManageEditScreen(this, type, index);
		add("ManageEditScreen", manageEditScreen);
		cardLayout.show(getContentPane(), "ManageEditScreen");
	}

	/**
	 * Show manage preview screen.
	 *
	 * @param type 0 = Bread, 1 = Ingredient, 2 = Account.
	 * @param index Selected index.
	 */
	public void showManagePreviewScreen(int type, int index)
	{
		ManagePreviewScreen managePreviewScreen = new ManagePreviewScreen(this, type, index);
		add("ManagePreviewScreen", managePreviewScreen);
		cardLayout.show(getContentPane(), "ManagePreviewScreen");
	}

	/**
	 * Show manage screen.
	 *
	 * @param type 0 = Bread, 1 = Ingredient, 2 = Account.
	 */
	public void showManageScreen(int type)
	{
		ManageScreen manageScreen = new ManageScreen(this, type);
		add("ManageScreen", manageScreen);
		cardLayout.show(getContentPane(), "ManageScreen");
	}

	/**
	 * Show order history screen.
	 */
	public void showOrderHistoryScreen()
	{
		OrderHistoryScreen orderHistoryScreen = new OrderHistoryScreen(this);
		add("OrderHistoryScreen", orderHistoryScreen);
		cardLayout.show(getContentPane(), "OrderHistoryScreen");
	}

	/**
	 * Show login screen.
	 *
	 * @param type 0 = Bread order, 1 = Ingredient order.
	 */
	public void showOrderScreen(int type)
	{
		OrderScreen orderScreen = new OrderScreen(this, type);
		add("OrderScreen", orderScreen);
		cardLayout.show(getContentPane(), "OrderScreen");
	}

	/**
	 * Show preview order screen.
	 */
	public void showPreviewOrderScreen()
	{
		PreviewOrderScreen previewOrderScreen = new PreviewOrderScreen(this);
		add("PreviewOrderScreen", previewOrderScreen);
		cardLayout.show(getContentPane(), "PreviewOrderScreen");
	}
}
