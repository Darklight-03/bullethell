package entities;

import reference.Config;

public class BackgroundObject extends EntityBase
{
	public BackgroundObject(String imageName){
		super(imageName);
		y = -100;
		x = Math.random()*Config.width;
	}
	public void update(){
		y = y + Config.scrollSpeed;
		if(y>Config.height+100){
			y = (Math.random()*-500)+100;
			x = Math.random()*Config.width;
		}
	}
}
