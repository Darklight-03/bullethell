package main;

import entities.Player;
import reference.Config;

public class GameManager implements Runnable {

	public Player player;

	public int GameState;

	/*
	 * 
	 */
	public GameManager() {
		GameState = Config.PLAYING; // TODO change this to start out as main
									// menu, it's like this for testing
		player = new Player("placeHolder.jpg");
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;

	}

}
