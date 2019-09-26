package model;

import controller.MainFrame;
import system.ExceptionHelper;
import system.collections.generic.List;
import system.io.StreamReader;
import system.io.StreamWriter;

public class OrderStorage
{
	/**
	 * Get/Set Current order.
	 */
	public Order currentOrder;

	/**
	 * Get/Set List of orders made.
	 */
	public List<Order> orders = new List<Order>();

	private List<Bread> breads = new List<Bread>();

	private List<Ingredient> ingredients = new List<Ingredient>();

	public OrderStorage()
	{
		loadOrderHistory();
		loadAvailableBread();
		loadAvailableIngredient();
	}

	/**
	 * Add bread into the availability list.
	 *
	 * @param type Type of bread to add.
	 * @param price Price of bread to add.
	 * @param description Description of bread to add.
	 */
	public boolean addAvailableBread(String type, Double price, String description)
	{
		if (checkAvailableBreadExist(type))
			return false;
		else
		{
			breads.add(new Bread(type, price, description));
			updateAvailableBread();
			return true;
		}
	}

	/**
	 * Add ingredient into the availability list.
	 *
	 * @param name Type of ingredient to add.
	 * @param price Price of ingredient to add.
	 * @param description Description of ingredient to add.
	 */
	public boolean addAvailableIngredient(String name, Double price, String description)
	{
		if (checkAvailableIngredientExist(name))
			return false;
		else
		{
			ingredients.add(new Ingredient(name, price, description));
			updateAvailableIngredient();
			return true;
		}
	}

	/**
	 * Edit the bread in the availability list.
	 *
	 * @param oldType Old bread type to edit.
	 * @param type New bread type to edit.
	 * @param price New bread price to edit.
	 * @param description New bread description to edit.
	 */
	public boolean editAvailableBread(String oldType, String type, Double price, String description)
	{
		if (!oldType.contentEquals(type) && checkAvailableBreadExist(type))
			return false;
		else
		{
			for (Bread bread : breads)
				if (bread.type.contentEquals(oldType))
				{
					bread.type = type;
					bread.price = price;
					bread.description = description;
				}

			updateAvailableBread();
			return true;
		}
	}

	/**
	 * Edit the ingredient in the availability list.
	 *
	 * @param oldName Old ingredient name to edit.
	 * @param name New ingredient name to edit.
	 * @param price New ingredient price to edit.
	 * @param description New ingredient description to edit.
	 */
	public boolean editAvailableIngredient(String oldName, String name, Double price, String description)
	{
		if (!oldName.contentEquals(name) && checkAvailableBreadExist(name))
			return false;
		else
		{
			for (Ingredient ingredient : ingredients)
				if (ingredient.name.contentEquals(oldName))
				{
					ingredient.name = name;
					ingredient.price = price;
					ingredient.description = description;
				}

			updateAvailableIngredient();
			return true;
		}
	}

	/**
	 * Get Available bread by index.
	 *
	 * @param selectedIndex Index of bread to get.
	 */
	public Bread getAvailableBread(int selectedIndex)
	{
		try
		{
			return breads.getItem(selectedIndex);
		}
		catch (Exception ex)
		{
			return null;
		}
	}

	/**
	 * Get Available bread by type.
	 *
	 * @param type Type of bread to get.
	 */
	public Bread getAvailableBread(String type)
	{
		for (Bread bread : breads)
			if (bread.type.contentEquals(type))
				return bread;

		return null;
	}

	/**
	 * Get All available bread that is for sale.
	 */
	public List<Bread> getAvailableBreads()
	{
		return breads;
	}

	/**
	 * Get Available ingredient by index.
	 *
	 * @param selectedIndex Index of ingredient to get.
	 */
	public Ingredient getAvailableIngredient(int selectedIndex)
	{
		try
		{
			return ingredients.getItem(selectedIndex);
		}
		catch (Exception ex)
		{
			return null;
		}
	}

	/**
	 * Get Available ingredient by type.
	 *
	 * @param name Name of ingredient to get.
	 */
	public Ingredient getAvailableIngredient(String name)
	{
		for (Ingredient ingredient : ingredients)
			if (ingredient.name.contentEquals(name))
				return ingredient;

		return null;
	}

	/**
	 * Get All available ingredient that is for sale.
	 */
	public List<Ingredient> getAvailableIngredients()
	{
		return ingredients;
	}

	/**
	 * Remove available bread by index.
	 */
	public void removeAvailableBread(int selectedIndex)
	{
		try
		{
			breads.removeAt(selectedIndex);
			updateAvailableBread();
		}
		catch (Exception ex)
		{}
	}

	/**
	 * Remove available ingredient by index.
	 */
	public void removeAvailableIngredient(int selectedIndex)
	{
		try
		{
			ingredients.removeAt(selectedIndex);
			updateAvailableIngredient();
		}
		catch (Exception ex)
		{}
	}

	/**
	 * Remove selected order history from the database.
	 *
	 * @param selectedIndex Selected index to remove.
	 * @param currentSession Current session.
	 */
	public void removeOrderHistory(int selectedIndex, Account currentSession)
	{
		int realIndex = 0;

		for (int i = 0; i < orders.getCount(); i++)
			try
			{
				if (orders.getItem(i).getAccount().username.contentEquals(currentSession.username))
					if (selectedIndex == realIndex)
					{
						orders.removeAt(i);
						break;
					}
					else
						realIndex++;
			}
			catch (Exception ex)
			{
				ExceptionHelper.catchError(ex);
			}

		updateOrderHistory();
	}

	/**
	 * Submit order and add into database.
	 */
	public void submitOrder()
	{
		orders.add(currentOrder);
		currentOrder = null;
		updateOrderHistory();
	}

	/**
	 * Check if the bread type exist on the availability list.
	 *
	 * @param type Type of bread to check.
	 */
	private boolean checkAvailableBreadExist(String type)
	{
		for (Bread bread : breads)
			if (bread.type.contentEquals(type))
				return true;

		return false;
	}

	/**
	 * Check if the ingredient exist on the availability list.
	 *
	 * @param name name of ingredient to check.
	 */
	private boolean checkAvailableIngredientExist(String name)
	{
		for (Ingredient ingredient : ingredients)
			if (ingredient.name.contentEquals(name))
				return true;

		return false;
	}

	/**
	 * Load the available bread from the database.
	 */
	private void loadAvailableBread()
	{
		try
		{
			StreamReader reader = new StreamReader(MainFrame.getWorkingDirectory() + "\\Breads.dat");

			while (reader.peek() > -1)
			{
				String result = reader.readLine();

				if (!result.isEmpty())
					breads.add(new Bread(result));
			}
		}
		catch (Exception ex)
		{}
	}

	/**
	 * Load the available ingredient from the database.
	 */
	private void loadAvailableIngredient()
	{
		try
		{
			StreamReader reader = new StreamReader(MainFrame.getWorkingDirectory() + "\\Ingredients.dat");

			while (reader.peek() > -1)
			{
				String result = reader.readLine();

				if (!result.isEmpty())
					ingredients.add(new Ingredient(result));
			}
		}
		catch (Exception ex)
		{}
	}

	/**
	 * Load the order history from the database.
	 */
	private void loadOrderHistory()
	{
		try
		{
			StreamReader reader = new StreamReader(MainFrame.getWorkingDirectory() + "\\Orders.dat");

			while (reader.peek() > -1)
			{
				String result = reader.readLine();

				if (!result.isEmpty())
					orders.add(new Order(result));
			}
		}
		catch (Exception ex)
		{}
	}

	/**
	 * Update available bread database.
	 */
	private void updateAvailableBread()
	{
		try
		{
			StreamWriter streamWriter = new StreamWriter(MainFrame.getWorkingDirectory() + "\\Breads.dat");
			streamWriter.autoFlush = true;

			for (Bread bread : breads)
				streamWriter.writeLine(bread.toString());
		}
		catch (Exception ex)
		{
			ExceptionHelper.catchError(ex);
		}
	}

	/**
	 * Update available ingredient database.
	 */
	private void updateAvailableIngredient()
	{
		try
		{
			StreamWriter streamWriter = new StreamWriter(MainFrame.getWorkingDirectory() + "\\Ingredients.dat");
			streamWriter.autoFlush = true;

			for (Ingredient ingredient : ingredients)
				streamWriter.writeLine(ingredient.toString());
		}
		catch (Exception ex)
		{
			ExceptionHelper.catchError(ex);
		}
	}

	/**
	 * Update order history database.
	 */
	private void updateOrderHistory()
	{
		try
		{
			StreamWriter streamWriter = new StreamWriter(MainFrame.getWorkingDirectory() + "\\Orders.dat");
			streamWriter.autoFlush = true;

			for (Order order : orders)
				if (order.getAccount().accountType != 0)
					streamWriter.writeLine(order.toString());
		}
		catch (Exception ex)
		{
			ExceptionHelper.catchError(ex);
		}
	}
}
