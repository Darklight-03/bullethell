package entities;

import entities.ProjectileBase;

public class Shot extends ProjectileBase{

	
	
	public Shot(String imageName, double angle, double speed,int startX,int startY) {
		super(imageName, angle, speed,startX,startY);
		// TODO Auto-generated constructor stub
	}

	public boolean update(){
		x += speed*Math.cos(Math.toRadians(angle));
		y += speed*Math.sin(Math.toRadians(angle));
		return super.update();
	}
	
}
