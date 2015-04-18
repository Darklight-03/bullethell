package entities;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import reference.Config;
import util.Log;

public class EntityBase {

	/*
	 * This is going to be the base for all of the game's entities
	 */
	public double x, y;
	private BufferedImage image;
	protected final String NAME = "entityBase";

	public EntityBase(String imageName) {
		try {
			setImage(ImageIO.read(new File(imageName)));
		} catch (IOException e) {
			Log.error("Could not load image" + NAME);
		}
	}
	
	public boolean update(){
		return true;
	}

	public int getX() {
		return (int) x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int drawX() {
		return (int) x - image.getWidth() / 2;
	}

	public int drawY() {
		return (int) y - image.getHeight() / 2;
	}

	public int getY() {
		return (int) y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isInBounds(double x, double y) {
		if (x < -100 || x > Config.width+100 || y < -100 || y > Config.height+100)
			return false;
		else
			return true;
	}

	public boolean isInBounds(double currentX, double currentY, double moveX, double moveY, double width, double height) {
		if (moveX > 0) {
			if (!isInBounds(currentX + moveX + width / 2, currentY)) return false;
		} else if (moveX < 0) {
			if (!isInBounds(currentX - moveX - width / 2, currentY)) return false;
		}
		if (moveY > 0) {
			if (!isInBounds(currentX, currentY + moveY + height / 2)) return false;
		} else if (moveY < 0) {
			if (!isInBounds(currentX, currentY - moveY - height / 2)) return false;
		}
		return true;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

}
