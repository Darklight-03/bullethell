package graphics;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import main.GameManager;
import reference.Config;

/*
 * 
 */
public class Frame extends JFrame
{
	
	private Config c;
	
	public Frame(Config c){
		super("Frame");
		this.c = c;
		setSize(c.width, c.height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(null);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, (dim.height / 2 - this.getSize().height / 2) - 25);
		
		new GameManager(c);
		
		setUndecorated(true);
		setVisible(true);
	}
}
