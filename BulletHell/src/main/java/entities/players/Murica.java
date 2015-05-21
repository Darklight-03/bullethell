package entities.players;

import graphics.ImageLoader;

import java.awt.Graphics;


public class Murica extends PlayerBase {

	protected final String NAME = "Murica";
	
	public Murica() {
		super();
	}
	
	public void drawThis(Graphics bg) {
		int i = (int)count %4;
		if (!dead) bg.drawImage(ImageLoader.player.get(i), drawX(), drawY(), null);
	}

}
