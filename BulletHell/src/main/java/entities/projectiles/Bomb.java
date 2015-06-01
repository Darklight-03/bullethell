package entities.projectiles;

import game.GameManager;
import graphics.ImageLoader;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import reference.Config;

public class Bomb extends ProjectileBase{
	boolean boom=false;
	double exploScale = .1;
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
		if(boom){
			if(count%(int)(Config.UPS/10)==0){
				exploScale += .1;
			}
		}
		if(exploScale == 1.1){
			return false;
		}
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
	
	public void drawThis(Graphics bg){
		if(boom){
			bg.drawImage(Config.scale(ImageLoader.explode,exploScale,exploScale),(int)x,(int)y,null);
		}
		bg.drawImage(ImageLoader.missile,(int)x,(int)y,null);
	}
	
	public void explode(){
		boom = true;
	}
}
