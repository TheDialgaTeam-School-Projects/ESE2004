package system.io;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class StreamWriter
{
	/**
	 * Get/Set a value indicating whether the StreamWriter will flush its buffer
	 * to the underlying stream after every call to StreamWriter.Write.
	 */
	public boolean autoFlush;

	private BufferedOutputStream bufferedOutputStream;

	/**
	 * Initializes a new instance of the StreamWriter class for the specified
	 * file by using the default encoding and buffer size.
	 *
	 * @param path The complete file path to write to. path can be a file name.
	 * @throws Exception ArgumentException, PathTooLongException,
	 * DirectoryNotFoundException.
	 */
	public StreamWriter(String path) throws Exception
	{
		if (path.isEmpty())
			throw new Exception("ArgumentException: path is an empty string or path contains the name of a system device (com1, com2, and so on).");
		else if (path.length() > 248 + 260)
			throw new Exception("PathTooLongException: The specified path, file name, or both exceed the system-defined maximum length. For example, on Windows-based platforms, paths must not exceed 248 characters, and file names must not exceed 260 characters.");
		else
			bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(path, false));
	}

	/**
	 * Closes the current StreamWriter object and the underlying stream.
	 */
	public void close() throws IOException
	{
		bufferedOutputStream.close();
	}

	/**
	 * Clears all buffers for the current writer and causes any buffered data to
	 * be written to the underlying stream.
	 */
	public void flush() throws IOException
	{
		bufferedOutputStream.flush();
	}

	/**
	 * Writes a string to the stream.
	 *
	 * @param value The string to write to the stream. If value is null, nothing
	 * is written.
	 */
	public void write(String value) throws IOException
	{
		bufferedOutputStream.write(value.getBytes(StandardCharsets.UTF_8));

		if (autoFlush)
			bufferedOutputStream.flush();
	}

	/**
	 * Writes a string followed by a line terminator to the text string or
	 * stream.
	 *
	 * @param value The string to write. If value is null, only the line
	 * terminator is written.
	 */
	public void writeLine(String value) throws IOException
	{
		String result = value + System.lineSeparator();
		bufferedOutputStream.write(result.getBytes(StandardCharsets.UTF_8));

		if (autoFlush)
			bufferedOutputStream.flush();
	}
}
