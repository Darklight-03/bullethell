package reference;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

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

	public String SAVELOCATION = "Config.txt", temp;
	public static int width, height;
	private ArrayList<Character> buttons;
	public char dropBombs, switchWeapon, extraKeyOne, extraKeyTwo, moveUp, moveDown, moveLeft, moveRight;

	/*
	 * This file is made in order to store all of the settings in an editable
	 * config file. If anything needs to be added to that file, make sure to add
	 * it to both the load and save methods in order for it to work
	 */
	public Config() {
		buttons = new ArrayList<Character>();
		load();
		// save();
	}

	/*
	 * use the read method in order to read all of the data from
	 * Config.txt
	 */
	public boolean load() {
		try {
			scan = new Scanner(new File(SAVELOCATION));

			loadLocation();

			loadResolution();

			loadButtons();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/*
	 * use the save method in order to write all of the settings to the
	 * Config.txt
	 */
	public boolean save() {
		try {
			writer = new PrintWriter(SAVELOCATION + "", "UTF-8");
			saveLocation();

			saveResolution();

			saveButtons();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		writer.close();
		return true;
	}

	public void loadLocation() {
		Scanner scanner = new Scanner(scan.nextLine());
		lineScanner = scanner.useDelimiter(":");
		lineScanner.next();
		SAVELOCATION = lineScanner.next().trim();
		scanner.close();
	}

	public void loadResolution() {
		Scanner scanner = new Scanner(scan.nextLine());
		lineScanner = scanner.useDelimiter("[:,]");
		lineScanner.next();
		width = Integer.parseInt(lineScanner.next().trim());
		height = Integer.parseInt(lineScanner.next().trim());
		scanner.close();
	}

	public void loadButtons() {
		lineScanner = scan.useDelimiter("[:\n]");

		lineScanner.next();
		dropBombs = lineScanner.next().trim().charAt(0);

		lineScanner.next();
		switchWeapon = lineScanner.next().trim().charAt(0);

		lineScanner.next();
		extraKeyOne = lineScanner.next().trim().charAt(0);

		lineScanner.next();
		extraKeyTwo = lineScanner.next().trim().charAt(0);

	}

	public void saveLocation() {
		writer.println("Config Location: " + SAVELOCATION);
	}

	public void saveResolution() {
		writer.println("Resolution: " + width + ", " + height);
	}

	public void saveButtons() {
		writer.println("Drop bombs: ");
		writer.println("Switch Weapons: ");
		writer.println("Extra button one: ");
		writer.println("Extra button two: ");
		writer.println("Move up: ");
		writer.println("Move down: ");
		writer.println("Move left: ");
		writer.println("Move Right: ");
	}

}
