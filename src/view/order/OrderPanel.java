package view.order;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;

import controller.MainFrame;
import model.Bread;
import model.Ingredient;
import system.collections.generic.List;

@SuppressWarnings("serial")
public class OrderPanel extends JPanel
{
	/**
	 * Initialize order screen.
	 *
	 * @param mainFrame Mainframe.
	 * @param type 0 = Bread order, 1 = Ingredient order.
	 */
	public OrderPanel(MainFrame mainFrame, int type)
	{
		/*
		 * Dimension: Every four +139 in height. Button: Each offset is +137.
		 */

		List<Bread> breads = mainFrame.getController().getOrderStorage().getAvailableBreads();
		List<Ingredient> ingredients = mainFrame.getController().getOrderStorage().getAvailableIngredients();
		int offsetX = 0;
		int offsetY = 0;

		if (type == 0)
			setPreferredSize(new Dimension(560, 150 + (int) (139 * Math.ceil((breads.getCount() - 4) / 4.0))));
		else if (type == 1)
			setPreferredSize(new Dimension(560, 150 + (int) (139 * Math.ceil((ingredients.getCount() - 4) / 4.0))));
		setLayout(null);

		if (type == 0)
			for (Bread bread : breads)
			{
				JButton button = new JButton(bread.type);
				button.setToolTipText(bread.type + " (" + String.format("$%.2f", bread.price) + ") " + bread.description);
				button.setBounds(12 + (137 * offsetX), 13 + (137 * offsetY), 125, 125);
				button.addActionListener(e -> mainFrame.getController().getOrderPanelController().selectBread(button.getText()));
				add(button);

				if (offsetX < 3)
					offsetX++;
				else
				{
					offsetX = 0;
					offsetY++;
				}
			}
		else
			for (Ingredient ingredient : ingredients)
			{
				JButton button = new JButton(ingredient.name);
				button.setToolTipText(ingredient.name + " (" + String.format("$%.2f", ingredient.price) + ") " + ingredient.description);
				button.setBounds(12 + (137 * offsetX), 13 + (137 * offsetY), 125, 125);
				button.addActionListener(e -> mainFrame.getController().getOrderPanelController().selectIngredient(button.getText()));
				add(button);

				if (offsetX < 3)
					offsetX++;
				else
				{
					offsetX = 0;
					offsetY++;
				}
			}
	}
}
