package main;

import graphics.Frame;
import graphics.ImageLoader;
import graphics.TextLoader;
import reference.Config;

//http://design.tutsplus.com/tutorials/how-to-create-a-gorgeous-glassy-text-effect--psd-3013
//http://design.tutsplus.com/tutorials/a-slick-supernatural-text-effect--psd-51

public class Main {
	/*
	 * Bullet Hell
	 * Created by Bo Corman and Andrew Kennedy
	 */

	public static Frame f;
	
	public static void main(String[] args) {
		new TextLoader();
		new ImageLoader();
		new Config();
		f = new Frame();
	}
}
