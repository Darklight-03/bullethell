package graphics;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import main.GameManager;
import reference.Config;

/*
 * 
 */
public class Panel extends JPanel implements KeyListener {

	Thread game;
	GameManager g;
	
	public Panel() {
		super();
		setSize(new Dimension(Config.width, Config.height));
		
		g = new GameManager();
		game = new Thread(g);
		game.start();
		
		addKeyListener(this);
		addNotify();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		System.out.println(e.getKeyChar()+"   "+ e.getKeyCode());
		//TODO get this working with the arrow keys
	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	public void addNotify() {
		super.addNotify();
		requestFocus();
	}

}
