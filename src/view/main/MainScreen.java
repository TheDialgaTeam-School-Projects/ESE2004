package view.main;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import controller.MainFrame;

@SuppressWarnings("serial")
public class MainScreen extends JPanel
{
	public MainScreen(MainFrame mainFrame)
	{
		int accountType = mainFrame.getController().getAccountStorage().getCurrentSession().accountType;

		setSize(MainFrame.getGlobalWidth() - 6, MainFrame.getGlobalHeight() - 48);
		setLayout(null);

		JLabel lblWelcomeToSandwich = new JLabel("Welcome to Sandwich Order System");
		lblWelcomeToSandwich.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeToSandwich.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblWelcomeToSandwich.setBounds(0, 13, 634, 25);
		add(lblWelcomeToSandwich);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 51, 634, 1);
		add(separator_1);

		JLabel welcomeLabel = new JLabel("Welcome " + mainFrame.getController().getAccountStorage().getCurrentSession().username + "!");
		welcomeLabel.setBounds(12, 65, 600, 16);
		add(welcomeLabel);

		JButton btnMakeNewOrder = new JButton("Make new order");
		btnMakeNewOrder.addActionListener(e -> mainFrame.showOrderScreen(0));

		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(0, 94, 634, 1);
		add(separator_3);
		btnMakeNewOrder.setBounds(12, 108, 150, 25);
		add(btnMakeNewOrder);

		JButton btnViewOrderHistory = new JButton("View order history");
		btnViewOrderHistory.addActionListener(e -> mainFrame.showOrderHistoryScreen());
		btnViewOrderHistory.setBounds(174, 108, 150, 25);
		add(btnViewOrderHistory);

		if (accountType == 2)
		{
			JSeparator separator = new JSeparator();
			separator.setBounds(0, 146, 634, 1);
			add(separator);

			JLabel lblAdminPanel = new JLabel("Admin Panel:");
			lblAdminPanel.setBounds(12, 161, 600, 16);
			add(lblAdminPanel);

			JButton btnManageAccounts = new JButton("Manage accounts");
			btnManageAccounts.addActionListener(e -> mainFrame.showManageScreen(2));
			btnManageAccounts.setBounds(12, 190, 150, 25);
			add(btnManageAccounts);

			JButton btnManageBreadList = new JButton("Manage bread list");
			btnManageBreadList.addActionListener(e -> mainFrame.showManageScreen(0));
			btnManageBreadList.setBounds(174, 190, 150, 25);
			add(btnManageBreadList);

			JButton btnManageIngredientList = new JButton("Manage ingredient list");
			btnManageIngredientList.addActionListener(e -> mainFrame.showManageScreen(1));
			btnManageIngredientList.setBounds(336, 190, 175, 25);
			add(btnManageIngredientList);
		}

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(0, 380, 634, 1);
		add(separator_2);

		if (accountType > 0)
		{
			JButton btnChangePassword = new JButton("Change password");
			btnChangePassword.addActionListener(e -> mainFrame.showLoginScreen(2));
			btnChangePassword.setBounds(312, 394, 150, 25);
			add(btnChangePassword);
		}

		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(e -> mainFrame.getController().getMainScreenController().logout());
		btnLogout.setBounds(474, 394, 150, 25);
		add(btnLogout);
	}
}
