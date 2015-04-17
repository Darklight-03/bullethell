package entities.projectiles;

import entities.EntityBase;

public class ProjectileBase extends EntityBase{

	double angle, speed;
	
	public ProjectileBase(String imageName, double angle, double speed,int startX, int startY) {
		super(imageName);
		this.angle = angle;
		this.speed = speed;
		x = startX;
		y = startY;
	}
	
	public boolean update(){
		if(!isInBounds(x,y)){
			return false;
		}
		return true;
	}


}
