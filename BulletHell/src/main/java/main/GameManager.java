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
	public static ArrayList<BackgroundObject> backgroundObjects = new ArrayList<BackgroundObject>();

	/*
	 * 
	 */
	public GameManager() {
		gameState = Config.PLAYING; // TODO change this to start out as main
									// menu, it's like this for testing
		player = new Player("placeHolder.jpg");
		entities.add(new BackgroundObject("placeHolderBackgroundObject.jpg"));
		
		
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
				if(gameState == Config.PLAYING){
				updatePlayer();
				updateE();
				updateP();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void updatePlayer(){
		
	}
	
	private void updateE(){
		for(int i = 0;i<entities.size();i++){
			entities.get(i).update();
		}
	}
	private void updateP(){
		for(int i = 0;i<projectiles.size();i++){
			if(!projectiles.get(i).update())
				projectiles.remove(i);
		}
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

}
