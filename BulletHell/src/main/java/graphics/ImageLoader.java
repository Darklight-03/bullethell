package graphics;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import util.Log;

public class ImageLoader {
	
	private final String IMG_DIR = "src/main/resources/";
	private String nextImage = "none";
	// permLoaded
	public static BufferedImage menuBackground, player, tully, basicPlayerShot, pBul0, pBul1, pBul2, pBul3;

	// StageOne
	public static BufferedImage smallTurretEnemy0Degrees, smallTurretEnemy45Degrees, smallTurretEnemy90Degrees,
			smallTurretEnemy135Degrees, smallTurretEnemy180Degrees, smallTurretEnemy225Degrees,
			smallTurretEnemy270Degrees, smallTurretEnemy315Degrees, Background1, bigPlane0, bigPlane1, bigPlane2,
			bigPlane3, roundLargeBullet, roundSmallBullet;

	public ImageLoader() {
		try {
			permLoaded();
			loadStageOne();
		}
		catch (Exception e) {
			Log.error("Failed to load the images (ImageLoader:CONSTRUCTOR)");
		}
	}

	public void permLoaded(){
		try{
			
			nextImage = "menuBackground";
			menuBackground = ImageIO.read(new File(IMG_DIR+"permLoaded/menuBackground.png"));
			
			nextImage = "tully";
			tully = ImageIO.read(new File(IMG_DIR+"permLoaded/tully.png"));
			
			nextImage = "player";
			player = ImageIO.read(new File("src/main/resources/players/placeHolder.png"));
			
			nextImage = "bullets";
			pBul0 = ImageIO.read(new File(IMG_DIR+"permLoaded/Bullets/Player/Bul0.png"));
			pBul1 = ImageIO.read(new File(IMG_DIR+"permLoaded/Bullets/Player/Bul1.png"));
			pBul2 = ImageIO.read(new File(IMG_DIR+"permLoaded/Bullets/Player/Bul2.png"));
			pBul3 = ImageIO.read(new File(IMG_DIR+"permLoaded/Bullets/Player/Bul3.png"));
			roundLargeBullet = ImageIO.read(new File(IMG_DIR+"permloaded/Bullets/Enemies/RoundBulletLarge.png"));
			roundSmallBullet = ImageIO.read(new File(IMG_DIR+"permloaded/Bullets/Enemies/RoundBulletSmall.png"));
		
		}catch(Exception e){
			Log.warn(nextImage+" failed to load in ImageLoader:permLoaded()");
		}
	}
	

	public void loadStageOne(){
		try{
			
			nextImage = "smallTurretEnemy";
			smallTurretEnemy0Degrees = ImageIO.read(new File(IMG_DIR + "stageOne/smallTurretEnemy/smallTurretEnemy0Degrees.png"));
			smallTurretEnemy45Degrees = ImageIO.read(new File(IMG_DIR + "stageOne/smallTurretEnemy/smallTurretEnemy45Degrees.png"));
			smallTurretEnemy90Degrees = ImageIO.read(new File(IMG_DIR + "stageOne/smallTurretEnemy/smallTurretEnemy90Degrees.png"));
			smallTurretEnemy135Degrees = ImageIO.read(new File(IMG_DIR + "stageOne/smallTurretEnemy/smallTurretEnemy135Degrees.png"));
			smallTurretEnemy180Degrees = ImageIO.read(new File(IMG_DIR + "stageOne/smallTurretEnemy/smallTurretEnemy180Degrees.png"));
			smallTurretEnemy225Degrees = ImageIO.read(new File(IMG_DIR + "stageOne/smallTurretEnemy/smallTurretEnemy225Degrees.png"));
			smallTurretEnemy270Degrees = ImageIO.read(new File(IMG_DIR + "stageOne/smallTurretEnemy/smallTurretEnemy270Degrees.png"));
			smallTurretEnemy315Degrees = ImageIO.read(new File(IMG_DIR + "stageOne/smallTurretEnemy/smallTurretEnemy315Degrees.png"));
			
			nextImage = "Background1";
			Background1 = ImageIO.read(new File(IMG_DIR + "stageOne/background32/Background.png"));
			
			nextImage = "bigPlane";
			bigPlane0 = ImageIO.read(new File(IMG_DIR + "stageOne/bigPlaneEnemy/bigPlane0000.png"));
			bigPlane1 = ImageIO.read(new File(IMG_DIR + "stageOne/bigPlaneEnemy/bigPlane0001.png"));
			bigPlane2 = ImageIO.read(new File(IMG_DIR + "stageOne/bigPlaneEnemy/bigPlane0002.png"));
			bigPlane3 = ImageIO.read(new File(IMG_DIR + "stageOne/bigPlaneEnemy/bigPlane0003.png"));
		
		}catch(Exception e){
			Log.warn(nextImage+" failed to load in ImageLoader:loadStageOne()");
		}
		
		
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
