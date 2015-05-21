package entities.projectiles.player;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import entities.projectiles.ProjectileBase;

public class PlayerShot extends ProjectileBase {

	protected final String NAME = "PlayerShot";

	public int size = 0;

	public PlayerShot(BufferedImage image, double x, double y, double vx0, double vy0, double ax0, double ay0, double damage) {
		super(image, x, y, vx0, vy0, ax0, ay0);
		hitBox = new Rectangle((int) x - this.getImage().getWidth() / 2, (int) y - this.getImage().getHeight(), this
				.getImage().getWidth(), this.getImage().getHeight());
		this.damage = damage;
	}

	public PlayerShot(BufferedImage image, double angle, double speed, double x, double y, double ax0, double ay0, double damage,
			boolean toUseAnglesPutABooleanHereThatIsTrue) {
		super(image, angle, speed, x, y, ax0, ay0, toUseAnglesPutABooleanHereThatIsTrue);
		hitBox = new Rectangle((int) x - this.getImage().getWidth() / 2, (int) y - this.getImage().getHeight(), this
				.getImage().getWidth(), this.getImage().getHeight());
		this.damage = damage;
		
	}
	//DEPRECATED ( NO MORE STRINGS FOR IMAGES ANDREW )
	@Deprecated
	public PlayerShot(String imageName, double x, double y, double vx0, double vy0, double ax0, double ay0, double damage) {
		super(imageName, x, y, vx0, vy0, ax0, ay0);
		hitBox = new Rectangle((int) x - this.getImage().getWidth() / 2, (int) y - this.getImage().getHeight(), this
				.getImage().getWidth(), this.getImage().getHeight());
		this.damage = damage;
	}
	//DEPRECATED ( NO MORE STRINGS FOR IMAGES ANDREW )
	@Deprecated
	public PlayerShot(String imageName, double angle, double speed, double x, double y, double ax0, double ay0, double damage,
			boolean toUseAnglesPutABooleanHereThatIsTrue) {
		super(imageName, angle, speed, x, y, ax0, ay0, toUseAnglesPutABooleanHereThatIsTrue);
		hitBox = new Rectangle((int) x - this.getImage().getWidth() / 2, (int) y - this.getImage().getHeight(), this
				.getImage().getWidth(), this.getImage().getHeight());
		this.damage = damage;
		
	}

	public String toString() {
		return NAME + ":  " + x + ", " + y;
	}

}
