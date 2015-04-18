package entities.projectiles;


public class PlayerShot extends ProjectileBase{

	
	
	public PlayerShot(String imageName, double x, double y, double vx0, double vy0, double ax0, double ay0){
		super(imageName, x, y, vx0, vy0, ax0, ay0);
	}
	
	public PlayerShot(String imageName, double angle, double speed, double x, double y, double ax0, double ay0, boolean toUseAnglesPutABooleanHereThatIsTrue){
		super(imageName, angle, speed, x, y, ax0, ay0, toUseAnglesPutABooleanHereThatIsTrue);
	}
	
}
