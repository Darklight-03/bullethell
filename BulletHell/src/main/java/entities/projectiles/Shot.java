package entities.projectiles;

import entities.ProjectileBase;

public class Shot extends ProjectileBase{

	
	
	public Shot(String imageName, double angle, double speed,int startX,int startY) {
		super(imageName, angle, speed,startX,startY);
		// TODO Auto-generated constructor stub
	}

	public void update(){
		x += speed*Math.cos(angle);
		y += speed*Math.sin(angle);
	}
	
}
