package main;

import java.util.ArrayList;

import entities.BackgroundObject;
import entities.EntityBase;
import entities.Player;
import entities.projectiles.ProjectileBase;
import reference.Config;
import util.Log;

public class GameManager implements Runnable {

	public Player player;
	public static int gameState;
	private int moveUp, moveDown, moveLeft, moveRight,moveSlow;
	private boolean moveUpDepressed = false, moveDownDepressed = false, moveLeftDepressed = false, moveRightDepressed = false, shouldMoveSlow = false;
	public static ArrayList<ProjectileBase> projectiles = new ArrayList<ProjectileBase>();

	public static ArrayList<EntityBase> entities = new ArrayList<EntityBase>();
	public static ArrayList<BackgroundObject> backGroundObjects = new ArrayList<BackgroundObject>();
	public static int count;


	/*
	 * This should be the class that controls almost every aspect of the game.
	 * From updating all of the entities on the screen, to creating new
	 * entities, and other various methods that will be necessary for gameplay.
	 */
	public GameManager() {
		gameState = Config.PLAYING; // TODO change this to start out as main
									// menu, it's like this for testing
		player = new Player("placeHolder.png");
		backGroundObjects.add(new BackgroundObject("placeHolderBackgroundObject.jpg"));
		backGroundObjects.add(new BackgroundObject("placeHolderBackgroundObject.jpg"));
		backGroundObjects.add(new BackgroundObject("placeHolderBackgroundObject.jpg"));
		backGroundObjects.add(new BackgroundObject("placeHolderBackgroundObject.jpg"));
		backGroundObjects.add(new BackgroundObject("placeHolderBackgroundObject.jpg"));
		backGroundObjects.add(new BackgroundObject("placeHolderBackgroundObject.jpg"));

	}

	/*
	 * This thread will call the update methods for all of the existing
	 * projectiles and entities- besides the player-, in order to make them move
	 */
	@Override
	public void run() {
		while (true) {
			try{
				Thread.sleep(1000 / Config.UPS);

				if (gameState == Config.PLAYING) {
					count++;
					if (count > 999999999) {
						count = 0;
					}
					if(GameManager.count%((Config.UPS*Config.GAME_SPEED)/400)==0){
					getPlayer().move(moveUpDepressed, moveDownDepressed, moveLeftDepressed, moveRightDepressed,shouldMoveSlow);
					}
					updatePlayer();
					updateE();
					updateP();
					
				}
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

		

	private void updatePlayer() {

	}

	/*
	 * This method will call update() on both all of the entities and all of the
	 * background objects, and remove any background objects who scrolled of the
	 * edge of the screen.
	 */
	private void updateE() {
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).update();
		}
		for (int i = 0; i < backGroundObjects.size(); i++) {
			if (!backGroundObjects.get(i).update()) backGroundObjects.remove(i);
		}
	}

	/*
	 * This method will call update() on all of the projectiles on the screen,
	 * and remove any that have traversed off the edge
	 */
	private void updateP() {
		for (int i = 0; i < projectiles.size(); i++) {
			try {
				if (!projectiles.get(i).update()) projectiles.remove(i);
			}
			catch (Exception e) {
				Log.error("failed to update a projectile");
			}
		}
	}

	/*
	 * This method will find the nearest enemy to whichever EntityBase is passed
	 * to it. It utilizes the pythagorean theorem to calculate distance, and
	 * will end up returning the entity that has the shortest distance from the
	 * EntityBase's x and y values. This method will only return enemies that
	 * have a higher y value that the object given to it.
	 */
	public static EntityBase findNearestEnemy(EntityBase e) {
		// TODO change this to use the entities arraylist once the testing is
		// complete, right now it uses the background objects
		double distance = Integer.MAX_VALUE, temp;
		int posToReturn = -1;
		for (int i = 0; i < backGroundObjects.size(); i++) {
			if (backGroundObjects.get(i).getY() < e.getY()) {
				temp = Math.sqrt(Math.pow(e.getY() - backGroundObjects.get(i).getY(), 2)
						+ Math.pow(e.getX() - backGroundObjects.get(i).getX(), 2));
				if (temp < distance) {
					distance = temp;
					posToReturn = i;
				}
			}
		}
		if (posToReturn != -1) return backGroundObjects.get(posToReturn);
		else return null;
	}

		

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;

	}

	public ArrayList<ProjectileBase> getProjectiles() {
		return projectiles;
	}

	public ArrayList<EntityBase> getEntities() {
		return entities;
	}


	public ArrayList<BackgroundObject> getBackGroundObjects() {
		return backGroundObjects;
	}
	public void shouldMoveSlow(boolean b) {
		shouldMoveSlow = b;
		
	}

	public void moveUpDepressed(boolean b) {
		moveUpDepressed = b;
		
	}

	public void moveDownDepressed(boolean b) {
		moveDownDepressed = b;
		
	}

	public void moveLeftDepressed(boolean b) {
		moveLeftDepressed = b;
		
	}

	public void moveRightDepressed(boolean b) {
		moveRightDepressed = b;
	}

}
