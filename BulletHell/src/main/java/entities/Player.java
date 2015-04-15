package entities;

public class Player extends EntityBase {

	protected final String NAME = "Player";
	
	public Player(String imageName) {
		super(imageName);
	}
	
	
	public void move(String direction){
		switch(direction){
		case "up":
			break;
		case "down":
			break;
		case "left":
			break;
		case "right":
			break;
			
		}
	}

}
