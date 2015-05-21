package entities.players;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import entities.EntityBase;
import entities.projectiles.player.HomingMissile;
import entities.projectiles.player.PlayerShot;
import game.GameManager;
import graphics.ImageLoader;
import graphics.Panel;
import reference.Config;
import util.Log;

public class PlayerBase extends EntityBase implements Runnable {

	protected final String NAME = "Player";
	int lives = 0, invulnTime = 0;
	long iEnd = 0;
	public int weapon = 0, powerLevel = 2;
	private Thread t;
	protected boolean dead = false;
	long count = 0;
	Rectangle hitBox;
	ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();

	// TODO add method to switch the weapon when the switchweapon method is
	// called

	public PlayerBase() {
		super();
		setImages();
		lives = 3;
		respawn();
		t = new Thread(this);
		t.start();
	}

	public int getLives() {
		return lives;
	}

	public void hit() {
		if (invulnTime == 0) {
			dead = true;
			lives--;
			losePower();
			try {
				Thread.sleep(500);
			}
			catch (InterruptedException e) {

				e.printStackTrace();
				Log.error("PlayerBase:hit() was interrupted");
			}
			GameManager.getGame().theDeathMethod();

		}
	}

	public void respawn() {
		dead = false;
		// TODO Implement invulnerability.
		invulnTime = 3;
		x = Config.WIDTH / 2;
		y = Config.HEIGHT / 2 + 300;
		GameManager.getGame().gameState = Config.PLAYING;
	}

	public Thread getThread() {
		return t;
	}

	public void attack() {
		if (invulnTime > 0) {
			invulnTime = 0;
		}
		switch (weapon)
		{
		case 0:
			switch (powerLevel)
			{
			case 0:

				if (count % (int) (Config.PLAYER_UPS * Config.GAME_SPEED / 100) == 0) {
					GameManager.getGame().playerProjectiles.add(new PlayerShot(ImageLoader.pBul3, x - 7, y, (Math
							.random() - .5) * 1, -1, 0, -.1, .75));
				}
				if (count % (int) (Config.PLAYER_UPS * Config.GAME_SPEED / 100) == 0) {
					GameManager.getGame().playerProjectiles.add(new PlayerShot(ImageLoader.pBul3, x + 7, y, (Math
							.random() - .5) * 1, -1, 0, -.1, .75));
				}
				break;
			case 1:
				if (count % (int) (Config.PLAYER_UPS * Config.GAME_SPEED / 8) == 0) {
					GameManager.getGame().playerProjectiles.add(new PlayerShot(Config.scale(ImageLoader.pBul2, 2, 2),
							x - 10, y, 0, -1, 0, -.1, 3));
					GameManager.getGame().playerProjectiles.add(new PlayerShot(Config.scale(ImageLoader.pBul2, 2, 2),
							x + 10, y, 0, -1, 0, -.1, 3));
				}
				if (count % (int) (Config.PLAYER_UPS * Config.GAME_SPEED / 50) == 0) {
					GameManager.getGame().playerProjectiles.add(new PlayerShot(ImageLoader.pBul3, x - 7, y, -.5, 3,
							-.005, -.1, 1));
					GameManager.getGame().playerProjectiles.add(new PlayerShot(ImageLoader.pBul3, x + 7, y, .5, 3,
							.005, -.1, 1));
				}
				break;
			case 2:

				if (count % (int) (Config.PLAYER_UPS * Config.GAME_SPEED / 12) == 0) {
					GameManager.getGame().playerProjectiles.add(new PlayerShot(Config.scale(ImageLoader.pBul0, 2, 2),
							x - 10, y, 0, -4, 0, -.1, 5));
					GameManager.getGame().playerProjectiles.add(new PlayerShot(Config.scale(ImageLoader.pBul0, 2, 2),
							x + 10, y, 0, -4, 0, -.1, 5));
				}

				if (count % (int) (Config.PLAYER_UPS * Config.GAME_SPEED / 5) == 0) {
					GameManager.getGame().playerProjectiles.add(new HomingMissile(ImageLoader.pBul3, x - 7, y, -.4, -1,
							0, -.05));
					GameManager.getGame().playerProjectiles.add(new HomingMissile(ImageLoader.pBul3, x + 7, y, .4, -1,
							0, -.05));
				}
				if (count % ((int) ((Config.PLAYER_UPS * Config.GAME_SPEED) / 12)) == 0) {

					GameManager.getGame().playerProjectiles.add(new PlayerShot(Config.scale(ImageLoader.pBul1, 2, 2),
							x - 10, y, -1, -4, 0, -.1, 5));
					GameManager.getGame().playerProjectiles.add(new PlayerShot(Config.scale(ImageLoader.pBul1, 2, 2),
							x + 10, y, 1, -4, 0, -.1, 5));
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
		hitBox = new Rectangle((int) this.x - Config.PLAYER_HITBOX_RADIUS, (int) this.y - Config.PLAYER_HITBOX_RADIUS,
				Config.PLAYER_HITBOX_RADIUS * 2, Config.PLAYER_HITBOX_RADIUS * 2);
		if (shouldMoveSlow) speed = Config.slowMoveSpeed;
		else speed = Config.moveSpeed;

		if (up) {
			if (this.y - height / 2 > 0) y -= speed;
		}
		if (down) {
			if (this.y + height / 2 < Config.HEIGHT - .75) y += speed;
		}
		if (left) {
			if (this.x - width / 2 > 0) x -= speed;
		}
		if (right) {
			if (this.x + width / 2 < Config.WIDTH - .76) x += speed;
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

	public void drawHitBox(Graphics bg) {
		if (!dead) {
			bg.setColor(Config.hitBoxColor);
			bg.fillRect((int) hitBox.getX(), (int) hitBox.getY(), (int) hitBox.getWidth(), (int) hitBox.getHeight());
		}
	}

	public void drawThis(Graphics bg) {
		if (!dead) bg.drawImage(image, drawX(), drawY(), null);

	}

	public int getPower() {
		return powerLevel;
	}

	public void increasePower() {
		powerLevel++;
	}

	public void losePower() {
		int temp = powerLevel;
		powerLevel = powerLevel / 2;
		if (powerLevel == temp && powerLevel != 0) powerLevel--;
	}

	@Override
	public void run() {
		Thread.currentThread().setPriority((int) (Thread.MAX_PRIORITY * 0.8));
		while (true) {

			try {

				Thread.sleep(1000 / Config.PLAYER_UPS);

				if (invulnTime > 0 && iEnd == 0) {
					iEnd = count + (Config.PLAYER_UPS * invulnTime);
				}
				if (iEnd > 0 && count > iEnd) {
					iEnd = 0;
					invulnTime = 0;
				}
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
				Log.error("PlayerBase:run() was interrupted");
			}
		}
	}

	public Rectangle getHitBox() {
		return hitBox;
	}

	public void setImages() {
		ImageLoader.loadPlayer(GameManager.getGame().chosenPlayer);
		width = ImageLoader.player.get(0).getWidth();
		height = ImageLoader.player.get(0).getHeight();
	}
}
