package entities;

import reference.Config;

public class BackgroundObject extends EntityBase
{
	public BackgroundObject(String imageName){
		super(imageName);
	}
	public void update(){
		x = x - Config.scrollSpeed;
	}
}
