package entities.projectiles;

import util.Log;
import entities.EntityBase;

public class ProjectileBase extends EntityBase{

	double ay, ax, vx, vy, angle, speed;
	
	public ProjectileBase(String imageName, double x, double y, double vx0, double vy0, double ax0, double ay0) {
		super(imageName);
		this.x = x;
		this.y = y;
		this.vx = vx0;
		this.vy = vy0;
		this.ax = ax0;
		this.ay = ay0;
	}
	public ProjectileBase(String imageName, double angle, double speed, double x, double y, double ax0, double ay0, boolean toUseAnglesPutABooleanHereThatIsTrue) {
		super(imageName);
		if(!toUseAnglesPutABooleanHereThatIsTrue)
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
	
	
	
	public boolean update(){
		if(!isInBounds(x,y)){
			return false;
		}
		x = x + vx;
		y = y + vy;
		vx = vx + ax;
		vy = vy + ay;
		
		return true;
	}


}
