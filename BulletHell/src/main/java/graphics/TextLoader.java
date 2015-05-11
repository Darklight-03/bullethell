package graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TextLoader {

	public static BufferedImage titleScreen;

	public TextLoader() {
		try {
			titleScreen = ImageIO.read(new File("src/main/resources/Text/title Screen.png"));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
