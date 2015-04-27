package entities.projectiles.player;

import reference.Config;
import main.GameManager;
import entities.EntityBase;
import entities.projectiles.ProjectileBase;

public class HomingMissile extends ProjectileBase {

	protected final String NAME = "Homing Missile";
	double vx, vy, ax, ay;
	EntityBase target;

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
		target = GameManager.findNearestEnemy(this);
	}

	public boolean update() {
		if (GameManager.count % (int)((Config.UPS * Config.GAME_SPEED) / 50) == 0) {
			if (!isInBounds(x, y) || hasHitTarget) {
				return false;
			}
			vx = vx + ax / 5;
			vy = vy + ay / 5;
			if (target == null) {
				target = GameManager.findNearestEnemy(this);
				x = x + vx;
				y = y + vy;
			}
			else if (target.getY() > y + 15) {
				target = null;
				// TODO remove when hitboxes are working
				hasHitTarget = true;
			}
			else {
				double changeInX = target.getX() - x, changeInY = target.getY() - y;
				ay = changeInY / 700;
				ax = changeInX / 400;
				x += vx;
				y += vy;

			}
		}
		return true;
	}

	public String toString() {
		return NAME + ":  " + x + ", " + y;
	}

}
