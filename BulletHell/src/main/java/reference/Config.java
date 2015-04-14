package reference;

import java.io.PrintWriter;

public class Config
{
	PrintWriter writer;

	public final int MainMenu = 0;
	public final int Paused = 1;
	public final int Playing = 2;
	public final int Dead = 3;

	/*
	 * use the config constructor in order to read all of the data from
	 * Config.txt
	 */
	public Config()
	{

	}

	/*
	 * use the save method in order to write all of the settings to the Config.txt
	 */
	public boolean save() throws Exception
	{
		writer = new PrintWriter("Config.txt", "UTF-8");

		writer.println("testing");
		writer.close();
		return true;
	}
}
