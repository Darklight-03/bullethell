package game;

public class GameManager {

	public static Game game = new Game();


	public static Game getGame() {
		return game;
	}
	public static void setGame(Game g) {
		game = g;
	}

}
