package reference;

import game.GameManager;
import graphics.ImageLoader;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

import util.Log;

public class Config {
	PrintWriter writer;
	Scanner scan, lineScanner;
	

	public static final boolean DEBUG_MODE = false;
	public static final boolean LOGGING = true;
	public static final String NAME = "Death's Abyss";
	public static final boolean USE_DIALOGS = true; // TODO implement later
	public static double GAME_SPEED = 1;
	public static final int MAIN_MENU = 0, PAUSED = 1, PLAYING = 2, DEAD = 3, GAMEOVER = 4, PLAYER_UPS = 100;
	public static final long UPS = 250, FPS = 60;
	/*
	 * These variables are used in order to determine which menu to create when
	 * making a new Menu
	 */
	public static final int TITLESCREEN = 0, PLAY = 1, HIGHSCORES = 2, SETTINGS = 3, ABOUT = 4, PAUSESCREEN = 5;

	// list addresses to all of the images in the game here
	public static final String IMG_DIR = "src/main/resources/";
	public static final String PLAYER_IMAGE = "src/main/resources/players/placeHolder.png";
	public static final String PLACEHOLDER_BACKGROUND_OBJECT = "src/main/resources/placeholderBackgroundObject.jpg";
	public static final String PLACEHOLDER_PROJECTILE = "src/main/resources/PlaceholderProjectile.jpg";
	public static final String  UGLY_PLACEHOLDER_PROJECTILE= "src/main/resources/uglyPlaceholderProjectile.jpg";
	public static final String ENEMY_PLACEHOLDER = "src/main/resources/EnemyPlaceholder.png";

	private String SAVELOCATION = "Config.txt", temp;
	public static int WIDTH = 600, HEIGHT = 900, MOVEUP = 38, MOVEDOWN = 40, MOVELEFT = 37, MOVERIGHT = 39,
			MOVESLOW = 16, PLAYER_HITBOX_RADIUS = 3;
	public static double moveSpeed = 1.5, slowMoveSpeed, scrollSpeed = 2;
	public static char SHOOT = 'z', DROPBOMBS = 'x', SWITCHWEAPONS = 'c', EXTRAKEYONE = 'v';
	public static Color hitBoxColor = Color.RED;

	/*
	 * This file is made in order to store all of the settings in an editable
	 * config file. If anything needs to be added to that file, make sure to add
	 * it to both the load and save methods in order for it to work
	 */
	public Config() {
		load();
	}
	
	public static BufferedImage getBackground(){
		int stage = GameManager.getGame().getStage();
		switch(stage){
		case 1:
			return ImageLoader.Background1;
		default: return null;
		}
		
		
	}

	/*
	 * use the read method in order to read all of the data from
	 * Config.txt
	 */
	public void load() {
		try {
			scan = new Scanner(new File(SAVELOCATION));

			if (!location("load")) Log.warn("Failed to load the save location for the Config.txt file");
			space("load");

			if (!resolution("load")) Log.warn("Failed to load the resolution");
			space("load");

			if (!buttons("load")) Log.warn("Failed to load the button configuration");
			space("load");

			if (!playerData("load")) Log.warn("Failed to load the PlayerData");
			space("load");

		}
		catch (Exception e) {
			e.printStackTrace();
			Log.warn("Something happened at Config:load()");
		}
	}

	/*
	 * use the save method in order to write all of the settings to the
	 * Config.txt
	 */
	public void save() {
		try {
			writer = new PrintWriter(SAVELOCATION + "", "UTF-8");

			if (!location("save")) Log.warn("Failed to save the save location for the Config.txt file");
			space("save");

			if (!resolution("save")) Log.warn("Failed to save the resolution");
			space("save");

			if (!buttons("save")) Log.warn("Failed to save the button configuration");
			space("save");

			if (!playerData("save")) Log.warn("Failed to save the PlayerData");
			space("save");

		}
		catch (Exception e) {
			e.printStackTrace();
			Log.warn("Something happened at Config:save()");
		}
		writer.close();
	}

	/*
	 * Just allows for blank lines to be placed in between lines in the config
	 * file, in order to make it easier to read. It will either write a blank
	 * line, or scan the next line(which should be blank) depending on the
	 * String passed to the method.
	 */
	public void space(String saveOrLoad) {
		switch (saveOrLoad)
		{
		case "save":
			writer.println();
			break;
		case "load":
			scan.nextLine();
			break;
		}

	}

	public boolean location(String saveOrLoad) {
		try {
			switch (saveOrLoad)
			{
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
		}
		catch (Exception e) {
			return false;
		}
	}

	public boolean resolution(String saveOrLoad) {
		try {
			switch (saveOrLoad)
			{
			case "save":
				writer.println("Resolution: " + WIDTH + ", " + HEIGHT);
				return true;
			case "load":
				Scanner scanner = new Scanner(scan.nextLine());
				lineScanner = scanner.useDelimiter("[:,]");
				lineScanner.next();
				WIDTH = Integer.parseInt(lineScanner.next().trim());
				HEIGHT = Integer.parseInt(lineScanner.next().trim());
				scanner.close();
				return true;
			default:
				return false;
			}
		}
		catch (NumberFormatException e) {
			return false;
		}
	}

	public boolean buttons(String saveOrLoad) {
		try {
			switch (saveOrLoad)
			{
			case "save":
				writer.println("Shoot weapon: " + SHOOT);
				writer.println("Drop bombs: " + DROPBOMBS);
				writer.println("switchWeapons: " + SWITCHWEAPONS);
				writer.println("Extra button one: " + EXTRAKEYONE);
				writer.println("Move up: " + MOVEUP);
				writer.println("Move down: " + MOVEDOWN);
				writer.println("Move left: " + MOVELEFT);
				writer.println("Move Right: " + MOVERIGHT);
				return true;
			case "load":
				lineScanner = scan.useDelimiter("[:\n]");

				lineScanner.next();
				SHOOT = lineScanner.next().trim().charAt(0);

				lineScanner.next();
				DROPBOMBS = lineScanner.next().trim().charAt(0);

				lineScanner.next();
				SWITCHWEAPONS = lineScanner.next().trim().charAt(0);

				lineScanner.next();
				EXTRAKEYONE = lineScanner.next().trim().charAt(0);

				lineScanner.next();
				temp = lineScanner.next();
				MOVEUP = Integer.parseInt(temp.trim());

				lineScanner.next();
				temp = lineScanner.next();
				MOVEDOWN = Integer.parseInt(temp.trim());

				lineScanner.next();
				temp = lineScanner.next();
				MOVELEFT = Integer.parseInt(temp.trim());

				lineScanner.next();
				temp = lineScanner.next();
				MOVERIGHT = Integer.parseInt(temp.trim());
				return true;
			default:
				return false;
			}
		}
		catch (NumberFormatException e) {
			return false;
		}
	}

	public boolean playerData(String saveOrLoad) {
		try {
			switch (saveOrLoad)
			{
			case "save":
				writer.println("Move speed: " + moveSpeed);
				writer.println("Slow move speed: " + slowMoveSpeed);
				writer.println("Scroll speed: " + scrollSpeed);
				writer.println("RGB value for hitBox: " + hitBoxColor.getRed() + ", " + hitBoxColor.getGreen() + ", "
						+ hitBoxColor.getBlue() + ", " + hitBoxColor.getAlpha());
				return true;
			case "load":
				scan.nextLine();
				Scanner scanner = new Scanner(scan.nextLine());
				lineScanner = scanner.useDelimiter(":");
				lineScanner.next();
				moveSpeed = Double.parseDouble(lineScanner.next().trim());

				scanner = new Scanner(scan.nextLine());
				lineScanner = scanner.useDelimiter(":");
				lineScanner.next();
				slowMoveSpeed = Double.parseDouble(lineScanner.next().trim());

				scanner = new Scanner(scan.nextLine());
				lineScanner = scanner.useDelimiter(":");
				lineScanner.next();
				scrollSpeed = Double.parseDouble(lineScanner.next().trim());
				scanner = new Scanner(scan.nextLine());
				lineScanner = scanner.useDelimiter(":");
				lineScanner.next();
				scanner = new Scanner(lineScanner.next()).useDelimiter(",");
				hitBoxColor = new Color(Integer.parseInt(scanner.next().trim()),
						Integer.parseInt(scanner.next().trim()), Integer.parseInt(scanner.next().trim()),
						Integer.parseInt(scanner.next().trim()));

				return true;
			default:
				return false;
			}
		}
		catch (Exception e) {
			return false;
		}
	}

}
