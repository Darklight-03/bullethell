package entities.enemies;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import reference.Config;
import entities.projectiles.BasicShot;
import game.GameManager;
import graphics.ImageLoader;

public class SmallTurretEnemy extends EnemyBase {
	boolean left;
	int RoF, bulletSpeed, Spread, bps, ct = 0;
	int[] xPoints, yPoints;

	int yMax = 0;
	BufferedImage i1, i2, i3, i4, i5, i6, i7, i8;

	public SmallTurretEnemy(int RoF, int Spread, int bulletsPerShot, int bulletSpeed) {
		super(100);
		x = -100;
		y = 300;
		yMax = (int) (-1 * ((Math.random() * 1000) + 500));
		this.bulletSpeed = bulletSpeed;
		this.bps = bulletsPerShot;
		this.RoF = RoF;
		this.Spread = Spread;
		if (x < Config.WIDTH / 2) {
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
			x = Config.WIDTH + 100;
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
			double deg = Math.toDegrees(Math.atan2(dy, dx));
			// Log.warn(deg+"");
			if (deg <= 22.5 && deg > -22.5) {
				setImage(Config.scale(ImageLoader.smallTurretEnemy0Degrees, 2, 2));
				// Log.info("1");
			}
			if (deg < -22.5 && deg >= -67.5) {
				setImage(Config.scale(ImageLoader.smallTurretEnemy45Degrees, 2, 2));
				// Log.info("2");
			}
			if (deg < -67.5 && deg >= -112.5) {
				setImage(Config.scale(ImageLoader.smallTurretEnemy90Degrees, 2, 2));
				// Log.info("3");
			}
			if (deg < -112.5 && deg >= -157.5) {
				setImage(Config.scale(ImageLoader.smallTurretEnemy135Degrees, 2, 2));
				// Log.info("4");
			}
			if (deg < -157.5 && deg >= -180 || deg <= 180 && deg > 157.5) {
				setImage(Config.scale(ImageLoader.smallTurretEnemy180Degrees, 2, 2));
				// Log.info("5");
			}
			if (deg > 112.5 && deg <= 157.5) {
				setImage(Config.scale(ImageLoader.smallTurretEnemy225Degrees, 2, 2));
				// Log.info("6");
			}
			if (deg > 67.5 && deg <= 112.5) {
				setImage(Config.scale(ImageLoader.smallTurretEnemy270Degrees, 2, 2));
				// Log.info("7");
			}
			if (deg > 22.5 && deg <= 67.5) {
				setImage(Config.scale(ImageLoader.smallTurretEnemy315Degrees, 2, 2));
				// Log.info("8");
			}
			while (bps > ct) {

				GameManager.getGame().enemyProjectiles.add(new BasicShot(Config.scale(ImageLoader.roundLargeBullet,2,2), Math
						.toDegrees(Math.atan2(dy, dx)) + (Math.random() * Spread) - (Spread / 2), bulletSpeed, x, y, 0,
						0, true));

				ct++;
			}
			ct = 0;
		}
		return false;
	}

	@Override
	public void updateHitBox() {
		hitBox = new Rectangle((int) x - 18, (int) y - 18, 36, 36);
	}

	
}
