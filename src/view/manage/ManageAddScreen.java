package view.manage;

import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import controller.MainFrame;

@SuppressWarnings("serial")
public class ManageAddScreen extends JPanel
{
	private JPasswordField accountPassword;
	private JTextField accountType;
	private JComboBox<String> breadType;
	private JButton btnAdd;
	private JTextArea description;
	private JTextField name;
	private JTextField price;

	/**
	 * Initialize manage add screen.
	 *
	 * @param mainFrame
	 *            Mainframe.
	 * @param type
	 *            0 = Bread, 1 = Ingredient, 2 = Account.
	 */
	public ManageAddScreen(MainFrame mainFrame, int type)
	{
		String title = "Add Bread Screen";
		String mainScreenDescription = "Please fill in all the infomation needed for the bread type you wish to add.";
		String field1 = "Bread Type*:";
		String field2 = "Bread Price*:";
		String field3 = "Bread Description:";

		if (type == 0)
		{
			title = "Add Bread Screen";
			mainScreenDescription = "Please fill in all the infomation needed for the bread type you wish to add.";
			field1 = "Bread Type*:";
			field2 = "Bread Price*:";
			field3 = "Bread Description:";
		}
		else if (type == 1)
		{
			title = "Add Ingredient Screen";
			mainScreenDescription = "Please fill in all the infomation needed for the ingredient you wish to add.";
			field1 = "Ingredient Name*:";
			field2 = "Ingredient Price*:";
			field3 = "Ingredient Description:";
		}
		else if (type == 2)
		{
			title = "Add Account Screen";
			mainScreenDescription = "Please fill in all the information needed for the acccount you wish to add,";
			field1 = "Username*:";
			field2 = "Password*:";
			field3 = "Account Type*:";
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

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 94, 634, 1);
		add(separator);

		JLabel lblBreadType = new JLabel(field1);
		lblBreadType.setBounds(12, 111, 125, 16);
		add(lblBreadType);

		if (type == 0)
		{
			DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
			model.addElement("9-Grain Wheat");
			model.addElement("Hearty Italian");
			model.addElement("9-Grain Honey Oat");
			model.addElement("Italian");
			model.addElement("Italian Herbs & Cheese");
			model.addElement("Flatbread");
			model.addElement("Jalapeno Cheese");
			model.addElement("Monterey Cheddar");
			model.addElement("Parmesan Oregano");
			model.addElement("Roasted Garlic");
			model.addElement("Rosemary & Sea Salt");
			breadType = new JComboBox<String>(model);
			breadType.setBounds(149, 108, 200, 22);
			add(breadType);
		}
		else if (type == 1 || type == 2)
		{
			name = new JTextField();
			name.getDocument().addDocumentListener(new DocumentListener()
			{
				@Override
				public void changedUpdate(DocumentEvent e)
				{
					validateInput();
				}

				@Override
				public void insertUpdate(DocumentEvent e)
				{
					validateInput();
				}

				@Override
				public void removeUpdate(DocumentEvent e)
				{
					validateInput();
				}

				public void validateInput()
				{
					if (type == 1)
						if (name.getText().isEmpty() || price.getText().isEmpty())
							btnAdd.setEnabled(false);
						else
							try
							{
								Double.valueOf(price.getText());
								btnAdd.setEnabled(true);
							}
							catch (Exception ex)
							{
								btnAdd.setEnabled(false);
							}
					else if (type == 2)
						if (name.getText().isEmpty() || accountPassword.getPassword().length == 0 || accountType.getText().isEmpty())
							btnAdd.setEnabled(false);
						else
							try
							{
								if (Integer.valueOf(accountType.getText()) > 2)
									btnAdd.setEnabled(false);
								else
									btnAdd.setEnabled(true);
							}
							catch (Exception ex)
							{
								btnAdd.setEnabled(false);
							}
				}
			});
			name.setBounds(149, 108, 200, 22);
			add(name);
		}

		JLabel lblBreadPrice = new JLabel(field2);
		lblBreadPrice.setBounds(12, 140, 125, 16);
		add(lblBreadPrice);

		if (type == 0 || type == 1)
		{
			price = new JTextField();
			price.getDocument().addDocumentListener(new DocumentListener()
			{
				@Override
				public void changedUpdate(DocumentEvent e)
				{
					validateInput();
				}

				@Override
				public void insertUpdate(DocumentEvent e)
				{
					validateInput();
				}

				@Override
				public void removeUpdate(DocumentEvent e)
				{
					validateInput();
				}

				public void validateInput()
				{
					if (type == 0)
						if (breadType.getSelectedIndex() < 0 || price.getText().isEmpty())
							btnAdd.setEnabled(false);
						else
							try
							{
								Double.valueOf(price.getText());
								btnAdd.setEnabled(true);
							}
							catch (Exception ex)
							{
								btnAdd.setEnabled(false);
							}
					else if (type == 1)
						if (name.getText().isEmpty() || price.getText().isEmpty())
							btnAdd.setEnabled(false);
						else
							try
							{
								Double.valueOf(price.getText());
								btnAdd.setEnabled(true);
							}
							catch (Exception ex)
							{
								btnAdd.setEnabled(false);
							}
				}
			});
			price.setBounds(149, 137, 200, 22);
			add(price);
		}
		else if (type == 2)
		{
			accountPassword = new JPasswordField();
			accountPassword.getDocument().addDocumentListener(new DocumentListener()
			{
				@Override
				public void changedUpdate(DocumentEvent e)
				{
					validateInput();
				}

				@Override
				public void insertUpdate(DocumentEvent e)
				{
					validateInput();
				}

				@Override
				public void removeUpdate(DocumentEvent e)
				{
					validateInput();
				}

				public void validateInput()
				{
					if (name.getText().isEmpty() || accountPassword.getPassword().length == 0 || accountType.getText().isEmpty())
						btnAdd.setEnabled(false);
					else
						try
						{
							if (Integer.valueOf(accountType.getText()) > 2)
								btnAdd.setEnabled(false);
							else
								btnAdd.setEnabled(true);
						}
						catch (Exception ex)
						{
							btnAdd.setEnabled(false);
						}
				}
			});
			accountPassword.setBounds(149, 137, 200, 19);
			add(accountPassword);
		}

		JLabel lblBreadDescription = new JLabel(field3);
		lblBreadDescription.setBounds(12, 169, 125, 16);
		add(lblBreadDescription);

		if (type == 0 || type == 1)
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(149, 172, 463, 150);
			add(scrollPane);

			description = new JTextArea();
			scrollPane.setViewportView(description);
		}
		else if (type == 2)
		{
			accountType = new JTextField();
			accountType.getDocument().addDocumentListener(new DocumentListener()
			{
				@Override
				public void changedUpdate(DocumentEvent e)
				{
					validateInput();
				}

				@Override
				public void insertUpdate(DocumentEvent e)
				{
					validateInput();
				}

				@Override
				public void removeUpdate(DocumentEvent e)
				{
					validateInput();
				}

				public void validateInput()
				{
					if (name.getText().isEmpty() || accountPassword.getPassword().length == 0 || accountType.getText().isEmpty())
						btnAdd.setEnabled(false);
					else
						try
						{
							Double.valueOf(accountType.getText());
							btnAdd.setEnabled(true);
						}
						catch (Exception ex)
						{
							btnAdd.setEnabled(false);
						}
				}
			});
			accountType.setBounds(149, 166, 200, 22);
			add(accountType);
		}

		JLabel lblFieldsThatAre = new JLabel("Fields that are marked with * are mandatory.");
		lblFieldsThatAre.setBounds(12, 351, 600, 16);
		add(lblFieldsThatAre);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(0, 380, 634, 1);
		add(separator_2);

		btnAdd = new JButton("Add");
		btnAdd.addActionListener(e ->
		{
			if (type == 0)
				mainFrame.getController().getAddScreenController().addBread(String.valueOf(breadType.getSelectedItem()), Double.valueOf(price.getText()), description.getText());
			else if (type == 1)
				mainFrame.getController().getAddScreenController().addIngredient(name.getText(), Double.valueOf(price.getText()), description.getText());
			else if (type == 2)
				mainFrame.getController().getAddScreenController().addAccount(name.getText(), String.valueOf(accountPassword.getPassword()), Integer.valueOf(accountType.getText()));
		});
		btnAdd.setEnabled(false);
		btnAdd.setBounds(525, 394, 97, 25);
		add(btnAdd);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(e -> mainFrame.showManageScreen(type));
		btnBack.setBounds(416, 394, 97, 25);
		add(btnBack);
	}
}
