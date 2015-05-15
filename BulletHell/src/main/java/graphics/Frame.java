package graphics;

import game.GameManager;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

import reference.Config;

/*
 * Frame for the project, not too much else to say here
 */
public class Frame extends JFrame {

	public Panel p = new Panel();
	private int posX, posY;

	public Frame() {
		super("Frame");
		setSize(Config.WIDTH, Config.HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(null);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, (dim.height / 2 - this.getSize().height / 2) - 25);

		add(p);

		initialiseGUI(this);
		setUndecorated(true);
		setVisible(true);
	}

	public Panel getPanel() {
		return p;
	}

	private void initialiseGUI(Component component) {
		if (GameManager.getGame().gameState == Config.PAUSED || GameManager.getGame().gameState == Config.MAIN_MENU) {
			component.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					posX = e.getX();
					posY = e.getY();
				}
			});

			component.addMouseMotionListener(new MouseAdapter() {
				public void mouseDragged(MouseEvent evt) {
					// sets frame position when mouse dragged
					Rectangle rectangle = getBounds();
					setBounds(evt.getXOnScreen() - posX, evt.getYOnScreen() - posY, rectangle.width, rectangle.height);
				}
			});

		}
	}
}
