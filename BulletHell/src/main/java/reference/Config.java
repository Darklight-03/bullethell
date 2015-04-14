package reference;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class Config
{
	PrintWriter writer;
	Scanner scan;

	public final int MainMenu = 0;
	public final int Paused = 1;
	public final int Playing = 2;
	public final int Dead = 3;

	public String SAVELOCATION = "Config.txt", temp;
	public int width, height;
	public char dropBombs, switchWeapon, extraKeyOne, extraKeyTwo;

	/*
	 * This file is made in order to store all of the settings in an editable
	 * config file. If anything needs to be added to that file, make sure to add
	 * it to both the load and save methods in order for it to work
	 */
	public Config()
	{
		load();
	}

	/*
	 * use the read method in order to read all of the data from
	 * Config.txt
	 */
	public boolean load()
	{
		try
		{
			scan = new Scanner(new File(SAVELOCATION)).useDelimiter("[,:\n]+");

			scan.next();
			SAVELOCATION = scan.next().trim();

			scan.next();
			width = Integer.parseInt(scan.next().trim());
			height = Integer.parseInt(scan.next().trim());

		} catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/*
	 * use the save method in order to write all of the settings to the
	 * Config.txt
	 */
	public boolean save()
	{
		try
		{
			writer = new PrintWriter(SAVELOCATION + "", "UTF-8");

			writer.println("Config Location: " + SAVELOCATION);
			writer.println("Resolution: " + width + ", " + height);

		} catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
		writer.close();
		return true;
	}
}
