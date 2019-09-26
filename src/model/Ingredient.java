package model;

import system.ExceptionHelper;
import system.StringHelper;

public class Ingredient
{
	/**
	 * Get/Set Ingredient description.
	 */
	public String description;

	/**
	 * Get/Set Ingredient name.
	 */
	public String name;

	/**
	 * Get/Set Ingredient price.
	 */
	public double price;

	public Ingredient(String result)
	{
		try
		{
			name = StringHelper.getSplit(result, 0);
			price = Double.valueOf(StringHelper.getSplit(result, 1));
			description = StringHelper.getSplit(result, 2);
		}
		catch (Exception ex)
		{
			ExceptionHelper.catchError(ex);
		}
	}

	public Ingredient(String name, double price, String description)
	{
		this.name = name;
		this.price = price;
		this.description = description;
	}

	@Override
	public String toString()
	{
		return name + "|" + price + "|" + description;
	}
}
