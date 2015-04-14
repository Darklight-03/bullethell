package main;

import reference.Config;
import graphics.Panel;

public class GameManager implements Runnable{

	private Panel p;
	public int GameState;

	/*
	 * 
	 */
	public GameManager() {
		GameState = Config.MAIN_MENU;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
