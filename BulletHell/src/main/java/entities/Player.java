package entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import entities.projectiles.player.HomingMissile;
import entities.projectiles.player.PlayerShot;
import game.GameManager;
import graphics.Panel;
import reference.Config;

public class Player extends EntityBase implements Runnable {

	protected final String NAME = "Player";
	int width, height;
	int lives;
	int invulnTime = 0;
	public int weapon = 0, powerLevel = 2;
	private Thread t;
	private boolean dead = false;
	long count = 0;
	Rectangle hitBox;

	// TODO add method to switch the weapon when the switchweapon method is
	// called

	public Player(String imageName) {
		super(imageName);
		width = getImage().getWidth();
		height = getImage().getHeight();
		lives = 3;
		x = Config.WIDTH / 2;
		y = Config.HEIGHT / 2 + 200;

		t = new Thread(this);
		t.start();
	}

	public void hit() {
		dead = true;
		lives--;
		losePower();
		try {
			Thread.sleep(500);
		}
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GameManager.getGame().theDeathMethod();
	}

	public void respawn() {
		dead = false;
		//TODO Implement invulnerability.
		invulnTime = 3;
		x = Config.WIDTH / 2;
		y = Config.HEIGHT / 2 + 200;
		GameManager.getGame().gameState = Config.PLAYING;
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
				if (count % (int) (Config.PLAYER_UPS * Config.GAME_SPEED / 5) == 0) {
					GameManager.getGame().playerProjectiles.add(new PlayerShot(Config.PLACEHOLDER_PROJECTILE, x - 7, y,
							0, 0, 0, -.1));
				}
				if (count % (int) (Config.PLAYER_UPS * Config.GAME_SPEED / 5) == 0) {
					GameManager.getGame().playerProjectiles.add(new PlayerShot(Config.PLACEHOLDER_PROJECTILE, x + 7, y,
							0, 0, 0, -.1));
				}
				break;
			case 1:
				if (count % (int) (Config.PLAYER_UPS * Config.GAME_SPEED / 8) == 0) {
					GameManager.getGame().playerProjectiles.add(new PlayerShot(Config.UGLY_PLACEHOLDER_PROJECTILE,
							x - 10, y, 0, -1, 0, -.1));
					GameManager.getGame().playerProjectiles.add(new PlayerShot(Config.UGLY_PLACEHOLDER_PROJECTILE,
							x + 10, y, 0, -1, 0, -.1));
				}
				if (count % (int) (Config.PLAYER_UPS * Config.GAME_SPEED / 50) == 0) {
					GameManager.getGame().playerProjectiles.add(new PlayerShot(Config.PLACEHOLDER_PROJECTILE, x - 7, y,
							-.5, 3, -.005, -.1));
					GameManager.getGame().playerProjectiles.add(new PlayerShot(Config.PLACEHOLDER_PROJECTILE, x + 7, y,
							.5, 3, .005, -.1));
				}
				break;
			case 2:

				if (count % (int) (Config.PLAYER_UPS * Config.GAME_SPEED / 12) == 0) {
					GameManager.getGame().playerProjectiles.add(new PlayerShot(Config.UGLY_PLACEHOLDER_PROJECTILE,
							x - 10, y, 0, -4, 0, -.1));
					GameManager.getGame().playerProjectiles.add(new PlayerShot(Config.UGLY_PLACEHOLDER_PROJECTILE,
							x + 10, y, 0, -4, 0, -.1));
				}

				if (count % (int) (Config.PLAYER_UPS * Config.GAME_SPEED / 5) == 0) {
					GameManager.getGame().playerProjectiles.add(new HomingMissile(Config.PLACEHOLDER_PROJECTILE, x - 7,
							y, -.4, -1, 0, -.05));
					GameManager.getGame().playerProjectiles.add(new HomingMissile(Config.PLACEHOLDER_PROJECTILE, x + 7,
							y, .4, -1, 0, -.05));
				}
				if (count % ((int) ((Config.PLAYER_UPS * Config.GAME_SPEED) / 12)) == 0) {

					GameManager.getGame().playerProjectiles.add(new PlayerShot(Config.UGLY_PLACEHOLDER_PROJECTILE,
							x - 10, y, -1, -4, 0, -.1));
					GameManager.getGame().playerProjectiles.add(new PlayerShot(Config.UGLY_PLACEHOLDER_PROJECTILE,
							x + 10, y, 1, -4, 0, -.1));
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

	public void increasePower() {
		powerLevel++;
	}

	public void losePower() {
		powerLevel = 0; // TODO this is too harsh, like your mother
	}

	@Override
	public void run() {
		Thread.currentThread().setPriority((int)(Thread.MAX_PRIORITY*0.8));
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

	public Rectangle getHitBox() {
		return hitBox;
	}
}
