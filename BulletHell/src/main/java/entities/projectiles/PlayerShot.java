package entities.projectiles;

public class PlayerShot extends ProjectileBase {

	public int size = 0;

	public PlayerShot(String imageName, double angle, double speed, double startX, double startY) {
		super(imageName, angle, speed, startX, startY);
		// TODO Auto-generated constructor stub
	}

	public void shoot(int powerLevel) {
		switch(powerLevel){
		case 0:
			break;
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		}
	}

	public boolean update() {
		x += speed * Math.cos(Math.toRadians(angle));
		y += speed * Math.sin(Math.toRadians(angle));
		return super.update();
	}

}
