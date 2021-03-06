package graphics;

import entities.enemies.EnemyBase;
import game.Game;
import game.GameManager;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import main.menus.BasicMenu;
import main.menus.VerticalChoices;
import reference.Config;
import util.Log;

/*
 * 
 */
@SuppressWarnings("unused")
public class Panel extends JPanel implements KeyListener, Runnable {

	Thread game;
	GameManager gM;
	private float fade = .05f;
	private long count = 0;
	private int moveUp, moveDown, moveLeft, moveRight, moveSlow, bY =0;
	private char shoot, dropBombs, switchWeapons, extraKeyOne;
	private boolean moveUpDepressed = false, moveDownDepressed = false, moveLeftDepressed = false,
			moveRightDepressed = false, shouldMoveSlow = false;
	public static boolean playerShoots = false;
	private BufferedImage buffer, lastUsedImage;
	public BasicMenu menu = new VerticalChoices(5, Config.TITLESCREEN);

	/*
	 * This is the panel. It handles all of the painting and graphics of the
	 * game. It is also responsible for creating the GameManager object, and
	 * calling the thread withing said object. Nothing to do with updating the
	 * game, creating entities, etc., should be done in here.
	 */
	public Panel() {
		super();
		setSize(new Dimension(Config.WIDTH, Config.HEIGHT));
		buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_4BYTE_ABGR);

		setButtons();

		gM = new GameManager();
		Thread t = new Thread(this);
		t.start();

		addKeyListener(this);
		addNotify();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	public void paint(Graphics g) {

		Graphics2D bg = (Graphics2D) buffer.getGraphics();
		g = (Graphics2D) g;

		switch (GameManager.getGame().getGameState())
		{
		case Config.PLAYING:
			bg.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade));
			// TODO remove the white rectangle once there is a working
			// background
			bg.setColor(Color.WHITE);
			for(int y = -32;y<=Config.HEIGHT+64;y+=32){
				for(int x = -32; x<=Config.WIDTH+32 ; x+=32){
					bg.drawImage(Config.getBackground(),x,y+GameManager.getGame().getYPos(),null);
				}
			}
			
			for (int i = 0; i < GameManager.getGame().getBackGroundObjects().size(); i++) {
				GameManager.getGame().getBackGroundObjects().get(i).drawThis(bg);
			}
			for (int i = 0; i < GameManager.getGame().getEnemies().size(); i++) {
				EnemyBase e = GameManager.getGame().getEnemies().get(i);

				GameManager.getGame().getEnemies().get(i).drawThis(bg);
				if (Config.DEBUG_MODE) {
					GameManager.getGame().getEnemies().get(i).drawHitBox(bg);
					bg.setColor(Color.RED);
					bg.drawString("" + e.getHealth(), e.getX() - 20, e.getY() - 50);
				}

			}
			for (int i = 0; i < GameManager.getGame().getEnemyProjectiles().size(); i++) {

				GameManager.getGame().getEnemyProjectiles().get(i).drawThis(bg);

				if (Config.DEBUG_MODE){
					GameManager.getGame().getEnemyProjectiles().get(i).drawHitBox(bg);
					GameManager.getGame().getEnemyProjectiles().get(i).drawCount(bg);
				}

			}

			for (int i = 0; i < GameManager.getGame().getPlayerProjectiles().size(); i++) {

				GameManager.getGame().getPlayerProjectiles().get(i).drawThis(bg);

				if (Config.DEBUG_MODE) GameManager.getGame().getPlayerProjectiles().get(i).drawHitBox(bg);

			}

			GameManager.getGame().getPlayer().drawThis(bg);
			if (shouldMoveSlow || Config.DEBUG_MODE) GameManager.getGame().getPlayer().drawHitBox(bg);

			bg.setColor(Color.RED);
			bg.drawString("Lives: " + GameManager.getGame().getPlayer().getLives(), 5, 15);
			bg.drawString("Power Level: " + GameManager.getGame().getPlayer().getPower(), Config.WIDTH - 100, 15);
			bg.drawString("Time: " + GameManager.getGame().getCurrentStage().getCount(), (Config.WIDTH / 2) - 50, 15);

			if (fade + .015 <= 1) fade += .015;
			lastUsedImage = buffer;

			break;

		case Config.MAIN_MENU:
			bg.drawImage(lastUsedImage, 0, 0, null);
			menu.update(bg);
			break;

		case Config.PAUSED:
			bg.drawImage(lastUsedImage, 0, 0, null);
			menu.update(bg);
			break;

		case Config.DEAD:
			break;

		}

		g.drawImage(buffer, 0, 0, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		Thread.currentThread().setPriority((int) (Thread.MAX_PRIORITY * 0.8));
		while (true) {
			try {
				Thread.sleep(1000 / Config.FPS);
				count++;
				
				repaint();
			}
			catch (InterruptedException e) {
				repaint();
				Log.fatal("Panel:run() was interrupted");
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 * 
	 * this method will handle the basic keys on the keyboard which only have to
	 * be pressed, and not depressed for extended periods of time.
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		switch (GameManager.getGame().getGameState())
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
	 * 
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
		switch (GameManager.getGame().getGameState())
		{
		case Config.MAIN_MENU:
			if (e.getKeyCode() == 10) menu.enter();

			if (e.getKeyCode() == moveUp) menu.moveUp();

			if (e.getKeyCode() == moveDown) menu.moveDown();

			if (e.getKeyCode() == moveLeft) menu.moveLeft();

			if (e.getKeyCode() == moveRight) menu.moveRight();

			break;

		case Config.PAUSED:
			if (e.getKeyCode() == 27) {
				unpauseGame();
			}
			break;

		case Config.PLAYING:
			if (e.getKeyCode() == 27) {
				pauseGame();
				menu = new BasicMenu();
			}
			if (e.getKeyCode() == moveSlow) {
				GameManager.getGame().shouldMoveSlow(true);
				shouldMoveSlow = true;
			}
			if (e.getKeyChar() == shoot) playerShoots = true;

			if (e.getKeyCode() == moveUp) GameManager.getGame().moveUpDepressed(true);

			if (e.getKeyCode() == moveDown) GameManager.getGame().moveDownDepressed(true);

			if (e.getKeyCode() == moveLeft) GameManager.getGame().moveLeftDepressed(true);

			if (e.getKeyCode() == moveRight) GameManager.getGame().moveRightDepressed(true);

			break;

		case Config.DEAD:
			break;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 * 
	 * Depending on what the gameState is, this will perform different actions
	 * for pressing different keys. Most of the time, it will just set public
	 * static booleans in the Panel class to false, to show that the key has
	 * been released.
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		switch (GameManager.getGame().getGameState())
		{
		case Config.MAIN_MENU:
			break;

		case Config.PAUSED:
			break;

		case Config.PLAYING:
			if (e.getKeyCode() == moveSlow) {
				GameManager.getGame().shouldMoveSlow(false);
				shouldMoveSlow = false;
			}
			if (e.getKeyChar() == shoot) playerShoots = false;

			if (e.getKeyCode() == moveUp) GameManager.getGame().moveUpDepressed(false);

			if (e.getKeyCode() == moveDown) GameManager.getGame().moveDownDepressed(false);

			if (e.getKeyCode() == moveLeft) GameManager.getGame().moveLeftDepressed(false);

			if (e.getKeyCode() == moveRight) GameManager.getGame().moveRightDepressed(false);

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
		playerShoots = false;
		GameManager.getGame().moveUpDepressed(false);
		GameManager.getGame().moveDownDepressed(false);
		GameManager.getGame().moveLeftDepressed(false);
		GameManager.getGame().moveRightDepressed(false);
		GameManager.getGame().shouldMoveSlow(false);
		shouldMoveSlow = false;
		GameManager.getGame().gameState = Config.PAUSED;
	}

	public void unpauseGame() {
		playerShoots = false;
		GameManager.getGame().moveUpDepressed(false);
		GameManager.getGame().moveDownDepressed(false);
		GameManager.getGame().moveLeftDepressed(false);
		GameManager.getGame().moveRightDepressed(false);
		GameManager.getGame().shouldMoveSlow(false);
		shouldMoveSlow = false;
		GameManager.getGame().gameState = Config.PLAYING;
		fade = .05f;
	}

	/*
	 * This is just here so that it isn't necessary to repeatedly type in
	 * "Config." before the name of the button. It makes the if statements in
	 * the keyListeners more managable and easier to read.
	 */
	public void setButtons() {
		moveUp = Config.MOVEUP;
		moveDown = Config.MOVEDOWN;
		moveLeft = Config.MOVELEFT;
		moveRight = Config.MOVERIGHT;
		moveSlow = Config.MOVESLOW;
		shoot = Config.SHOOT;
		dropBombs = Config.DROPBOMBS;
		switchWeapons = Config.SWITCHWEAPONS;
		extraKeyOne = Config.EXTRAKEYONE;
	}

	public void addNotify() {
		super.addNotify();
		requestFocus();
	}

}
