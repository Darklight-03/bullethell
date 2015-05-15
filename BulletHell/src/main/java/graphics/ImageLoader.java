package graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {

	public static BufferedImage background;
	
	public ImageLoader(){
		try {
			background = ImageIO.read(new File("src/main/resources/MenuBackGround.png"));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
