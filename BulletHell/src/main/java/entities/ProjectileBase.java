package entities;

public class ProjectileBase extends EntityBase{

	double angle, speed;
	
	public ProjectileBase(String imageName, double angle, double speed,int startX, int startY) {
		super(imageName);
		this.angle = angle;
		this.speed = speed;
		x = startX;
		y = startY;
	}


}
