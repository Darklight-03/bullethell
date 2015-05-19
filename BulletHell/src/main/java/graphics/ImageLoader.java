package graphics;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import util.Log;

public class ImageLoader {

	//permLoaded
	public static BufferedImage background, player, tully;
	
	//StageOne
	public static BufferedImage basicPlayerShot;

	public ImageLoader() {
		try {
			permLoaded();
		}
		catch (Exception e) {
			Log.error("Failed to load the images");
		}
	}

	public void permLoaded() throws Exception {
		background = ImageIO.read(new File("src/main/resources/MenuBackGround.png"));
		player = ImageIO.read(new File("src/main/resources/placeHolder.png"));
		tully = ImageIO.read(new File("src/main/resources/Tully.png"));
	}

	public void loadStageOne() {

	}

	public void unLoadStageOne() {

	}

	public void loadStageTwo() {

	}

	public void unLoadStageTwo() {

	}

	public void loadStageThree() {

	}

	public void unLoadStageThree() {

	}

	public void loadStageFour() {

	}

	public void unLoadStageFour() {

	}

	public void loadStageFive() {

	}

	public void unLoadStageFive() {

	}

	public void loadStageSix() {

	}

	public void unLoadStageSix() {

	}

}
