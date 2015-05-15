package entities;

import game.GameManager;
import reference.Config;

public class BackgroundObject extends EntityBase {
	
	protected final String NAME = "BackgroundObject";
	
	public BackgroundObject(String imageName) {
		super(imageName);
		y = -100 + (Math.random() * -900);
		x = Math.random() * Config.WIDTH;
	}

	public boolean update() {
		if (!isInBounds(x, y)) {
			return false;
		}
		if (GameManager.getGame().getCount() % (Config.UPS / 100) == 0) {
			y = y + Config.scrollSpeed;
			if (y > Config.HEIGHT + 100) {
				y = (Math.random() * -500) + 100;
				x = Math.random() * Config.WIDTH;
			}
		}
		return super.update();
	}
	
	public boolean isInBounds(double x, double y) {
		if (x < -100 || x > Config.WIDTH+100 ||  y > Config.HEIGHT+100)
			return false;
		else
			return true;
	}
	
	public String toString(){
		return NAME+":  "+x+", "+y;
	}
}
