package entities.projectiles.player;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import reference.Config;
import entities.EntityBase;
import entities.projectiles.ProjectileBase;
import game.GameManager;

public class HomingMissile extends ProjectileBase {

	protected final String NAME = "HomingMissile";
	double vx, vy, ax, ay;
	EntityBase target;

	public HomingMissile(BufferedImage image, double x, double y, double vx0, double vy0, double ax0, double ay0) {
		// TODO possibly make is so that the bullets fired from the right side
		// of the ship won't be able to cross the center of the ship
		super(image, x, y, vx0, vy0, ax0, ay0);
		this.x = x;
		this.y = y;
		this.vx = vx0;
		this.vy = vy0;
		this.ax = ax0;
		this.ay = ay0;
		target = GameManager.getGame().findNearestEnemy(this);
		damage = 1;
	}
	//DEPRECATED ( NO MORE STRINGS FOR IMAGES ANDREW )
	@Deprecated
	public HomingMissile(String imageName, double x, double y, double vx0, double vy0, double ax0, double ay0) {
		// TODO possibly make is so that the bullets fired from the right side
		// of the ship won't be able to cross the center of the ship
		super(imageName, x, y, vx0, vy0, ax0, ay0);
		this.x = x;
		this.y = y;
		this.vx = vx0;
		this.vy = vy0;
		this.ax = ax0;
		this.ay = ay0;
		target = GameManager.getGame().findNearestEnemy(this);
		damage = 1;
	}

	public boolean update() {
		//return false;
		if (GameManager.getGame().getCount() % (int) ((Config.UPS * Config.GAME_SPEED) / 50) == 0) {
			if (!isInBounds(x, y) || hasHitTarget) {
				return false;
			}
			vx = vx + ax / 5;
			vy = vy + ay / 5;
			if (target == null) {
				target = GameManager.getGame().findNearestEnemy(this);
				x = x + vx;
				y = y + vy;
			}
			else if (target.getY() > y + 15) {
				target = null;
			}
			else {
				double changeInX = target.getX() - x, changeInY = target.getY() - y;
				ay = changeInY / 100;
				ax = changeInX / 100;
				x += vx;
				y += vy;

			}
		}
		hitBox = new Rectangle((int) x - this.getImage().getWidth() / 2, (int) y - this.getImage().getHeight() /2 , this
				.getImage().getWidth(), this.getImage().getHeight());
		return true;
	}

	public String toString() {
		return NAME + ":  " + x + ", " + y;
	}

}
