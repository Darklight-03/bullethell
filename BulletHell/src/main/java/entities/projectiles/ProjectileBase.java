package entities.projectiles;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import reference.Config;
import util.Log;
import entities.EntityBase;
import game.GameManager;

public class ProjectileBase extends EntityBase {

	double ay, ax, vx, vy, angle, speed;
	protected double damage;
	protected boolean hasHitTarget = false;
	protected final String NAME = "ProjectileBase";
	public Rectangle hitBox;
	int count = 0;

	
	/*
	 * this constructor accepts the directory of the image, starting x position,
	 * starting y position, starting x velocity, starting y velocity,
	 * acceleration of x, acceleration of y
	 */
	public ProjectileBase(BufferedImage image, double x, double y, double vx0, double vy0, double ax0, double ay0) {
		super(image);
		this.x = x;
		this.y = y;
		this.vx = vx0;
		this.vy = vy0;
		this.ax = ax0;
		this.ay = ay0;

		hitBox = new Rectangle((int) x - this.getImage().getWidth() / 2, (int) y - this.getImage().getHeight(), this
				.getImage().getWidth(), this.getImage().getHeight());
	}

	/*
	 * this constructor accepts the directory of the image, the angle that the
	 * image will be fired at, the speed at which the bullet will travel at, the
	 * starting x position, the starting y position, the acceleration in the x
	 * axis, the acceleraton in the y axis, as well as a boolean to
	 * differentiate the two constructors(the boolean should always be set to
	 * true
	 */
	public ProjectileBase(BufferedImage image, double angle, double speed, double x, double y, double ax0, double ay0,
			boolean toUseAnglesPutABooleanHereThatIsTrue) {
		super(image);
		if (!toUseAnglesPutABooleanHereThatIsTrue)
			Log.warn("Something attempted to create a projectile that didn't use angles, but is using angles!! (remove the false at the end of the parameters.)");
		this.angle = angle;
		this.speed = speed;
		this.x = x;
		this.y = y;
		this.ax = ax0;
		this.ay = ay0;
		this.vx = Math.cos(Math.toRadians(angle))*speed;
		this.vy = Math.sin(Math.toRadians(angle))*speed;
		hitBox = new Rectangle((int) x - this.getImage().getWidth() / 2, (int) y - this.getImage().getHeight(), this
				.getImage().getWidth(), this.getImage().getHeight());
	}
	
	/*
	 * this constructor accepts the directory of the image, starting x position,
	 * starting y position, starting x velocity, starting y velocity,
	 * acceleration of x, acceleration of y
	 */
	public ProjectileBase(String imageName, double x, double y, double vx0, double vy0, double ax0, double ay0) {
		super(imageName);
		this.x = x;
		this.y = y;
		this.vx = vx0;
		this.vy = vy0;
		this.ax = ax0;
		this.ay = ay0;

		hitBox = new Rectangle((int) x - this.getImage().getWidth() / 2, (int) y - this.getImage().getHeight(), this
				.getImage().getWidth(), this.getImage().getHeight());
	}

	/*
	 * this constructor accepts the directory of the image, the angle that the
	 * image will be fired at, the speed at which the bullet will travel at, the
	 * starting x position, the starting y position, the acceleration in the x
	 * axis, the acceleraton in the y axis, as well as a boolean to
	 * differentiate the two constructors(the boolean should always be set to
	 * true
	 */
	public ProjectileBase(String imageName, double angle, double speed, double x, double y, double ax0, double ay0,
			boolean toUseAnglesPutABooleanHereThatIsTrue) {
		super(imageName);
		if (!toUseAnglesPutABooleanHereThatIsTrue)
			Log.warn("Something attempted to create a projectile that didn't use angles, but is using angles!! (remove the false at the end of the parameters.)");
		this.angle = angle;
		this.speed = speed;
		this.x = x;
		this.y = y;
		this.ax = ax0;
		this.ay = ay0;
		this.vx = Math.cos(Math.toRadians(angle))*speed;
		this.vy = Math.sin(Math.toRadians(angle))*speed;
		hitBox = new Rectangle((int) x - this.getImage().getWidth() / 2, (int) y - this.getImage().getHeight(), this
				.getImage().getWidth(), this.getImage().getHeight());
	}

	/*
	 * This method overrides the update() method in the EntityBase class, and
	 * makes it more suitable for projectiles: it gives them a constant
	 * acceleration in both the x and the y axis, which will not end up
	 * changing. The projectile will continue accelerating in a straight line
	 * until it goes off of the screen
	 */
	public boolean update() {
		if (!isInBounds(x, y)) {
			return false;
		}
//		if(x+(this.getImage().getWidth()/2)>GameManager.getGame().getPlayer().getX()-4&&x-(this.getImage().getWidth()/2)<GameManager.getGame().getPlayer().getX()+4
//				&&y+(this.getImage().getWidth()/2)>GameManager.getGame().getPlayer().getY()-4&&y-(this.getImage().getWidth()/2)<GameManager.getGame().getPlayer().getY()+4){
//			Log.info("ded");
//		}
		if (GameManager.getGame().getCount() % (int) ((Config.UPS * Config.GAME_SPEED) / 100) == 0) {
			x = x + vx;
			y = y + vy;
			vx = vx + ax;
			vy = vy + ay;
		}
		// System.out.println("X: "+x+" Y: "+y+" VX: "+vx+" VY:"+" AX: "+ax+" AY: "+ay);
		hitBox = new Rectangle((int) x - (this.getImage().getWidth() / 2)+1, (int) y - (this.getImage().getHeight() / 2)+1,
				this.getImage().getWidth()-1, this.getImage().getHeight()-1);

		return true;
	}

	public String toString() {
		return NAME + ":  " + x + ", " + y;
	}

	public Rectangle getHitBox() {
		return hitBox;
	}
	
	public void drawHitBox(Graphics bg) {
		bg.setColor(Color.RED);
		bg.fillRect((int) hitBox.getX(), (int) hitBox.getY(), (int) hitBox.getWidth(), (int) hitBox.getHeight());
	}
	
	public double getDamage(){
		return damage;
	}
	
	public void drawCount(Graphics bg) {
		//bg.drawString(count+"",(int)x,(int)y-10);
	}

}
