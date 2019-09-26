package view.order;

import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import controller.MainFrame;
import model.Ingredient;

@SuppressWarnings("serial")
public class OrderScreen extends JPanel
{
	/**
	 * Initialize order screen.
	 *
	 * @param mainFrame Mainframe.
	 * @param type 0 = Bread order, 1 = Ingredient order.
	 */
	public OrderScreen(MainFrame mainFrame, int type)
	{
		String mainScreenDescription = "Please select the bread type you wish to order.";
		String button3SetText = "Back";

		if (type == 0)
		{
			mainScreenDescription = "Please select the bread type you wish to order.";
			button3SetText = "Back";
		}
		else if (type == 1)
		{
			mainScreenDescription = "Please select the ingredient(s) you wish to order.";
			button3SetText = "Submit";
		}

		setSize(MainFrame.getGlobalWidth() - 6, MainFrame.getGlobalHeight() - 48);
		setLayout(null);

		JLabel lblWelcomeToSandwich = new JLabel("Welcome to Sandwich Order System - Make a new order");
		lblWelcomeToSandwich.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeToSandwich.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblWelcomeToSandwich.setBounds(0, 13, 624, 25);
		add(lblWelcomeToSandwich);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 51, 634, 1);
		add(separator);

		JLabel welcomeLabel = new JLabel(mainScreenDescription);
		welcomeLabel.setBounds(12, 65, 600, 16);
		add(welcomeLabel);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(0, 94, 634, 1);
		add(separator_2);

		OrderPanel orderPanel = new OrderPanel(mainFrame, type);
		JScrollPane scrollPane = new JScrollPane(orderPanel);
		scrollPane.setBounds(12, 108, 612, 259);
		add(scrollPane);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 380, 634, 1);
		add(separator_1);

		if (type == 1)
		{
			JButton btnViewIngredientsSelected = new JButton("View ingredients selected");
			btnViewIngredientsSelected.addActionListener(e ->
			{
				JScrollPane scrollPane1 = new JScrollPane();
				scrollPane1.setBounds(6, 5, 258, 280);
				add(scrollPane1);

				JList<String> list = new JList<String>();
				list.setEnabled(false);
				scrollPane1.setViewportView(list);

				DefaultListModel<String> model = new DefaultListModel<String>();
				for (Ingredient ingredient : mainFrame.getController().getOrderStorage().currentOrder.ingredients)
					model.addElement(ingredient.name);
				list.setModel(model);

				JOptionPane.showMessageDialog(null, scrollPane1, "Selected Ingredients", JOptionPane.PLAIN_MESSAGE);
			});
			btnViewIngredientsSelected.setBounds(10, 394, 200, 25);
			add(btnViewIngredientsSelected);

			JButton button1 = new JButton("Back");
			button1.addActionListener(e -> mainFrame.showOrderScreen(0));
			button1.setBounds(309, 394, 97, 25);
			add(button1);

			JButton button2 = new JButton("Reset");
			button2.addActionListener(e -> mainFrame.getController().getOrderScreenController().reset());
			button2.setBounds(418, 394, 97, 25);
			add(button2);
		}

		JButton button3 = new JButton(button3SetText);
		button3.addActionListener(e ->
		{
			if (type == 0)
				mainFrame.showMainScreen();
			else if (type == 1)
				mainFrame.showPreviewOrderScreen();
		});
		button3.setBounds(527, 394, 97, 25);
		add(button3);
	}
}
