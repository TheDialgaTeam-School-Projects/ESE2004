package view.order;

import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.MainFrame;
import model.Ingredient;

@SuppressWarnings("serial")
public class PreviewOrderScreen extends JPanel
{
	private JTextField type;

	public PreviewOrderScreen(MainFrame mainFrame)
	{
		String oldBreadType = mainFrame.getController().getOrderStorage().currentOrder.getBread().type;
		double oldBreadPrice = mainFrame.getController().getOrderStorage().currentOrder.getBread().price;

		setSize(MainFrame.getGlobalWidth() - 6, MainFrame.getGlobalHeight() - 48);
		setLayout(null);

		JLabel lblWelcomeToSandwich = new JLabel("Welcome to Sandwich Order System - Preview");
		lblWelcomeToSandwich.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeToSandwich.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblWelcomeToSandwich.setBounds(0, 13, 634, 25);
		add(lblWelcomeToSandwich);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 51, 634, 1);
		add(separator);

		JLabel welcomeLabel = new JLabel("This is a preview of your selection. Please confirm your order.");
		welcomeLabel.setBounds(12, 65, 600, 16);
		add(welcomeLabel);

		JLabel lblBreadType = new JLabel("Bread Type:");
		lblBreadType.setBounds(12, 111, 97, 16);
		add(lblBreadType);

		type = new JTextField(oldBreadType + " - " + String.format("$%.2f", oldBreadPrice));
		type.setEditable(false);
		type.setBounds(121, 108, 200, 22);
		add(type);

		JLabel lblIngredients = new JLabel("Ingredient(s):");
		lblIngredients.setBounds(12, 140, 97, 16);
		add(lblIngredients);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 169, 612, 175);
		add(scrollPane);

		DefaultListModel<String> model = new DefaultListModel<String>();
		for (Ingredient ingredient : mainFrame.getController().getOrderStorage().currentOrder.ingredients)
			model.addElement(ingredient.name + " - " + String.format("$%.2f", ingredient.price));

		JList<String> list = new JList<String>(model);
		list.setEnabled(false);
		scrollPane.setViewportView(list);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 380, 634, 1);
		add(separator_1);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(e -> mainFrame.showOrderScreen(1));
		btnBack.setBounds(418, 394, 97, 25);
		add(btnBack);

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(e -> mainFrame.getController().getPreviewOrderScreenController().submitOrder());
		btnSubmit.setBounds(527, 394, 97, 25);
		add(btnSubmit);

		JLabel lblSubTotal = new JLabel("Sub Total:");
		lblSubTotal.setBounds(12, 351, 97, 16);
		add(lblSubTotal);

		JLabel lblTesting = new JLabel(String.format("$%.2f", mainFrame.getController().getOrderStorage().currentOrder.calculatePrice()));
		lblTesting.setBounds(121, 351, 97, 16);
		add(lblTesting);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(0, 94, 634, 1);
		add(separator_2);
	}
}
