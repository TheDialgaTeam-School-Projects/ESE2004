package model;

import java.util.Base64;

import system.StringHelper;
import system.collections.generic.List;

public class Order
{
	/**
	 * Get/Set Bread ingredients.
	 */
	public List<Ingredient> ingredients = new List<Ingredient>();

	private Account account;

	private Bread bread;

	public Order(Account account, Bread bread)
	{
		this.account = account;
		this.bread = bread;
	}

	public Order(String order)
	{
		String[] result = StringHelper.split(order, '|');

		account = new Account(StringHelper.toString(Base64.getDecoder().decode(result[0])));
		bread = new Bread(result[1], Double.valueOf(result[2]), result[3]);

		for (int i = 4; i < result.length; i += 3)
			ingredients.add(new Ingredient(result[i], Double.valueOf(result[i + 1]), result[i + 2]));
	}

	/**
	 * Get the total price of the current order.
	 */
	public double calculatePrice()
	{
		double result = bread.price;

		for (Ingredient ingredient : ingredients)
			result += ingredient.price;

		return result;
	}

	/**
	 * Get Account.
	 *
	 * @return
	 */
	public Account getAccount()
	{
		return account;
	}

	/**
	 * Get Bread.
	 */
	public Bread getBread()
	{
		return bread;
	}

	@Override
	public String toString()
	{
		String result = account.toString() + "|" + bread.toString();

		for (Ingredient ingredient : ingredients)
			result += "|" + ingredient.toString();

		return result;
	}
}
