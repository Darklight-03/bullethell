package graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import main.GameManager;
import reference.Config;
import util.Log;

/*
 * 
 */
public class Panel extends JPanel implements KeyListener, Runnable {

	Thread game;
	GameManager gM;
	private int moveUp, moveDown, moveLeft, moveRight;
	private char dropBombs, switchWeapon, extraKeyOne, extraKeyTwo;
	private boolean moveUpDepressed = false, moveDownDepressed = false, moveLeftDepressed = false, moveRightDepressed = false;
	private BufferedImage buffer;

	public Panel() {
		super();
		setSize(new Dimension(Config.width, Config.height));
		buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_4BYTE_ABGR);

		setButtons();

		gM = new GameManager();
		game = new Thread(gM);
		game.start();
		Thread t = new Thread(this);
		t.start();

		addKeyListener(this);
		addNotify();
	}

	public void paint(Graphics g) {
		Graphics bg = buffer.getGraphics();
		bg.setColor(Color.WHITE);
		bg.fillRect(0, 0, getWidth(), getHeight());

		for (int i = 0; i < gM.getProjectiles().size(); i++) {
			bg.drawImage(gM.getProjectiles().get(i).getImage(), gM.getProjectiles().get(i).getX(), gM.getProjectiles().get(i).getY(), null);

		}
		for (int i = 0; i < gM.getEntities().size(); i++) {
			bg.drawImage(gM.getEntities().get(i).getImage(), gM.getEntities().get(i).getX(), gM.getEntities().get(i).getY(), null);
		}

		bg.drawImage(gM.getPlayer().getImage(), gM.getPlayer().drawX(), gM.getPlayer().drawY(), null);
		g.drawImage(buffer, 0, 0, null);
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(Config.TIME_BETWEEN_FRAMES);
				repaint();
				gM.getPlayer().move(moveUpDepressed, moveDownDepressed, moveLeftDepressed, moveRightDepressed);
			} catch (InterruptedException e) {
				Log.error("Failed at repainting");
			}
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		switch (gM.gameState) {
		case Config.MAIN_MENU:
			break;
		case Config.PAUSED:
			break;
		case Config.PLAYING:
			char c = e.getKeyChar();
			if (c == dropBombs) {

			}
			if (c == switchWeapon) {

			}
			if (c == extraKeyOne) {

			}
			if (c == extraKeyOne) {

			}
			break;
		case Config.DEAD:
			break;
		}

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// System.out.println(e.getKeyCode());
		switch (gM.gameState) {
		case Config.MAIN_MENU:
			break;
		case Config.PAUSED:
			break;
		case Config.PLAYING:
			if (e.getKeyCode() == moveUp) {
				moveUpDepressed = true;
			}
			if (e.getKeyCode() == moveDown) {
				moveDownDepressed = true;
			}
			if (e.getKeyCode() == moveLeft) {
				moveLeftDepressed = true;
			}
			if (e.getKeyCode() == moveRight) {
				moveRightDepressed = true;
			}
			break;
		case Config.DEAD:
			break;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (gM.gameState) {
		case Config.MAIN_MENU:
			break;
		case Config.PAUSED:
			break;
		case Config.PLAYING:
			if (e.getKeyCode() == moveUp) {
				moveUpDepressed = false;
			}
			if (e.getKeyCode() == moveDown) {
				moveDownDepressed = false;
			}
			if (e.getKeyCode() == moveLeft) {
				moveLeftDepressed = false;
			}
			if (e.getKeyCode() == moveRight) {
				moveRightDepressed = false;
			}
			break;
		case Config.DEAD:
			break;
		}

	}

	public void setButtons() {
		moveUp = Config.moveUp;
		moveDown = Config.moveDown;
		moveLeft = Config.moveLeft;
		moveRight = Config.moveRight;
		dropBombs = Config.dropBombs;
		switchWeapon = Config.switchWeapon;
		extraKeyOne = Config.extraKeyOne;
		extraKeyTwo = Config.extraKeyTwo;
	}

	public void addNotify() {
		super.addNotify();
		requestFocus();
	}

}
