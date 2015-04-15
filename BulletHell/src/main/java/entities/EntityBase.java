package entities;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import util.Log;

public class EntityBase {

	/*
	 * This is going to be the base for all of the game's entities
	 */
	public int x, y;
	public BufferedImage image;
	protected final String NAME = "entityBase";

	public EntityBase(String imageName) {
		try {
			image = ImageIO.read(new File(imageName));
		} catch (IOException e) {
			Log.error("Could not load image" + NAME);
		}
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
