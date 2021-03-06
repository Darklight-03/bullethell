package entities.projectiles;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.image.BufferedImage;

import reference.Config;
import util.Log;
import main.Main;
import entities.projectiles.ProjectileBase;
import game.GameManager;

public class BasicShot extends ProjectileBase {

	protected final String NAME = "PlayerShot";
	
	public int size = 0;
	
	public BasicShot(BufferedImage image, double x, double y, double vx0, double vy0, double ax0, double ay0){
		super(image, x, y, vx0, vy0, ax0, ay0);
		hitBox = new Rectangle((int) x - this.getImage().getWidth() / 2, (int) y - this.getImage().getHeight(), this
				.getImage().getWidth(), this.getImage().getHeight());
	}
	
	public BasicShot(BufferedImage image, double angle, double speed, double x, double y, double ax0, double ay0, boolean toUseAnglesPutABooleanHereThatIsTrue){
		super(image, angle, speed, x, y, ax0, ay0, toUseAnglesPutABooleanHereThatIsTrue);
		hitBox = new Rectangle((int) x - this.getImage().getWidth() / 2, (int) y - this.getImage().getHeight(), this
				.getImage().getWidth(), this.getImage().getHeight());
	}
	
	//DEPRECATED ( NO MORE STRINGS FOR IMAGES ANDREW )
	@Deprecated
	public BasicShot(String imageName, double x, double y, double vx0, double vy0, double ax0, double ay0){
		super(imageName, x, y, vx0, vy0, ax0, ay0);
		hitBox = new Rectangle((int) x - this.getImage().getWidth() / 2, (int) y - this.getImage().getHeight(), this
				.getImage().getWidth(), this.getImage().getHeight());
	}
	//DEPRECATED ( NO MORE STRINGS FOR IMAGES ANDREW )
	@Deprecated
	public BasicShot(String imageName, double angle, double speed, double x, double y, double ax0, double ay0, boolean toUseAnglesPutABooleanHereThatIsTrue){
		super(imageName, angle, speed, x, y, ax0, ay0, toUseAnglesPutABooleanHereThatIsTrue);
		hitBox = new Rectangle((int) x - this.getImage().getWidth() / 2, (int) y - this.getImage().getHeight(), this
				.getImage().getWidth(), this.getImage().getHeight());
	}
	
	public String toString(){
		return NAME+":  "+x+", "+y;
	}
	
	public boolean update() {
		count++;
		if (!isInBounds(x, y)) {
			return false;
		}
		if(count>20000) return false;
		
		if (GameManager.getGame().getCount() % (int) ((Config.UPS * Config.GAME_SPEED) / 100) == 0) {
			x = x + vx;
			y = y + vy;
			vx = vx + ax;
			vy = vy + ay;
		}
		// System.out.println("X: "+x+" Y: "+y+" VX: "+vx+" VY:"+" AX: "+ax+" AY: "+ay);
		hitBox = new Rectangle((int) x - (this.getImage().getWidth() / 4), (int) y - (this.getImage().getHeight() / 4),
				this.getImage().getWidth()/2, this.getImage().getHeight()/2);
//		if(hitBox.intersects((Rectangle)GameManager.getGame().getPlayer().getHitBox()))
//			Log.info("ded");


		return true;
	}

	

	public void addV(double vx, double vy) {
		this.vx +=vx;
		this.vy +=vy;
		
	}

}
