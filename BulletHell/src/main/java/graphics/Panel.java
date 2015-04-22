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
	private int moveUp, moveDown, moveLeft, moveRight, moveSlow;
	private char shoot, dropBombs, switchWeapons, extraKeyOne;
	private boolean moveUpDepressed = false, moveDownDepressed = false, moveLeftDepressed = false,
			moveRightDepressed = false, shouldMoveSlow = false;
	public static boolean playerShoots = false;
	private BufferedImage buffer;

	/*
	 * This is the panel. It handles all of the painting and graphics of the
	 * game. It is also responsible for creating the GameManager object, and
	 * calling the thread withing said object. Nothing to do with updating the
	 * game, creating entities, etc., should be done in here.
	 */
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

	/*
	 * (non-Javadoc)
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	public void paint(Graphics g) {
		Graphics bg = buffer.getGraphics();
		bg.setColor(Color.WHITE);
		bg.fillRect(0, 0, getWidth(), getHeight());

		for (int i = 0; i < gM.getProjectiles().size(); i++) {
			bg.drawImage(gM.getProjectiles().get(i).getImage(), gM.getProjectiles().get(i).drawX(), gM.getProjectiles()
					.get(i).drawY(), null);

		}
		for (int i = 0; i < gM.getEntities().size(); i++) {
			bg.drawImage(gM.getEntities().get(i).getImage(), gM.getEntities().get(i).drawX(), gM.getEntities().get(i)
					.drawY(), null);
		}
		for (int i = 0; i < gM.getBackGroundObjects().size(); i++) {
			bg.drawImage(gM.getBackGroundObjects().get(i).getImage(), gM.getBackGroundObjects().get(i).drawX(), gM
					.getBackGroundObjects().get(i).drawY(), null);
		}

		bg.drawImage(gM.getPlayer().getImage(), gM.getPlayer().drawX(), gM.getPlayer().drawY(), null);
		if (shouldMoveSlow) {
			bg.setColor(Config.hitBoxColor);
			bg.fillRect(gM.getPlayer().getX() - 4, gM.getPlayer().getY() - 4, 8, 8);
		}
		g.drawImage(buffer, 0, 0, null);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(Config.TIME_BETWEEN_FRAMES);
				repaint();
				// TODO make is so that the player.move() method is not called
				// in the panel, or at least in the thread that handles
				// repaints, because the repaint speed currently affects the
				// speed of which the ship moves.
				gM.getPlayer().move(moveUpDepressed, moveDownDepressed, moveLeftDepressed, moveRightDepressed,
						shouldMoveSlow);
			}
			catch (InterruptedException e) {
				Log.error("Failed at repainting");
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 * 
	 * this method will handle the basic keys on the keyboard which only have to
	 * be pressed, and not depressed for extended periods of time.
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		switch (gM.gameState)
		{
		case Config.MAIN_MENU:
			break;
		case Config.PAUSED:
			break;
		case Config.PLAYING:
			char c = e.getKeyChar();

			if (c == dropBombs) {

			}
			if (c == switchWeapons) {

			}
			if (c == switchWeapons) {

			}
			break;
		case Config.DEAD:
			break;
		}

	}

	/*
	 * (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 * 
	 * Depending on what the gameState is, this will perform different actions
	 * for pressing different keys. Most of the time, it will just set public
	 * static booleans in the Panel class to true, to show that the key has been
	 * depressed
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		// System.out.println(e.getKeyCode());
		switch (gM.gameState)
		{
		case Config.MAIN_MENU:
			break;
		case Config.PAUSED:
			if (e.getKeyCode() == 27) {
				gM.gameState = Config.PLAYING;
			}
			break;
		case Config.PLAYING:
			if (e.getKeyCode() == 27) {
				pauseGame();
			}
			if (e.getKeyChar() == shoot) {
				playerShoots = true;
			}
			if (e.getKeyCode() == moveSlow) {
				shouldMoveSlow = true;
			}
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

	/*
	 * (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 * 
	 * Depending on what the gameState is, this will perform different actions
	 * for pressing different keys. Most of the time, it will just set public
	 * static booleans in the Panel class to false, to show that the key has
	 * been released.
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		switch (GameManager.gameState)
		{
		case Config.MAIN_MENU:
			break;
		case Config.PAUSED:
			break;
		case Config.PLAYING:
			if (e.getKeyChar() == shoot) {
				playerShoots = false;
			}
			if (e.getKeyCode() == moveSlow) {
				shouldMoveSlow = false;
			}
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

	/*
	 * This method is here so that the keyPressed() method won't be so
	 * cluttered. It is important to set all of the booleans corresponding to
	 * buttons being depressed to false, otherwise, the game will think that
	 * they are still depressed when you unpause it, and you have to click the
	 * key again to set the value to false
	 */
	public void pauseGame() {
		GameManager.gameState = Config.PAUSED;
		playerShoots = false;
		moveUpDepressed = false;
		moveDownDepressed = false;
		moveLeftDepressed = false;
		moveRightDepressed = false;
		shouldMoveSlow = false;
	}

	/*
	 * This is just here so that it isn't necessary to repeatedly type in
	 * "Config." before the name of the button. It makes the if statements in
	 * the keyListeners more managable and easier to read.
	 */
	public void setButtons() {
		moveUp = Config.moveUp;
		moveDown = Config.moveDown;
		moveLeft = Config.moveLeft;
		moveRight = Config.moveRight;
		moveSlow = Config.moveSlow;
		shoot = Config.shoot;
		dropBombs = Config.dropBombs;
		switchWeapons = Config.switchWeapons;
		extraKeyOne = Config.extraKeyOne;
	}

	public void addNotify() {
		super.addNotify();
		requestFocus();
	}

}
