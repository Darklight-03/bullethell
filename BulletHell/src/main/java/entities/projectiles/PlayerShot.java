package entities.projectiles;


public class PlayerShot extends ProjectileBase{

	
	
	public PlayerShot(String imageName, double angle, double speed,double startX,double startY) {
		super(imageName, angle, speed,startX,startY);
		// TODO Auto-generated constructor stub
	}
	
	public boolean update(){
		x += speed*Math.cos(Math.toRadians(angle));
		y += speed*Math.sin(Math.toRadians(angle));
		return super.update();
	}
	
}
