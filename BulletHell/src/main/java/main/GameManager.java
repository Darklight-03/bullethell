package main;

import java.util.ArrayList;

import entities.BackgroundObject;
import entities.EntityBase;
import entities.Player;
import entities.projectiles.ProjectileBase;
import reference.Config;

public class GameManager implements Runnable {

	public Player player;
	public static int gameState;
	public static ArrayList<ProjectileBase> projectiles = new ArrayList<ProjectileBase>();
	public static ArrayList<EntityBase> entities = new ArrayList<EntityBase>();
	public static ArrayList<BackgroundObject> backGroundObjects = new ArrayList<BackgroundObject>();

	/*
	 * 
	 */
	public GameManager() {
		gameState = Config.PLAYING; // TODO change this to start out as main
									// menu, it's like this for testing
		player = new Player("placeHolder.png");
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
			try {
				Thread.sleep(Config.TIME_BETWEEN_UPDATES);
				if (gameState == Config.PLAYING) {
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

	private void updateE() {
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).update();
		}
		for (int i = 0; i < backGroundObjects.size(); i++) {
			if (!backGroundObjects.get(i).update()) backGroundObjects.remove(i);
		}
	}

	private void updateP() {
		for (int i = 0; i < projectiles.size(); i++) {
			if (!projectiles.get(i).update()) projectiles.remove(i);
		}
	}

	public static EntityBase findNearestEnemy(EntityBase e) {
		// TODO change this to use the entities arraylist once the testing is
		// complete
		double distance = 0, temp;
		int posToReturn = -1;
		for (int i = 0; i < backGroundObjects.size(); i++) {

			temp = Math.sqrt(Math.pow(e.getY() - backGroundObjects.get(i).getY(), 2)
					+ Math.pow(e.getX() - backGroundObjects.get(i).getX(), 2));
			if (temp > distance) {
				distance = temp;
				posToReturn = i;
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

}
