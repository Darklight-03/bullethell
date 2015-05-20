package graphics;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import reference.Config;
import util.Log;

public class ImageLoader {
	
	private final String IMG_DIR = "src/main/resources/";

	// permLoaded
	public static BufferedImage menuBackground, player, tully, basicPlayerShot;

	// StageOne
	public static BufferedImage smallTurretEnemy0Degrees, smallTurretEnemy45Degrees, smallTurretEnemy90Degrees,
			smallTurretEnemy135Degrees, smallTurretEnemy180Degrees, smallTurretEnemy225Degrees,
			smallTurretEnemy270Degrees, smallTurretEnemy315Degrees;

	public ImageLoader() {
		try {
			permLoaded();
			loadStageOne();
		}
		catch (Exception e) {
			Log.error("Failed to load the images");
		}
	}

	public void permLoaded() throws Exception {
		menuBackground = ImageIO.read(new File(IMG_DIR+"permLoaded/MenuBackground.png"));
		tully = ImageIO.read(new File(IMG_DIR+"permLoaded/Tully.png"));
		player = ImageIO.read(new File("src/main/resources/placeHolder.png"));
	}
	

	public void loadStageOne() throws Exception{
		smallTurretEnemy0Degrees = ImageIO.read(new File(IMG_DIR + "stageOne/smallTurretEnemy/smallTurretEnemy0Degrees.png"));
		smallTurretEnemy45Degrees = ImageIO.read(new File(IMG_DIR + "stageOne/smallTurretEnemy/smallTurretEnemy45Degrees.png"));
		smallTurretEnemy90Degrees = ImageIO.read(new File(IMG_DIR + "stageOne/smallTurretEnemy/smallTurretEnemy90Degrees.png"));
		smallTurretEnemy135Degrees = ImageIO.read(new File(IMG_DIR + "stageOne/smallTurretEnemy/smallTurretEnemy135Degrees.png"));
		smallTurretEnemy180Degrees = ImageIO.read(new File(IMG_DIR + "stageOne/smallTurretEnemy/smallTurretEnemy180Degrees.png"));
		smallTurretEnemy225Degrees = ImageIO.read(new File(IMG_DIR + "stageOne/smallTurretEnemy/smallTurretEnemy225Degrees.png"));
		smallTurretEnemy270Degrees = ImageIO.read(new File(IMG_DIR + "stageOne/smallTurretEnemy/smallTurretEnemy270Degrees.png"));
		smallTurretEnemy315Degrees = ImageIO.read(new File(IMG_DIR + "stageOne/smallTurretEnemy/smallTurretEnemy315Degrees.png"));
		
		
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
