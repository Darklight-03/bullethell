package entities;

import main.GameManager;
import entities.projectiles.PlayerShot;
import graphics.Panel;
import reference.Config;

public class Player extends EntityBase implements Runnable {

	protected final String NAME = "Player";
	int width, height;
	public int weapon = 0, powerLevel = 0;
	private Thread t;

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
	
	public Thread getThread(){
		return t;
	}

	public void attack() {
		// TODO make a method in the PlayerShot and PlayerLaser to call, and
		// then that class will determine what to do based on the player's power
		// level
		switch (weapon) {
		case 0:
			switch (powerLevel) {
			case 0:
				GameManager.projectiles.add(new PlayerShot("PlaceholderProjectile.jpg", -90, 5, x - 7, y));
				GameManager.projectiles.add(new PlayerShot("PlaceholderProjectile.jpg", -90, 5, x + 7, y));
				break;
			case 1:
				GameManager.projectiles.add(new PlayerShot("uglyPlaceholderProjectile.jpg", -90, 4, x - 10, y));
				GameManager.projectiles.add(new PlayerShot("uglyPlaceholderProjectile.jpg", -90, 4, x + 10, y));
				GameManager.projectiles.add(new PlayerShot("PlaceholderProjectile.jpg", -100, 4, x - 7, y));
				GameManager.projectiles.add(new PlayerShot("PlaceholderProjectile.jpg", -80, 4, x + 7, y));
				break;
			case 2:

				break;
			case 3:

				break;
			case 4:
				GameManager.projectiles.add(new PlayerShot("uglyPlaceholderProjectile.jpg", -90, 7, x - 10, y));
				GameManager.projectiles.add(new PlayerShot("uglyPlaceholderProjectile.jpg", -90, 7, x + 10, y));
				GameManager.projectiles.add(new PlayerShot("uglyPlaceholderProjectile.jpg", -20, 7, x - 7, y));

				GameManager.projectiles.add(new PlayerShot("uglyPlaceholderProjectile.jpg", -160, 7, x + 7, y));
				GameManager.projectiles.add(new PlayerShot("uglyPlaceholderProjectile.jpg", -36, 7, x - 7, y));
				GameManager.projectiles.add(new PlayerShot("uglyPlaceholderProjectile.jpg", -72, 7, x + 7, y));
				GameManager.projectiles.add(new PlayerShot("uglyPlaceholderProjectile.jpg", -108, 7, x - 7, y));
				GameManager.projectiles.add(new PlayerShot("uglyPlaceholderProjectile.jpg", -144, 7, x + 7, y));
				break;
			}
			break;
		case 1:
			switch (powerLevel) {
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
	public void move(boolean up, boolean down, boolean left, boolean right) {
		double x = 0, y = 0;

		if (up) {
			if (this.y - height / 2 > 0) y -= Config.moveSpeed;

		}
		if (down) {
			if (this.y + height / 2 < Config.height - .75) y += Config.moveSpeed;
		}
		if (left) {
			if (this.x - width / 2 > 0) x -= Config.moveSpeed;

		}
		if (right) {
			if (this.x + width / 2 < Config.width - .76) x += Config.moveSpeed;
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
		powerLevel = 0;
	}

	@Override
	public void run() {
		while (GameManager.gameState == Config.PLAYING) {
			try {
				Thread.sleep(50);
				if (Panel.playerShoots) {
					attack();
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
