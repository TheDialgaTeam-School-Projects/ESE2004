package controller;

public abstract class BaseController
{
	private Controller controller;
	private MainFrame mainFrame;

	public BaseController(Controller controller, MainFrame mainFrame)
	{
		this.controller = controller;
		this.mainFrame = mainFrame;
	}

	/**
	 * Get Global controller.
	 */
	public Controller getController()
	{
		return controller;
	}

	/**
	 * Get Global mainframe.
	 */
	public MainFrame getMainFrame()
	{
		return mainFrame;
	}
}
