package main;

import reference.Config;
import graphics.Panel;

public class GameManager
{
	
	private Panel p;
	private Config c;
	public int GameState;
	
	/*
	 * 
	 */
	public GameManager(Config c){
		this.c = c;
		p = new Panel();
		GameState =  c.MAIN_MENU;
	}

}
