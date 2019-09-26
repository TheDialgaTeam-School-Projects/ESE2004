package model;

import system.ExceptionHelper;
import system.StringHelper;

public class Bread
{
	/**
	 * Get/Set Bread description.
	 */
	public String description;

	/**
	 * Get/Set Bread price.
	 */
	public double price;

	/**
	 * Get/Set Bread type.
	 */
	public String type;

	public Bread(String bread)
	{
		try
		{
			type = StringHelper.getSplit(bread, 0);
			price = Double.valueOf(StringHelper.getSplit(bread, 1));
			description = StringHelper.getSplit(bread, 2);
		}
		catch (Exception ex)
		{
			ExceptionHelper.catchError(ex);
		}
	}

	public Bread(String type, double price, String description)
	{
		this.type = type;
		this.price = price;
		this.description = description;
	}

	@Override
	public String toString()
	{
		return type + "|" + price + "|" + description;
	}
}
