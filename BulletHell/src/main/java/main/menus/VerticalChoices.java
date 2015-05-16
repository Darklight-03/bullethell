package main.menus;

import game.Game;
import game.GameManager;
import graphics.TextLoader;

import java.awt.Graphics2D;

import reference.Config;
import reference.ConfigLoader;

public class VerticalChoices extends BasicMenu {

	int selectedChoice = 0, numChoices, menuType;

	public VerticalChoices(int numChoices, int menuType) {
		this.numChoices = numChoices - 1;
		this.menuType = menuType;
	}

	public void update(Graphics2D g) {
		super.update(g);

		switch (menuType)
		{
		case Config.TITLESCREEN:
			g.drawImage(TextLoader.MainMenuChoices.get(selectedChoice), 0, 0, null);
			break;
		}
	}

	public void moveUp() {
		selectedChoice--;
		if (selectedChoice < 0) selectedChoice = numChoices;
	}

	public void moveDown() {
		selectedChoice++;
		if (selectedChoice > numChoices) selectedChoice = 0;
	}

	public void enter() {

		switch (menuType)
		{
		case Config.TITLESCREEN:

			switch (selectedChoice)
			{
			case 0:
				GameManager.setGame(new Game(true));
				break;
			case 1:
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				ConfigLoader.getConfig().save();
				System.exit(0);
				break;
			}

			break;

		case Config.PAUSESCREEN:
			break;
		}
	}
}
