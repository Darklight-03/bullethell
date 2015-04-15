package entities;

import reference.Config;

public class Player extends EntityBase {

	protected final String NAME = "Player";
	
	public Player(String imageName) {
		super(imageName);
	}
	
	
	public void move(String direction){
//		System.out.println(x+",  "+y);  TODO remove this when the movements are finallized
		switch(direction){
		case "up":
			y-=Config.moveSpeed;
			break;
		case "down":
			y+=Config.moveSpeed;
			break;
		case "left":
			x-=Config.moveSpeed;
			break;
		case "right":
			x+=Config.moveSpeed;
			break;
			
		}
	}

}
