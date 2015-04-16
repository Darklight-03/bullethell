package main;

import java.util.ArrayList;

import entities.EntityBase;
import entities.Player;
import entities.ProjectileBase;
import reference.Config;

public class GameManager implements Runnable {

	public Player player;
	public int GameState;
	public ArrayList<ProjectileBase> projectiles = new ArrayList<ProjectileBase>();
	public ArrayList<EntityBase> entities = new ArrayList<EntityBase>();

	/*
	 * 
	 */
	public GameManager() {
		GameState = Config.PLAYING; // TODO change this to start out as main
									// menu, it's like this for testing
		player = new Player("placeHolder.jpg");
	}

	/*
	 * This thread will call the update methods for all of the existing
	 * projectiles and entities- besides the player-, in order to make them move
	 */
	@Override
	public void run() {
		while(true){
			try {
				Thread.sleep(Config.TIME_BETWEEN_UPDATES);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;

	}

}
