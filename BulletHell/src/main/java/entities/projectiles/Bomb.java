package entities.projectiles;

import game.GameManager;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import reference.Config;

public class Bomb extends ProjectileBase{

	public Bomb(BufferedImage image, double x, double y, double vx0, double vy0, double ax0, double ay0, BufferedImage explosionImage) {
		super(image, x, y, vx0, vy0, ax0, ay0);
		hitBox = new Rectangle((int) x - this.getImage().getWidth() / 2, (int) y - this.getImage().getHeight(), this
				.getImage().getWidth(), this.getImage().getHeight());
	}
	public Bomb(BufferedImage image, double angle, double speed, double x, double y, double ax0, double ay0, boolean toUseAnglesPutABooleanHereThatIsTrue, BufferedImage explosionImage){
		super(image, angle, speed, x, y, ax0, ay0, toUseAnglesPutABooleanHereThatIsTrue);
		hitBox = new Rectangle((int) x - this.getImage().getWidth() / 2, (int) y - this.getImage().getHeight(), this
				.getImage().getWidth(), this.getImage().getHeight());
	}

	public boolean update() {
		count++;
		if (!isInBounds(x, y)) {
			return false;
		}
		if(count>500) explode();
		
		if (GameManager.getGame().getCount() % (int) ((Config.UPS * Config.GAME_SPEED) / 100) == 0) {
			x = x + vx;
			y = y + vy;
			vx = vx + ax;
			vy = vy + ay;
		}
		hitBox = new Rectangle((int) x - (this.getImage().getWidth() / 4), (int) y - (this.getImage().getHeight() / 4),
				this.getImage().getWidth()/2, this.getImage().getHeight()/2);


		return true;
	}
	
	public void explode(){
		
	}
}
