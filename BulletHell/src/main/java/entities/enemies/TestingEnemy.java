package entities.enemies;

import entities.EntityBase;

public class TestingEnemy extends EntityBase{
	
	public TestingEnemy(String imageName, int x, int y){
		super(imageName);
		this.x = x;
		this.y = y;
	}
	
	public boolean update(){
		
		return false;
	}
	
}
