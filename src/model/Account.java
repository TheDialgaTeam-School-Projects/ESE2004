package model;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import system.ExceptionHelper;
import system.StringHelper;

public class Account
{
	/**
	 * Get/Set Account type. 0 = Guest, 1 = Registered user, 2 = Administrator.
	 */
	public int accountType = 0;

	/**
	 * Get/Set Account password.
	 */
	public String password;

	/**
	 * Get/Set Account username.
	 */
	public String username;

	public Account(String decodedAccount)
	{
		try
		{
			username = StringHelper.getSplit(decodedAccount, 0);
			password = StringHelper.getSplit(decodedAccount, 1);
			accountType = Integer.valueOf(StringHelper.getSplit(decodedAccount, 2));
		}
		catch (Exception ex)
		{
			ExceptionHelper.catchError(ex);
		}
	}

	public Account(String username, String password)
	{
		this.username = username;
		this.password = password;
	}

	@Override
	public String toString()
	{
		return Base64.getEncoder().encodeToString((username + "|" + password + "|" + accountType).getBytes(StandardCharsets.UTF_8));
	}
}
