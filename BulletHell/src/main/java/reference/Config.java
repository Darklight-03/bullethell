package reference;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import util.Log;

public class Config {
	PrintWriter writer;
	Scanner scan, lineScanner;

	public static final boolean DEBUG_MODE = false;
	public static final boolean LOGGING = true;
	public static final String NAME = "Bullet Hell";
	public static final boolean USE_DIALOGS = true;
	public static final int MAIN_MENU = 0;
	public static final int PAUSED = 1;
	public static final int PLAYING = 2;
	public static final int DEAD = 3;

	private String SAVELOCATION = "Config.txt", temp;
	public static int width, height, moveUp, moveDown, moveLeft, moveRight;
	public static char dropBombs, switchWeapon, extraKeyOne, extraKeyTwo;

	/*
	 * This file is made in order to store all of the settings in an editable
	 * config file. If anything needs to be added to that file, make sure to add
	 * it to both the load and save methods in order for it to work
	 */
	public Config() {
		load();
		// save();
	}

	/*
	 * use the read method in order to read all of the data from
	 * Config.txt
	 */
	public void load() {
		try {
			scan = new Scanner(new File(SAVELOCATION));

			if (!location("load")) Log.error("Failed to load the save location for the Config.txt file");

			if (!resolution("load")) Log.error("Failed to load the resolution");

			if (!buttons("load")) Log.error("Failed to load the button configuration");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * use the save method in order to write all of the settings to the
	 * Config.txt
	 */
	public void save() {
		try {
			writer = new PrintWriter(SAVELOCATION + "", "UTF-8");
			if (!location("save")) Log.error("Failed to save the save location for the Config.txt file");

			if (!resolution("save")) Log.error("Failed to save the resolution");

			if (!buttons("save")) Log.error("Failed to save the button configuration");

		} catch (Exception e) {
			e.printStackTrace();
		}
		writer.close();
	}
	
	

	public boolean location(String saveOrLoad) {
		try {
			switch (saveOrLoad) {
			case "save":
				writer.println("Config Location: " + SAVELOCATION);
				return true;
			case "load":
				Scanner scanner = new Scanner(scan.nextLine());
				lineScanner = scanner.useDelimiter(":");
				lineScanner.next();
				SAVELOCATION = lineScanner.next().trim();
				scanner.close();
				return true;
			default:
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	public boolean resolution(String saveOrLoad) {
		try {
			switch (saveOrLoad) {
			case "save":
				writer.println("Resolution: " + width + ", " + height);
				return true;
			case "load":
				Scanner scanner = new Scanner(scan.nextLine());
				lineScanner = scanner.useDelimiter("[:,]");
				lineScanner.next();
				width = Integer.parseInt(lineScanner.next().trim());
				height = Integer.parseInt(lineScanner.next().trim());
				scanner.close();
				return true;
			default:
				return false;
			}
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public boolean buttons(String saveOrLoad) {
		try {
			switch (saveOrLoad) {
			case "save":
				writer.println("Drop bombs: " + dropBombs);
				writer.println("Switch Weapons: " + switchWeapon);
				writer.println("Extra button one: " + extraKeyOne);
				writer.println("Extra button two: " + extraKeyTwo);
				writer.println("Move up: " + moveUp);
				writer.println("Move down: " + moveDown);
				writer.println("Move left: " + moveLeft);
				writer.println("Move Right: " + moveRight);
				return true;
			case "load":
				lineScanner = scan.useDelimiter("[:\n]");

				lineScanner.next();
				dropBombs = lineScanner.next().trim().charAt(0);

				lineScanner.next();
				switchWeapon = lineScanner.next().trim().charAt(0);

				lineScanner.next();
				extraKeyOne = lineScanner.next().trim().charAt(0);

				lineScanner.next();
				extraKeyTwo = lineScanner.next().trim().charAt(0);

				lineScanner.next();
				temp = lineScanner.next();
				moveUp = Integer.parseInt(temp.trim());

				lineScanner.next();
				temp = lineScanner.next();
				moveDown = Integer.parseInt(temp.trim());

				lineScanner.next();
				temp = lineScanner.next();
				moveLeft = Integer.parseInt(temp.trim());

				lineScanner.next();
				temp = lineScanner.next();
				moveRight = Integer.parseInt(temp.trim());
				return true;
			default:
				return false;
			}
		} catch (NumberFormatException e) {
			return false;
		}
	}

}
