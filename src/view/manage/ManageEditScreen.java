package view.manage;

import java.awt.Font;

import javax.swing.JButton;
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
import system.ExceptionHelper;

@SuppressWarnings("serial")
public class ManageEditScreen extends JPanel
{
	private JPasswordField accountPassword;
	private JTextField accountType;
	private JButton btnAdd;
	private JTextArea description;
	private String field1txt = "";
	private String field2txt = "";
	private String field3txt = "";
	private JTextField name;
	private JTextField price;

	/**
	 * Initialize manage edit screen.
	 *
	 * @param mainFrame Mainframe.
	 * @param type 0 = Bread, 1 = Ingredient.
	 * @param index Selected Index.
	 */
	public ManageEditScreen(MainFrame mainFrame, int type, int index)
	{
		String title = "Edit Bread Screen";
		String mainScreenDescription = "Please fill in all the infomation needed for the bread type you wish to edit.";
		String field1 = "Bread Type*:";
		String field2 = "Bread Price*:";
		String field3 = "Bread Description:";

		if (type == 0)
		{
			title = "Edit Bread Screen";
			mainScreenDescription = "Please fill in all the infomation needed for the bread type you wish to edit.";
			field1 = "Bread Type*:";
			field2 = "Bread Price*:";
			field3 = "Bread Description:";

			try
			{
				field1txt = mainFrame.getController().getOrderStorage().getAvailableBreads().getItem(index).type;
				field2txt = String.valueOf(mainFrame.getController().getOrderStorage().getAvailableBreads().getItem(index).price);
				field3txt = mainFrame.getController().getOrderStorage().getAvailableBreads().getItem(index).description;
			}
			catch (Exception ex)
			{
				ExceptionHelper.catchError(ex);
			}
		}
		else if (type == 1)
		{
			title = "Edit Ingredient Screen";
			mainScreenDescription = "Please fill in all the infomation needed for the ingredient you wish to edit.";
			field1 = "Ingredient Name*:";
			field2 = "Ingredient Price*:";
			field3 = "Ingredient Description:";

			try
			{
				field1txt = mainFrame.getController().getOrderStorage().getAvailableIngredients().getItem(index).name;
				field2txt = String.valueOf(mainFrame.getController().getOrderStorage().getAvailableIngredients().getItem(index).price);
				field3txt = mainFrame.getController().getOrderStorage().getAvailableIngredients().getItem(index).description;
			}
			catch (Exception ex)
			{
				ExceptionHelper.catchError(ex);
			}
		}
		else if (type == 2)
		{
			title = "Edit Account Screen";
			mainScreenDescription = "Please fill in all the information needed for the account you wish to edit.";
			field1 = "Username*:";
			field2 = "Password*:";
			field3 = "Account Type*:";

			try
			{
				field1txt = mainFrame.getController().getAccountStorage().getAccounts().getItem(index).username;
				field2txt = mainFrame.getController().getAccountStorage().getAccounts().getItem(index).password;
				field3txt = String.valueOf(mainFrame.getController().getAccountStorage().getAccounts().getItem(index).accountType);
			}
			catch (Exception ex)
			{
				ExceptionHelper.catchError(ex);
			}
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

		name = new JTextField(field1txt);
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
				if (type == 0 || type == 1)
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

		JLabel lblBreadPrice = new JLabel(field2);
		lblBreadPrice.setBounds(12, 140, 125, 16);
		add(lblBreadPrice);

		if (type == 0 || type == 1)
		{
			price = new JTextField(field2txt);
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
			accountPassword = new JPasswordField(field2txt);
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
					if (name.getText().isEmpty() || accountPassword.getPassword().length == 0 || accountType.getText().isEmpty() || (name.getText().contentEquals(field1txt) && String.valueOf(accountPassword.getPassword()).contentEquals(field2txt) && accountType.getText().contentEquals(field3txt)))
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

			description = new JTextArea(field3txt);
			description.getDocument().addDocumentListener(new DocumentListener()
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
					if (name.getText().isEmpty() || price.getText().isEmpty() || (name.getText().contentEquals(field1txt) && price.getText().contentEquals(field2txt) && description.getText().contentEquals(field3txt)))
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
			scrollPane.setViewportView(description);
		}
		else if (type == 2)
		{
			accountType = new JTextField(field3txt);
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
					if (name.getText().isEmpty() || accountPassword.getPassword().length == 0 || accountType.getText().isEmpty() || (name.getText().contentEquals(field1txt) && String.valueOf(accountPassword.getPassword()).contentEquals(field2txt) && accountType.getText().contentEquals(field3txt)))
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
			accountType.setBounds(149, 166, 200, 22);
			add(accountType);
		}

		JLabel lblFieldsThatAre = new JLabel("Fields that are marked with * are mandatory.");
		lblFieldsThatAre.setBounds(12, 351, 600, 16);
		add(lblFieldsThatAre);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(0, 380, 634, 1);
		add(separator_2);

		btnAdd = new JButton("Edit");
		btnAdd.addActionListener(e ->
		{
			if (type == 0)
				mainFrame.getController().getManageEditScreenController().editBread(field1txt, name.getText(), Double.valueOf(price.getText()), description.getText());
			else if (type == 1)
				mainFrame.getController().getManageEditScreenController().editIngredient(field1txt, name.getText(), Double.valueOf(price.getText()), description.getText());
			else if (type == 2)
				mainFrame.getController().getManageEditScreenController().editAccount(field1txt, name.getText(), String.valueOf(accountPassword.getPassword()), Integer.valueOf(accountType.getText()));
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
