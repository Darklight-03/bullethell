package entities;

import main.GameManager;
import entities.projectiles.PlayerShot;
import reference.Config;

public class Player extends EntityBase {

	protected final String NAME = "Player";
	int width, height;
	public int weapon = 0, powerLevel;

	// TODO add method to switch the weapon when the switchweapon method is
	// called

	public Player(String imageName) {
		super(imageName);
		width = getImage().getWidth();
		height = getImage().getHeight();
		x = Config.width / 2;
		y = Config.height / 2;
		powerLevel = 0;
	}
	
	public void attack(){
		switch(weapon){
		case 0:
			GameManager.projectiles.add(new PlayerShot("placeholderProjectile.jpg",-90,5,x,y));
			break;
		case 1:
			break;
		}
		
	}

	// Moves the player in the direction specified --
	public void move(boolean up, boolean down, boolean left, boolean right) {
		double x = 0, y = 0;

		if (up) {
			if (this.y - height / 2 > 0) y -= Config.moveSpeed;

		}
		if (down) {
			if (this.y + height / 2 < Config.height - .75) y += Config.moveSpeed;
		}
		if (left) {
			if (this.x - width / 2 > 0) x -= Config.moveSpeed;

		}
		if (right) {
			if (this.x + width / 2 < Config.width - .76) x += Config.moveSpeed;
		}

		if (x != 0 && y != 0) {
			x /= Math.sqrt(2);
			y /= Math.sqrt(2);
		}

		if (isInBounds(this.x, this.y, x, y, width, height)) {
			this.x += x;
			this.y += y;
		}
	}
	
	public void increasePower(){
		powerLevel++;
	}
	public void losePower(){
		powerLevel = 0;
	}

}
