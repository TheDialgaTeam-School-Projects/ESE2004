package view.order;

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
import model.Ingredient;
import model.Order;

@SuppressWarnings("serial")
public class OrderHistoryScreen extends JPanel
{
	private JButton btnRemove;

	public OrderHistoryScreen(MainFrame mainFrame)
	{
		setSize(MainFrame.getGlobalWidth() - 6, MainFrame.getGlobalHeight() - 48);
		setLayout(null);

		JLabel lblWelcomeToSandwich = new JLabel("Welcome to Sandwich Order System - Order History");
		lblWelcomeToSandwich.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeToSandwich.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblWelcomeToSandwich.setBounds(0, 13, 634, 25);
		add(lblWelcomeToSandwich);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 51, 634, 1);
		add(separator);

		JLabel welcomeLabel = new JLabel("This is a list of sandwich you have ordered so far:");
		welcomeLabel.setBounds(12, 65, 600, 16);
		add(welcomeLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 108, 612, 259);
		add(scrollPane);

		JList<String> list = new JList<String>();
		list.addListSelectionListener(e ->
		{
			if (list.getSelectedIndex() > -1)
				btnRemove.setEnabled(true);
			else
				btnRemove.setEnabled(false);
		});
		DefaultListModel<String> model = new DefaultListModel<String>();
		for (Order order : mainFrame.getController().getOrderStorage().orders)
		{
			boolean addOnce = true;
			String ingredient = "";

			if (order.getAccount().username.contentEquals(mainFrame.getController().getAccountStorage().getCurrentSession().username))
			{
				for (Ingredient ingredient2 : order.ingredients)
					if (addOnce)
					{
						ingredient += ingredient2.name + " (" + String.format("$%.2f", ingredient2.price) + ")";
						addOnce = false;
					}
					else
						ingredient += ", " + ingredient2.name + " (" + String.format("$%.2f", ingredient2.price) + ")";

				model.addElement(order.getBread().type + " (" + String.format("$%.2f", order.getBread().price) + ") - " + ingredient + ": " + String.format("$%.2f", order.calculatePrice()));
			}
		}
		list.setModel(model);
		scrollPane.setViewportView(list);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 380, 634, 1);
		add(separator_1);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(e -> mainFrame.showMainScreen());
		btnBack.setBounds(527, 394, 97, 25);
		add(btnBack);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(0, 94, 634, 1);
		add(separator_2);

		btnRemove = new JButton("Remove");
		btnRemove.addActionListener(e -> mainFrame.getController().getOrderHistoryScreenController().removeOrderHistory(list.getSelectedIndex()));
		btnRemove.setEnabled(false);
		btnRemove.setBounds(418, 394, 97, 25);
		add(btnRemove);
	}
}
