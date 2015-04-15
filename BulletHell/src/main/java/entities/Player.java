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
			if(isInBounds(x,y-Config.moveSpeed))
			y-=Config.moveSpeed;
			break;
		case "down":
			if(isInBounds(x,y+Config.moveSpeed))
			y+=Config.moveSpeed;
			break;
		case "left":
			if(isInBounds(x-Config.moveSpeed,y))
			x-=Config.moveSpeed;
			break;
		case "right":
			if(isInBounds(x+Config.moveSpeed,y))
			x+=Config.moveSpeed;
			break;
			
		}
	}

}
