package entities.enemies;

import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GameManager;
import main.Main;
import reference.Config;
import util.Log;
import entities.EntityBase;
import entities.PolygonHitBox;
import entities.projectiles.BasicShot;
import entities.projectiles.player.PlayerShot;

public class BasicEnemy1 extends EntityBase implements PolygonHitBox {
	boolean left;
	int RoF, bulletSpeed, Spread, bps, ct = 0;
	int[] xPoints, yPoints;
	int yMax=0;

	public BasicEnemy1(String img, int RoF, int Spread, int bulletsPerShot, int bulletSpeed) {
		super(img);
		x = -100;
		y = 300;
		yMax = (int)(-1*((Math.random()*1000)+500));
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
		xPoints = new int[16];
		yPoints = new int[16];
		hitBox = new Polygon();

	}
	public BasicEnemy1(BufferedImage img, int RoF, int Spread, int bulletsPerShot, int bulletSpeed) {
		super(img);
		x = -100;
		y = 300;
		yMax = (int)(-1*((Math.random()*1000)+500));
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
		xPoints = new int[16];
		yPoints = new int[16];
		hitBox = new Polygon();

	}

	public boolean update() {
		updateHitBox();
		if (GameManager.count % (int) ((Config.UPS * Config.GAME_SPEED) / 100) == 0) {
			physUpdate();
		}
		if (y < yMax && left == true) {
			yMax = (int)(-1*((Math.random()*1000)+500));
			left = false;
			vx = -2;
			vy = 2;
			ay = -.02;
			ax = 0;
			x = Config.width + 100;
			y = 300;
		}
		if (y < yMax && left == false) {
			yMax = (int)(-1*((Math.random()*1000)+500));
			left = true;
			vx = 2;
			vy = 2;
			ay = -.02;
			ax = 0;
			x = -100;
			y = 300;
		}
		if (GameManager.count % (int) ((Config.UPS * Config.GAME_SPEED) / RoF) == 0) {
			double px = Main.f.getPanel().getGM().getPlayer().getX();
			double py = Main.f.getPanel().getGM().getPlayer().getY();
			double dx = px - x;
			double dy = py - y;
		//	Log.info(Math.atan2(dy, dx) + "");
			// Log.info(dx + "  " + dy);
			// GameManager.projectiles.add(new
			// PlayerShot("uglyPlaceholderProjectile.jpg", x, y, dx, dy, 0 ,
			// 0));
			while (bps > ct) {

				GameManager.projectiles.add(new BasicShot(Config.UGLY_PLACEHOLDER_PROJECTILE, Math.toDegrees(Math.atan2(
						dy, dx)) + (Math.random() * Spread) - (Spread / 2), bulletSpeed, x, y, 0, 0, true));
				ct++;
			}
			ct = 0;
		}
		return false;
	}

	@Override
	public void updateHitBox() {
		updateXPoints();
		updateYPoints();
		hitBox = new Rectangle((int)x-18, (int)y-18, 36,36);

	}

	@Override
	public void updateXPoints() {
		int radius = this.getImage().getWidth() / 2;
		for (int i = 1; i < 16; i++) {
			xPoints[i - 1] = (int) (x + radius * Math.cos((i * 24) / (Math.PI * 2)));
		}

	}

	@Override
	public void updateYPoints() {
		int radius = this.getImage().getWidth() / 2;
		for (int i = 1; i < 16; i++) {
			yPoints[i - 1] = (int) (y + radius * Math.cos((i * 24) / (Math.PI * 2)));
		}

	}

	@Override
	public int[][] getArrays() {
		// TODO Auto-generated method stub
		return null;
	}
}
