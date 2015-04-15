package graphics;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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

	public Panel() {
		super();
		setSize(new Dimension(Config.width, Config.height));

		gM = new GameManager();
		game = new Thread(gM);
		game.start();

		addKeyListener(this);
		addNotify();
	}

	public void paint(Graphics g) {
		switch (gM.GameState) {
			
		}
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(Config.TIME_BETWEEN_FRAMES);
				repaint();
			} catch (InterruptedException e) {
				Log.error("Failed at repainting");
			}
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// System.out.println(e.getKeyChar()+"   "+ e.getKeyCode());
		// TODO get this working with the arrow keys
		switch (e.getKeyChar()) {

		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println(e.getKeyCode());
		switch (e.getKeyCode()) {

		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	public void addNotify() {
		super.addNotify();
		requestFocus();
	}

}
