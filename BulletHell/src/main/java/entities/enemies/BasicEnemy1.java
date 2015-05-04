package entities.enemies;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GameManager;
import reference.Config;
import entities.EntityBase;
import entities.projectiles.BasicShot;

public class BasicEnemy1 extends EntityBase{
	boolean left;
	int RoF, bulletSpeed, Spread, bps, ct = 0,yMax = 0;

	public BasicEnemy1(String img, int RoF, int Spread, int bulletsPerShot, int bulletSpeed) {
		super(img);
		x = -100;
		y = 300;

		yMax = (int) (-1 * ((Math.random() * 1000) + 500));
		this.bulletSpeed = bulletSpeed;
		this.bps = bulletsPerShot;
		this.RoF = RoF;
		this.Spread = Spread;
		if (x < Config.width / 2) {
			left = true;
			vx = 2;
			vy = 2;
			ay = -.02;
			ax = 0;
		}
		else {
			left = false;
			vx = -2;
			vy = 2;
			ay = -.02;
			ax = 0;
		}
		hitBox = new Rectangle();

	}

	public BasicEnemy1(BufferedImage img, int RoF, int Spread, int bulletsPerShot, int bulletSpeed) {
		super(img);
		x = -100;
		y = 300;
		yMax = (int) (-1 * ((Math.random() * 1000) + 500));
		this.bulletSpeed = bulletSpeed;
		this.bps = bulletsPerShot;
		this.RoF = RoF;
		this.Spread = Spread;
		if (x < Config.width / 2) {
			left = true;
			vx = 2;
			vy = 2;
			ay = -.02;
			ax = 0;
		}
		else {
			left = false;
			vx = -2;
			vy = 2;
			ay = -.02;
			ax = 0;
		}
		hitBox = new Rectangle();
		health = 100;

	}

	public boolean update() {
		updateHitBox();
		if (GameManager.getGame().getCount() % (int) ((Config.UPS * Config.GAME_SPEED) / 100) == 0) {
			physUpdate();
		}
		if (y < yMax && left == true) {
			yMax = (int) (-1 * ((Math.random() * 1000) + 500));
			left = false;
			vx = -2;
			vy = 2;
			ay = -.02;
			ax = 0;
			x = Config.width + 100;
			y = 300;
		}
		if (y < yMax && left == false) {
			yMax = (int) (-1 * ((Math.random() * 1000) + 500));
			left = true;
			vx = 2;
			vy = 2;
			ay = -.02;
			ax = 0;
			x = -100;
			y = 300;
		}
		if (GameManager.getGame().getCount() % (int) ((Config.UPS * Config.GAME_SPEED) / RoF) == 0) {
			double px = GameManager.getGame().getPlayer().getX();
			double py = GameManager.getGame().getPlayer().getY();
			double dx = px - x;
			double dy = py - y;
			// Log.info(Math.atan2(dy, dx) + "");
			// Log.info(dx + "  " + dy);
			// GameManager.projectiles.add(new
			// PlayerShot("uglyPlaceholderProjectile.jpg", x, y, dx, dy, 0 ,
			// 0));
			while (bps > ct) {

				GameManager.getGame().projectiles.add(new BasicShot(Config.UGLY_PLACEHOLDER_PROJECTILE, Math.toDegrees(Math
						.atan2(dy, dx)) + (Math.random() * Spread) - (Spread / 2), bulletSpeed, x, y, 0, 0, true));
				ct++;
			}
			ct = 0;
		}
		return false;
	}

	@Override
	public void updateHitBox() {
		hitBox = new Rectangle((int)x-18, (int)y-18, 36,36);


	}
}
