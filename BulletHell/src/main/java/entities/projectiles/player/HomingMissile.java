package entities.projectiles.player;

import main.GameManager;
import entities.EntityBase;
import entities.projectiles.ProjectileBase;

public class HomingMissile extends ProjectileBase {

	double vx, vy, ax, ay;
	EntityBase target;

	public HomingMissile(String imageName, double x, double y, double vx0, double vy0, double ax0, double ay0) {
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
		if (!isInBounds(x, y)) {
			return false;
		}
		vx = vx + ax/5;
		vy = vy + ay/5;
		if (target == null) {
			target = GameManager.findNearestEnemy(this);
			x = x + vx;
			y = y + vy;
		}
		else {
			double changeInX = target.getX() - x, changeInY = target.getY() - y;
			ay =  changeInY/2000;
			ax = changeInX/2000;
			x += vx;
			y += vy;
			
		}

		return true;
	}

}
