package entities;

import main.GameManager;
import entities.projectiles.player.HomingMissile;
import entities.projectiles.player.PlayerShot;
import graphics.Panel;
import reference.Config;

public class Player extends EntityBase implements Runnable {

	protected final String NAME = "Player";
	int width, height;
	public int weapon = 0, powerLevel = 2;
	private Thread t;
	boolean now = true;
	long count = 0;

	// TODO add method to switch the weapon when the switchweapon method is
	// called

	public Player(String imageName) {
		super(imageName);
		width = getImage().getWidth();
		height = getImage().getHeight();
		x = Config.width / 2;
		y = Config.height / 2;

		t = new Thread(this);
		t.start();
	}

	public Thread getThread() {
		return t;
	}

	public void attack() {
		switch (weapon)
		{
		case 0:
			switch (powerLevel)
			{
			case 0:
				if (count % (Config.PLAYER_UPS*Config.GAME_SPEED / 5) == 0) {
					GameManager.projectiles.add(new PlayerShot("PlaceholderProjectile.jpg", x - 7, y, 0, 0, 0, -.1));
				}if (count % (Config.PLAYER_UPS*Config.GAME_SPEED / 5) == 0) {
					GameManager.projectiles.add(new PlayerShot("PlaceholderProjectile.jpg", x + 7, y, 0, 0, 0, -.1));
				}
				break;
			case 1:
				if (count % (Config.PLAYER_UPS*Config.GAME_SPEED / 8) == 0) {
					GameManager.projectiles.add(new PlayerShot("uglyPlaceholderProjectile.jpg", x - 10, y, 0, -1, 0,
							-.1));
					GameManager.projectiles.add(new PlayerShot("uglyPlaceholderProjectile.jpg", x + 10, y, 0, -1, 0,
							-.1));
				}
				if (count % (Config.PLAYER_UPS*Config.GAME_SPEED / 50) == 0) {
					GameManager.projectiles.add(new PlayerShot("PlaceholderProjectile.jpg", x - 7, y, -.5, 3, -.005,
							-.1));
					GameManager.projectiles
							.add(new PlayerShot("PlaceholderProjectile.jpg", x + 7, y, .5, 3, .005, -.1));
				}
				break;
			case 2:
				if (count % (Config.PLAYER_UPS*Config.GAME_SPEED / 12) == 0) {
					GameManager.projectiles.add(new PlayerShot("uglyPlaceholderProjectile.jpg", x - 10, y, 0, -4, 0,
							-.1));
					GameManager.projectiles.add(new PlayerShot("uglyPlaceholderProjectile.jpg", x + 10, y, 0, -4, 0,
							-.1));
				}
				if (count % (Config.PLAYER_UPS*Config.GAME_SPEED / 5) == 0) {
					GameManager.projectiles.add(new HomingMissile("PlaceholderProjectile.jpg", x - 7, y, -.4, -1, 0, -.05));
					GameManager.projectiles.add(new HomingMissile("PlaceholderProjectile.jpg", x + 7, y, .4, -1, 0, -.05));
				}
				if (count % (Config.PLAYER_UPS*Config.GAME_SPEED / 12) == 0) {
					GameManager.projectiles.add(new PlayerShot("uglyPlaceholderProjectile.jpg", x - 10, y, -1, -4, 0,
							-.1));
					GameManager.projectiles.add(new PlayerShot("uglyPlaceholderProjectile.jpg", x + 10, y, 1, -4, 0,
							-.1));
				}
				break;
			case 3:

				break;
			case 4:
				break;
			}
			break;
		case 1:
			switch (powerLevel)
			{
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
			break;
		}
	}

	// Moves the player in the direction specified --
	public void move(boolean up, boolean down, boolean left, boolean right, boolean shouldMoveSlow) {
		double x = 0, y = 0, speed;

		if (shouldMoveSlow) speed = Config.slowMoveSpeed;
		else speed = Config.moveSpeed;

		if (up) {
			if (this.y - height / 2 > 0) y -= speed;
		}
		if (down) {
			if (this.y + height / 2 < Config.height - .75) y += speed;
		}
		if (left) {
			if (this.x - width / 2 > 0) x -= speed;
		}
		if (right) {
			if (this.x + width / 2 < Config.width - .76) x += speed;
		}

		if (x != 0 && y != 0) {
			x /= Math.sqrt(2);
			y /= Math.sqrt(2);
		}

		if (isInBounds(this.x, this.y, x, y, width, height)) {
			this.x += x;
			this.y += y;
		}
	}

	public void increasePower() {
		powerLevel++;
	}

	public void losePower() {
		powerLevel = 0; // TODO this is too harsh, like your mother
	}

	@Override
	public void run() {
		while (true) {

			try {

				Thread.sleep(1000 / Config.PLAYER_UPS);
				count++;
				if (count > 999999999) {
					count = 0;
				}
				if (Panel.playerShoots) {
					attack();
				}
			}
			catch (InterruptedException e) {
				e.printStackTrace();

			}
		}
	}
}
