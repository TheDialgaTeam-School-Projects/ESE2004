package model;

import java.util.Base64;

import controller.MainFrame;
import system.ExceptionHelper;
import system.StringHelper;
import system.collections.generic.List;
import system.io.StreamReader;
import system.io.StreamWriter;

public class AccountStorage
{
	private List<Account> accounts = new List<Account>();
	private Account currentSession;

	public AccountStorage()
	{
		loadAccount();

		if (!checkUsernameExist("admin"))
		{
			registerAccount("admin", "password");
			getAccountByUsername("admin").accountType = 2;
			updateAccount();
		}
	}

	/**
	 * Add the follow account into the database.
	 *
	 * @param username Username to add.
	 * @param password Password to add.
	 * @param type Account type to add.
	 */
	public boolean addAccount(String username, String password, int type)
	{
		if (!checkUsernameExist(username))
		{
			Account account = new Account(username, password);
			account.accountType = type;

			accounts.add(account);
			updateAccount();

			return true;
		}
		else
			return false;
	}

	/**
	 * Change the password for the following account into the database.
	 *
	 * @param username Username to change.
	 * @param oldPassword Old password to change.
	 * @param newPassword New password to change.
	 */
	public boolean changeAccountPassword(String username, String oldPassword, String newPassword)
	{
		for (Account account : accounts)
			if (account.username.contentEquals(username) && account.password.contentEquals(oldPassword))
			{
				account.password = newPassword;
				updateAccount();

				return true;
			}

		return false;
	}

	/**
	 * Edit the account in the database.
	 *
	 * @param oldUsername Username to edit.
	 * @param username Username to edit.
	 * @param password Password to edit.
	 * @param type Account type to edit.
	 */
	public boolean editAccount(String oldUsername, String username, String password, int type)
	{
		if (oldUsername.contentEquals(username) || !checkUsernameExist(oldUsername))
		{
			Account account = getAccountByUsername(oldUsername);
			account.username = username;
			account.password = password;
			account.accountType = type;

			updateAccount();

			return true;
		}
		else
			return false;
	}

	/**
	 * Get Account by username.
	 *
	 * @param username Username to get.
	 */
	public Account getAccountByUsername(String username)
	{
		for (Account account : accounts)
			if (account.username.contentEquals(username))
				return account;

		return null;
	}

	/**
	 * Get all available accounts from the database.
	 */
	public List<Account> getAccounts()
	{
		return accounts;
	}

	/**
	 * Get Current session.
	 */
	public Account getCurrentSession()
	{
		return currentSession;
	}

	/**
	 * Login account and add into the current session.
	 *
	 * @param username Username to login.
	 * @param password Password to login.
	 */
	public boolean loginAccount(String username, String password)
	{
		if (username.contentEquals("guest") && password.contentEquals("password"))
		{
			currentSession = new Account(username, password);
			return true;
		}
		else
		{
			for (Account account : accounts)
				if (account.username.contentEquals(username) && account.password.contentEquals(password))
				{
					currentSession = account;
					return true;
				}

			return false;
		}
	}

	/**
	 * Logout account and set current session to null.
	 */
	public void logoutAccount()
	{
		currentSession = null;
	}

	/**
	 * Add account into the database.
	 *
	 * @param username Username to add.
	 * @param password Password to add.
	 */
	public boolean registerAccount(String username, String password)
	{
		if (!checkUsernameExist(username))
		{
			Account account = new Account(username, password);
			account.accountType = 1;

			accounts.add(account);
			updateAccount();

			return true;
		}
		else
			return false;
	}

	public void removeAccount(int selectedIndex)
	{
		try
		{
			accounts.removeAt(selectedIndex);
		}
		catch (Exception ex)
		{
			ExceptionHelper.catchError(ex);
		}
	}

	/**
	 * Check if the username exist in the database.
	 *
	 * @param username Username to check.
	 */
	private boolean checkUsernameExist(String username)
	{
		if (username.contentEquals("guest"))
			return true;
		else
		{
			for (Account account : accounts)
				if (account.username.contentEquals(username))
					return true;

			return false;
		}
	}

	/**
	 * Load all the account that is in the database.
	 */
	private void loadAccount()
	{
		try
		{
			StreamReader reader = new StreamReader(MainFrame.getWorkingDirectory() + "\\Accounts.dat");

			while (reader.peek() > -1)
			{
				String EncodedAccount = reader.readLine();

				if (!EncodedAccount.isEmpty())
					accounts.add(new Account(StringHelper.toString(Base64.getDecoder().decode(EncodedAccount))));
			}
		}
		catch (Exception ex)
		{}
	}

	/**
	 * Update the account database.
	 */
	private void updateAccount()
	{
		try
		{
			StreamWriter streamWriter = new StreamWriter(MainFrame.getWorkingDirectory() + "\\Accounts.dat");
			streamWriter.autoFlush = true;

			for (Account account : accounts)
				streamWriter.writeLine(account.toString());
		}
		catch (Exception ex)
		{
			ExceptionHelper.catchError(ex);
		}
	}
}
