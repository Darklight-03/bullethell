package graphics;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import reference.Config;

/*
 * Frame for the project, not too much else to say here
 */
public class Frame extends JFrame {

	public Frame() {
		super("Frame");
		setSize(Config.width, Config.height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(null);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, (dim.height / 2 - this.getSize().height / 2) - 25);

		add(new Panel());

		setUndecorated(true);
		setVisible(true);
	}
}
