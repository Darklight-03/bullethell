package entities;

import main.GameManager;
import reference.Config;

public class BackgroundObject extends EntityBase {
	
	protected final String NAME = "BackgroundObject";
	
	public BackgroundObject(String imageName) {
		super(imageName);
		y = -100 + (Math.random() * -900);
		x = Math.random() * Config.width;
	}

	public boolean update() {
		if (!isInBounds(x, y)) {
			return false;
		}
		if (GameManager.count % (Config.UPS / 100) == 0) {
			y = y + Config.scrollSpeed;
			if (y > Config.height + 100) {
				y = (Math.random() * -500) + 100;
				x = Math.random() * Config.width;
			}
		}
		return super.update();
	}
	
	public boolean isInBounds(double x, double y) {
		if (x < -100 || x > Config.width+100 ||  y > Config.height+100)
			return false;
		else
			return true;
	}
	
	public String toString(){
		return NAME+":  "+x+", "+y;
	}
}
