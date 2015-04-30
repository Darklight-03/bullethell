package entities.projectiles;

import java.awt.Rectangle;

import reference.Config;
import util.Log;
import main.GameManager;
import main.Main;
import entities.projectiles.ProjectileBase;

public class BasicShot extends ProjectileBase {

	protected final String NAME = "PlayerShot";
	
	public int size = 0;
	
	public BasicShot(String imageName, double x, double y, double vx0, double vy0, double ax0, double ay0){
		super(imageName, x, y, vx0, vy0, ax0, ay0);
		hitBox = new Rectangle((int) x - this.getImage().getWidth() / 2, (int) y - this.getImage().getHeight(), this
				.getImage().getWidth(), this.getImage().getHeight());
	}
	
	public BasicShot(String imageName, double angle, double speed, double x, double y, double ax0, double ay0, boolean toUseAnglesPutABooleanHereThatIsTrue){
		super(imageName, angle, speed, x, y, ax0, ay0, toUseAnglesPutABooleanHereThatIsTrue);
	}
	
	public String toString(){
		return NAME+":  "+x+", "+y;
	}
	
	public boolean update() {
		if (!isInBounds(x, y)) {
			return false;
		}
		if(x+(this.getImage().getWidth()/4)>Main.f.getPanel().getGM().getPlayer().getX()-4&&x-(this.getImage().getWidth()/4)<Main.f.getPanel().getGM().getPlayer().getX()+4
				&&y+(this.getImage().getWidth()/4)>Main.f.getPanel().getGM().getPlayer().getY()-4&&y-(this.getImage().getWidth()/4)<Main.f.getPanel().getGM().getPlayer().getY()+4){
			Log.info("ded");
		}
		if (GameManager.count % (int) ((Config.UPS * Config.GAME_SPEED) / 100) == 0) {
			x = x + vx;
			y = y + vy;
			vx = vx + ax;
			vy = vy + ay;
		}
		// System.out.println("X: "+x+" Y: "+y+" VX: "+vx+" VY:"+" AX: "+ax+" AY: "+ay);
		hitBox = new Rectangle((int) x - (this.getImage().getWidth() / 4), (int) y - (this.getImage().getHeight() / 4),
				this.getImage().getWidth()/2, this.getImage().getHeight()/2);

		return true;
	}

}
