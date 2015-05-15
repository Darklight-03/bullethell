package graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class TextLoader {

	public static BufferedImage titleScreen, TS_AboutSelected, TS_ExitSelected, TS_HighScoresSelected, TS_PlaySelected,
			TS_SettingsSelected;
	public static ArrayList<BufferedImage> MainMenuChoices = new ArrayList<BufferedImage>();

	/*
	 * This class should handle anything to do with loading text into the game
	 * in order to minimize clutter and conserve RAM by reducing the possibility
	 * of multiple classes loading the same objects
	 */
	public TextLoader() {
		try {
			TS_AboutSelected = ImageIO.read(new File("src/main/resources/Text/TS_AboutSelected.png"));
			TS_ExitSelected = ImageIO.read(new File("src/main/resources/Text/TS_ExitSelected.png"));
			TS_HighScoresSelected = ImageIO.read(new File("src/main/resources/Text/TS_HighScoresSelected.png"));
			TS_PlaySelected = ImageIO.read(new File("src/main/resources/Text/TS_PlaySelected.png"));
			TS_SettingsSelected = ImageIO.read(new File("src/main/resources/Text/TS_SettingsSelected.png"));

			addToArrayList();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Adding everything into seperate arraylists just to make drawing the
	 * different menu screens easier, not having to use ifs or switch loops
	 */
	private void addToArrayList() {
		MainMenuChoices.add(TS_PlaySelected);
		MainMenuChoices.add(TS_HighScoresSelected);
		MainMenuChoices.add(TS_SettingsSelected);
		MainMenuChoices.add(TS_AboutSelected);
		MainMenuChoices.add(TS_ExitSelected);

	}
}
