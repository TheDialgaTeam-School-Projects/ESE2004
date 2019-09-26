package system;

import java.util.Vector;

public class StringHelper
{
	/**
	 * Returns a value indicating whether a specified substring occurs within
	 * this string.
	 *
	 * @param fullString The original string.
	 * @param value The char to seek.
	 * @return true if the value parameter occurs within this string, or if
	 * value is the empty string (""); otherwise, false.
	 * @throws Exception ArgumentNullException.
	 */
	public static boolean contains(String fullString, char value) throws Exception
	{
		if (String.valueOf(value) == null)
			throw new Exception("ArgumentNullException: value is null.");
		else
			return fullString.contains(String.valueOf(value));
	}

	/**
	 * Returns a value indicating whether a specified substring occurs within
	 * this string.
	 *
	 * @param fullString The original string.
	 * @param value The string to seek.
	 * @return true if the value parameter occurs within this string, or if
	 * value is the empty string (""); otherwise, false.
	 * @throws Exception ArgumentNullException.
	 */
	public static boolean contains(String fullString, String value) throws Exception
	{
		if (String.valueOf(value) == null)
			throw new Exception("ArgumentNullException: value is null.");
		else if (value.isEmpty())
			return true;
		else
			return fullString.contains(String.valueOf(value));
	}

	/**
	 * Splits a string into substrings that are based on the characters in an
	 * array and gets the Char object at a specified position in the current
	 * String object.
	 *
	 * @param fullString Represents text as a sequence of UTF-16 code units.
	 * @param valueIndex Gets the Char object at a specified position in the
	 * current String object.
	 * @throws Exception ArgumentNullException;
	 */
	public static String getSplit(String fullString, int valueIndex) throws Exception
	{
		int expectedSplitCount = splitCount(fullString, '|');

		if (expectedSplitCount == 1)
			return fullString;
		else if (valueIndex < expectedSplitCount)
			return split(fullString, '|')[valueIndex];
		else
			return split(fullString, '|')[expectedSplitCount - 1];
	}

	/**
	 * Splits a string into substrings that are based on the characters in an
	 * array and gets the Char object at a specified position in the current
	 * String object.
	 *
	 * @param fullString Represents text as a sequence of UTF-16 code units.
	 * @param valueIndex Gets the Char object at a specified position in the
	 * current String object.
	 * @param separator A character that delimits the substrings in this string,
	 * an empty array that contains no delimiters, or null.
	 * @throws Exception ArgumentNullException;
	 */
	public static String getSplit(String fullString, int valueIndex, char separator) throws Exception
	{
		int expectedSplitCount = splitCount(fullString, separator);

		if (expectedSplitCount == 1)
			return fullString;
		else if (valueIndex < expectedSplitCount)
			return split(fullString, separator)[valueIndex];
		else
			return split(fullString, separator)[expectedSplitCount - 1];
	}

	/**
	 * Splits a string into substrings that are based on the characters in an
	 * array.
	 *
	 * @param fullString The original string.
	 * @param separator A character array that delimits the substrings in this
	 * string, an empty array that contains no delimiters, or null.
	 * @return An array whose elements contain the substrings from this instance
	 * that are delimited by one or more characters in separator. For more
	 * information, see the Remarks section.
	 */
	public static String[] split(String fullString, char separator)
	{
		Vector<String> item = new Vector<String>();

		try
		{
			if (contains(fullString, separator))
			{
				String buffer = "";

				for (int i = 0; i < fullString.length(); i++)
					if (fullString.charAt(i) == separator)
					{
						item.addElement(buffer);
						buffer = "";
					}
					else
						buffer += fullString.charAt(i);

				item.addElement(buffer);
				String[] Result = new String[item.size()];
				item.copyInto(Result);

				return Result;
			}
			else
				return null;
		}
		catch (Exception ex)
		{
			return null;
		}
	}

	/**
	 * Splits a string into substrings that are based on the characters in an
	 * array and gets a 32-bit integer that represents the total number of
	 * elements in all the dimensions of the System.Array.
	 *
	 * @param fullString The full string to count the number of index.
	 * @throws Exception ArgumentNullException;
	 */
	public static int splitCount(String fullString) throws Exception
	{
		return contains(fullString, '|') ? split(fullString, '|').length : 1;
	}

	/**
	 * Splits a string into substrings that are based on the characters in an
	 * array and gets a 32-bit integer that represents the total number of
	 * elements in all the dimensions of the System.Array.
	 *
	 * @param fullString The full string to count the number of index.
	 * @param separator The separator to split the string.
	 * @throws Exception ArgumentNullException;
	 */
	public static int splitCount(String fullString, char separator) throws Exception
	{
		return contains(fullString, separator) ? split(fullString, separator).length : 1;
	}

	/**
	 * Convert byte[] to string.
	 *
	 * @param byteArray Byte array to convert.
	 */
	public static String toString(byte[] byteArray)
	{
		String Result = "";

		for (byte element : byteArray)
			Result += (char) element;

		return Result;
	}
}
