package game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import reference.Config;
import util.Log;
import entities.BackgroundObject;
import entities.EnemyBase;
import entities.EntityBase;
import entities.Player;
import entities.enemies.SmallTurretEnemy;
import entities.projectiles.ProjectileBase;
import game.stages.Stage;
import game.stages.Stage1;

public class Game implements Runnable {

	public Player player;
	public Thread mainThread = new Thread(this), projectileCollisions = new Thread(new Runnable() {
		/*
		 * This Thread is designed to check all of the projectiles arraylists
		 * and determine whether or not they have hit a target
		 */
		public void run() {
			Thread.currentThread().setPriority((int) (Thread.MAX_PRIORITY * 0.8));
			while (true) {
				try {
					Thread.sleep(1000 / Config.UPS);

					if (gameState == Config.PLAYING) {
						// count++;
						if (count > 999999999) {
							count = 0;
						}
						if (count % ((Config.UPS * Config.GAME_SPEED) / 100) == 0) {
							checkEnemyCollisions();
							checkIfPlayerIsDamaged();
						}
					}
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	});
	public int gameState;
	private boolean moveUpDepressed = false, moveDownDepressed = false, moveLeftDepressed = false,
			moveRightDepressed = false, shouldMoveSlow = false;
	public ArrayList<ProjectileBase> enemyProjectiles = new ArrayList<ProjectileBase>();
	public ArrayList<ProjectileBase> playerProjectiles = new ArrayList<ProjectileBase>();
	public ArrayList<EnemyBase> enemies = new ArrayList<EnemyBase>();
	public ArrayList<BackgroundObject> backGroundObjects = new ArrayList<BackgroundObject>();
	public static int count;
	BufferedImage e1;
	Stage currentStage;

	/*
	 * This should be the class that controls almost every aspect of the game.
	 * From updating all of the entities on the screen, to creating new
	 * entities, and other various methods that will be necessary for gameplay.
	 */
	public Game() {
		gameState = Config.MAIN_MENU;
	}

	public Game(boolean isRealGame) {
		
		
		
		gameState = Config.PLAYING;
		mainThread = new Thread(this);
		mainThread.start();
		player = new Player(Config.PLAYER_IMAGE);

		currentStage = new Stage1();
	}

	/*
	 * This thread will call the update methods for all of the existing
	 * projectiles and entities- besides the player-, in order to make them move
	 */
	@Override
	public void run() {
		Thread.currentThread().setPriority((int) (Thread.MAX_PRIORITY * 0.8));
		try {
			Thread.sleep(1000);
		}
		catch (Exception e) {

		}
		projectileCollisions.start();
		while (true) {
			try {
				Thread.sleep(1000 / Config.UPS);

				if (gameState == Config.PLAYING) {
					count++;
					if (count > 999999999) {
						count = 0;
					}
					// Log.info("count = "+count);

					// Log.info("count/this = 400 = "+(Config.UPS*Config.GAME_SPEED)/400);
					// Log.info("(400) 0 = "+count%((Config.UPS*Config.GAME_SPEED)/400));

					// Log.info("count/this = 1000 = "+Config.UPS/1000);
					// Log.info("(1000) 0 = "+count%(Config.UPS/1000));

					if (count % ((Config.UPS * Config.GAME_SPEED) / 125) == 0) {
						getPlayer().move(moveUpDepressed, moveDownDepressed, moveLeftDepressed, moveRightDepressed,
								shouldMoveSlow);
					}
					if (count % ((Config.UPS * Config.GAME_SPEED) / 125) == 0) {
						checkEnemyCollisions();
					}
					updatePlayer();
					updateE();
					updateP();

				}
			}
			catch (InterruptedException e) {
				e.printStackTrace();
				Log.info("fail");
			}
		}
	}

	/*
	 * This method will go through every enemy on the screen, and the for every
	 * enemy that is on the screen, it will go through the playerProjectiles
	 * arraylist, and determine whether or not the player's bullets have hit it.
	 */
	public void checkEnemyCollisions() {
		try {
			for (int i = 0; i < enemies.size(); i++) {
				EnemyBase e = enemies.get(i);
				if (e.isInBounds()) {
					for (int ii = 0; ii < playerProjectiles.size(); ii++) {
						if (e.getHitBox().intersects(playerProjectiles.get(ii).getHitBox())) {
							// TODO maybe add a little explosion here when the
							// projectiles hit the enemy?
							if (e != null) {
								if (!e.damage(playerProjectiles.get(ii).getDamage())) {
									enemies.remove(i);
								}
							}
							playerProjectiles.remove(ii);
						}
					}
				}
			}
		}
		catch (Exception e) {
			Log.error("Failed to run checkEnemyCollisions()");
		}
	}

	/*
	 * This method will go through the arrayList of enemyProjectiles, and
	 * determine whether or not any of them have hit the Player
	 */
	public void checkIfPlayerIsDamaged() {
		try {
			for (int i = 0; i < enemyProjectiles.size(); i++) {
				if (enemyProjectiles.get(i).getHitBox().intersects(player.getHitBox())) {
					player.hit();
				}
			}
		}
		catch (Exception e) {
			Log.error("Failed to run checkIfPlayerIsDamaged()");
		}
	}

	/*
	 * This Method is to be called when the player dies, this is the only method
	 * that should change the gamestate to dead in order to avoid conflicts.
	 * Generally, this method will be called by the Player Object
	 */
	public void theDeathMethod() {
		// TODO do more dying stuff here
		gameState = Config.DEAD;
		try {
			player.respawn();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * This method should only be called when the player has lost all of their
	 * lives. It will most likely end up creating a high scores screen, and then
	 * sending the player back to the main menu once they enter their score.
	 */
	public void gameOver() {
		gameState = Config.GAMEOVER;
	}

	private void updatePlayer() {

	}

	/*
	 * This method will call update() on both all of the entities and all of the
	 * background objects, and remove any background objects who scrolled of the
	 * edge of the screen.
	 */
	private void updateE() {
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).update();
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
		for (int i = 0; i < enemyProjectiles.size(); i++) {
			try {
				if (!enemyProjectiles.get(i).update()) enemyProjectiles.remove(i);
			}
			catch (Exception e) {
				Log.error("failed to update a projectile");
			}
		}
		for (int i = 0; i < playerProjectiles.size(); i++) {
			try {
				if (!playerProjectiles.get(i).update()) playerProjectiles.remove(i);
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
	public EntityBase findNearestEnemy(EntityBase e) {
		// TODO change this to use the entities arraylist once the testing is
		// complete, right now it uses the background objects
		double distance = Integer.MAX_VALUE, temp;
		int posToReturn = -1;
		for (int i = 0; i < enemies.size(); i++) {
			if (enemies.get(i).getY() < e.getY()) {
				temp = Math.sqrt(Math.pow(e.getY() - enemies.get(i).getY(), 2)
						+ Math.pow(e.getX() - enemies.get(i).getX(), 2));
				if (temp < distance) {
					distance = temp;
					posToReturn = i;
				}
			}
		}
		if (posToReturn != -1) return enemies.get(posToReturn);
		else return null;
	}

	public int getGameState() {
		return gameState;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;

	}

	public ArrayList<ProjectileBase> getEnemyProjectiles() {
		return enemyProjectiles;
	}

	public ArrayList<ProjectileBase> getPlayerProjectiles() {
		return playerProjectiles;
	}

	public ArrayList<EnemyBase> getEnemies() {
		return enemies;
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

	public int getCount() {
		return count;
	}

	public static BufferedImage scale(BufferedImage img, double horizontalScale, double verticalScale) {
		if (img != null) {
			int transparency = img.getColorModel().getTransparency();
			BufferedImage img2 = new BufferedImage((int) (img.getWidth() * horizontalScale),
					(int) (img.getHeight() * verticalScale), transparency);
			Graphics g = img2.getGraphics();
			g.drawImage(img, 0, 0, (int) ((img.getWidth() * horizontalScale)),
					(int) ((img.getHeight() * verticalScale)), 0, 0, img.getWidth(), img.getHeight(), null);
			return img2;

		}
		else return null;

	}

	public Stage getCurrentStage() {
		return currentStage;
	}
}
