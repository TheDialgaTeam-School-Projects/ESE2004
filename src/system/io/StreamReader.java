package system.io;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class StreamReader
{
	private BufferedInputStream bufferedInputStream;

	/**
	 * Initializes a new instance of the StreamReader class for the specified
	 * file name.
	 *
	 * @param path The complete file path to be read.
	 * @throws Exception ArgumentException, FileNotFoundException.
	 */
	public StreamReader(String path) throws Exception
	{
		if (path.isEmpty())
			throw new Exception("ArgumentException: path is an empty string or path contains the name of a system device (com1, com2, and so on).");
		else
			bufferedInputStream = new BufferedInputStream(new FileInputStream(path));
	}

	/**
	 * Closes the StreamReader object and the underlying stream, and releases
	 * any system resources associated with the reader.
	 */
	public void close() throws IOException
	{
		bufferedInputStream.close();
	}

	/**
	 * Returns the next available character but does not consume it.
	 *
	 * @return An integer representing the next character to be read, or -1 if
	 * there are no characters to be read or if the stream does not support
	 * seeking.
	 */
	public int peek() throws IOException
	{
		int Result;

		if (bufferedInputStream.markSupported())
			bufferedInputStream.mark(1);
		else
			return -1;

		Result = bufferedInputStream.read();
		bufferedInputStream.reset();

		return Result;
	}

	/**
	 * Reads the next character from the input stream and advances the character
	 * position by one character.
	 *
	 * @return The next character from the input stream represented as an Int32
	 * object, or -1 if no more characters are available.
	 */
	public int read() throws IOException
	{
		return bufferedInputStream.read();
	}

	/**
	 * Reads a line of characters from the current stream and returns the data
	 * as a string.
	 *
	 * @return The next line from the input stream, or null if the end of the
	 * input stream is reached.
	 */
	public String readLine() throws IOException
	{
		String Result = "";
		int byteChar;

		do
		{
			byteChar = bufferedInputStream.read();

			if (byteChar != '\r' && byteChar != '\n')
				Result += (char) byteChar;
		} while (byteChar != '\r' && byteChar != '\n');

		return Result;
	}

	/**
	 * Reads all characters from the current position to the end of the stream.
	 *
	 * @return The rest of the stream as a string, from the current position to
	 * the end. If the current position is at the end of the stream, returns an
	 * empty string ("").
	 */
	public String readToEnd() throws IOException
	{
		String Result = "";
		int byteChar;

		do
		{
			byteChar = bufferedInputStream.read();

			if (byteChar != -1)
				Result += (char) byteChar;
		} while (byteChar != -1);

		return Result;
	}
}
