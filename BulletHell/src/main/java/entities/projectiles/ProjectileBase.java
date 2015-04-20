package entities.projectiles;

import reference.Config;
import main.GameManager;
import util.Log;
import entities.EntityBase;

public class ProjectileBase extends EntityBase {

	double ay, ax, vx, vy, angle, speed;

	/*
	 * this constructor accepts the directory of the image, starting x position,
	 * starting y position, starting x velocity, starting y velocity,
	 * acceleration of x, acceleration of y
	 */
	public ProjectileBase(String imageName, double x, double y, double vx0, double vy0, double ax0, double ay0) {
		super(imageName);
		this.x = x;
		this.y = y;
		this.vx = vx0;
		this.vy = vy0;
		this.ax = ax0;
		this.ay = ay0;
	}

	/*
	 * this constructor accepts the directory of the image, the angle that the
	 * image will be fired at, the speed at which the bullet will travel at, the
	 * starting x position, the starting y position, the acceleration in the x
	 * axis, the acceleraton in the y axis, as well as a boolean to
	 * differentiate the two constructors(the boolean should always be set to
	 * true
	 */
	public ProjectileBase(String imageName, double angle, double speed, double x, double y, double ax0, double ay0, boolean toUseAnglesPutABooleanHereThatIsTrue) {
		super(imageName);
		if (!toUseAnglesPutABooleanHereThatIsTrue)
			Log.warn("Something attempted to create a projectile that didn't use angles, but is using angles!! (remove the false at the end of the parameters.)");
		this.angle = angle;
		this.speed = speed;
		this.x = x;
		this.y = y;
		this.ax = ax0;
		this.ay = ay0;
		this.vx = Math.cos(Math.toRadians(angle));
		this.vy = Math.sin(Math.toRadians(angle));
	}

	public boolean update() {
		if (!isInBounds(x, y)) {
			return false;
		}
		if(GameManager.count%(Config.UPS/100)==0){
		x = x + vx;
		y = y + vy;
		vx = vx + ax;
		vy = vy + ay;
		}
		//System.out.println("X: "+x+" Y: "+y+" VX: "+vx+" VY:"+" AX: "+ax+" AY: "+ay);
		

		return true;
	}

}
