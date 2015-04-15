package entities;

public class BackgroundObject extends EntityBase
{
	public BackgroundObject(String imageName){
		super(imageName);
	}
	public void update(){
		x = x - Config.ScrollSpeed;
	}
}
