package view.manage;

import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import controller.MainFrame;
import model.Account;
import model.Bread;
import model.Ingredient;

@SuppressWarnings("serial")
public class ManageScreen extends JPanel
{
	private JButton btnEdit;
	private JButton btnPreview;
	private JButton btnRemove;
	private JList<String> list;

	/**
	 * Initialize manage screen.
	 *
	 * @param mainFrame Mainframe.
	 * @param type 0 = Bread, 1 = Ingredient, 2 = Account.
	 */
	public ManageScreen(MainFrame mainFrame, int type)
	{
		String title = "Manage Bread List";
		String mainScreenDescription = "This page contains all the available bread types for sale.";

		if (type == 0)
		{
			title = "Manage Bread List";
			mainScreenDescription = "This page contains all the available bread types for sale.";
		}
		else if (type == 1)
		{
			title = "Manage Ingredient List";
			mainScreenDescription = "This page contains all the available ingredients for sale.";
		}
		else if (type == 2)
		{
			title = "Manage Account List";
			mainScreenDescription = "This page contains all the available account for this application.";
		}

		setSize(MainFrame.getGlobalWidth() - 6, MainFrame.getGlobalHeight() - 48);
		setLayout(null);

		JLabel lblWelcomeToSandwich = new JLabel("Welcome to Sandwich Order System - " + title);
		lblWelcomeToSandwich.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeToSandwich.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblWelcomeToSandwich.setBounds(0, 13, 634, 25);
		add(lblWelcomeToSandwich);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 51, 634, 1);
		add(separator_1);

		JLabel welcomeLabel = new JLabel(mainScreenDescription);
		welcomeLabel.setBounds(12, 65, 600, 16);
		add(welcomeLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 108, 612, 259);
		add(scrollPane);

		list = new JList<String>();
		list.addListSelectionListener(e ->
		{
			if (list.getSelectedIndex() > -1)
			{
				btnRemove.setEnabled(true);
				btnEdit.setEnabled(true);
				btnPreview.setEnabled(true);
			}
			else
			{
				btnRemove.setEnabled(false);
				btnEdit.setEnabled(false);
				btnPreview.setEnabled(false);
			}
		});
		DefaultListModel<String> listModel = new DefaultListModel<String>();

		if (type == 0)
			for (Bread bread : mainFrame.getController().getOrderStorage().getAvailableBreads())
				listModel.addElement(bread.type);
		else if (type == 1)
			for (Ingredient ingredient : mainFrame.getController().getOrderStorage().getAvailableIngredients())
				listModel.addElement(ingredient.name);
		else if (type == 2)
			for (Account account : mainFrame.getController().getAccountStorage().getAccounts())
				listModel.addElement(account.username);

		list.setModel(listModel);
		scrollPane.setViewportView(list);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(0, 380, 634, 1);
		add(separator_2);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(e -> mainFrame.showManageAddScreen(type));
		btnAdd.setBounds(12, 394, 97, 25);

		add(btnAdd);

		btnPreview = new JButton("Preview");
		btnPreview.addActionListener(e -> mainFrame.showManagePreviewScreen(type, list.getSelectedIndex()));
		btnPreview.setEnabled(false);
		btnPreview.setBounds(121, 394, 97, 25);
		add(btnPreview);

		btnEdit = new JButton("Edit");
		btnEdit.addActionListener(e -> mainFrame.showManageEditScreen(type, list.getSelectedIndex()));
		btnEdit.setEnabled(false);
		btnEdit.setBounds(230, 394, 97, 25);
		add(btnEdit);

		btnRemove = new JButton("Remove");
		btnRemove.addActionListener(e ->
		{
			if (type == 0)
				mainFrame.getController().getManageScreenController().removeAvailableBread(list.getSelectedIndex());
			else if (type == 1)
				mainFrame.getController().getManageScreenController().removeAvailableIngredient(list.getSelectedIndex());
			else if (type == 2)
				mainFrame.getController().getManageScreenController().removeAccount(list.getSelectedIndex());
		});
		btnRemove.setEnabled(false);
		btnRemove.setBounds(339, 394, 97, 25);
		add(btnRemove);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(e -> mainFrame.showMainScreen());
		btnBack.setBounds(525, 394, 97, 25);
		add(btnBack);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 94, 634, 1);
		add(separator);
	}
}
