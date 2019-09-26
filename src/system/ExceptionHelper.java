package system;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.JOptionPane;

import controller.MainFrame;
import system.io.StreamWriter;

public class ExceptionHelper
{
	/**
	 * Catch exception and create a crash log.
	 *
	 * @param ex Exception.
	 */
	public static void catchError(Exception ex)
	{
		try
		{
			String ErrorLog = "[CODE]" + System.lineSeparator() + "Sandwich Order System Crash Log v1" + System.lineSeparator() + "--------------------------------------------------" + System.lineSeparator() + System.lineSeparator() + "System specifications:" + System.lineSeparator() + System.lineSeparator() + "\tOS: " + System.getProperty("os.name") + " " + System.getProperty("os.arch") + " [Java]" + System.lineSeparator() + "\tLanguage: " + Locale.getDefault().getDisplayName() + System.lineSeparator() + "\tFramework: Version " + System.getProperty("java.version") + System.lineSeparator() + System.lineSeparator() + "--------------------------------------------------" + System.lineSeparator() + System.lineSeparator() + "Error information:" + System.lineSeparator() + "Message: " + ex.getMessage() + System.lineSeparator() + System.lineSeparator() + "--------------------------------------------------" + System.lineSeparator() + System.lineSeparator() + "CallStack:" + System.lineSeparator() + System.lineSeparator() + getStackTrace(ex) + System.lineSeparator() + System.lineSeparator() + "--------------------------------------------------" + System.lineSeparator() + System.lineSeparator() + "You should report this error if it is reproduceable or you could not solve it by yourself." + System.lineSeparator() + "[/CODE]";

			DateFormat ErrorTime = new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss");
			Date date = new Date();

			StreamWriter streamWriter = new StreamWriter(MainFrame.getWorkingDirectory() + "\\Crash_" + ErrorTime.format(date) + ".dat");
			streamWriter.autoFlush = true;
			streamWriter.write(ErrorLog);

			JOptionPane.showMessageDialog(null, ex.getMessage() + System.lineSeparator() + "Error Log saved at: " + MainFrame.getWorkingDirectory() + "\\Crash_" + ErrorTime.format(date) + ".dat", "Application have crashed!", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		catch (Exception exc)
		{
			JOptionPane.showMessageDialog(null, exc.getMessage(), "Application have crashed!", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Get stack trace from the exception.
	 * 
	 * @param ex Exception.
	 */
	private static String getStackTrace(Exception ex)
	{
		String Result = "";

		for (int i = 0; i < ex.getStackTrace().length; i++)
			Result += ex.getStackTrace()[i].toString() + System.lineSeparator();

		return Result;
	}
}
